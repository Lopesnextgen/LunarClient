package com.moonsworth.lunar.client.config.override;

import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import org.jspecify.annotations.Nullable;

public class InterceptHandler<O, T> implements SettingIntercept<O, T> {
   private final @Nullable SettingIntercept<O, T> field1;
   private final BooleanSupplier field2;
   private final Optional<T> field3;

   public InterceptHandler(@Nullable SettingIntercept<O, T> alert21, BooleanSupplier booleansupplier2, T value3) {
      this.field1 = alert21;
      this.field2 = booleansupplier2;
      this.field3 = Optional.of((T)value3);
   }

   @Override
   public boolean method1(O value1, OverrideSource overrideSource, @Nullable T value3) {
      return this.field1 != null && this.field1.method1((O)value1, overrideSource, (T)value3);
   }

   @Override
   public @Nullable OverrideSource method2() {
      return this.field1 == null ? null : this.field1.method2();
   }

   @Override
   public Optional<T> method3() {
      if (this.field2.getAsBoolean()) {
         return this.field3;
      } else {
         return this.field1 == null ? Optional.empty() : this.field1.method3();
      }
   }

   @Override
   public void method4(O value1, @Nullable String text2) {
      if (this.field1 != null) {
         this.field1.method4((O)value1, text2);
      }
   }

   @Override
   public void method5(O value1) {
      if (this.field1 != null) {
         this.field1.method5((O)value1);
      }
   }

   @Override
   public void method6(Predicate<String> predicate1, T t) {
      if (this.field1 != null) {
         this.field1.method6(predicate1, (T)t);
      }
   }
}
