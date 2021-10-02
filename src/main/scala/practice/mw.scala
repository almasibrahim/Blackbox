package practice

import chisel3._
import chisel3.util._
import chisel3.experimental._

class IOm extends Bundle{
    val clk0=Input(Bool())
    val csb0=Input(Bool())
    val web0=Input(Bool())
    val addr0=Input(UInt(7.W))
    val din0=Input(UInt(32.W))
    val dout0= Output(UInt(32.W))
}
class sram_32_128_freepdk45 extends BlackBox with HasBlackBoxResource{
    val io = IO(new IOm)
    addResource("/sram_32_128_freepdk45.v")
}

class mw extends Module{

    val io = IO(new Bundle{
    	val clk0=Input(Bool())
	val csb0=Input(Bool())
	val web0=Input(Bool())
	val addr0=Input(UInt(7.W))
	val din0=Input(UInt(32.W))
    	val dout0 = Output(UInt(32.W))
    })
    val a = Module(new sram_32_128_freepdk45)

   
    val clk = WireInit(clock.asUInt()(0))
    a.io.clk0 := ~clk0
    a.io.csb0:= io.csb0
    a.io.web0:=io.web0
    a.io.addr0:=io.addr0
    a.io.din0:=io.din0
    io.dout0 := a.io.dout0

}
