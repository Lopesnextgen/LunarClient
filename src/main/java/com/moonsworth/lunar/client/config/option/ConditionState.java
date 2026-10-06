package com.moonsworth.lunar.client.config.option;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public enum ConditionState {
   ANY,
   TRUE,
   FALSE;

   ConditionState() {
   }

   public boolean isConditional() {
      return this == ANY;
   }

   public boolean isTrue() {
      return this == TRUE;
   }

   public boolean isFalse() {
      return this == FALSE;
   }

   public boolean isConditionalOrTrue(BooleanSupplier booleansupplier1) {
      return this.isConditional() ? booleansupplier1.getAsBoolean() : this.isTrue();
   }

   public boolean isConditionalOrFalse(BooleanSupplier booleansupplier1) {
      return this.isConditional() ? booleansupplier1.getAsBoolean() : this.isFalse();
   }

   public boolean isConditionalOrElse(BooleanSupplier booleansupplier1, boolean flag) {
      return this.isConditional() ? booleansupplier1.getAsBoolean() : flag;
   }

   public static ConditionState orDefault(@Nullable ConditionState threadmoduledumptype50) {
      return threadmoduledumptype50 == null ? ANY : threadmoduledumptype50;
   }

   public abstract static class Data<T> extends ConditionState.Condition {
      public Data() {
      }

      public abstract ConditionState method1(T value1);
   }

   public static class FunctionCondition<T> extends ConditionState.Data<T> {
      @Nullable
      private final Function<T, Boolean> field1;

      @Override
      public ConditionState method1(T value1) {
         return this.field1 == null ? ConditionState.ANY : (this.field1.apply((T)value1) ? ConditionState.TRUE : ConditionState.FALSE);
      }

      @Override
      public ConditionState method1() {
         return ConditionState.ANY;
      }

      @Generated
      public FunctionCondition(@Nullable Function<T, Boolean> function1) {
         this.field1 = function1;
      }
   }

   public abstract static class Condition {
      public Condition() {
      }

      public abstract ConditionState method1();

      public static ConditionState.Condition method2(@Nullable ConditionState.Condition data30) {
         return data30 == null ? new ConditionState.ConstantCondition() : data30;
      }
   }

   public static class ConstantCondition extends ConditionState.Condition {
      private final ConditionState field1;

      public ConstantCondition() {
         this.field1 = ConditionState.ANY;
      }

      public ConstantCondition(boolean flag) {
         this.field1 = flag ? ConditionState.TRUE : ConditionState.FALSE;
      }

      @Override
      public ConditionState method1() {
         return this.field1;
      }

      @Generated
      public ConstantCondition(ConditionState state) {
         this.field1 = state;
      }
   }

   public static class SupplierCondition extends ConditionState.Condition {
      @Nullable
      private final BooleanSupplier field1;

      @Override
      public ConditionState method1() {
         return this.field1 == null ? ConditionState.ANY : (this.field1.getAsBoolean() ? ConditionState.TRUE : ConditionState.FALSE);
      }

      @Generated
      public SupplierCondition(@Nullable BooleanSupplier booleansupplier1) {
         this.field1 = booleansupplier1;
      }
   }
}
