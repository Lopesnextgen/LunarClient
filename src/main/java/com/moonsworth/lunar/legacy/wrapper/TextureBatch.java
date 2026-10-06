package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class TextureBatch {
   private final int first;
   private final int field1;
   private final ResourceLocationBridge field2;

   public TextureBatch(int value, int value2, ResourceLocationBridge horsestats143) {
      this.first = value;
      this.field1 = value2;
      this.field2 = horsestats143;
   }

   public int method1() {
      return this.first;
   }

   public int count() {
      return this.field1;
   }

   public ResourceLocationBridge method2() {
      return this.field2;
   }
}
