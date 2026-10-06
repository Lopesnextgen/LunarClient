package com.moonsworth.lunar.client.framework.feature.lightoverlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.client.mod.render.lightoverlay.LightOverlay;

class LightOverlayCrossRenderer implements LightOverlayRenderer {
   private final LightOverlay field1;
   private final BufferBuilderBridge field2;
   private final int field3;

   protected LightOverlayCrossRenderer(AbstractRenderContext bridgeextension_91, LightOverlay lightoverlay2) {
      this.field1 = lightoverlay2;
      Bridge.method42().method72(true);
      this.field2 = bridgeextension_91.method11((Float)lightoverlay2.crossThickness.get());
      this.field3 = lightoverlay2.getSkylightSubtract();
   }

   @Override
   public void method1(double value1, double value3, double value5, byte number7, byte number8) {
      int number9 = this.field1.method15(number7, number8, this.field3, (float)(value1 + value5));
      this.field2.method1(number9);
      this.field2.method3(value1 + 0.25, value3, value5 + 0.25, value1 + 0.75, value3, value5 + 0.75);
      this.field2.method3(value1 + 0.75, value3, value5 + 0.25, value1 + 0.25, value3, value5 + 0.75);
   }

   @Override
   public void end() {
      this.field2.end();
      Bridge.method42().method72(false);
   }
}
