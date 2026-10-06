package com.moonsworth.lunar.client.config.option;

import org.jetbrains.annotations.Contract;

public interface SteppedNumberRangeConfigurator<B extends SteppedNumberRangeConfigurator<B, T>, T extends Number & Comparable<T>> extends NumberRangeConfigurator<B, T> {
   @Contract("_->this")
   B method1(int var1);
}
