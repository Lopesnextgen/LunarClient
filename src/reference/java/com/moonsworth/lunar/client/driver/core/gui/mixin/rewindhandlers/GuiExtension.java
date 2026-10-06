package com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiLoader;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class GuiExtension implements DriverGuiExtensionLegacy {
   @CallbackJS("createWaypointGroup")
   public static void method1(String var0, String var1, Boolean var2, Boolean var3, Boolean var4, Boolean var5, Boolean var6, String var7) {
      try {
         PhosphorIconLegacy var8 = PhosphorIconLegacy.valueOf(var1);
         GuiHandler var9 = GuiHandler.method13().method2(var0).method3(WaypointStore.method19()).method5(100).method11();
         var9.method20(var2);
         var9.method19(new GuiLoader(var4, var8, var5, var3, var6));
         var9.method23().method4().method10(var7);
         Client.method109().method48().method25(var9);
      } catch (Exception var10) {
         Slayer.method7("Error creating waypoint group: " + var10.getMessage(), new Object[0]);
      }
   }

   @CallbackJS("deleteWaypointGroup")
   public static void method2(UUID var0, boolean var1) {
      Client.method109().method48().method26(var0, var1);
   }

   @CallbackJS("updateWaypointGroup")
   public static void method3(UUID var0, String var1, String var2, Boolean var3, Boolean var4, Boolean var5, Boolean var6, Boolean var7, String var8) {
      try {
         PhosphorIconLegacy var9 = PhosphorIconLegacy.valueOf(var2);
         Client.method109().method48().method27(var0).ifPresent(var8x -> {
            var8x.setName(var1);
            var8x.method20(var3);
            var8x.method19(new GuiLoader(var5, var9, var6, var4, var7));
            var8x.method23().method4().method10(var8);
            Client.method109().method48().method20();
            Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         });
      } catch (Exception var10) {
         Slayer.method7("Error updating waypoint group: " + var10.getMessage(), new Object[0]);
      }
   }

   @CallbackJS("setWaypointGroup")
   public static void method4(String var0, String var1, String var2, UUID var3) {
      method5(var0, var1, var2).ifPresent(var1x -> {
         Client.method109().method48().method27(var3).ifPresent(var1x::method32);
         Client.method109().method48().method20();
         Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      });
   }

   private static Optional<GuiHandler2> method5(String var0, String var1, String var2) {
      return Client.method109().method48().method24(var0, var1, var2);
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
