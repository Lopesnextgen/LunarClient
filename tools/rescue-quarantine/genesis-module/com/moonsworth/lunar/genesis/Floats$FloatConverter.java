package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Converter;

final class Floats$FloatConverter extends Converter<String, Float> implements Serializable {
   static final Floats$FloatConverter field3 = new Floats$FloatConverter();
   private static final long field4 = 1L;

   private Floats$FloatConverter() {
   }

   protected Float doForward(String text1) {
      return Float.valueOf(text1);
   }

   protected String doBackward(Float value1) {
      return value1.toString();
   }

   @Override
   public String toString() {
      return "Floats.stringConverter()";
   }

   private Object readResolve() {
      return field3;
   }
}
