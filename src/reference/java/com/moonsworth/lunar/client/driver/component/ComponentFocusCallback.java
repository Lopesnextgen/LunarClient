package com.moonsworth.lunar.client.driver.component;

import com.moonsworth.lunar.client.driver.DriverComponent;

@FunctionalInterface
public interface ComponentFocusCallback<T extends DriverComponent<?>> {
   void accept(T value1, boolean flag2);
}
