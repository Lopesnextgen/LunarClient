package com.moonsworth.lunar.client.framework.feature.blockoutline;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import java.util.function.Supplier;
import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   STATIC("outlineModeStatic", () -> LunarRenderTypes.field26),
   RAINBOW("outlineModeRainbow", () -> LunarRenderTypes.field26),
   BLEND("outlineModeBlend", () -> LunarRenderTypes.field26),
   INVERTED("outlineModeInverted", () -> LunarRenderTypes.field27),
   DARKEN("outlineModeDarken", () -> LunarRenderTypes.field28);

   private final String id;
   private final Supplier<RenderLayerBridge> renderTypeSupplier;

   public String id() {
      return this.id;
   }

   public RenderLayerBridge renderType() {
      return this.renderTypeSupplier.get();
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension2(String text, Supplier<RenderLayerBridge> supplier) {
      this.id = text;
      this.renderTypeSupplier = supplier;
   }
}
