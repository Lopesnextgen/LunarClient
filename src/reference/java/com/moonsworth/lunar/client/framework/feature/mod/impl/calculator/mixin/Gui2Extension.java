package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin;

import javax.annotation.Nullable;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ALL("all", null),
   EASY("easy", CalculatorType2.EASY),
   MEDIUM("medium", CalculatorType2.MEDIUM),
   HARD("hard", CalculatorType2.HARD);

   private final String id;
   @Nullable
   private final CalculatorType2 difficulty;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id(), new Object[0]);
   }

   @Generated
   Gui2Extension(String text, @Nullable CalculatorType2 var4) {
      this.id = text;
      this.difficulty = var4;
   }

   @Nullable
   @Generated
   public CalculatorType2 getDifficulty() {
      return this.difficulty;
   }
}
