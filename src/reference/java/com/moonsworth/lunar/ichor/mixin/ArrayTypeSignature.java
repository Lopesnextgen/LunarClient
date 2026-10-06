package com.moonsworth.lunar.ichor.mixin;

import java.util.Objects;

public class ArrayTypeSignature extends MixinHelper3 {
   public MixinHelper3 field3;
   public int depth = 1;

   public ArrayTypeSignature() {
      super(new ArrayElementSignature());
      ((ArrayElementSignature)this.field2).field1 = this;
   }

   @Override
   public void method1(MixinHelper3 mixinhelper31) {
      this.field3.method1(mixinhelper31);
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else {
         return !(object instanceof ArrayTypeSignature mixinhelper332)
            ? false
            : this.depth == mixinhelper332.depth && this.field3 != this && mixinhelper332.field3 != mixinhelper332 && Objects.equals(this.field3, mixinhelper332.field3);
      }
   }
}
