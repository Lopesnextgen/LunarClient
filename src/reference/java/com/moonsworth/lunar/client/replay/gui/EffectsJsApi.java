package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.webosr.javascript.CallbackJS;

public class EffectsJsApi extends RewindEditorContext implements DriverGuiExtension, Extension {
   public EffectsJsApi() {
   }

   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return !RRRCROOHOHICIIIOHHIHOIHCHIOCOH().method19() ? null : COOHOCCHHRHOICOCCIROCRRHRIIROI().method37();
   }

   @CallbackJS("updateBoundingBoxes")
   public static void method2(Nameplate[] items0) {
      RewindHandlers rewindhandlers1 = COOHOCCHHRHOICOCCIROCRRHRIIROI();
      if (rewindhandlers1 != null) {
         rewindhandlers1.method49().method10(items0);
      }
   }
}
