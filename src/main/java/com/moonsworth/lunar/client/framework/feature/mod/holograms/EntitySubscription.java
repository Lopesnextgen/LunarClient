package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MovementInputMarker;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface EntitySubscription<T extends MovementInputMarker> extends Iterable<T> {
   boolean method4(MovementInputMarker bridgeextension1);

   boolean isEmpty();

   Stream<T> stream();

   default Optional<T> method2() {
      return this.stream().findFirst();
   }

   EntitySubscription<T> method3(Consumer<T> consumer1);

   EntitySubscription<T> method4(Consumer<T> consumer1);

   EntitySubscription<T> method5(Consumer<T> consumer1);
}
