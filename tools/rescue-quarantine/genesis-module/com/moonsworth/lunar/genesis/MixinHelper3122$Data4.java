package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

public abstract class MixinHelper3122$Data4<V> extends MixinHelper3122_3<V> {
   private final ListenableFuture<V> field1;

   protected MixinHelper3122$Data4(ListenableFuture<V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   protected final ListenableFuture<V> method1() {
      return this.field1;
   }
}
