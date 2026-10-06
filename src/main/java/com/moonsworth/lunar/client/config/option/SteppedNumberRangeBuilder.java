package com.moonsworth.lunar.client.config.option;

import org.jetbrains.annotations.Contract;

public interface SteppedNumberRangeBuilder<B extends SteppedNumberRangeBuilder<B, T>, T extends Number & Comparable<T>> extends NumberRangeBuilder<B, T> {
   @Contract("_->this")
   B method1(int number1);
}
