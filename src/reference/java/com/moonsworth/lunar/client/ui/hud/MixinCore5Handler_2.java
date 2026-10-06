package com.moonsworth.lunar.client.ui.hud;

public abstract class MixinCore5Handler_2<T extends MixinCore5Handler_2<T>> implements MixinCore5 {
   private MixinCore5 field1;

   public MixinCore5Handler_2() {
      this.field1 = null;
   }

   public MixinCore5Handler_2(MixinCore5 var1) {
      this.field1 = var1;
   }

   public T method1(MixinCore5 var1) {
      if (var1 == null) {
         throw new NullPointerException("Component cant be null!");
      }

      this.field1 = var1;
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
   public void method1(float var1, float value, HudRenderContext hudRenderContext) {
      if (this.field1 != null) {
         this.field1.method1(var1, value, hudRenderContext);
      }
   }

   @Override
   public void clearCache() {
      if (this.field1 != null) {
         this.field1.clearCache();
      }
   }
}
