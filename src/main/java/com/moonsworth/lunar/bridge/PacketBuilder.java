package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public interface PacketBuilder {
   @Nullable
   Class<?> method1();

   boolean method2(ResourceLocationBridge horsestats141, int number2);

   boolean method3(Class<?> clazz1);

   boolean method4(Class<?> clazz1);

   default void method5(Consumer<Object[]> consumer1, PacketBridge bridge3_212) {
   }

   default PacketBridge method6(Supplier<Object[]> supplier1, PacketBridge bridge3_212) {
      return null;
   }
}
