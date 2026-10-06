package com.moonsworth.lunar.genesis;

import java.util.regex.Pattern;

final class Platform$JdkPatternCompiler implements PatternCompiler {
   private Platform$JdkPatternCompiler() {
   }

   public CommonPattern method1(String text1) {
      return new MixinHelper222(Pattern.compile(text1));
   }

   public boolean isPcreLike() {
      return true;
   }
}
