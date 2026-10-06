package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public interface MixinHelper_19 {
   @Nullable
   Class<?> method1();

   boolean method2(ResourceLocationBridge var1, int var2);

   boolean method3(Class<?> var1);

   boolean method4(Class<?> var1);

   default void method5(Consumer<Object[]> var1, Bridge3_21 var2) {
   }

   default Bridge3_21 method6(Supplier<Object[]> var1, Bridge3_21 var2) {
      return null;
   }
}
