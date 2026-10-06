package com.moonsworth.lunar.client.util.collection;

import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.Contract;

public interface ListBuilder<T> extends ElementAdder<T> {
   @Contract("_->this")
   default <B> B method2(T... items1) {
      return this.method3(Arrays.asList((T[])items1));
   }

   @Contract("_->this")
   <B> B method3(Collection<T> list1);
}
