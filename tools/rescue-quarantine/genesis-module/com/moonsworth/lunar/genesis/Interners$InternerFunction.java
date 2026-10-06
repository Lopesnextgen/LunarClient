package com.moonsworth.lunar.genesis;
import com.google.common.collect.Interner;
import com.google.common.base.Function;

class Interners$InternerFunction<E> implements Function<E, E> {
   private final Interner<E> field1;

   public Interners$InternerFunction(Interner<E> mixinhelper20_21) {
      this.field1 = mixinhelper20_21;
   }

   @Override
   public E apply(E value1) {
      return (E)this.field1.intern(value1);
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public boolean equals(Object obj1) {
      if (obj1 instanceof Interners$InternerFunction) {
         Interners$InternerFunction mixinhelper27$data52 = (Interners$InternerFunction)obj1;
         return this.field1.equals(mixinhelper27$data52.field1);
      } else {
         return false;
      }
   }
}
