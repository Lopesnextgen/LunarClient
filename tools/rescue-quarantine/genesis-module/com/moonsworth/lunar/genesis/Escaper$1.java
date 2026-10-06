package com.moonsworth.lunar.genesis;
import com.google.common.escape.Escaper;
import com.google.common.base.Function;

class Escaper$1 implements Function<String, String> {
   Escaper$1(Escaper mixinhelper3_71) {
      this.field1 = mixinhelper3_71;
   }

   public String apply(String text1) {
      return this.field1.escape(text1);
   }
}
