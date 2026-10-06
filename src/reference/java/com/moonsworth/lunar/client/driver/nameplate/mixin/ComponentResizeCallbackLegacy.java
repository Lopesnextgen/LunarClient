package com.moonsworth.lunar.client.driver.nameplate.mixin;

import com.moonsworth.lunar.client.driver.DriverComponentLegacy;

@FunctionalInterface
public interface ComponentResizeCallbackLegacy<T extends DriverComponentLegacy<?>> {
   void accept(T var1, int var2, int var3);
}
