package com.moonsworth.lunar.bridge.horsestats;

import java.util.Optional;
import java.util.function.Consumer;

public interface Horsestats_3<T extends Horsestats_3<T, S>, S extends T> {
   default Horsestats$Type method1() {
      return Horsestats$Type.MISS;
   }

   T method2();

   default Optional<S> method3() {
      return this.method1() == Horsestats$Type.MISS ? Optional.empty() : Optional.of((S)this);
   }

   default boolean isSuccessful() {
      return this.method1() != Horsestats$Type.MISS;
   }

   default boolean method4() {
      return this.method1() == Horsestats$Type.MISS;
   }

   default void method5(Consumer<S> var1) {
      if (this.method1() != Horsestats$Type.MISS) {
         var1.accept(this);
      }
   }

   default void method6(Consumer<S> var1, Runnable var2) {
      if (this.method1() != Horsestats$Type.MISS) {
         var1.accept(this);
      } else {
         var2.run();
      }
   }
}
