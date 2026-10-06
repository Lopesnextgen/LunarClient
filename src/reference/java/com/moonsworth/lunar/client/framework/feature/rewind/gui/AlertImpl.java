package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.render.texture.FramebufferCapture;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class AlertImpl extends FramebufferCapture {
   public void method1(AbstractRenderContext var1, float var2, float var3, float var4, float var5) {
      this.method2(var1, var2, var3, var4, var5, this.field3);
   }

   public void method2(AbstractRenderContext var1, float var2, float var3, float var4, float var5, Bridge3_24 bridge3_24) {
      if (ThreadModuleDump63.MC_VERSION < 29) {
         float var7 = LcuiScreen.getScale() * LcuiScreen.method151().method3();
         var2 *= var7;
         var3 *= var7;
         var4 *= var7;
         var5 *= var7;
         var3 = ThreadModuleDump63.method3().bridge$displayHeight() - var3 - var5;
         bridge3_24.bridge$blitToScreen((int)var2, (int)var3, (int)(var2 + var4), (int)(var3 + var5), true);
      } else {
         var1.push();
         var1.translate(var2, var3, 0.0);
         super.method8(var1, var4, var5, bridge3_24, false);
         var1.pop();
      }
   }
}
