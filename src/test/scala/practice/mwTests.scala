package practice

import chisel3._
import chisel3.util._
import org.scalatest._
import chisel3.experimental._
import chiseltest._
import chisel3.experimental.BundleLiterals._
import chiseltest.experimental.TestOptionBuilder._
import chiseltest.internal.VerilatorBackendAnnotation
// import org.scalatest.flatspec.AnyFlatSpec

class mwTests extends FreeSpec with ChiselScalatestTester {

  "mw" in {
    test(new mw()).withAnnotations(Seq(VerilatorBackendAnnotation)) { c =>
      // c.io.rst_ni.poke(0.B) 
      // c.clock.step(1)
      // c.io.rst_ni.poke(1.B)
      // c.clock.step(1)
      // c.io.rst_ni.poke(0.B)
      c.io.csb0.poke(0.B)
      c.io.web0.poke(1.B)
      c.io.addr0.poke(3.U)
      // c.clock.step(1)
      //c.io.din0.poke(2.U)
      c.clock.step(1)
      c.io.addr0.poke(4.U)
      c.clock.step(1)
      c.io.addr0.poke(5.U)
      // c.clock.step(1)
      // c.io.din0.poke(4095.U)
      // c.io.clk.poke(clock.asUInt()(0).asClock())
      // c.io.rst_n.poke(0.B)
      // c.io.data_in.poke(8.U)
      // c.io.start.poke(1.B)

      c.clock.step(20)
    }
  }
}
