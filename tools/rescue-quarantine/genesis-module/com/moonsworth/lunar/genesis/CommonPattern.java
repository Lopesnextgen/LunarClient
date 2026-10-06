package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class CommonPattern {
   CommonPattern() {
   }

   public abstract CommonMatcher method1(CharSequence text1);

   public abstract String pattern();

   public abstract int flags();

   @Override
   public abstract String toString();

   public static CommonPattern method2(String text0) {
      return MixinHelper14.method3(text0);
   }

   public static boolean isPcreLike() {
      return MixinHelper14.patternCompilerIsPcreLike();
   }
}
