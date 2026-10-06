package com.moonsworth.lunar.client.framework.feature.inventorymod.slot.inventorysearch;

import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   BOTTOM_MIDDLE("bottomMiddle"),
   BOTTOM_LEFT("bottomLeft"),
   TOP_MIDDLE("topMiddle"),
   TOP_LEFT("topLeft");

   private final String translationKey;

   Gui2Extension(@Annotation(method1 = Annotation.Type.INVENTORY_SEARCH_INFO) String var3) {
      this.translationKey = var3;
   }

   @Override
   public String id() {
      return this.translationKey;
   }

   @Override
   public String getLanguagePath() {
      return "features.INVENTORY_SEARCH.info";
   }

   @Override
   public String toString() {
      return this.method1(this.id(), new Object[0]);
   }

   @Generated
   public String getTranslationKey() {
      return this.translationKey;
   }
}
