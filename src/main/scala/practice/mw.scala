package practice

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.util.experimental._

class IOm extends Bundle {
  val clk0 = Input(Bool())
  val csb0 = Input(Bool())
  val web0 = Input(Bool())
  val addr0 = Input(UInt(7.W))
  val din0 = Input(UInt(32.W))
  val dout0 = Output(UInt(32.W))
}
class sram_32_128_freepdk45 extends BlackBox with HasBlackBoxResource {
  val io = IO(new IOm)
  addResource("/sram_32_128_freepdk45.v")
}

class mw extends Module {

  val io = IO(new Bundle {
    //val clk0 = Input(Clock())
    val csb0 = Input(Bool())
    val web0 = Input(Bool())
    val addr0 = Input(UInt(7.W))
    val din0 = Input(UInt(32.W))
    val dout0 = Output(UInt(32.W))
    val r = Output(Bool())
    val f = Output(Bool())
  })
  val a = Module(new sram_32_128_freepdk45)

  val clk = WireInit(clock.asUInt()(0))

  // def risingedge(x: Bool) = !fallingedge(clk)
  // def fallingedge(x: Bool) = !x && RegNext(x)
  val r = Mux(clk,1.B,0.B)
  val f = Mux(clk,0.B,1.B)

  io.dout0 := DontCare

  when(r){
    a.io.clk0 := clk
    a.io.csb0 := io.csb0
    a.io.web0 := io.web0
    a.io.addr0 := io.addr0
    a.io.din0 := io.din0
    io.dout0 := DontCare
  }.elsewhen(f){
    a.io.clk0 := clk
    a.io.csb0 := DontCare
    a.io.web0 := DontCare
    a.io.addr0 := DontCare
    a.io.din0 := DontCare
    io.dout0 := a.io.dout0
  }


  // a.io.clk0 := ~clk
  // a.io.csb0 := io.csb0
  // a.io.web0 := io.web0
  // a.io.addr0 := io.addr0
  // a.io.din0 := io.din0
  // io.dout0 := a.io.dout0

  io.r := r
  io.f := f
}
