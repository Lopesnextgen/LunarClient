package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   PARTICLES("routeRenderTypeParticles"),
   LINE("routeRenderTypeLines"),
   ARROW("routeRenderTypeArrows"),
   LINE_DASHED("routeRenderTypeDashedLines"),
   ARROW_DASHED("routeRenderTypeDashedArrows"),
   NONE("routeRenderTypeDisabled");

   private final String id;

   Gui2Extension(String text) {
      this.id = text;
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }
}
