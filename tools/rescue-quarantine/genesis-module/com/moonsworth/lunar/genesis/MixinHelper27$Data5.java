package com.moonsworth.lunar.genesis;
import com.google.common.collect.Interner;

class MixinHelper27$Data5<E> implements MixinHelper24_2<E, E> {
   private final Interner<E> field1;

   public MixinHelper27$Data5(Interner<E> var1) {
      this.field1 = var1;
   }

   @Override
   public E apply(E var1) {
      return this.field1.intern((E)var1);
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 instanceof MixinHelper27$Data5) {
         MixinHelper27$Data5 var2 = (MixinHelper27$Data5)var1;
         return this.field1.equals(var2.field1);
      } else {
         return false;
      }
   }
}
