package com.moonsworth.lunar.genesis;
import com.google.common.collect.Interner;
import com.google.common.base.Preconditions;

@Annotation2
@Annotation3
public final class MixinHelper27_2 {
   private MixinHelper27_2() {
   }

   public static MixinHelper27$Data6 method1() {
      return new MixinHelper27$Data6();
   }

   public static <E> Interner<E> method2() {
      return method1().method1().method4();
   }

   @Annotation3("java.lang.ref.WeakReference")
   public static <E> Interner<E> method3() {
      return method1().method2().method4();
   }

   public static <E> MixinHelper24_2<E, E> method4(Interner<E> var0) {
      return new MixinHelper27$Data5<>(Preconditions.checkNotNull(var0));
   }
}
