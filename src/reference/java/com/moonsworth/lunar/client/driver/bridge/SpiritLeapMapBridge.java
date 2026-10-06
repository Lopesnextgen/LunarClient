package com.moonsworth.lunar.client.driver.bridge;

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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SpiritLeapMap;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.SpiritLeapGuiContainer;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockSpiritLeapDebug;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class SpiritLeapMapBridge implements DriverGuiExtension {
   public SpiritLeapMapBridge() {
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return this.provide();
   }

   @Override
   public JsonElement provide() {
      SkyblockSpiritLeapDebug skyblockspiritleapdebug1 = method10();
      return skyblockspiritleapdebug1 != null ? method8(skyblockspiritleapdebug1.method15().method1()) : method8(Ref.method4().method40().method82().method95().method17().method1());
   }

   @CallbackJS("setMap")
   public static void method2(Nameplate nameplate0) {
      method9().method4(nameplate0);
   }

   @CallbackJS("sendButtonPress")
   public static void method3(String text0) {
      SkyblockSpiritLeapDebug skyblockspiritleapdebug1 = method10();
      if (skyblockspiritleapdebug1 != null) {
         skyblockspiritleapdebug1.method5(text0);
      } else {
         Ref.method4().method40().method82().method95().method6(text0);
      }
   }

   @CallbackJS("sendMapPress")
   public static void method4() {
      method9().method2();
   }

   @CallbackJS("settings")
   public static void method5() {
      Ref.method7().bridge$closeScreen();
      FeatureSettingsScreen bridge7iterator0 = new FeatureSettingsScreen(Ref.method3().bridge$getCurrentScreen());
      Ref.method3().bridge$displayScreen(Bridge.method8().method18(bridge7iterator0));
      ModMenuWidget calculator2iterator61 = bridge7iterator0.method10();
      FeatureSettingsWidget calculator2iterator32 = new FeatureSettingsWidget(calculator2iterator61, Ref.method4().method40().method82().method95());
      calculator2iterator61.method2(calculator2iterator32);
   }

   @CallbackJS("close")
   public static void close() {
      Ref.method7().bridge$closeScreen();
   }

   @CallbackJS("showMap")
   public static void method6(boolean flag0) {
      method9().method3(flag0);
   }

   @CallbackJS("setDefault")
   public static void method7(String text0) {
      Ref.method4().method40().method82().method95().method15().HIHRRHRROIRRHIRCOIRCCCHHHOCIII(text0);
   }

   private static JsonElement method8(@Nullable DungeonStateTracker holograms2_50) {
      JsonObject json1 = new JsonObject();
      if (holograms2_50 == null) {
         return json1;
      }

      json1.addProperty("floor", holograms2_50.method46().getPrettyName());
      JsonArray array2 = new JsonArray();
      CosmeticManager holograms123 = Ref.method4().method53();

      for (DungeonPlayerTracker holograms4updater5 : holograms2_50.getPlayers()) {
         if (!holograms4updater5.method19() && holograms4updater5.getUuid() != null) {
            JsonObject json6 = new JsonObject();
            json6.addProperty("name", holograms4updater5.method20(false));
            json6.addProperty("uuid", holograms4updater5.getUuid().toString());
            json6.addProperty("class", holograms4updater5.method37() != null ? holograms4updater5.method37().name() : "none");
            json6.addProperty("level", holograms4updater5.method38());
            json6.addProperty("dead", holograms4updater5.isDead());
            Data data7 = (Data)holograms123.method63().get(holograms4updater5.getUuid());
            if (data7 != null && data7.method1()) {
               JsonObject json8 = new JsonObject();
               json8.addProperty("plusColor", data7.method9());
               json8.addProperty("logoColor", new Color(data7.method5(), data7.method6(), data7.method7()).getRGB());
               json6.add("lunarPlus", json8);
            }

            array2.add(json6);
         }
      }

      json1.add("players", array2);
      return json1;
   }

   private static SpiritLeapMap method9() {
      SkyblockSpiritLeapDebug skyblockspiritleapdebug0 = method10();
      return skyblockspiritleapdebug0 != null ? skyblockspiritleapdebug0.method16() : Ref.method4().method40().method82().method95().method19();
   }

   @Nullable
   private static SkyblockSpiritLeapDebug method10() {
      if (Ref.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 bridge5extension620) {
         return bridge5extension620.method2() instanceof SpiritLeapGuiContainer bridge7impl3 ? bridge7impl3.method5() : null;
      } else {
         return null;
      }
   }
}
