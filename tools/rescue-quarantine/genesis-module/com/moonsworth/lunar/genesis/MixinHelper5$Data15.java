package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Converter;

final class MixinHelper5$Data15 extends Converter<String, Long> implements Serializable {
   static final MixinHelper5$Data15 field3 = new MixinHelper5$Data15();
   private static final long field4 = 1L;

   private MixinHelper5$Data15() {
   }

   protected Long doForward(String var1) {
      return Long.decode(var1);
   }

   protected String doBackward(Long var1) {
      return var1.toString();
   }

   @Override
   public String toString() {
      return "Longs.stringConverter()";
   }

   private Object readResolve() {
      return field3;
   }
}
