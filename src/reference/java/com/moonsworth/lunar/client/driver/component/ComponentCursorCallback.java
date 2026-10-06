package com.moonsworth.lunar.client.driver.component;

import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverComponent;

@FunctionalInterface
public interface ComponentCursorCallback<T extends DriverComponent<?>> {
   void accept(T value1, MarkerModel.Data5 data52);
}
