package com.moonsworth.lunar.bridge;

public class MixinTargetVersionBridge {
   private final int field1;
   private final Bridge6_6[] field2;

   public MixinTargetVersionBridge(int value, Bridge6_6[] items2) {
      this.field1 = value;
      this.field2 = items2;
   }

   public int version() {
      return this.field1;
   }

   public Bridge6_6[] method1() {
      return this.field2;
   }
}
