package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Preconditions;

final class Converter$IdentityConverter<T> extends MixinHelper242<T, T> implements Serializable {
   static final Converter$IdentityConverter<?> field3 = new Converter$IdentityConverter();
   private static final long field4 = 0L;

   private Converter$IdentityConverter() {
   }

   protected T doForward(T value1) {
      return (T)value1;
   }

   protected T doBackward(T value1) {
      return (T)value1;
   }

   public Converter$IdentityConverter<T> method1() {
      return this;
   }

   <S> MixinHelper242<T, S> method4(MixinHelper242<T, S> mixinhelper2421) {
      return Preconditions.checkNotNull(mixinhelper2421, "otherConverter");
   }

   @Override
   public String toString() {
      return "Converter.identity()";
   }

   private Object readResolve() {
      return field3;
   }
}
