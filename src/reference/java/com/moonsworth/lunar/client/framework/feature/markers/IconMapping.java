package com.moonsworth.lunar.client.framework.feature.markers;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface IconMapping<S, T> extends DynamicMapping<Markers3_2, S, T> {
   default boolean method1(@NotNull Markers3_2 markers3_21) {
      return this.method4().equals(markers3_21.value());
   }

   @Override
   default Optional<Markers3_2> method3(@NotNull S value1) {
      return Optional.of(new Markers3_2(this.method4(), this.method6((S)value1)));
   }

   default Optional<T> method3(@NotNull Markers3_2 markers3_21) {
      return this.method7(markers3_21.method1());
   }

   @NotNull
   String method4();

   @Override
   boolean method1(@NotNull S value1);

   @NotNull
   Markers2_3 method6(@NotNull S value1);

   Optional<T> method7(@NotNull Markers2_3 markers2_31);
}
