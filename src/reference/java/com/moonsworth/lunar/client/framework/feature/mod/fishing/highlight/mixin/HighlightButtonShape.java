package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;

public enum HighlightButtonShape implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SQUARE("square", getTexture("square"), getTexture("square_border")),
   ROUNDED_SQUARE("roundedSquare", getTexture("rounded_square"), getTexture("rounded_square_border")),
   CIRCLE("circle", getTexture("circle"), getTexture("circle_border"));

   private final String id;
   private final ResourceLocationBridge buttonTexture;
   private final ResourceLocationBridge borderTexture;

   private static ResourceLocationBridge getTexture(String text0) {
      return ResourceLocationBridge.create("lunar", "skyblock/inventorybuttons/" + text0 + ".png");
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   HighlightButtonShape(String text3, ResourceLocationBridge horsestats144, ResourceLocationBridge horsestats145) {
      this.id = text3;
      this.buttonTexture = horsestats144;
      this.borderTexture = horsestats145;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public ResourceLocationBridge getButtonTexture() {
      return this.buttonTexture;
   }

   @Generated
   public ResourceLocationBridge getBorderTexture() {
      return this.borderTexture;
   }
}
