package com.moonsworth.lunar.client.util.chest.mixin;

import lombok.Generated;

public class ChestHandler3 implements Chest {
   private final boolean expandZeroWidth;
   private float expansion = 0.0F;
   private float partialTicks = 0.0F;

   @Generated
   public boolean isExpandZeroWidth() {
      return this.expandZeroWidth;
   }

   @Generated
   public float getExpansion() {
      return this.expansion;
   }

   @Generated
   public float getPartialTicks() {
      return this.partialTicks;
   }

   @Generated
   public ChestHandler3(boolean var1) {
      this.expandZeroWidth = var1;
   }

   @Generated
   public ChestHandler3(boolean var1, float value, float value2) {
      this.expandZeroWidth = var1;
      this.expansion = value;
      this.partialTicks = value2;
   }
}
