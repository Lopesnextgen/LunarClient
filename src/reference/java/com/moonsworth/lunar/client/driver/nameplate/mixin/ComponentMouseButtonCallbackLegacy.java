package com.moonsworth.lunar.client.driver.nameplate.mixin;

import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverComponentLegacy;

@FunctionalInterface
public interface ComponentMouseButtonCallbackLegacy<T extends DriverComponentLegacy<?>> {
   void accept(T var1, int var2, int var3, int var4, MarkerModel.Data5 var5);
}
