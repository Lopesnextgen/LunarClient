package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.render.texture.FramebufferCapture;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.Ref;

public class AlertImpl extends FramebufferCapture {
   public AlertImpl() {
   }

   public void method1(AbstractRenderContext bridgeextension_91, float value2, float value3, float value4, float value5) {
      this.method2(bridgeextension_91, value2, value3, value4, value5, this.field3);
   }

   public void method2(AbstractRenderContext bridgeextension_91, float value2, float value3, float value4, float value5, Bridge3_24 bridge3_246) {
      if (Ref.MC_VERSION < 29) {
         float value7 = LcuiScreen.getScale() * LcuiScreen.method151().method3();
         value2 *= value7;
         value3 *= value7;
         value4 *= value7;
         value5 *= value7;
         value3 = Ref.method3().bridge$displayHeight() - value3 - value5;
         bridge3_246.bridge$blitToScreen((int)value2, (int)value3, (int)(value2 + value4), (int)(value3 + value5), true);
      } else {
         bridgeextension_91.push();
         bridgeextension_91.translate(value2, value3, 0.0);
         super.method8(bridgeextension_91, value4, value5, bridge3_246, false);
         bridgeextension_91.pop();
      }
   }
}
