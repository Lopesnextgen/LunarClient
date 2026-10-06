package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.ListenableFuture;

final class AbstractFuture$SetFuture<V> implements Runnable {
   final MixinHelper292<V> field1;
   final ListenableFuture<? extends V> field2;

   AbstractFuture$SetFuture(MixinHelper292<V> mixinhelper2921, ListenableFuture<? extends V> futureextension2) {
      this.field1 = mixinhelper2921;
      this.field2 = futureextension2;
   }

   @Override
   public void run() {
      if (MixinHelper292.method9(this.field1) == this) {
         Object obj1 = MixinHelper292.method10(this.field2);
         if (MixinHelper292.method8().method5(this.field1, this, obj1)) {
            MixinHelper292.method11(this.field1);
         }
      }
   }
}
