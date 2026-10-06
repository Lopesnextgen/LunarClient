package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.network.GameBatchEvent;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class AnalyticsDataProvider implements com.moonsworth.lunar.client.driver.DriverGuiExtension {
   public AnalyticsDataProvider() {
   }

   @CallbackJS("submit")
   public static void method1(String text, String text2) {
      Class clazz2 = (Class)GameBatchEvent.field4.get(text);
      if (clazz2 == null) {
         LunarLogger.method8("Analytics", "Unknown analytics event type received: %s", new Object[]{text});
      } else {
         Client.method109().method105().method2(MixinHelper7.getGson().fromJson(text2, clazz2));
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
