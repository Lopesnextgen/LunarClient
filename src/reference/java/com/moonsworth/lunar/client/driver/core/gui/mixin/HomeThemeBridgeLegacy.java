package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuHomeScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuTheme;
import com.moonsworth.lunar.client.ui.mainmenu.BedrockTheme;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.ui.mainmenu.SeasonalTheme;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public class HomeThemeBridgeLegacy implements DriverGuiExtensionLegacy, Calculator2 {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create(
      ThreadModuleDump63.MC_VERSION >= 6 ? "entity.firework_rocket.blast" : (ThreadModuleDump63.MC_VERSION == 5 ? "entity.firework.blast" : "fireworks.blast")
   );
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create(ThreadModuleDump63.MC_VERSION >= 5 ? "entity.villager.hurt" : "mob.villager.hit");
   private static final int field3;
   private static final ResourceLocationBridge field4 = ResourceLocationBridge.create("lunar", "sound/transition.ogg");
   private static final MainMenuHomeScreen field5 = new MainMenuHomeScreen();

   @Override
   public JsonElement provide() {
      MainMenuHomeScreen.method3().method4();
      JsonObject var1 = new JsonObject();
      JsonArray var2 = new JsonArray();
      List var3 = MainMenuHomeScreen.field21
         .stream()
         .filter(var0 -> var0.method2() && !(var0.method3() instanceof BedrockTheme))
         .map(SeasonalTheme::method3)
         .collect(Collectors.toList());
      var3.add(0, MainMenuHomeScreen.field22);
      String var4 = MainMenuThemeManager.method12() == null ? MainMenuThemeManager.method10() : MainMenuThemeManager.method12().name();

      for (MainMenuTheme var6 : var3) {
         JsonObject var7 = new JsonObject();
         var7.addProperty("id", var6.name().toLowerCase());
         var7.addProperty("name", this.method1(var6.name(), new Object[0]));
         var7.addProperty("splash", var6.method2().bridge$getPath());
         var7.addProperty("isSelected", var4 != null && var4.equals(var6.name()));
         JsonArray var8 = new JsonArray();

         for (String var10 : var6.additions()) {
            var8.add(var10);
         }

         var7.add("additions", var8);
         var2.add(var7);
      }

      var1.add("themes", var2);
      var1.addProperty("theme", var4);
      JsonArray var11 = new JsonArray();

      for (String var13 : field5.method10()) {
         var11.add(var13);
      }

      var1.add("additions", var11);
      var1.addProperty("year", field3);
      var1.addProperty("balloonHighscore", MainMenuThemeManager.method13());
      return var1;
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return this.provide();
   }

   @CallbackJS("selectTheme")
   public static void method2(String var0) {
      MainMenuTheme var1 = MainMenuHomeScreen.field21
         .stream()
         .filter(SeasonalTheme::method2)
         .filter(var1x -> var1x.method3().name().equals(var0))
         .map(SeasonalTheme::method3)
         .findAny()
         .orElse(MainMenuHomeScreen.field22);
      if (!var1.name().equals(MainMenuThemeManager.method10())) {
         MainMenuThemeManager.method6(var1.name());
         MainMenuHomeScreen.method3().method1();
         com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground.method9().init();
         Slayer.method4("WebOSR", "Loading theme: " + var0, new Object[0]);
         Client.method109().method23();
      }
   }

   @CallbackJS("selectDefaultTheme")
   public static void method3() {
      MainMenuThemeManager.method6("");
      MainMenuHomeScreen.method3().method1();
      com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground.method9().init();
      Slayer.method4("WebOSR", "Loading default theme", new Object[0]);
   }

   @CallbackJS("playSound")
   public static void method4() {
      ThreadModuleDump63.method3().bridge$getSoundHandler().method1(field4);
   }

   @CallbackJS("playSuccessSound")
   public static void method5() {
      ThreadModuleDump63.method3().bridge$getSoundHandler().method1(field1);
   }

   @CallbackJS("playFailSound")
   public static void method7() {
      ThreadModuleDump63.method3().bridge$getSoundHandler().method1(field2);
   }

   @CallbackJS("openWrapped")
   public static void method8() {
      if (!ThreadModuleDump63.method4().method43().method23()) {
         AccountBridgeLegacy.method2();
      } else {
         ((EntityRenderer4)ThreadModuleDump63.method5().get()).method115().method1("GAME_WRAPPED_URL", var0 -> {
            if (var0 != null) {
               String var1 = Client.method109().method64().method3().getOrDefault("wrapped", "https://wrapped.lunarclient.com");
               String var2 = URLEncoder.encode(var0, StandardCharsets.UTF_8);
               String var3 = URLEncoder.encode(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId().toString(), StandardCharsets.UTF_8);
               String var4 = URLEncoder.encode(ThreadModuleDump63.method3().bridge$getSession().bridge$getUsername(), StandardCharsets.UTF_8);
               String var5 = var1 + "?token=" + var2 + "&uuid=" + var3 + "&username=" + var4;
               if (ThreadModuleDump61.method7(var5, Initiator.INITIATOR_HOME_BUTTON)) {
                  ThreadModuleDump63.method4().method69().method3(String.format("Opened Your %s in browser", field3));
               } else {
                  ThreadModuleDump68.setClipboardString(var5);
                  ThreadModuleDump63.method4().method69().method3(String.format("Copied Your %s to clipboard", field3));
               }
            }
         });
      }
   }

   @CallbackJS("openAdvent")
   public static void method9() {
      if (ThreadModuleDump61.method8("https://advent.lunarclient.com/calendar", Initiator.INITIATOR_HOME_CTA, true)) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.INFO, "Opened Advent Calendar", "Complete the Advent Calendar in the Lunar Client Launcher!");
      } else {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Could not open Calendar", "Make sure the Lunar Client launcher is running and up-to-date");
      }
   }

   @CallbackJS("setBalloonHighscore")
   public static void method9(Integer var0) {
      MainMenuThemeManager.method13(var0);
      MainMenuHomeScreen.method3().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   @CallbackJS("openStoreCoins")
   public static void method10() {
      ThreadModuleDump61.method7(
         "https://store.lunarclient.com/category/coins?utm_source=client-game&utm_medium=home&utm_campaign=lead", Initiator.INITIATOR_HOME_BUTTON
      );
   }

   public String getLanguagePath() {
      return "main_menu.themes";
   }

   static {
      LocalDate var0 = LocalDate.now();
      Month var1 = var0.getMonth();
      field3 = var0.getYear() + (var1.getValue() <= Month.FEBRUARY.getValue() ? -1 : 0);
   }
}
