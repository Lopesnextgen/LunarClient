package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;

public class UiStateBridge implements DriverGuiExtension, GuiIterator.Extension {
   public UiStateBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method100().method11();
   }

   @CallbackJS("setWindowState")
   public static void method2(String text0, Double value1, Double value2, Double value3, Double value4, Boolean flag5, Boolean flag6) {
      Client.method109().method100().method4(text0, value1, value2, value3, value4, flag5, flag6);
   }

   @CallbackJS("setSortState")
   public static void method3(String text0, String text1) {
      Client.method109().method100().method5(text0, text1);
   }

   @CallbackJS("setQuickNavigationState")
   public static void method4(JsonObject json0) {
      Client.method109().method100().method6(json0);
   }
}
