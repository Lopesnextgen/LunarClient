package com.moonsworth.lunar.client.util;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public enum ThreadModuleDumpType5 {
   ANY,
   TRUE,
   FALSE;

   public boolean isConditional() {
      return this == ANY;
   }

   public boolean isTrue() {
      return this == TRUE;
   }

   public boolean isFalse() {
      return this == FALSE;
   }

   public boolean isConditionalOrTrue(BooleanSupplier var1) {
      return this.isConditional() ? var1.getAsBoolean() : this.isTrue();
   }

   public boolean isConditionalOrFalse(BooleanSupplier var1) {
      return this.isConditional() ? var1.getAsBoolean() : this.isFalse();
   }

   public boolean isConditionalOrElse(BooleanSupplier var1, boolean flag) {
      return this.isConditional() ? var1.getAsBoolean() : flag;
   }

   public static ThreadModuleDumpType5 orDefault(@Nullable ThreadModuleDumpType5 var0) {
      return var0 == null ? ANY : var0;
   }

   public abstract static class Data<T> extends ThreadModuleDumpType5.Data3 {
      public abstract ThreadModuleDumpType5 method1(T var1);
   }

   public static class Data2<T> extends ThreadModuleDumpType5.Data<T> {
      @Nullable
      private final Function<T, Boolean> field1;

      @Override
      public ThreadModuleDumpType5 method1(T var1) {
         return this.field1 == null ? ThreadModuleDumpType5.ANY : (this.field1.apply((T)var1) ? ThreadModuleDumpType5.TRUE : ThreadModuleDumpType5.FALSE);
      }

      @Override
      public ThreadModuleDumpType5 method1() {
         return ThreadModuleDumpType5.ANY;
      }

      @Generated
      public Data2(@Nullable Function<T, Boolean> var1) {
         this.field1 = var1;
      }
   }

   public abstract static class Data3 {
      public abstract ThreadModuleDumpType5 method1();

      public static ThreadModuleDumpType5.Data3 method2(@Nullable ThreadModuleDumpType5.Data3 var0) {
         return var0 == null ? new ThreadModuleDumpType5.Data4() : var0;
      }
   }

   public static class Data4 extends ThreadModuleDumpType5.Data3 {
      private final ThreadModuleDumpType5 field1;

      public Data4() {
         this.field1 = ThreadModuleDumpType5.ANY;
      }

      public Data4(boolean var1) {
         this.field1 = var1 ? ThreadModuleDumpType5.TRUE : ThreadModuleDumpType5.FALSE;
      }

      @Override
      public ThreadModuleDumpType5 method1() {
         return this.field1;
      }

      @Generated
      public Data4(ThreadModuleDumpType5 var1) {
         this.field1 = var1;
      }
   }

   public static class Data5 extends ThreadModuleDumpType5.Data3 {
      @Nullable
      private final BooleanSupplier field1;

      @Override
      public ThreadModuleDumpType5 method1() {
         return this.field1 == null ? ThreadModuleDumpType5.ANY : (this.field1.getAsBoolean() ? ThreadModuleDumpType5.TRUE : ThreadModuleDumpType5.FALSE);
      }

      @Generated
      public Data5(@Nullable BooleanSupplier var1) {
         this.field1 = var1;
      }
   }
}
