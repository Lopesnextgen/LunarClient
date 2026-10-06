package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;

public class GiftRecord {
   private final BridgeExtension field1;
   private final BridgeExtension field2;
   private final String field3;
   private final BridgeExtension field4;
   private final String field5;
   private final boolean field6;
   private final boolean field7;

   public GiftRecord(BridgeExtension bridge, BridgeExtension bridge2, String text, BridgeExtension bridge3, String text2, boolean flag, boolean flag2) {
      this.field1 = bridge;
      this.field2 = bridge2;
      this.field3 = text;
      this.field4 = bridge3;
      this.field5 = text2;
      this.field6 = flag;
      this.field7 = flag2;
   }

   public boolean method1() {
      return !this.field6 && !this.field7;
   }

   public BridgeExtension method2() {
      return this.field1;
   }

   public BridgeExtension method3() {
      return this.field2;
   }

   public String method4() {
      return this.field3;
   }

   public BridgeExtension method5() {
      return this.field4;
   }

   public String method6() {
      return this.field5;
   }

   public boolean method7() {
      return this.field6;
   }

   public boolean method8() {
      return this.field7;
   }
}
