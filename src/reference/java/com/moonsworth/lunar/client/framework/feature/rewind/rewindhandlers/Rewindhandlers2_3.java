package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.webosr.javascript.CallbackJS;

public class Rewindhandlers2_3 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public com.moonsworth.lunar.client.driver.core.gui.GuiIterator getProvider() {
      return !method16().method19() ? null : method17().method37();
   }

   @CallbackJS("updateBoundingBoxes")
   public static void updateBoundingBoxes(Nameplate[] items) {
      RewindHandlers var1 = method17();
      if (var1 != null) {
         var1.method49().method10(items);
      }
   }
}
