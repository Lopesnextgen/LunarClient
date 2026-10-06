package com.moonsworth.lunar.client.config.option;

import org.jetbrains.annotations.Contract;

public interface NumberRangeConfigurator<B extends NumberRangeConfigurator<B, T>, T extends Number & Comparable<T>> {
   @Contract("_,_->this")
   B method1(boolean var1, boolean var2);

   @Contract("_,_->this")
   B method2(T var1, T var2);
}
