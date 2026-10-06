package com.moonsworth.lunar.client.driver.waypoint;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.mod.render.waypoints.Waypoints;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class WaypointJsApi implements DriverGuiExtension {
   public WaypointJsApi() {
   }

   @CallbackJS("requestColor")
   public static String method2() {
      ColorOption lightingextension42220 = (ColorOption)((Data)OptionFactory.method8("color").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1)).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      lightingextension42220.method17();
      lightingextension42220.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(1.0F);
      com.moonsworth.lunar.client.config.option.OptionDataProvider guiextension1 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)lightingextension42220.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
         OptionTraits.field10
      );
      return guiextension1 != null ? guiextension1.provide().toString() : "";
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
