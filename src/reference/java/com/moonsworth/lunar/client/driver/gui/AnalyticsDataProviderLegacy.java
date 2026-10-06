package com.moonsworth.lunar.client.driver.gui;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.network.MixinHelper62;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class AnalyticsDataProviderLegacy implements com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy {
   @CallbackJS("submit")
   public static void method1(String text, String text2) {
      Class var2 = (Class)MixinHelper62.field4.get(text);
      if (var2 == null) {
         Slayer.method8("Analytics", "Unknown analytics event type received: %s", new Object[]{text});
      } else {
         Client.method109().method105().method2(MixinHelper7.getGson().fromJson(text2, var2));
      }
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      return null;
   }
}
