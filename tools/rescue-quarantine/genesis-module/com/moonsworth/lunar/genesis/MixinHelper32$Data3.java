package com.moonsworth.lunar.genesis;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;

final class MixinHelper32$Data3<V> implements Runnable {
   @Nullable MixinHelper32_4<V> field1;

   MixinHelper32$Data3(MixinHelper32_4<V> var1) {
      this.field1 = var1;
   }

   @Override
   public void run() {
      MixinHelper32_4 var1 = this.field1;
      if (var1 != null) {
         ListenableFuture var2 = MixinHelper32_4.method2(var1);
         if (var2 != null) {
            this.field1 = null;
            if (var2.isDone()) {
               var1.method3(var2);
            } else {
               try {
                  ScheduledFuture var3 = MixinHelper32_4.method3(var1);
                  MixinHelper32_4.method4(var1, null);
                  String var4 = "Timed out";

                  try {
                     if (var3 != null) {
                        long var5 = Math.abs(var3.getDelay(TimeUnit.MILLISECONDS));
                        if (var5 > 10L) {
                           var4 = var4 + " (timeout delayed by " + var5 + " ms after scheduled time)";
                        }
                     }

                     var4 = var4 + ": " + var2;
                  } finally {
                     var1.setException(new MixinHelper32$Data2(var4));
                  }
               } finally {
                  var2.cancel(true);
               }
            }
         }
      }
   }
}
