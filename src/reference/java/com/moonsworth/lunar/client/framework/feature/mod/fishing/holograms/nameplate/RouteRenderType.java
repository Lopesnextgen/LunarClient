package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

public enum RouteRenderType implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   PARTICLES("routeRenderTypeParticles"),
   LINE("routeRenderTypeLines"),
   ARROW("routeRenderTypeArrows"),
   LINE_DASHED("routeRenderTypeDashedLines"),
   ARROW_DASHED("routeRenderTypeDashedArrows"),
   NONE("routeRenderTypeDisabled");

   private final String id;

   RouteRenderType(String text3) {
      this.id = text3;
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }
}
