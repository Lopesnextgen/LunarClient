package com.moonsworth.lunar.client.driver.waypoint;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class WaypointGroupBridge implements DriverGuiExtension {
   public WaypointGroupBridge() {
   }

   @CallbackJS("createWaypointGroup")
   public static void method1(String text0, String text1, Boolean flag2, Boolean flag3, Boolean flag4, Boolean flag5, Boolean flag6, String text7) {
      try {
         PhosphorIcon markerstype8 = PhosphorIcon.valueOf(text1);
         GuiHandler guihandler9 = GuiHandler.method13().method2(text0).method3(WaypointStore.method19()).method5(100).method11();
         guihandler9.method20(flag2);
         guihandler9.method19(new GuiLoader(flag4, markerstype8, flag5, flag3, flag6));
         guihandler9.method23().method4().method10(text7);
         Client.method109().method48().method25(guihandler9);
      } catch (Exception exception10) {
         LunarLogger.method7("Error creating waypoint group: " + exception10.getMessage(), new Object[0]);
      }
   }

   @CallbackJS("deleteWaypointGroup")
   public static void method2(UUID uuid0, boolean flag1) {
      Client.method109().method48().method26(uuid0, flag1);
   }

   @CallbackJS("updateWaypointGroup")
   public static void method3(UUID uuid0, String text1, String text2, Boolean flag3, Boolean flag4, Boolean flag5, Boolean flag6, Boolean flag7, String text8) {
      try {
         PhosphorIcon markerstype9 = PhosphorIcon.valueOf(text2);
         Client.method109().method48().method27(uuid0).ifPresent(arg8x -> {
            arg8x.setName(text1);
            arg8x.method20(flag3);
            arg8x.method19(new GuiLoader(flag5, markerstype9, flag6, flag4, flag7));
            arg8x.method23().method4().method10(text8);
            Client.method109().method48().method20();
            Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         });
      } catch (Exception exception10) {
         LunarLogger.method7("Error updating waypoint group: " + exception10.getMessage(), new Object[0]);
      }
   }

   @CallbackJS("setWaypointGroup")
   public static void method4(String text0, String text1, String text2, UUID uuid3) {
      method5(text0, text1, text2).ifPresent(arg1x -> {
         Client.method109().method48().method27(uuid3).ifPresent(arg1x::method32);
         Client.method109().method48().method20();
         Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      });
   }

   private static Optional<Waypoint> method5(String text0, String text1, String text2) {
      return Client.method109().method48().method24(text0, text1, text2);
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
