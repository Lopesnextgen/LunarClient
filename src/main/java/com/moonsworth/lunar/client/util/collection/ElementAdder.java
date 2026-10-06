package com.moonsworth.lunar.client.util.collection;

import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.Contract;

public interface ElementAdder<T> {
   @Contract("_->this")
   default <B> B method1(T... items1) {
      return this.method2(Arrays.asList((T[])items1));
   }

   @Contract("_->this")
   <B> B method2(Collection<T> list1);
}
