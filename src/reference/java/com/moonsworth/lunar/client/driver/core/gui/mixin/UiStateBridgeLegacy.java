package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;

public class UiStateBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method100().method11();
   }

   @CallbackJS("setWindowState")
   public static void method2(String var0, Double var1, Double doubleValue, Double doubleValue2, Double doubleValue3, Boolean booleanValue, Boolean booleanValue2) {
      Client.method109().method100().method4(var0, var1, doubleValue, doubleValue2, doubleValue3, booleanValue, booleanValue2);
   }

   @CallbackJS("setSortState")
   public static void method3(String var0, String var1) {
      Client.method109().method100().method5(var0, var1);
   }

   @CallbackJS("setQuickNavigationState")
   public static void method4(JsonObject var0) {
      Client.method109().method100().method6(var0);
   }
}
