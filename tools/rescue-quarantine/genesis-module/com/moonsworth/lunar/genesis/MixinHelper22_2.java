package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class MixinHelper22_2 {
   public abstract MixinHelper21_2 method1(CharSequence var1);

   public abstract String pattern();

   public abstract int flags();

   @Override
   public abstract String toString();

   public static MixinHelper22_2 method2(String var0) {
      return MixinHelper14.method3(var0);
   }

   public static boolean isPcreLike() {
      return MixinHelper14.patternCompilerIsPcreLike();
   }
}
