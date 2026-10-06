package com.moonsworth.lunar.client.driver.nameplate.mixin;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.driver.DriverComponentLegacy;

@FunctionalInterface
public interface ComponentKeyCallbackLegacy<T extends DriverComponentLegacy<?>> {
   void accept(T var1, KeyCode var2, int var3, int var4, int var5, int var6);
}
