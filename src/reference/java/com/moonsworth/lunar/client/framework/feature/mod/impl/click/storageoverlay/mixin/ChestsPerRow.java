package com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin;

import lombok.Generated;

public enum ChestsPerRow implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   COUNT_2(2),
   COUNT_3(3),
   COUNT_4(4),
   COUNT_5(5);

   private final int chestsPerRow;

   public String id() {
      return "chestsPerRow" + this.chestsPerRow;
   }

   @Override
   public String toString() {
      return String.valueOf(this.chestsPerRow);
   }

   @Generated
   ChestsPerRow(int value) {
      this.chestsPerRow = value;
   }

   @Generated
   public int getChestsPerRow() {
      return this.chestsPerRow;
   }
}
