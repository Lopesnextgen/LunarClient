package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class TextureHudComponent implements HudComponent {
   private final ResourceLocationBridge texture;
   private final float field1;
   private final float field2;

   public TextureHudComponent(ResourceLocationBridge horsestats141, float value2, float value) {
      this.texture = horsestats141;
      this.field1 = value2;
      this.field2 = value;
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
   public void method1(float value, float value2, HudRenderContext mixincore43) {
      mixincore43.method6().method24(this.texture, (int)value, (int)value2, (int)this.field1, (int)this.field2, -1);
   }
}
