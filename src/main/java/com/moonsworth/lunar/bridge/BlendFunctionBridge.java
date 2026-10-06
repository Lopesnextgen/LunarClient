package com.moonsworth.lunar.bridge;

public class BlendFunctionBridge {
   private final BlendFactor field1;
   private final BlendFactor field2;
   private final BlendFactor field3;
   private final BlendFactor field4;
   public static BlendFunctionBridge field5 = new BlendFunctionBridge(BlendFactor.SRC_ALPHA, BlendFactor.ONE);
   public static BlendFunctionBridge field6 = new BlendFunctionBridge(BlendFactor.SRC_COLOR, BlendFactor.ONE, BlendFactor.ZERO, BlendFactor.ONE);
   public static BlendFunctionBridge field7 = new BlendFunctionBridge(BlendFactor.SRC_ALPHA, BlendFactor.ONE, BlendFactor.ONE, BlendFactor.ZERO);
   public static BlendFunctionBridge field8 = new BlendFunctionBridge(
      BlendFactor.SRC_ALPHA, BlendFactor.ONE_MINUS_SRC_ALPHA, BlendFactor.ONE, BlendFactor.ONE_MINUS_SRC_ALPHA
   );
   public static BlendFunctionBridge field9 = new BlendFunctionBridge(BlendFactor.ONE, BlendFactor.ONE);
   public static BlendFunctionBridge field10 = new BlendFunctionBridge(BlendFactor.SRC_ALPHA, BlendFactor.ONE_MINUS_SRC_ALPHA, BlendFactor.ONE, BlendFactor.ZERO);
   public static BlendFunctionBridge field11 = new BlendFunctionBridge(BlendFactor.SRC_ALPHA, BlendFactor.ONE_MINUS_SRC_ALPHA, BlendFactor.ZERO, BlendFactor.ONE);
   public static BlendFunctionBridge field12 = new BlendFunctionBridge(BlendFactor.ZERO, BlendFactor.ONE_MINUS_SRC_COLOR);
   public static BlendFunctionBridge field13 = new BlendFunctionBridge(BlendFactor.SRC_ALPHA, BlendFactor.ONE_MINUS_SRC_ALPHA);
   public static BlendFunctionBridge field14 = new BlendFunctionBridge(BlendFactor.ONE, BlendFactor.ONE_MINUS_SRC_ALPHA);
   public static BlendFunctionBridge field15 = new BlendFunctionBridge(
      BlendFactor.ONE_MINUS_DST_COLOR, BlendFactor.ONE_MINUS_SRC_COLOR, BlendFactor.ONE, BlendFactor.ZERO
   );

   public BlendFunctionBridge(BlendFactor bridgetype2_81, BlendFactor bridgetype2_82) {
      this(bridgetype2_81, bridgetype2_82, bridgetype2_81, bridgetype2_82);
   }

   public BlendFunctionBridge(BlendFactor bridgetype2_81, BlendFactor bridgetype2_82, BlendFactor bridgetype2_83, BlendFactor bridgetype2_84) {
      this.field1 = bridgetype2_81;
      this.field2 = bridgetype2_82;
      this.field3 = bridgetype2_83;
      this.field4 = bridgetype2_84;
   }

   public BlendFactor method1() {
      return this.field1;
   }

   public BlendFactor method2() {
      return this.field2;
   }

   public BlendFactor method3() {
      return this.field3;
   }

   public BlendFactor method4() {
      return this.field4;
   }
}
