package com.moonsworth.lunar.client.framework.feature.directionhud;

enum DirectionHudPosition implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ABOVE,
   BELOW,
   NONE;

   DirectionHudPosition() {
   }

   public String id() {
      return this.name();
   }
}
