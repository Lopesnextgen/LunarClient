package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true)
public abstract class MixinHelper8_4<K, V> {
   protected MixinHelper8_4() {
   }

   public abstract V load(K var1);

   @Annotation3
   public ListenableFuture<V> method1(K var1, V var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return MixinHelper262.method1(this.load((K)var1));
   }

   public Map<K, V> loadAll(Iterable<? extends K> var1) {
      throw new MixinHelper8$Data26();
   }

   public static <K, V> MixinHelper8_4<K, V> method2(MixinHelper24_2<K, V> var0) {
      return new MixinHelper8$Data24<>(var0);
   }

   public static <V> MixinHelper8_4<Object, V> method3(SupplierExtension<V> var0) {
      return new MixinHelper8$Data25<>(var0);
   }

   @Annotation3
   public static <K, V> MixinHelper8_4<K, V> method4(final MixinHelper8_4<K, V> var0, final Executor var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new MixinHelper8_4<K, V>() {
         @Override
         public V load(K var1x) {
            return (V)var0.load(var1x);
         }

         @Override
         public ListenableFuture<V> method1(final K var1x, final V var2) {
            FutureTask var3 = FutureTask.method1(new Callable<V>() {
               @Override
               public V call() {
                  return (V)var0.method1(var1x, var2).get();
               }
            });
            var1.execute(var3);
            return var3;
         }

         @Override
         public Map<K, V> loadAll(Iterable<? extends K> var1x) {
            return var0.loadAll(var1x);
         }
      };
   }
}
