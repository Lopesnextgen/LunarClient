package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;

public class LanguagePageBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method67().method20();
   }

   @CallbackJS("selectLanguage")
   public static void method2(String var0) {
      Slayer.method4("WebOSR", "Updating language to: " + var0, new Object[0]);

      try {
         ThreadModuleDump63.method3().bridge$getLanguageManager().bridge$setCurrentLanguage(var0);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      Client.method109().method67().setLanguage(var0);
   }

   @CallbackJS("setForceUnicode")
   public static void method3(Boolean var0) {
      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setForceUnicode(var0);
      ThreadModuleDump63.method4().method67().method14();
   }
}
