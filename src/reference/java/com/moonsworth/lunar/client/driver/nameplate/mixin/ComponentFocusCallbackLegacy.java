package com.moonsworth.lunar.client.driver.nameplate.mixin;

import com.moonsworth.lunar.client.driver.DriverComponentLegacy;

@FunctionalInterface
public interface ComponentFocusCallbackLegacy<T extends DriverComponentLegacy<?>> {
   void accept(T var1, boolean var2);
}
