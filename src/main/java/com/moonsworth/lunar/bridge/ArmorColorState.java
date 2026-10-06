package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class ArmorColorState {
   private final boolean field1;
   private final int field2;
   private final String field3;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Generated
   public int getColor() {
      return this.field2;
   }

   @Generated
   public String method2() {
      return this.field3;
   }

   @Generated
   public ArmorColorState(boolean flag, int value, String text) {
      this.field1 = flag;
      this.field2 = value;
      this.field3 = text;
   }
}
