package com.moonsworth.lunar.client.framework.feature.markers;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface SExtension_2<S, T> extends Markers_3<Markers3_2, S, T> {
   default boolean method1(@NotNull Markers3_2 var1) {
      return this.method4().equals(var1.value());
   }

   @Override
   default Optional<Markers3_2> method3(@NotNull S var1) {
      return Optional.of(new Markers3_2(this.method4(), this.method6((S)var1)));
   }

   default Optional<T> method3(@NotNull Markers3_2 var1) {
      return this.method7(var1.method1());
   }

   @NotNull
   String method4();

   @Override
   boolean method1(@NotNull S var1);

   @NotNull
   Markers2_3 method6(@NotNull S var1);

   Optional<T> method7(@NotNull Markers2_3 var1);
}
