package com.moonsworth.lunar.client.driver.component;

import com.moonsworth.lunar.client.driver.DriverComponent;

@FunctionalInterface
public interface ComponentCharCallback<T extends DriverComponent<?>> {
   void accept(T value1, int number2);
}
