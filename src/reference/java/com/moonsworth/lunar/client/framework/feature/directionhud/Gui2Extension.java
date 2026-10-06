package com.moonsworth.lunar.client.framework.feature.directionhud;

enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ABOVE,
   BELOW,
   NONE;

   public String id() {
      return this.name();
   }
}
