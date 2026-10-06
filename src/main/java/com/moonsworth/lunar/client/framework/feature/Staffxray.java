package com.moonsworth.lunar.client.framework.feature;

import lombok.Generated;

public class Staffxray {
   public static final Staffxray field1 = new Staffxray("Xray", 1);
   private final String field2;
   private final int field3;
   private boolean field4 = false;

   @Generated
   public Staffxray(String var1, int value) {
      this.field2 = var1;
      this.field3 = value;
   }

   @Generated
   public String getName() {
      return this.field2;
   }

   @Generated
   public int getOrdinal() {
      return this.field3;
   }

   @Generated
   public boolean method1() {
      return this.field4;
   }

   @Generated
   public void method2(boolean var1) {
      this.field4 = var1;
   }
}
