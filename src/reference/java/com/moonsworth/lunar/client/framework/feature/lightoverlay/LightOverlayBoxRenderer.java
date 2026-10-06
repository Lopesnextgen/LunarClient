package com.moonsworth.lunar.client.framework.feature.lightoverlay;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.client.mod.render.lightoverlay.LightOverlay;
import com.moonsworth.lunar.client.util.math.ColorUtils;

class LightOverlayBoxRenderer implements LightOverlayRenderer {
   private final LightOverlay field1;
   private final DrawBufferBridge field2;
   private final int field3;

   protected LightOverlayBoxRenderer(AbstractRenderContext bridgeextension_91, LightOverlay lightoverlay2) {
      this.field1 = lightoverlay2;
      this.field2 = bridgeextension_91.method10(LunarRenderTypes.field15);
      this.field2.method1();
      this.field3 = lightoverlay2.getSkylightSubtract();
   }

   @Override
   public void method1(double value1, double value3, double value5, byte number7, byte number8) {
      int number9 = this.field1.method15(number7, number8, this.field3, (float)(value1 + value5));
      float value10 = ColorUtils.method5(number9);
      float value11 = ColorUtils.method6(number9);
      float value12 = ColorUtils.method7(number9);
      float value13 = ColorUtils.method8(number9) * 0.66F;
      this.field2.method2(value1, value3, value5).method8(value10, value11, value12, value13).method16();
      this.field2.method2(value1, value3, value5 + 1.0).method8(value10, value11, value12, value13).method16();
      this.field2.method2(value1 + 1.0, value3, value5 + 1.0).method8(value10, value11, value12, value13).method16();
      this.field2.method2(value1 + 1.0, value3, value5).method8(value10, value11, value12, value13).method16();
   }

   @Override
   public void end() {
      this.field2.method17(BufferMode.IMMEDIATE);
   }
}
