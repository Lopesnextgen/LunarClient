package com.moonsworth.lunar.client.ui.hud;

public abstract class HudComponentWrapper<T extends HudComponentWrapper<T>> implements HudComponent {
   private HudComponent field1;

   public HudComponentWrapper() {
      this.field1 = null;
   }

   public HudComponentWrapper(HudComponent mixincore51) {
      this.field1 = mixincore51;
   }

   public T method1(HudComponent mixincore51) {
      if (mixincore51 == null) {
         throw new NullPointerException("Component cant be null!");
      }

      this.field1 = mixincore51;
      return (T)this;
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

   @Override
   public void clearCache() {
      if (this.field1 != null) {
         this.field1.clearCache();
      }
   }
}
