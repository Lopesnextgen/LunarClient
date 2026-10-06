package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class DisplayListBridge {
   private boolean valid = true;
   private final int field1;

   @Generated
   public boolean isValid() {
      return this.valid;
   }

   @Generated
   public int getGlId() {
      return this.field1;
   }

   @Generated
   public void setValid(boolean flag) {
      this.valid = flag;
   }

   @Generated
   public DisplayListBridge(int value) {
      this.field1 = value;
   }
}
