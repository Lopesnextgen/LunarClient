package com.moonsworth.lunar.client.framework.feature.crosshair.mixin;

import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SMALL("crosshairGridSmall", 7),
   MEDIUM("crosshairGridMedium", 15),
   BIG("crosshairGridBig", 31),
   HUGE("crosshairGridHuge", 63);

   private final String id;
   private final int gridSize;

   public static Gui2Extension2 fromGridLength(int value) {
      for (Gui2Extension2 var4 : values()) {
         if (value <= var4.gridSize) {
            return var4;
         }
      }

      throw new IllegalArgumentException(String.format("Invalid crosshair grid %d * %d", value, value));
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   public int size() {
      return this.gridSize;
   }

   public Gui2Extension2 smaller() {
      Gui2Extension2[] var1 = values();
      int var2 = Math.max(0, this.ordinal() - 1);
      return var1[var2];
   }

   public Gui2Extension2 bigger() {
      Gui2Extension2[] var1 = values();
      int var2 = Math.min(var1.length - 1, this.ordinal() + 1);
      return var1[var2];
   }

   @Generated
   Gui2Extension2(String text, int var4) {
      this.id = text;
      this.gridSize = var4;
   }
}
