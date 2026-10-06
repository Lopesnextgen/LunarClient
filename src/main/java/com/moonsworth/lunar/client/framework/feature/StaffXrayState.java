package com.moonsworth.lunar.client.framework.feature;

import lombok.Generated;

public class StaffXrayState {
   public static final StaffXrayState field1 = new StaffXrayState("Xray", 1);
   private final String field2;
   private final int field3;
   private boolean field4 = false;

   @Generated
   public StaffXrayState(String text, int value) {
      this.field2 = text;
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
   public void method2(boolean flag) {
      this.field4 = flag;
   }
}
