package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Converter;

final class Longs$LongConverter extends Converter<String, Long> implements Serializable {
   static final Longs$LongConverter field3 = new Longs$LongConverter();
   private static final long field4 = 1L;

   private Longs$LongConverter() {
   }

   protected Long doForward(String text1) {
      return Long.decode(text1);
   }

   protected String doBackward(Long number1) {
      return number1.toString();
   }

   @Override
   public String toString() {
      return "Longs.stringConverter()";
   }

   private Object readResolve() {
      return field3;
   }
}
