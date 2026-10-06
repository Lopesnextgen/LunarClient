package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import lombok.Generated;

public enum WaypointRenderMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   WIREFRAME("wireframe"),
   FILL("fill"),
   BOTH("wireframeAndFill");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   WaypointRenderMode(String text3) {
      this.id = text3;
   }
}
