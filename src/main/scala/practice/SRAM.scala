package practice
import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.util.experimental._

class SRAM_IO extends Bundle{
    val csb0 = Input(Bool())
    val web0 = Input(Bool())
    val addr0 = Input(UInt(7.W))
    val din0 = Input(UInt(32.W))
    val dout0 = Output(UInt(32.W))
}

class SRAM extends Module{
    val io = IO(new SRAM_IO)

    val DATA_WIDTH = 32 
    val ADDR_WIDTH = 7 
    val RAM_DEPTH = 1 << ADDR_WIDTH;
    // FIXME: This delay is arbitrary.
    val DELAY = 3 
    val VERBOSE = 1  //Set to 0 to only display warnings
    val T_HOLD = 1  //Delay to hold dout value after posedge. Value is arbitrary
    val IFILE = "/home/talha/Blackbox/program" 


    val csb0_reg = RegInit(0.B)
    val web0_reg = RegInit(0.B)
    val addr0_reg = RegInit(0.U(ADDR_WIDTH.W))
    val din0_reg = RegInit(0.U(DATA_WIDTH.W))
    val dout0 = RegInit(0.U(DATA_WIDTH.W))

    val mem = Mem(32,UInt(32.W))
    loadMemoryFromFile(mem,"/home/talha/Blackbox/program.hex")

    val clk = WireInit(clock.asUInt()(0))
    val r = Mux(clk,1.B,0.B)
    val f = Mux(clk,0.B,1.B)

    io.dout0 := DontCare

    when(r){
        csb0_reg := io.csb0
        web0_reg := io.web0
        addr0_reg := io.addr0
        din0_reg := io.din0
        io.dout0 := DontCare
    }.elsewhen(f){
        when(~csb0_reg & ~web0_reg){
            mem.write(addr0_reg, din0_reg)
        }.elsewhen(~csb0_reg & web0_reg){
            io.dout0 := mem.read(addr0_reg)
        }.otherwise{io.dout0 := DontCare}
    }.otherwise{io.dout0 := DontCare}

}
