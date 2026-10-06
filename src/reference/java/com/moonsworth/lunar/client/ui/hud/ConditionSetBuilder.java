package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.config.option.ConditionState.FunctionCondition;
import com.moonsworth.lunar.client.config.option.ConditionState.Condition;
import com.moonsworth.lunar.client.config.option.ConditionState.ConstantCondition;
import com.moonsworth.lunar.client.config.option.ConditionState.SupplierCondition;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ConditionSetBuilder {
   @Nullable
   private Condition field1;
   @Nullable
   private Condition field2;
   @Nullable
   private Condition field3;
   @Nullable
   private Condition field4;

   public ConditionSetBuilder method1(boolean flag1) {
      this.field1 = new ConstantCondition(flag1);
      return this;
   }

   public ConditionSetBuilder method2(boolean flag1) {
      this.field2 = new ConstantCondition(flag1);
      return this;
   }

   public ConditionSetBuilder method3(boolean flag1) {
      this.field3 = new ConstantCondition(flag1);
      return this;
   }

   public ConditionSetBuilder method4(boolean flag1) {
      this.field4 = new ConstantCondition(flag1);
      return this;
   }

   public ConditionSetBuilder method5(Function<String, Boolean> function1) {
      this.field1 = new FunctionCondition(function1);
      return this;
   }

   public ConditionSetBuilder method6(BooleanSupplier booleansupplier1) {
      this.field3 = new SupplierCondition(booleansupplier1);
      return this;
   }

   public ConditionSetBuilder method7(BooleanSupplier booleansupplier1) {
      this.field4 = new SupplierCondition(booleansupplier1);
      return this;
   }

   public HudConditionSet method8() {
      return new HudConditionSet(Condition.method2(this.field1), Condition.method2(this.field2), Condition.method2(this.field3), Condition.method2(this.field4));
   }

   @Generated
   private ConditionSetBuilder() {
   }
}
