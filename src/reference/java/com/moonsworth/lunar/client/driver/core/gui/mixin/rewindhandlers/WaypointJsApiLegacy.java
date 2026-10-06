package com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.mod.render.waypoints.Waypoints;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class WaypointJsApiLegacy implements DriverGuiExtensionLegacy {
   @CallbackJS("requestColor")
   public static String method2() {
      ColorOption var0 = (ColorOption)((Data)OptionFactory.method8("color").method4(-1)).method31();
      var0.method17();
      var0.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(1.0F);
      com.moonsworth.lunar.client.config.option.OptionDataProvider var1 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)var0.method7(
         OptionTraits.field10
      );
      return var1 != null ? var1.provide().toString() : "";
   }

   @CallbackJS("showAddWaypoint")
   public static void method3() {
      Waypoints.method21();
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
