package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class Wrapper$Data3 {
   private final int first;
   private final int field1;
   private final ResourceLocationBridge field2;

   public Wrapper$Data3(int value, int value2, ResourceLocationBridge resourceLocationBridge) {
      this.first = value;
      this.field1 = value2;
      this.field2 = resourceLocationBridge;
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
