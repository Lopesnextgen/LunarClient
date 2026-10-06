package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Supplier;

public class HideableHudComponent extends HudComponentWrapper<HideableHudComponent> {
   private final HudComponentValue<Boolean> field2 = new HudComponentValue<>(false, false, true);

   public HideableHudComponent() {
   }

   public HideableHudComponent(HudComponent mixincore51) {
      super(mixincore51);
   }

   public HideableHudComponent method1(boolean flag) {
      this.field2.method1(flag);
      return this;
   }

   public HideableHudComponent method2(Supplier<Boolean> supplier1) {
      this.field2.method2(supplier1);
      return this;
   }

   @Override
   public void method1(float value, float value2, HudRenderContext mixincore43) {
      if (!this.field2.get()) {
         super.method1(value, value2, mixincore43);
      }
   }

   @Override
   public float getWidth() {
      return this.field2.get() ? 0.0F : super.getWidth();
   }

   @Override
   public float getHeight() {
      return this.field2.get() ? 0.0F : super.getHeight();
   }

   @Override
   public void clearCache() {
      this.field2.clearCache();
      super.clearCache();
   }

   public boolean isHidden() {
      return this.field2.get();
   }
}
