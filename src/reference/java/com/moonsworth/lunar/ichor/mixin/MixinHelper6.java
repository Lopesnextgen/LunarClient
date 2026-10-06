package com.moonsworth.lunar.ichor.mixin;

import lombok.Generated;

class MixinHelper6 implements MixinHelper {
   protected MixinHelper33 field1;

   @Override
   public String getDescriptor() {
      return "[".repeat(this.field1.depth) + this.field1.field3.method2().getDescriptor();
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof MixinHelper6 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         MixinHelper33 var3 = this.field1;
         MixinHelper33 var4 = var2.field1;
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof MixinHelper6;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      byte var2 = 1;
      MixinHelper33 var3 = this.field1;
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }
}
