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

class SRAMTests extends FreeSpec with ChiselScalatestTester {

  "SRAM" in {
    test(new SRAM()).withAnnotations(Seq(VerilatorBackendAnnotation)) { c =>
      c.io.csb0.poke(0.B)
      c.io.web0.poke(1.B)
      
      var count = 0
      while(count != 15){
          c.io.addr0.poke(count.U)
          c.clock.step(1)
          count += 1
      }
    

    //   c.io.addr0.poke(3.U)
    //   c.clock.step(1)
    //   c.io.addr0.poke(1.U)

      c.clock.step(100)
    }
  }
}
