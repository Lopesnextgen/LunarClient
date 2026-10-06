package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Converter;

final class Shorts$ShortConverter extends Converter<String, Short> implements Serializable {
   static final Shorts$ShortConverter field3 = new Shorts$ShortConverter();
   private static final long field4 = 1L;

   private Shorts$ShortConverter() {
   }

   protected Short doForward(String text1) {
      return Short.decode(text1);
   }

   protected String doBackward(Short number1) {
      return number1.toString();
   }

   @Override
   public String toString() {
      return "Shorts.stringConverter()";
   }

   private Object readResolve() {
      return field3;
   }
}
