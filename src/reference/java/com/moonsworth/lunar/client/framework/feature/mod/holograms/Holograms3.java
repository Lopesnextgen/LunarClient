package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface Holograms3<T extends BridgeExtension> extends Iterable<T> {
   boolean method4(BridgeExtension var1);

   boolean isEmpty();

   Stream<T> stream();

   default Optional<T> method2() {
      return this.stream().findFirst();
   }

   Holograms3<T> method3(Consumer<T> var1);

   Holograms3<T> method4(Consumer<T> var1);

   Holograms3<T> method5(Consumer<T> var1);
}
