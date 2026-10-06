package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import lombok.Generated;

public enum SkyblockWeather {
   DAY("☀"),
   NIGHT("☽"),
   RAIN("☔"),
   THUNDER("⚡");

   private final String icon;

   public static SkyblockWeather getFromIcon(String text) {
      for (SkyblockWeather highlighttype54 : values()) {
         if (highlighttype54.getIcon().equals(text)) {
            return highlighttype54;
         }
      }

      return null;
   }

   @Generated
   SkyblockWeather(String text) {
      this.icon = text;
   }

   @Generated
   public String getIcon() {
      return this.icon;
   }
}
