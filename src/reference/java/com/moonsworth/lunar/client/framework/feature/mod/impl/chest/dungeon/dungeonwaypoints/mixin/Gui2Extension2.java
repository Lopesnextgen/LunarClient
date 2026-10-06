package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   WIREFRAME("wireframe"),
   FILL("fill"),
   BOTH("wireframeAndFill");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension2(String text) {
      this.id = text;
   }
}
