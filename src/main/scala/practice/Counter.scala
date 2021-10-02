package practice

import chisel3._
import chisel3.util._
import chisel3.experimental._

class IOs extends Bundle{
    //val a = Input(UInt(4.W))
    //val b = Input(UInt(4.W))
    //val sum = Output(UInt(4.W))
    val clk=Input(Bool())
    val reset=Input(Bool())
    val counter= Output(UInt(4.W))
}

class counter extends BlackBox with HasBlackBoxResource{
    val io = IO(new IOs)
    addResource("/counter.v")
}

class Counter extends Module{

    val io = IO(new Bundle{
    	val output = Output(UInt(4.W))
    })
    val a = Module(new counter)

   
    val clk = WireInit(clock.asUInt()(0))
    a.io.clk := ~clk
    val rst= WireInit(reset.asUInt()(0))
    a.io.reset:= rst
    //a.io <> io
    io.output := a.io.counter

}
