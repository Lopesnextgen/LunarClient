package com.moonsworth.lunar.genesis;
import com.google.common.escape.Escaper;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class MixinHelper2_4 {
   private static final Escaper field1 = MixinHelper4_4.method2()
      .method3('"', "&quot;")
      .method3('\'', "&#39;")
      .method3('&', "&amp;")
      .method3('<', "&lt;")
      .method3('>', "&gt;")
      .method4();

   public static Escaper method1() {
      return field1;
   }

   private MixinHelper2_4() {
   }
}
