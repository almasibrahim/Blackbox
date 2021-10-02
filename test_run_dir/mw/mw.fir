;buildInfoPackage: chisel3, version: 3.4.2, scalaVersion: 2.12.12, sbtVersion: 1.3.10
circuit mw : 
  extmodule sram_32_128_freepdk45 : 
    input clk0 : UInt<1>
    input csb0 : UInt<1>
    input web0 : UInt<1>
    input addr0 : UInt<7>
    input din0 : UInt<32>
    output dout0 : UInt<32>
    
    defname = sram_32_128_freepdk45
    
    
  module mw : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip clk0 : UInt<1>, flip csb0 : UInt<1>, flip web0 : UInt<1>, flip addr0 : UInt<7>, flip din0 : UInt<32>, dout0 : UInt<32>}
    
    inst a of sram_32_128_freepdk45 @[mw.scala 30:19]
    a.dout0 is invalid
    a.din0 is invalid
    a.addr0 is invalid
    a.web0 is invalid
    a.csb0 is invalid
    a.clk0 is invalid
    a.clk0 <= io.clk0 @[mw.scala 34:15]
    a.csb0 <= io.csb0 @[mw.scala 35:14]
    a.web0 <= io.web0 @[mw.scala 36:14]
    a.addr0 <= io.addr0 @[mw.scala 37:15]
    a.din0 <= io.din0 @[mw.scala 38:14]
    io.dout0 <= a.dout0 @[mw.scala 39:14]
    
