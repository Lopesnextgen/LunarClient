package com.moonsworth.lunar.client.util.mixin;

import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.Contract;

public interface MixinHelper<T> {
   @Contract("_->this")
   default <B> B method1(T... var1) {
      return this.method2(Arrays.asList((T[])var1));
   }

   @Contract("_->this")
   <B> B method2(Collection<T> var1);
}
