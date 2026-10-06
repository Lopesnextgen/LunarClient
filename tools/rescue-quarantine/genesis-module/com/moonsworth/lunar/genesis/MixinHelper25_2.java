package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class MixinHelper25_2 {
   private MixinHelper25_2() {
   }

   public static <F, T> SupplierExtension<T> method1(MixinHelper24_2<? super F, T> var0, SupplierExtension<F> var1) {
      return new MixinHelper25$Data6<>(var0, var1);
   }

   public static <T> SupplierExtension<T> method2(SupplierExtension<T> var0) {
      if (!(var0 instanceof MixinHelper25$Data5) && !(var0 instanceof MixinHelper25$Data3)) {
         return var0 instanceof Serializable ? new MixinHelper25$Data3<>(var0) : new MixinHelper25$Data5<>(var0);
      } else {
         return var0;
      }
   }

   public static <T> SupplierExtension<T> method3(SupplierExtension<T> var0, long var1, TimeUnit var3) {
      return new MixinHelper25$Data4<>(var0, var1, var3);
   }

   public static <T> SupplierExtension<T> method4(@Nullable T var0) {
      return new MixinHelper25$Data7<>((T)var0);
   }

   public static <T> SupplierExtension<T> method5(SupplierExtension<T> var0) {
      return new MixinHelper25$Data8<>(var0);
   }

   public static <T> MixinHelper24_2<SupplierExtension<T>, T> method6() {
      return MixinHelper25$Type4.INSTANCE;
   }
}
