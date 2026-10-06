package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import lombok.Generated;

public enum DetectedRoomShape {
   ONE_BY_ONE("1x1"),
   TWO_BY_TWO("2x2"),
   ONE_BY_TWO("1x2"),
   ONE_BY_THREE("1x3"),
   ONE_BY_FOUR("1x4"),
   L_SHAPE("L-shape"),
   TRAP("Trap"),
   PUZZLE("Puzzle"),
   GENERAL("General"),
   NONE("???");

   public final String name;

   @Generated
   DetectedRoomShape(String text) {
      this.name = text;
   }
}
