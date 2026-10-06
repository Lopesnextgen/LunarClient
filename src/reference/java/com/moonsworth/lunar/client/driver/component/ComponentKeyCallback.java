package com.moonsworth.lunar.client.driver.component;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.driver.DriverComponent;

@FunctionalInterface
public interface ComponentKeyCallback<T extends DriverComponent<?>> {
   void accept(T value1, KeyCode bridgetype_82, int number3, int number4, int number5, int number6);
}
