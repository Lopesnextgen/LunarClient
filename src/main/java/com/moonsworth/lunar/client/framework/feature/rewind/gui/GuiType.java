package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import java.util.function.BiFunction;

public enum GuiType {
   START((var0, var1) -> var0),
   CENTER((var0, var1) -> var1 / 2 - var0),
   END((var0, var1) -> var1 - var0);

   private final BiFunction<Integer, Integer, Integer> function;

   GuiType(BiFunction<Integer, Integer, Integer> function2) {
      this.function = function2;
   }

   public int apply(int var1, int value) {
      return this.function.apply(var1, value);
   }
}
