package com.moonsworth.lunar.client.util.chest.mixin;

import lombok.Generated;

public class ChestHandler implements Chest {
   private final boolean checkParts;

   @Generated
   public boolean isCheckParts() {
      return this.checkParts;
   }

   @Generated
   public ChestHandler(boolean flag) {
      this.checkParts = flag;
   }
}
