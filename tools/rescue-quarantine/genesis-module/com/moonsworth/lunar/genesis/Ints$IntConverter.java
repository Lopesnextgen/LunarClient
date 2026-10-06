package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Converter;

final class Ints$IntConverter extends Converter<String, Integer> implements Serializable {
   static final Ints$IntConverter field3 = new Ints$IntConverter();
   private static final long field4 = 1L;

   private Ints$IntConverter() {
   }

   protected Integer doForward(String text1) {
      return Integer.decode(text1);
   }

   protected String doBackward(Integer number1) {
      return number1.toString();
   }

   @Override
   public String toString() {
      return "Ints.stringConverter()";
   }

   private Object readResolve() {
      return field3;
   }
}
