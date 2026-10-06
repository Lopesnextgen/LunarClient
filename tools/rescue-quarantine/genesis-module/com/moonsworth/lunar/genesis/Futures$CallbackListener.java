package com.moonsworth.lunar.genesis;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.FutureCallback;

final class Futures$CallbackListener<V> implements Runnable {
   final Future<V> field1;
   final FutureCallback<? super V> field2;

   Futures$CallbackListener(Future<V> future1, FutureCallback<? super V> mixinhelper20_32) {
      this.field1 = future1;
      this.field2 = mixinhelper20_32;
   }

   @Override
   public void run() {
      if (this.field1 instanceof InternalFutureFailureAccess) {
         Throwable exception1 = InternalFutures.method1((InternalFutureFailureAccess)this.field1);
         if (exception1 != null) {
            this.field2.onFailure(exception1);
            return;
         }
      }

      Object obj5;
      try {
         obj5 = MixinHelper262.getDone(this.field1);
      } catch (ExecutionException executionexception3) {
         this.field2.onFailure(executionexception3.getCause());
         return;
      } catch (RuntimeException | Error exception4) {
         this.field2.onFailure(exception4);
         return;
      }

      this.field2.onSuccess(obj5);
   }

   @Override
   public String toString() {
      return MoreObjects.method1(this).method9(this.field2).toString();
   }
}
