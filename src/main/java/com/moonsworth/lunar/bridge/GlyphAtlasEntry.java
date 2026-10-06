package com.moonsworth.lunar.bridge;

public class GlyphAtlasEntry {
   private final float field1;
   private final float field2;
   private final boolean field3;
   private final int field4;

   public GlyphAtlasEntry(float value, float value2, boolean flag, int value3) {
      this.field1 = value;
      this.field2 = value2;
      this.field3 = flag;
      this.field4 = value3;
   }

   public float method1() {
      return this.field1;
   }

   public float method2() {
      return this.field2;
   }

   public boolean method3() {
      return this.field3;
   }

   public int index() {
      return this.field4;
   }
}
