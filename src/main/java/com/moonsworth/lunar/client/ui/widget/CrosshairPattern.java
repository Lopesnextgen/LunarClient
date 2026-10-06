package com.moonsworth.lunar.client.ui.widget;

class CrosshairPattern {
   private final int field1;
   private final boolean field2;
   private final boolean field3;

   private CrosshairPattern(int value, boolean flag, boolean flag2) {
      this.field1 = value;
      this.field2 = flag;
      this.field3 = flag2;
   }

   public int index() {
      return this.field1;
   }

   public boolean method1() {
      return this.field2;
   }

   public boolean method2() {
      return this.field3;
   }
}
