package com.moonsworth.lunar.client.driver.nameplate.mixin;

import com.moonsworth.lunar.client.driver.DriverComponentLegacy;
import java.nio.file.Path;
import java.util.List;

@FunctionalInterface
public interface ComponentDropCallbackLegacy<T extends DriverComponentLegacy<?>> {
   boolean accept(T var1, List<Path> var2);
}
