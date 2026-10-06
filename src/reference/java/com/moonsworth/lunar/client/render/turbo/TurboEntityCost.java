package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;

class TurboEntityCost {
   private final Horsestats20Extension field1;
   private final float field2;

   private TurboEntityCost(Horsestats20Extension var1, float var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else {
         return var1 instanceof TurboEntityCost var2 ? var2.field1.equals(this.field1) : false;
      }
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   public Horsestats20Extension method1() {
      return this.field1;
   }

   public float method2() {
      return this.field2;
   }
}
