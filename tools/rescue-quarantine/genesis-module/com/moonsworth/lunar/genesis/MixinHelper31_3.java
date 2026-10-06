package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper31_3 {
   protected MixinHelper31_3() {
   }

   protected abstract Object delegate();

   @Override
   public String toString() {
      return this.delegate().toString();
   }
}
