module mw(
  input         clock,
  input         reset,
  input         io_clk0,
  input         io_csb0,
  input         io_web0,
  input  [6:0]  io_addr0,
  input  [31:0] io_din0,
  output [31:0] io_dout0
);
  wire  a_clk0; // @[mw.scala 30:19]
  wire  a_csb0; // @[mw.scala 30:19]
  wire  a_web0; // @[mw.scala 30:19]
  wire [6:0] a_addr0; // @[mw.scala 30:19]
  wire [31:0] a_din0; // @[mw.scala 30:19]
  wire [31:0] a_dout0; // @[mw.scala 30:19]
  sram_32_128_freepdk45 a ( // @[mw.scala 30:19]
    .clk0(a_clk0),
    .csb0(a_csb0),
    .web0(a_web0),
    .addr0(a_addr0),
    .din0(a_din0),
    .dout0(a_dout0)
  );
  assign io_dout0 = a_dout0; // @[mw.scala 39:14]
  assign a_clk0 = io_clk0; // @[mw.scala 34:15]
  assign a_csb0 = io_csb0; // @[mw.scala 35:14]
  assign a_web0 = io_web0; // @[mw.scala 36:14]
  assign a_addr0 = io_addr0; // @[mw.scala 37:15]
  assign a_din0 = io_din0; // @[mw.scala 38:14]
endmodule
