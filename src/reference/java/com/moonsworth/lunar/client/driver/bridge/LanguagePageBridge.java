package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;

public class LanguagePageBridge implements DriverGuiExtension, GuiIterator.Extension {
   public LanguagePageBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method67().method20();
   }

   @CallbackJS("selectLanguage")
   public static void method2(String text0) {
      LunarLogger.method4("WebOSR", "Updating language to: " + text0, new Object[0]);

      try {
         Ref.method3().bridge$getLanguageManager().bridge$setCurrentLanguage(text0);
      } catch (Exception exception2) {
         exception2.printStackTrace();
      }

      Client.method109().method67().setLanguage(text0);
   }

   @CallbackJS("setForceUnicode")
   public static void method3(Boolean flag0) {
      Ref.method3().bridge$getGameSettings().bridge$setForceUnicode(flag0);
      Ref.method4().method67().method14();
   }
}
