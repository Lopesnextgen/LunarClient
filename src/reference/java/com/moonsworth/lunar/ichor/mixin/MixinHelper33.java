package com.moonsworth.lunar.ichor.mixin;

import java.util.Objects;

public class MixinHelper33 extends MixinHelper3 {
   public MixinHelper3 field3;
   public int depth = 1;

   public MixinHelper33() {
      super(new MixinHelper6());
      ((MixinHelper6)this.field2).field1 = this;
   }

   @Override
   public void method1(MixinHelper3 var1) {
      this.field3.method1(var1);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return !(var1 instanceof MixinHelper33 var2)
            ? false
            : this.depth == var2.depth && this.field3 != this && var2.field3 != var2 && Objects.equals(this.field3, var2.field3);
      }
   }
}
