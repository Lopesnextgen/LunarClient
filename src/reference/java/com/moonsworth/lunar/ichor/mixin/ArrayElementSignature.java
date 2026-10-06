package com.moonsworth.lunar.ichor.mixin;

import lombok.Generated;

class ArrayElementSignature implements DescriptorType {
   protected ArrayTypeSignature field1;

   ArrayElementSignature() {
   }

   @Override
   public String getDescriptor() {
      return "[".repeat(this.field1.depth) + this.field1.field3.method2().getDescriptor();
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ArrayElementSignature mixinhelper62)) {
         return false;
      } else {
         if (!mixinhelper62.canEqual(this)) {
            return false;
         }

         ArrayTypeSignature mixinhelper333 = this.field1;
         ArrayTypeSignature mixinhelper334 = mixinhelper62.field1;
         return mixinhelper333 == null ? mixinhelper334 == null : mixinhelper333.equals(mixinhelper334);
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ArrayElementSignature;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      byte number2 = 1;
      ArrayTypeSignature mixinhelper333 = this.field1;
      return number2 * 59 + (mixinhelper333 == null ? 43 : mixinhelper333.hashCode());
   }
}
