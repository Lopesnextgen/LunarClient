package com.moonsworth.lunar.genesis;

import java.util.concurrent.Future;
import com.google.common.base.Preconditions;

public abstract class MixinHelper312$Data5<V> extends MixinHelper312_2<V> {
   private final Future<V> field1;

   protected MixinHelper312$Data5(Future<V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   protected final Future<V> delegate() {
      return this.field1;
   }
}
