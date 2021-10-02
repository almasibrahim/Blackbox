package practice

import chisel3._
import chisel3.util._
import chisel3.experimental._

class IO extends Bundle{
    val a = Input(UInt(4.W))
    val b = Input(UInt(4.W))
    val sum = Output(UInt(4.W))
    // clock    input(bool)
    // reset    == = = = = 
}

class adder extends BlackBox with HasBlackBoxResource{
    val io = IO(new IO)
    addResource("/adder.v")
}

class Adder extends Module{

    val io = IO(new IO)

    // val io = IO(new Bundle{
    //     a
    //     B
    //     sum
    // })
    // val clk = WireInit(clock.asUInt()(0))
    // a.io.clock := ~clk

    val a = Module(new adder)

    a.io <> io

}