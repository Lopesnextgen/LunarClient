package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.webosr.javascript.CallbackJS;

public class InputTimelineJsApi extends RewindEditorContext implements DriverGuiExtension, Extension {
   public InputTimelineJsApi() {
   }

   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return !RRRCROOHOHICIIIOHHIHOIHCHIOCOH().method19() ? null : COOHOCCHHRHOICOCCIROCRRHRIIROI().method39();
   }

   @CallbackJS("goToTick")
   public static void method2(int number0) {
      ReplayContext nameplate41 = (ReplayContext)COOHOCCHHRHOICOCCIROCRRHRIIROI().method42().get();
      ReplayTimeline highlight_32 = HCHIOCOCRHRHORCHIOHRRIHCOOHCOI();
      if (highlight_32 != null) {
         int number3 = nameplate41.getTick() - number0;
         long number4 = number3 * 50L;
         int number6 = (int)(number4 / highlight_32.method9());
         highlight_32.method8(Math.max(0, highlight_32.method15() - number6));
      }
   }
}
