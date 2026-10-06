package com.moonsworth.lunar.genesis;

import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class MixinHelper6_13 {
   private MixinHelper6_13() {
   }

   public static MixinHelper24_2<Object, String> method1() {
      return MixinHelper6$Type.INSTANCE;
   }

   public static <E> MixinHelper24_2<E, E> method2() {
      return MixinHelper6$Type2.INSTANCE;
   }

   public static <K, V> MixinHelper24_2<K, V> method3(Map<K, V> var0) {
      return new MixinHelper6$Data23<>(var0);
   }

   public static <K, V> MixinHelper24_2<K, V> method4(Map<K, ? extends V> var0, @Nullable V var1) {
      return new MixinHelper6$Data20<>(var0, (V)var1);
   }

   public static <A, B, C> MixinHelper24_2<A, C> method5(MixinHelper24_2<B, C> var0, MixinHelper24_2<A, ? extends B> var1) {
      return new MixinHelper6$Data22<>(var0, var1);
   }

   public static <T> MixinHelper24_2<T, Boolean> method6(PredicateExtension<T> var0) {
      return new MixinHelper6$Data24<>(var0);
   }

   public static <E> MixinHelper24_2<Object, E> method7(@Nullable E var0) {
      return new MixinHelper6$Data21<>((E)var0);
   }

   public static <T> MixinHelper24_2<Object, T> method8(SupplierExtension<T> var0) {
      return new MixinHelper6$Data25<>(var0);
   }
}
