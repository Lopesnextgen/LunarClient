package com.moonsworth.lunar.client.ui.hud;

public abstract class HudComponentDecorator implements HudComponent {
   protected HudComponent field1;

   public HudComponentDecorator() {
   }

   public HudComponentDecorator(HudComponent mixincore51) {
      this.field1 = mixincore51;
   }

   @Override
   public void clearCache() {
      if (this.field1 != null) {
         this.field1.clearCache();
      }
   }

   @Override
   public float getWidth() {
      return this.field1 == null ? 0.0F : this.field1.getWidth();
   }

   @Override
   public float getHeight() {
      return this.field1 == null ? 0.0F : this.field1.getHeight();
   }

   @Override
   public void method1(float value, float value2, HudRenderContext mixincore43) {
      if (this.field1 != null) {
         this.field1.method1(value, value2, mixincore43);
      }
   }
}
