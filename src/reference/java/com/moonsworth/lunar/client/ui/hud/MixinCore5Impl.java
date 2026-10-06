package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Supplier;

public class MixinCore5Impl extends MixinCore5Handler_2<MixinCore5Impl> {
   private final MixinCore6<Boolean> field2 = new MixinCore6<>(false, false, true);

   public MixinCore5Impl() {
   }

   public MixinCore5Impl(MixinCore5 var1) {
      super(var1);
   }

   public MixinCore5Impl method1(boolean var1) {
      this.field2.method1(var1);
      return this;
   }

   public MixinCore5Impl method2(Supplier<Boolean> var1) {
      this.field2.method2(var1);
      return this;
   }

   @Override
   public void method1(float var1, float value, HudRenderContext hudRenderContext) {
      if (!this.field2.get()) {
         super.method1(var1, value, hudRenderContext);
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
