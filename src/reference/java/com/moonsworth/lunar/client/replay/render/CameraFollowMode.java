package com.moonsworth.lunar.client.replay.render;

import lombok.Generated;

public enum CameraFollowMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   POSITION("positionOnly"),
   POS_ROT_BODY("posRotBody"),
   POS_ROT_HEAD("posRotHead");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   CameraFollowMode(String text3) {
      this.id = text3;
   }
}
