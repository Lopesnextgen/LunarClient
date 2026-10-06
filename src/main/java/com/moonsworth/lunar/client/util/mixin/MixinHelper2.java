package com.moonsworth.lunar.client.util.mixin;

import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.Contract;

public interface MixinHelper2<T> extends MixinHelper<T> {
   @Contract("_->this")
   default <B> B method2(T... var1) {
      return this.method3(Arrays.asList((T[])var1));
   }

   @Contract("_->this")
   <B> B method3(Collection<T> var1);
}
