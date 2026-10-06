package com.moonsworth.lunar.client.driver.component;

import com.moonsworth.lunar.client.driver.DriverComponent;

@FunctionalInterface
public interface ComponentScrollCallback<T extends DriverComponent<?>> {
   void accept(T value1, double value2, double value4);
}
