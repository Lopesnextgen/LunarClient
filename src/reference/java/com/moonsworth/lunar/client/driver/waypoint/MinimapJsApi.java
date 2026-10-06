package com.moonsworth.lunar.client.driver.waypoint;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.mod.render.minimap.MinimapMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class MinimapJsApi implements DriverGuiExtension {
   public static int field1;
   public static int field2;
   private static float field3 = 3.0F;
   private static float field4 = 0.0F;
   private static float field5 = 0.0F;

   public MinimapJsApi() {
   }

   @CallbackJS("setMapDimensions")
   public static void method1(Integer number0, Integer number1) {
      field1 = number0;
      field2 = number1;
   }

   @CallbackJS("mapDrag")
   public static void method2(Integer number0, Integer number1) {
      if (number0 != null) {
         field4 = field4 + number0.intValue();
      }

      if (number1 != null) {
         field5 = field5 + number1.intValue();
      }
   }

   @CallbackJS("mapZoomBy")
   public static void method3(Integer number0) {
      float value1 = 0.25F;
      if (number0 != null && number0 > 0) {
         field3 -= value1;
      } else if (number0 != null && number0 < 0) {
         field3 += value1;
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
   public static void method7(Float value0) {
      method8(value0 == null ? 3.0F : value0);
   }

   private static void method8(float value0) {
      field4 = 0.0F;
      field5 = 0.0F;
      field3 = value0;
   }

   @CallbackJS("showMap")
   public static String method9() {
      MinimapMod minimap0 = Ref.method4().method40().method94();
      return minimap0.isEnabled() && Ref.method8() != null ? "true" : "false";
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
