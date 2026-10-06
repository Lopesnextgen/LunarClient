package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2;
import lombok.Generated;

public enum Gui2Extension3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   CLEAR("clear"),
   SECRETS("secrets"),
   BOTH("both");

   private final String id;

   public boolean rendersAt(HologramsType2 hologramsType2) {
      return switch (this) {
         case BOTH -> true;
         case CLEAR -> hologramsType2 != HologramsType2.CLEARED && hologramsType2 != HologramsType2.COMPLETED;
         case SECRETS -> hologramsType2 == HologramsType2.CLEARED;
      };
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension3(String text) {
      this.id = text;
   }
}
