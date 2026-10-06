package com.moonsworth.lunar.client.alert.mixin;

import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import org.jspecify.annotations.Nullable;

public class Alert2Handler<O, T> implements Alert2<O, T> {
   private final @Nullable Alert2<O, T> field1;
   private final BooleanSupplier field2;
   private final Optional<T> field3;

   public Alert2Handler(@Nullable Alert2<O, T> var1, BooleanSupplier var2, T var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = Optional.of((T)var3);
   }

   @Override
   public boolean method1(O var1, AlertType var2, @Nullable T var3) {
      return this.field1 != null && this.field1.method1((O)var1, var2, (T)var3);
   }

   @Override
   public @Nullable AlertType method2() {
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
   public void method4(O var1, @Nullable String var2) {
      if (this.field1 != null) {
         this.field1.method4((O)var1, var2);
      }
   }

   @Override
   public void method5(O var1) {
      if (this.field1 != null) {
         this.field1.method5((O)var1);
      }
   }

   @Override
   public void method6(Predicate<String> var1, T var2) {
      if (this.field1 != null) {
         this.field1.method6(var1, (T)var2);
      }
   }
}
