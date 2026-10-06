package com.moonsworth.lunar.client.alert.mixin;

import java.util.Optional;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public interface Alert2<O, T> {
   boolean method1(O var1, AlertType var2, @Nullable T var3);

   @Nullable
   AlertType method2();

   Optional<T> method3();

   void method4(O var1, @Nullable String var2);

   void method5(O var1);

   void method6(Predicate<String> var1, T var2);
}
