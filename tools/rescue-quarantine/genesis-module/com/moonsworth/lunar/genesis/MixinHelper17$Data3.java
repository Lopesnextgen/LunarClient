package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Converter;

final class MixinHelper17$Data3<T extends Enum<T>> extends Converter<String, T> implements Serializable {
   private final Class<T> field3;
   private static final long field4 = 0L;

   MixinHelper17$Data3(Class<T> var1) {
      this.field3 = Preconditions.checkNotNull(var1);
   }

   protected T doForward(String var1) {
      return Enum.valueOf(this.field3, var1);
   }

   protected String doBackward(T var1) {
      return var1.name();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper17$Data3) {
         MixinHelper17$Data3 var2 = (MixinHelper17$Data3)var1;
         return this.field3.equals(var2.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode();
   }

   @Override
   public String toString() {
      return "Enums.stringConverter(" + this.field3.getName() + ".class)";
   }
}
