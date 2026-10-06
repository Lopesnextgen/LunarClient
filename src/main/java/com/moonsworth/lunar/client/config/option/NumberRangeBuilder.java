package com.moonsworth.lunar.client.config.option;

import org.jetbrains.annotations.Contract;

public interface NumberRangeBuilder<B extends NumberRangeBuilder<B, T>, T extends Number & Comparable<T>> {
   @Contract("_,_->this")
   B method1(boolean flag1, boolean flag2);

   @Contract("_,_->this")
   B method2(T value1, T value2);
}
