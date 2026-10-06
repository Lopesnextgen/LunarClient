package com.moonsworth.lunar.client.framework.feature.lightoverlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.client.mod.render.lightoverlay.LightOverlay;

class LightoverlayHandler implements Lightoverlay {
   private final LightOverlay field1;
   private final Bridge_28 field2;
   private final int field3;

   protected LightoverlayHandler(AbstractRenderContext var1, LightOverlay var2) {
      this.field1 = var2;
      Bridge.method42().method72(true);
      this.field2 = var1.method11((Float)var2.crossThickness.get());
      this.field3 = var2.getSkylightSubtract();
   }

   @Override
   public void method1(double var1, double var3, double var5, byte var7, byte var8) {
      int var9 = this.field1.method15(var7, var8, this.field3, (float)(var1 + var5));
      this.field2.method1(var9);
      this.field2.method3(var1 + 0.25, var3, var5 + 0.25, var1 + 0.75, var3, var5 + 0.75);
      this.field2.method3(var1 + 0.75, var3, var5 + 0.25, var1 + 0.25, var3, var5 + 0.75);
   }

   @Override
   public void end() {
      this.field2.end();
      Bridge.method42().method72(false);
   }
}
