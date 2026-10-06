package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Converter;

final class Doubles$DoubleConverter extends Converter<String, Double> implements Serializable {
   static final Doubles$DoubleConverter field3 = new Doubles$DoubleConverter();
   private static final long field4 = 1L;

   private Doubles$DoubleConverter() {
   }

   protected Double doForward(String text1) {
      return Double.valueOf(text1);
   }

   protected String doBackward(Double value1) {
      return value1.toString();
   }

   @Override
   public String toString() {
      return "Doubles.stringConverter()";
   }

   private Object readResolve() {
      return field3;
   }
}
