package com.moonsworth.lunar.client.render.font;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextureManagerBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class TextRenderPass {
   private static final TextureManagerBridge field1 = Bridge.method9().bridge$getTextureManager();
   private final int field2;
   private final ResourceLocationBridge field3;

   public TextRenderPass(int value, ResourceLocationBridge horsestats142) {
      this.field2 = value;
      this.field3 = horsestats142;
   }

   public void method1() {
      if (this.field3 == null) {
         Bridge.method42().method32();
      } else {
         field1.bridge$bindTexture(this.field3);
      }

      Bridge.method42().method80(this.field2);
      if (this.field3 == null) {
         Bridge.method42().method31();
      }
   }

   public void delete() {
      Bridge.method42().method81(this.field2, 1);
   }
}
