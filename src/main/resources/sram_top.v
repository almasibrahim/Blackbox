module sram_top
(
  input clk_i,
  input rst_ni,
  
// sram interface
  input         csb0,
  input         web0,
  input  [6:0] addr0,
  input  [31:0] din0,
  output reg [31:0] dout0
  
);

//   logic        csb;
//   logic        we_o;
//   logic [11:0] addr_o;
//   logic [31:0] wdata_o;
//   logic [31:0] rdata_i;

//   logic        tl_req;
//   logic [31:0] tl_wmask;
//   logic        we_i;
//   logic        rvalid_o;
  
  reg        data_csbD;
  reg [6:0]  data_addrD;
  reg [3:0]  data_wmaskD;
  reg        data_weD;
  reg [31:0] data_wdataD;

//   assign data_wmaskD[0] = (tl_wmask[7:0]   != 8'b0) ? 1'b1: 1'b0;
//   assign data_wmaskD[1] = (tl_wmask[15:8]  != 8'b0) ? 1'b1: 1'b0;
//   assign data_wmaskD[2] = (tl_wmask[23:16] != 8'b0) ? 1'b1: 1'b0;
//   assign data_wmaskD[3] = (tl_wmask[31:24] != 8'b0) ? 1'b1: 1'b0; 
  
  assign data_weD    = web0;
  assign data_csbD   = csb0;
  
  always @(negedge clk_i or posedge rst_ni) begin
    if(rst_ni) begin
      data_addrD   =  '0;
      data_csbD    =  '1;
      data_weD     =  '0;
      data_wdataD  =  '0;
    end else begin
      data_addrD   =  addr0;
      data_csbD    =  csb0;
      data_weD     =  web0;
      dout0        =  data_wdataD;
    end
  
  end
  
  sram_32_128_freepdk45 data_mem (
    .clk0      (clk_i),
    .csb0      (data_csbD),
    .web0      (data_weD), 
    .addr0     (data_addrD),
    .din0      (din0),
    .dout0     (data_wdataD)
  );


//   always_ff @(posedge clk_i or negedge rst_ni) begin
//     if (!rst_ni) begin
//       rvalid_o <= 1'b0;
//     end else if (we_i) begin
//       rvalid_o <= 1'b0;
//     end else begin 
//       rvalid_o <= tl_req;
//     end
//   end

endmodule
