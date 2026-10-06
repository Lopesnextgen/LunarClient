package com.moonsworth.lunar.client.render.turbo;

import java.util.List;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface FragDataFactory<T> {
   FragData createFragData(List<T> var1, @Nullable TransparencyLayerMap var2);
}
