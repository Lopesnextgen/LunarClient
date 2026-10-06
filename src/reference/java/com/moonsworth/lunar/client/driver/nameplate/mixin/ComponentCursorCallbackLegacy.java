package com.moonsworth.lunar.client.driver.nameplate.mixin;

import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverComponentLegacy;

@FunctionalInterface
public interface ComponentCursorCallbackLegacy<T extends DriverComponentLegacy<?>> {
   void accept(T var1, MarkerModel.Data5 var2);
}
