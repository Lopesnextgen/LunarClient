package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin;

import javax.annotation.Nullable;
import lombok.Generated;

public enum RaffleTaskFilter implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ALL("all", null),
   EASY("easy", RaffleTaskDifficulty.EASY),
   MEDIUM("medium", RaffleTaskDifficulty.MEDIUM),
   HARD("hard", RaffleTaskDifficulty.HARD);

   private final String id;
   @Nullable
   private final RaffleTaskDifficulty difficulty;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id(), new Object[0]);
   }

   @Generated
   RaffleTaskFilter(String text3, @Nullable RaffleTaskDifficulty calculatortype24) {
      this.id = text3;
      this.difficulty = calculatortype24;
   }

   @Nullable
   @Generated
   public RaffleTaskDifficulty getDifficulty() {
      return this.difficulty;
   }
}
