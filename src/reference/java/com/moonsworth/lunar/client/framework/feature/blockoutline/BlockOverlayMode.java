package com.moonsworth.lunar.client.framework.feature.blockoutline;

import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import java.util.function.Supplier;
import lombok.Generated;

public enum BlockOverlayMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   STATIC("outlineModeStatic", () -> LunarRenderTypes.field26),
   RAINBOW("outlineModeRainbow", () -> LunarRenderTypes.field26),
   BLEND("outlineModeBlend", () -> LunarRenderTypes.field26),
   INVERTED("outlineModeInverted", () -> LunarRenderTypes.field27),
   DARKEN("outlineModeDarken", () -> LunarRenderTypes.field28);

   private final String id;
   private final Supplier<RenderTypeBridge> renderTypeSupplier;

   public String id() {
      return this.id;
   }

   public RenderTypeBridge renderType() {
      return this.renderTypeSupplier.get();
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   BlockOverlayMode(String text3, Supplier<RenderTypeBridge> supplier4) {
      this.id = text3;
      this.renderTypeSupplier = supplier4;
   }
}
