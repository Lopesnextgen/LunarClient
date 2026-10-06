package com.moonsworth.lunar.client.replay.gui;

import java.util.function.BiFunction;

public enum GuiType {
   START((arg0, arg1) -> arg0),
   CENTER((arg0, arg1) -> arg1 / 2 - arg0),
   END((arg0, arg1) -> arg1 - arg0);

   private final BiFunction<Integer, Integer, Integer> function;

   GuiType(BiFunction<Integer, Integer, Integer> function3) {
      this.function = function3;
   }

   public int apply(int value, int value2) {
      return this.function.apply(value, value2);
   }
}
