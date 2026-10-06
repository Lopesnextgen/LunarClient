package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.replay.gui.AlertImpl;
import com.moonsworth.lunar.client.replay.timeline.GameplayTrack;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.driver.DriverViewContext;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRenderQueue;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;

public class ReplayViewContext implements DriverViewContext {
   private float field1;
   private float field2;
   private long field3 = 0L;

   public ReplayViewContext() {
   }

   public void method1(AbstractRenderContext bridgeextension_91, Data5 data52) {
      RewindMod rewind3 = Ref.method4().method40().method85();
      RewindHandlers rewindhandlers4 = rewind3.method35();
      if (rewindhandlers4 != null) {
         ReplayTimeline highlight_35 = rewindhandlers4.method40().method37();
         AlertImpl alertimpl6 = rewindhandlers4.method48().method24();
         if (alertimpl6.method11() != null) {
            if (highlight_35 != null) {
               boolean flag7 = false;

               for (GameplayTrack guiimpl39 : highlight_35.method11().method3()) {
                  if (guiimpl39.isEnabled() && guiimpl39.CCOIHCHRIHICROIOOCRRRHORHIRIOO(highlight_35.method15()) != null) {
                     flag7 = true;
                     break;
                  }
               }

               if (!flag7 && !Ref.method3().bridge$hasOverlay() && rewindhandlers4.method48().method25()) {
                  alertimpl6.method11().bridge$framebufferClear();
                  Ref.method3().method1(alertimpl6.method11(), false);
               }
            }

            rewindhandlers4.method48().method7(bridgeextension_91);
            RewindRenderQueue rewindhandlers410 = rewindhandlers4.method57();
            if (!rewindhandlers410.method25() || rewindhandlers410.method29()) {
               Bridge3_24 bridge3_2411 = Ref.method3().bridge$getMainRenderTarget();
               com.moonsworth.lunar.client.ui.LcuiScreen.method67(
                  bridgeextension_91, 0.0F, 0.0F, bridge3_2411.bridge$framebufferTextureWidth(), bridge3_2411.bridge$framebufferTextureHeight(), -16777216
               );
               if (highlight_35 != null) {
                  bridgeextension_91.push();
                  float value12 = com.moonsworth.lunar.client.ui.LcuiScreen.getScale();
                  bridgeextension_91.scale(value12, value12, 1.0F);
                  this.method2(rewindhandlers4);
                  this.method3(highlight_35, bridgeextension_91, rewindhandlers4.method48().method24(), this.field1, this.field2);
                  bridgeextension_91.pop();
               }
            }
         }
      }
   }

   private void method2(RewindHandlers rewindhandlers1) {
      long number2 = System.currentTimeMillis();
      float value4 = MathUtils.method1((float)(number2 - this.field3), 1.0F, 1000.0F) / 1000.0F;
      this.field3 = number2;
      float value5 = rewindhandlers1.method58() / com.moonsworth.lunar.client.ui.LcuiScreen.field6;
      float value6 = rewindhandlers1.method59() / com.moonsworth.lunar.client.ui.LcuiScreen.field6;
      float value7 = value5 - this.field1;
      float value8 = value6 - this.field2;
      float value9 = 50.0F;
      if (this.field1 == 0.0F || this.field2 == 0.0F || !(Math.abs(value7) > 1.0F) && !(Math.abs(value8) > 1.0F)) {
         this.field1 = value5;
         this.field2 = value6;
      } else {
         float value10 = Math.min(0.8F, value9 * value4);
         float value11 = value7 * value10;
         float value12 = value8 * value10;
         this.field1 += value11;
         this.field2 += value12;
      }
   }

   private void method3(ReplayTimeline highlight_31, AbstractRenderContext bridgeextension_92, AlertImpl alertimpl3, float value4, float value5) {
      if (alertimpl3.method11() != null) {
         float value6 = value4;
         float value7 = value5;
         int number8 = alertimpl3.method11().bridge$framebufferWidth();
         int number9 = alertimpl3.method11().bridge$framebufferHeight();
         if (value6 > value7) {
            value6 = value7 * number8 / number9;
            if (value6 > value4) {
               value6 = value4;
               value7 = value6 * number9 / number8;
            }
         } else {
            value7 = value6 * number9 / number8;
            if (value7 > value5) {
               value7 = value5;
               value6 = value7 * number8 / number9;
            }
         }

         float value10 = com.moonsworth.lunar.client.ui.LcuiScreen.method135((value4 - value6) / 2.0F);
         float value11 = com.moonsworth.lunar.client.ui.LcuiScreen.method135((value5 - value7) / 2.0F);
         value6 = com.moonsworth.lunar.client.ui.LcuiScreen.method135(value6);
         value7 = com.moonsworth.lunar.client.ui.LcuiScreen.method135(value7);
         if (Ref.MC_VERSION >= 6) {
            Ref.method3().bridge$getRenderBuffers().bridge$bufferSource().bridge$endBatch();
         }

         alertimpl3.method1(bridgeextension_92, value10, value11, value6, value7);
         Ref.method3().method1(null, true);
         int number12 = -1325400065;
         if (highlight_31.method18()) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(bridgeextension_92, value10 + value6 / 3.0F, value11, 1.0F, value7, number12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(bridgeextension_92, value10 + value6 * 2.0F / 3.0F, value11, 1.0F, value7, number12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(bridgeextension_92, value10, value11 + value7 / 3.0F, value6, 1.0F, number12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(bridgeextension_92, value10, value11 + value7 * 2.0F / 3.0F, value6, 1.0F, number12);
         }

         if (highlight_31.method20()) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(bridgeextension_92, value10 + value6 / 2.0F, value11, 1.0F, value7, number12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(bridgeextension_92, value10, value11 + value7 / 2.0F, value6, 1.0F, number12);
         }
      }
   }
}
