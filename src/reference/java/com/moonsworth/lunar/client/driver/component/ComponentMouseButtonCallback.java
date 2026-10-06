package com.moonsworth.lunar.client.driver.component;

import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverComponent;

@FunctionalInterface
public interface ComponentMouseButtonCallback<T extends DriverComponent<?>> {
   void accept(T value1, int number2, int number3, int number4, MarkerModel.Data5 data55);
}
