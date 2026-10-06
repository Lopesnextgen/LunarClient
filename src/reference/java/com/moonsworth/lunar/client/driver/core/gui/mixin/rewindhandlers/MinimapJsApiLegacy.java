package com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class MinimapJsApiLegacy implements DriverGuiExtensionLegacy {
   public static int field1;
   public static int field2;
   private static float field3 = 3.0F;
   private static float field4 = 0.0F;
   private static float field5 = 0.0F;

   @CallbackJS("setMapDimensions")
   public static void method1(Integer var0, Integer var1) {
      field1 = var0;
      field2 = var1;
   }

   @CallbackJS("mapDrag")
   public static void method2(Integer var0, Integer var1) {
      if (var0 != null) {
         field4 = field4 + var0.intValue();
      }

      if (var1 != null) {
         field5 = field5 + var1.intValue();
      }
   }

   @CallbackJS("mapZoomBy")
   public static void method3(Integer var0) {
      float var1 = 0.25F;
      if (var0 != null && var0 > 0) {
         field3 -= var1;
      } else if (var0 != null && var0 < 0) {
         field3 += var1;
      }

      if (field3 < -1.75F) {
         field3 = -1.75F;
      }

      if (field3 > 20.0F) {
         field3 = 20.0F;
      }
   }

   public static float getZoom() {
      return field3;
   }

   public static float method4() {
      return field4;
   }

   public static float method5() {
      return field5;
   }

   @CallbackJS("resetMapInteraction")
   public static void method7() {
      method8(3.0F);
   }

   @CallbackJS("resetMapInteractionZoom")
   public static void method7(Float var0) {
      method8(var0 == null ? 3.0F : var0);
   }

   private static void method8(float var0) {
      field4 = 0.0F;
      field5 = 0.0F;
      field3 = var0;
   }

   @CallbackJS("showMap")
   public static String method9() {
      Minimap var0 = ThreadModuleDump63.method4().method40().method94();
      return var0.isEnabled() && ThreadModuleDump63.method8() != null ? "true" : "false";
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
