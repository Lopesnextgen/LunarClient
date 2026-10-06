package com.moonsworth.lunar.bridge;

public class Bridge2_15 {
   private final BridgeType2_8 field1;
   private final BridgeType2_8 field2;
   private final BridgeType2_8 field3;
   private final BridgeType2_8 field4;
   public static Bridge2_15 field5 = new Bridge2_15(BridgeType2_8.SRC_ALPHA, BridgeType2_8.ONE);
   public static Bridge2_15 field6 = new Bridge2_15(BridgeType2_8.SRC_COLOR, BridgeType2_8.ONE, BridgeType2_8.ZERO, BridgeType2_8.ONE);
   public static Bridge2_15 field7 = new Bridge2_15(BridgeType2_8.SRC_ALPHA, BridgeType2_8.ONE, BridgeType2_8.ONE, BridgeType2_8.ZERO);
   public static Bridge2_15 field8 = new Bridge2_15(
      BridgeType2_8.SRC_ALPHA, BridgeType2_8.ONE_MINUS_SRC_ALPHA, BridgeType2_8.ONE, BridgeType2_8.ONE_MINUS_SRC_ALPHA
   );
   public static Bridge2_15 field9 = new Bridge2_15(BridgeType2_8.ONE, BridgeType2_8.ONE);
   public static Bridge2_15 field10 = new Bridge2_15(BridgeType2_8.SRC_ALPHA, BridgeType2_8.ONE_MINUS_SRC_ALPHA, BridgeType2_8.ONE, BridgeType2_8.ZERO);
   public static Bridge2_15 field11 = new Bridge2_15(BridgeType2_8.SRC_ALPHA, BridgeType2_8.ONE_MINUS_SRC_ALPHA, BridgeType2_8.ZERO, BridgeType2_8.ONE);
   public static Bridge2_15 field12 = new Bridge2_15(BridgeType2_8.ZERO, BridgeType2_8.ONE_MINUS_SRC_COLOR);
   public static Bridge2_15 field13 = new Bridge2_15(BridgeType2_8.SRC_ALPHA, BridgeType2_8.ONE_MINUS_SRC_ALPHA);
   public static Bridge2_15 field14 = new Bridge2_15(BridgeType2_8.ONE, BridgeType2_8.ONE_MINUS_SRC_ALPHA);
   public static Bridge2_15 field15 = new Bridge2_15(
      BridgeType2_8.ONE_MINUS_DST_COLOR, BridgeType2_8.ONE_MINUS_SRC_COLOR, BridgeType2_8.ONE, BridgeType2_8.ZERO
   );

   public Bridge2_15(BridgeType2_8 var1, BridgeType2_8 var2) {
      this(var1, var2, var1, var2);
   }

   public Bridge2_15(BridgeType2_8 var1, BridgeType2_8 var2, BridgeType2_8 bridgeType2_8, BridgeType2_8 bridgeType2_82) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = bridgeType2_8;
      this.field4 = bridgeType2_82;
   }

   public BridgeType2_8 method1() {
      return this.field1;
   }

   public BridgeType2_8 method2() {
      return this.field2;
   }

   public BridgeType2_8 method3() {
      return this.field3;
   }

   public BridgeType2_8 method4() {
      return this.field4;
   }
}
