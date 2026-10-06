package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import com.google.common.base.Preconditions;

@Annotation3
public final class MixinHelper7_11 {
   private MixinHelper7_11() {
   }

   public static <K, V> MixinHelper12_6<K, V> method1(final MixinHelper12_6<K, V> var0, final Executor var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new MixinHelper12_6<K, V>() {
         @Override
         public void onRemoval(final AbstractMapImpl<K, V> var1x) {
            var1.execute(new Runnable() {
               @Override
               public void run() {
                  var0.onRemoval(var1x);
               }
            });
         }
      };
   }
}
