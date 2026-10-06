package com.moonsworth.lunar.client.alert.mixin;

import java.util.Optional;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Alert<T> {
   private AlertType field1;
   @Nullable
   private T field2;
   @Nullable
   private Predicate<String> field3;
   @Nullable
   private T field4 = (T)null;

   public Optional<T> method1() {
      return Optional.ofNullable(this.field2);
   }

   public void method2(AlertType var1, T var2) {
      this.field1 = var1;
      this.field2 = (T)var2;
   }

   public void method3(@Nullable Predicate<String> var1, T var2) {
      if (var1 == null) {
         this.field3 = null;
         this.field4 = (T)var2;
      } else {
         this.field3 = this.field3 == null ? var1 : this.field3.or(var1);
         this.field4 = (T)var2;
      }
   }

   public boolean test(@Nullable String var1) {
      return this.field3 != null && this.field3.test(var1);
   }

   public boolean method4() {
      this.field2 = null;
      this.field1 = null;
      return this.field3 == null;
   }

   @Generated
   public AlertType method5() {
      return this.field1;
   }

   @Nullable
   @Generated
   public T method6() {
      return this.field4;
   }
}
