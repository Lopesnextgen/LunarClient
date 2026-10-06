package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class MixinCore5Handler2_2 implements MixinCore5 {
   private final ResourceLocationBridge texture;
   private final float field1;
   private final float field2;

   public MixinCore5Handler2_2(ResourceLocationBridge var1, float var2, float var3) {
      this.texture = var1;
      this.field1 = var2;
      this.field2 = var3;
   }

   @Override
   public void clearCache() {
   }

   @Override
   public float getWidth() {
      return this.field1;
   }

   @Override
   public float getHeight() {
      return this.field2;
   }

   @Override
   public void method1(float var1, float var2, HudRenderContext var3) {
      var3.method6().method24(this.texture, (int)var1, (int)var2, (int)this.field1, (int)this.field2, -1);
   }
}
