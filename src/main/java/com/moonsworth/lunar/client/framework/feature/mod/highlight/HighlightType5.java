package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import lombok.Generated;

public enum HighlightType5 {
   DAY("☀"),
   NIGHT("☽"),
   RAIN("☔"),
   THUNDER("⚡");

   private final String icon;

   public static HighlightType5 getFromIcon(String text) {
      for (HighlightType5 var4 : values()) {
         if (var4.getIcon().equals(text)) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   HighlightType5(String text) {
      this.icon = text;
   }

   @Generated
   public String getIcon() {
      return this.icon;
   }
}
