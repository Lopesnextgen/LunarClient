package com.moonsworth.lunar.genesis;

import java.io.OutputStream;
import java.nio.charset.Charset;
import com.google.common.hash.Funnel;

@Annotation2
public final class MixinHelper3_6 {
   private MixinHelper3_6() {
   }

   public static Funnel<byte[]> method1() {
      return MixinHelper3$Type6.INSTANCE;
   }

   public static Funnel<CharSequence> method2() {
      return MixinHelper3$Type8.INSTANCE;
   }

   public static Funnel<CharSequence> method3(Charset var0) {
      return new MixinHelper3$Data38(var0);
   }

   public static Funnel<Integer> method4() {
      return MixinHelper3$Type5.INSTANCE;
   }

   public static <E> Funnel<Iterable<? extends E>> method5(Funnel<E> var0) {
      return new MixinHelper3$Data37<>(var0);
   }

   public static Funnel<Long> method6() {
      return MixinHelper3$Type7.INSTANCE;
   }

   public static OutputStream method7(MixinHelper4_3 var0) {
      return new MixinHelper3$Data39(var0);
   }
}
