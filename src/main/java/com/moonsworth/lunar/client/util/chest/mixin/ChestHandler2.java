package com.moonsworth.lunar.client.util.chest.mixin;

import lombok.Generated;

public class ChestHandler2 implements Chest {
   private final boolean retryAxisSwaps;

   @Generated
   public boolean isRetryAxisSwaps() {
      return this.retryAxisSwaps;
   }

   @Generated
   public ChestHandler2(boolean flag) {
      this.retryAxisSwaps = flag;
   }
}
