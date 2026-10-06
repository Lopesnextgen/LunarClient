package com.moonsworth.lunar.genesis;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;

@Annotation3
final class MixinHelper32_4<V> extends MixinHelper29222.Data<V> {
   private @Nullable ListenableFuture<V> field8;
   private @Nullable ScheduledFuture<?> timer;

   static <V> ListenableFuture<V> method1(ListenableFuture<V> var0, long var1, TimeUnit var3, ScheduledExecutorService var4) {
      MixinHelper32_4 var5 = new MixinHelper32_4(var0);
      MixinHelper32$Data3 var6 = new MixinHelper32$Data3(var5);
      var5.timer = var4.schedule(var6, var1, var3);
      var0.addListener(var6, MoreExecutors.directExecutor());
      return var5;
   }

   private MixinHelper32_4(ListenableFuture<V> var1) {
      this.field8 = Preconditions.checkNotNull(var1);
   }

   @Override
   protected String pendingToString() {
      ListenableFuture var1 = this.field8;
      ScheduledFuture var2 = this.timer;
      if (var1 != null) {
         String var3 = "inputFuture=[" + var1 + "]";
         if (var2 != null) {
            long var4 = var2.getDelay(TimeUnit.MILLISECONDS);
            if (var4 > 0L) {
               var3 = var3 + ", remaining delay=[" + var4 + " ms]";
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   @Override
   protected void afterDone() {
      this.method3(this.field8);
      ScheduledFuture var1 = this.timer;
      if (var1 != null) {
         var1.cancel(false);
      }

      this.field8 = null;
      this.timer = null;
   }
}
