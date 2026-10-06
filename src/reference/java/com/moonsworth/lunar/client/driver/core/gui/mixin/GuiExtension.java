package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.ui.menu.ModMenuWidget;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager.Data;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.Bridge7Impl;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockSpiritLeapDebug;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class GuiExtension implements DriverGuiExtensionLegacy {
   @Nullable
   @Override
   public JsonElement method128() {
      return this.provide();
   }

   @Override
   public JsonElement provide() {
      SkyblockSpiritLeapDebug var1 = method10();
      return var1 != null ? method8(var1.method15().method1()) : method8(ThreadModuleDump63.method4().method40().method82().method95().method17().method1());
   }

   @CallbackJS("setMap")
   public static void method2(Nameplate var0) {
      method9().method4(var0);
   }

   @CallbackJS("sendButtonPress")
   public static void method3(String var0) {
      SkyblockSpiritLeapDebug var1 = method10();
      if (var1 != null) {
         var1.method5(var0);
      } else {
         ThreadModuleDump63.method4().method40().method82().method95().method6(var0);
      }
   }

   @CallbackJS("sendMapPress")
   public static void method4() {
      method9().method2();
   }

   @CallbackJS("settings")
   public static void method5() {
      ThreadModuleDump63.method7().bridge$closeScreen();
      FeatureSettingsScreen var0 = new FeatureSettingsScreen(ThreadModuleDump63.method3().bridge$getCurrentScreen());
      ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var0));
      ModMenuWidget var1 = var0.method10();
      FeatureSettingsWidget var2 = new FeatureSettingsWidget(var1, ThreadModuleDump63.method4().method40().method82().method95());
      var1.method2(var2);
   }

   @CallbackJS("close")
   public static void close() {
      ThreadModuleDump63.method7().bridge$closeScreen();
   }

   @CallbackJS("showMap")
   public static void method6(boolean var0) {
      method9().method3(var0);
   }

   @CallbackJS("setDefault")
   public static void method7(String var0) {
      ThreadModuleDump63.method4().method40().method82().method95().method15().method21(var0);
   }

   private static JsonElement method8(@Nullable Holograms2_5 var0) {
      JsonObject var1 = new JsonObject();
      if (var0 == null) {
         return var1;
      }

      var1.addProperty("floor", var0.method46().getPrettyName());
      JsonArray var2 = new JsonArray();
      CosmeticManager var3 = ThreadModuleDump63.method4().method53();

      for (Holograms4Updater var5 : var0.getPlayers()) {
         if (!var5.method19() && var5.getUuid() != null) {
            JsonObject var6 = new JsonObject();
            var6.addProperty("name", var5.method20(false));
            var6.addProperty("uuid", var5.getUuid().toString());
            var6.addProperty("class", var5.method37() != null ? var5.method37().name() : "none");
            var6.addProperty("level", var5.method38());
            var6.addProperty("dead", var5.isDead());
            Data var7 = (Data)var3.method63().get(var5.getUuid());
            if (var7 != null && var7.method1()) {
               JsonObject var8 = new JsonObject();
               var8.addProperty("plusColor", var7.method9());
               var8.addProperty("logoColor", new Color(var7.method5(), var7.method6(), var7.method7()).getRGB());
               var6.add("lunarPlus", var8);
            }

            var2.add(var6);
         }
      }

      var1.add("players", var2);
      return var1;
   }

   private static Holograms5 method9() {
      SkyblockSpiritLeapDebug var0 = method10();
      return var0 != null ? var0.method16() : ThreadModuleDump63.method4().method40().method82().method95().method19();
   }

   @Nullable
   private static SkyblockSpiritLeapDebug method10() {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var0) {
         return var0.method2() instanceof Bridge7Impl var3 ? var3.method5() : null;
      } else {
         return null;
      }
   }
}
