package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;

final class Enums$StringConverter<T extends Enum<T>> extends Converter<String, T> implements Serializable {
   private final Class<T> field3;
   private static final long field4 = 0L;

   Enums$StringConverter(Class<T> clazz1) {
      this.field3 = (Class<T>)Preconditions.checkNotNull(clazz1);
   }

   protected T doForward(String text1) {
      return Enum.valueOf(this.field3, text1);
   }

   protected String doBackward(T value1) {
      return value1.name();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Enums$StringConverter) {
         Enums$StringConverter mixinhelper17$data32 = (Enums$StringConverter)obj1;
         return this.field3.equals(mixinhelper17$data32.field3);
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
