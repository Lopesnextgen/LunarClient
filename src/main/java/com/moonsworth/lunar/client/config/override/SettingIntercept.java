package com.moonsworth.lunar.client.config.override;

import java.util.Optional;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public interface SettingIntercept<O, T> {
   boolean method1(O value1, OverrideSource alerttype2, @Nullable T value3);

   @Nullable
   OverrideSource method2();

   Optional<T> method3();

   void method4(O value1, @Nullable String text2);

   void method5(O value1);

   void method6(Predicate<String> predicate1, T value2);
}
