package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuHomeScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuTheme;
import com.moonsworth.lunar.client.ui.mainmenu.BedrockTheme;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.ui.mainmenu.SeasonalTheme;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public class HomeThemeBridge implements DriverGuiExtension, Translatable {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create(
      Ref.MC_VERSION >= 6 ? "entity.firework_rocket.blast" : (Ref.MC_VERSION == 5 ? "entity.firework.blast" : "fireworks.blast")
   );
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create(Ref.MC_VERSION >= 5 ? "entity.villager.hurt" : "mob.villager.hit");
   private static final int field3;
   private static final ResourceLocationBridge field4 = ResourceLocationBridge.create("lunar", "sound/transition.ogg");
   private static final MainMenuHomeScreen field5 = new MainMenuHomeScreen();

   public HomeThemeBridge() {
   }

   @Override
   public JsonElement provide() {
      MainMenuHomeScreen.method3().HHIHOOIHCOHOIRORCHICOCHCORROCR();
      JsonObject json1 = new JsonObject();
      JsonArray array2 = new JsonArray();
      List list3 = MainMenuHomeScreen.field21
         .stream()
         .filter(arg0 -> arg0.method2() && !(arg0.method3() instanceof BedrockTheme))
         .map(SeasonalTheme::method3)
         .collect(Collectors.toList());
      list3.add(0, MainMenuHomeScreen.field22);
      String text4 = MainMenuThemeManager.method12() == null ? MainMenuThemeManager.method10() : MainMenuThemeManager.method12().name();

      for (MainMenuTheme highlight3extension6 : list3) {
         JsonObject json7 = new JsonObject();
         json7.addProperty("id", highlight3extension6.name().toLowerCase());
         json7.addProperty("name", this.OHROCHICOIOICHOCRROORRCIIICIHO(highlight3extension6.name(), new Object[0]));
         json7.addProperty("splash", highlight3extension6.method2().bridge$getPath());
         json7.addProperty("isSelected", text4 != null && text4.equals(highlight3extension6.name()));
         JsonArray array8 = new JsonArray();

         for (String text10 : highlight3extension6.additions()) {
            array8.add(text10);
         }

         json7.add("additions", array8);
         array2.add(json7);
      }

      json1.add("themes", array2);
      json1.addProperty("theme", text4);
      JsonArray array11 = new JsonArray();

      for (String text13 : field5.method10()) {
         array11.add(text13);
      }

      json1.add("additions", array11);
      json1.addProperty("year", field3);
      json1.addProperty("balloonHighscore", MainMenuThemeManager.method13());
      return json1;
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return this.provide();
   }

   @CallbackJS("selectTheme")
   public static void method2(String text0) {
      MainMenuTheme highlight3extension1 = MainMenuHomeScreen.field21
         .stream()
         .filter(SeasonalTheme::method2)
         .filter(arg1x -> arg1x.method3().name().equals(text0))
         .<MainMenuTheme>map(SeasonalTheme::method3)
         .findAny()
         .orElse(MainMenuHomeScreen.field22);
      if (!highlight3extension1.name().equals(MainMenuThemeManager.method10())) {
         MainMenuThemeManager.method6(highlight3extension1.name());
         MainMenuHomeScreen.method3().method1();
         com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground.method9().init();
         LunarLogger.method4("WebOSR", "Loading theme: " + text0, new Object[0]);
         Client.method109().method23();
      }
   }

   @CallbackJS("selectDefaultTheme")
   public static void method3() {
      MainMenuThemeManager.method6("");
      MainMenuHomeScreen.method3().method1();
      com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground.method9().init();
      LunarLogger.method4("WebOSR", "Loading default theme", new Object[0]);
   }

   @CallbackJS("playSound")
   public static void method4() {
      Ref.method3().bridge$getSoundHandler().method1(field4);
   }

   @CallbackJS("playSuccessSound")
   public static void method5() {
      Ref.method3().bridge$getSoundHandler().method1(field1);
   }

   @CallbackJS("playFailSound")
   public static void method7() {
      Ref.method3().bridge$getSoundHandler().method1(field2);
   }

   @CallbackJS("openWrapped")
   public static void method8() {
      if (!Ref.method4().method43().method23()) {
         AccountBridge.method2();
      } else {
         ((AssetServerClient)Ref.method5().get()).method115().method1("GAME_WRAPPED_URL", arg0 -> {
            if (arg0 != null) {
               String text1 = Client.method109().method64().IORHHHROCRRHORHRCHCCHHIHICCRCO().getOrDefault("wrapped", "https://wrapped.lunarclient.com");
               String text2 = URLEncoder.encode(arg0, StandardCharsets.UTF_8);
               String text3 = URLEncoder.encode(Ref.method3().bridge$getSession().bridge$getProfile().getId().toString(), StandardCharsets.UTF_8);
               String text4 = URLEncoder.encode(Ref.method3().bridge$getSession().bridge$getUsername(), StandardCharsets.UTF_8);
               String text5 = text1 + "?token=" + text2 + "&uuid=" + text3 + "&username=" + text4;
               if (BrowserUtils.method7(text5, Initiator.INITIATOR_HOME_BUTTON)) {
                  Ref.method4().method69().method3(String.format("Opened Your %s in browser", field3));
               } else {
                  ClipboardUtils.method2(text5);
                  Ref.method4().method69().method3(String.format("Copied Your %s to clipboard", field3));
               }
            }
         });
      }
   }

   @CallbackJS("openAdvent")
   public static void method9() {
      if (BrowserUtils.method8("https://advent.lunarclient.com/calendar", Initiator.INITIATOR_HOME_CTA, true)) {
         Ref.method4()
            .method69()
            .method6(NotificationType.INFO, "Opened Advent Calendar", "Complete the Advent Calendar in the Lunar Client Launcher!");
      } else {
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "Could not open Calendar", "Make sure the Lunar Client launcher is running and up-to-date");
      }
   }

   @CallbackJS("setBalloonHighscore")
   public static void method9(Integer number0) {
      MainMenuThemeManager.method13(number0);
      MainMenuHomeScreen.method3().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   @CallbackJS("openStoreCoins")
   public static void method10() {
      BrowserUtils.method7(
         "https://store.lunarclient.com/category/coins?utm_source=client-game&utm_medium=home&utm_campaign=lead", Initiator.INITIATOR_HOME_BUTTON
      );
   }

   public String getLanguagePath() {
      return "main_menu.themes";
   }

   static {
      LocalDate localdate0 = LocalDate.now();
      Month month1 = localdate0.getMonth();
      field3 = localdate0.getYear() + (month1.getValue() <= Month.FEBRUARY.getValue() ? -1 : 0);
   }
}
