package com.moonsworth.lunar.legacy.wrapper;

public class GlStateRecorder {
   public int field1 = 7425;
   public boolean field2 = false;
   public boolean field3 = false;
   public int field4 = 519;
   public float field5 = 0.0F;
   public boolean field6 = false;
   public int field7 = 513;
   public boolean[] field8 = new boolean[8];
   public int field9 = 0;
   public boolean field10 = false;
   public boolean field11 = true;
   public boolean field12 = true;
   public boolean field13 = true;
   public boolean field14 = true;
   public boolean field15 = true;
   public float lineWidth = 1.0F;
   public boolean field16 = false;
   public int field17 = 1;
   public int field18 = 1;
   public int field19 = 0;
   public int field20 = 0;
   public boolean lighting = false;
   public boolean field21 = false;

   public void method1() {
      if (this.field9 < this.field8.length) {
         this.field8[this.field9] = true;
      }
   }

   public void method2(boolean flag) {
      if (this.field9 < this.field8.length) {
         this.field8[this.field9] = flag;
      }
   }

   public boolean method3() {
      return this.field9 >= this.field8.length ? false : this.field8[this.field9];
   }
}
