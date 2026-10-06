package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.webosr.javascript.CallbackJS;

public class Rewindhandlers2_2 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return !method16().method19() ? null : method17().method39();
   }

   @CallbackJS("goToTick")
   public static void goToTick(int var0) {
      Nameplate4 var1 = (Nameplate4)method17().method42().get();
      Highlight_3 var2 = method19();
      if (var2 != null) {
         int var3 = var1.getTick() - var0;
         long var4 = var3 * 50L;
         int var6 = (int)(var4 / var2.method9());
         var2.method8(Math.max(0, var2.method15() - var6));
      }
   }
}
