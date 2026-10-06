package com.moonsworth.lunar.client.framework.feature.crosshair;

import lombok.Generated;

public enum CrosshairType {
   NORMAL("CROSSHAIR_NORMAL"),
   FRIENDLY("CROSSHAIR_FRIENDLY"),
   ENEMY("CROSSHAIR_ENEMY");

   public final String id;

   @Generated
   CrosshairType(String text) {
      this.id = text;
   }
}
