package com.moonsworth.lunar.client.driver.nameplate.mixin;

import com.moonsworth.lunar.client.driver.DriverComponentLegacy;

@FunctionalInterface
public interface ComponentScrollCallbackLegacy<T extends DriverComponentLegacy<?>> {
   void accept(T var1, double var2, double var4);
}
