package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSoundPlay;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class HomeRadioBridgeLegacy implements DriverGuiExtensionLegacy {
   private static boolean field1 = false;
   private static final ImmutableMap<ResourceLocationBridge, Long> field2 = ImmutableMap.of(
      ResourceLocationBridge.create("lunar", "sound/jingle_through_the_snow.ogg"),
      166L,
      ResourceLocationBridge.create("lunar", "sound/twelve_days_of_christmas.ogg"),
      237L,
      ResourceLocationBridge.create("lunar", "sound/we_wish_you.ogg"),
      119L,
      ResourceLocationBridge.create("lunar", "sound/jingle_bells.ogg"),
      139L,
      ResourceLocationBridge.create("lunar", "sound/silent_night.ogg"),
      114L
   );
   private static final Map<String, String> field3 = ImmutableMap.of(
      "sound/jingle_through_the_snow.ogg",
      "Jingle Through The Snow",
      "sound/twelve_days_of_christmas.ogg",
      "Twelve Days of Christmas",
      "sound/we_wish_you.ogg",
      "We Wish You",
      "sound/jingle_bells.ogg",
      "Jingle Bells",
      "sound/silent_night.ogg",
      "Silent Night"
   );
   private static String field4;
   private static int field5 = -1;
   private static long field6 = 0L;
   private static boolean field7;
   private static boolean field8;
   private static boolean field9;

   public HomeRadioBridgeLegacy() {
      field7 = true;
      field8 = true;
      field4 = "None";
      field1 = false;
      field9 = GeneralSettings.field77;
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("currentSong", field4);
      var1.addProperty("musicPlaying", field9);
      var1.addProperty("volume", getVolume() * 10.0F);
      return var1;
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return this.provide();
   }

   private static float getVolume() {
      return GeneralSettings.field76 == null ? 0.5F : GeneralSettings.field76 / 10.0F;
   }

   @CallbackJS("mute")
   public static void method2() {
      field9 = !field9;
      GeneralSettings.field77 = field9;
      if (field9) {
         ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(getVolume());
      } else {
         ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(0.0F);
      }
   }

   @CallbackJS("setVolume")
   public static void method3(Float var0) {
      GeneralSettings.field76 = var0;
      ThreadModuleDump63.method3().bridge$submit(() -> {
         if (field9) {
            ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(getVolume());
         } else {
            ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(0.0F);
         }
      });
   }

   @CallbackJS("close")
   public static void onClose() {
      ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$stopPlayingLunarMusic();
      field1 = false;
      field5 = -1;
      field6 = 0L;
      field7 = true;
      field8 = true;
   }

   private static void method4(boolean var0) {
      if (method8()) {
         field9 = false;
      } else {
         try {
            if (!field1) {
               if (field5 == -1) {
                  field5 = ThreadModuleDump48.field24.nextInt(5);
               }

               if (var0) {
                  try {
                     ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$stopPlayingLunarMusic();
                  } catch (Exception var2) {
                  }

                  field5++;
               }

               ResourceLocationBridge var1 = (ResourceLocationBridge)field2.keySet().asList().get(field5 % 5);
               field6 = System.currentTimeMillis() + (Long)field2.get(var1) * 1000L;
               field4 = field3.get(var1.bridge$getPath());
               ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$playLunarMusic(var1);
               if (field9) {
                  ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(getVolume());
               } else {
                  ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(0.0F);
               }

               field1 = true;
            }
         } catch (Exception var3) {
         }
      }
   }

   private static void method5(EventEverySecond var0) {
      if (method8()) {
         if (field1) {
            onClose();
         }
      } else {
         if (field5 != -1 && System.currentTimeMillis() >= field6) {
            field8 = true;
            field7 = true;
            field1 = false;
         }

         if (field7) {
            method4(field8);
            field8 = false;
            field7 = false;
         }
      }
   }

   private static void method6(EventSoundPlay var0) {
      if (!method8()) {
         if (var0.getCategory().equals("music")) {
            if (field5 == -1) {
               field8 = true;
               field7 = true;
            }

            var0.setCancelled(true);
         }
      }
   }

   private static void method7(ScreenChangeEvent var0) {
      if (field9) {
         if (DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field4) {
            try {
               ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(0.0F);
            } catch (Exception var2) {
            }
         } else {
            ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setLunarMusicVolume(getVolume());
         }
      }
   }

   private static boolean method8() {
      return DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field4 || !MainMenuThemeManager.method12().additions().contains("radio");
   }

   static {
      ClientEventBus.method29().method2(EventEverySecond.class, HomeRadioBridgeLegacy::method5);
      ClientEventBus.method29().method2(EventSoundPlay.class, HomeRadioBridgeLegacy::method6);
      ClientEventBus.method29().method2(ScreenChangeEvent.class, HomeRadioBridgeLegacy::method7);
   }
}
