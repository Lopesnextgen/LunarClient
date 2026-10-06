package com.moonsworth.lunar.client.config.override;

import java.util.Optional;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class SettingOverride<T> {
   private OverrideSource field1;
   @Nullable
   private T field2;
   @Nullable
   private Predicate<String> field3;
   @Nullable
   private T field4 = (T)null;

   public SettingOverride() {
   }

   public Optional<T> method1() {
      return Optional.ofNullable(this.field2);
   }

   public void method2(OverrideSource overrideSource, T value2) {
      this.field1 = overrideSource;
      this.field2 = (T)value2;
   }

   public void method3(@Nullable Predicate<String> predicate1, T value2) {
      if (predicate1 == null) {
         this.field3 = null;
         this.field4 = (T)value2;
      } else {
         this.field3 = this.field3 == null ? predicate1 : this.field3.or(predicate1);
         this.field4 = (T)value2;
      }
   }

   public boolean test(@Nullable String text1) {
      return this.field3 != null && this.field3.test(text1);
   }

   public boolean method4() {
      this.field2 = null;
      this.field1 = null;
      return this.field3 == null;
   }

   @Generated
   public OverrideSource method5() {
      return this.field1;
   }

   @Nullable
   @Generated
   public T method6() {
      return this.field4;
   }
}
