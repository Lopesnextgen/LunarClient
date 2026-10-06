package com.moonsworth.lunar.client.framework.feature.markers;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface DynamicMapping<K, S, T> {
   boolean method1(@NotNull S value1);

   boolean method2(@NotNull K value1);

   Optional<K> method3(@NotNull S value1);

   Optional<T> method4(@NotNull K value1);
}
