package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.moonsworth.lunar.bridge.Bridge3_28;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.account.skin.SkinUploadService;
import com.moonsworth.lunar.client.account.skin.SkinLoadException;
import com.moonsworth.lunar.client.account.skin.SavedSkin;
import com.moonsworth.lunar.client.account.skin.SkinType;
import com.moonsworth.lunar.client.cosmetics.OutfitManager;
import com.moonsworth.lunar.client.account.skin.SavedSkinManager;
import com.moonsworth.lunar.client.cosmetics.Outfit;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class SavedSkinBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method75().method15();
   }

   @Override
   public JsonElement provide() {
      ThreadModuleDump63.method4().method75().method21();
      return this.provide();
   }

   @Override
   public void method5(List<Path> var1) {
      if (var1.isEmpty()) {
         ThreadModuleDump63.method4()
            .method69()
            .method7(NotificationType.ERROR, ThreadModuleDump63.method4().method67().method2("popups", "skinDropEmpty", new Object[0]));
      } else if (var1.size() > 1) {
         ThreadModuleDump63.method4()
            .method69()
            .method7(NotificationType.ERROR, ThreadModuleDump63.method4().method67().method2("popups", "skinDropAmount", new Object[0]));
         ThreadModuleDump63.method4()
            .method69()
            .method7(NotificationType.ERROR, ThreadModuleDump63.method4().method67().method2("popups", "skinDropFileType", new Object[0]));
      } else {
         for (Path var3 : var1) {
            String var4 = var3.getFileName().toString();
            String var5 = "";
            int var6 = var4.lastIndexOf(46);
            if (var6 > 0 && var6 < var4.length() - 1) {
               var5 = var4.substring(var6 + 1).toLowerCase(Locale.ROOT);
            }

            if (!var5.equals("png") && !var5.equals("jpg") && !var5.equals("jpeg")) {
               ThreadModuleDump63.method4()
                  .method69()
                  .method7(NotificationType.ERROR, ThreadModuleDump63.method4().method67().method2("popups", "skinDropFileType", new Object[0]));
            } else {
               try {
                  byte[] var7 = Files.readAllBytes(var3);
                  SkinUploadService.method3(SkinType.CLASSIC, var7, false, var0 -> {
                     if (var0.has("skins")) {
                        JsonArray var1x = var0.getAsJsonArray("skins");
                        var1x.forEach(var0x -> {
                           if (var0x.isJsonObject()) {
                              JsonObject var1xx = var0x.getAsJsonObject();
                              String var2 = var1xx.get("url").getAsString();
                              SavedSkin var3x = new SavedSkin(var2, SkinType.CLASSIC, "New skin");
                              Client.method109().method75().method22(var3x);
                              if (!Client.method109().method75().method2().containsKey(var3x.getHash())) {
                                 ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerAdded", var3x.getName()));
                                 Client.method109().method75().method2().put(var3x.getHash(), var3x);
                              } else {
                                 ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerAlreadySaved"));
                              }

                              method8(var3x);
                              Client.method109().method75().method21();
                           }
                        });
                     }
                  }, var0 -> ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", var0)));
               } catch (IOException var8) {
                  ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", var8.getMessage()));
               }
            }
         }
      }
   }

   @CallbackJS("updateSkinType")
   public static void method3(String var0, String var1) {
      SkinType var2 = Arrays.stream(SkinType.values()).filter(var1x -> var1x.toString().equals(var1)).findFirst().orElse(null);
      if (var2 != null) {
         SavedSkin var3 = method7(var0);
         if (var3 != null) {
            var3.method2(var2);
            ThreadModuleDump63.method4().method75().method21();
         }
      }
   }

   @CallbackJS("setFavorite")
   public static void method4(String var0, Boolean var1) {
      SavedSkin var2 = method7(var0);
      if (var2 != null) {
         ThreadModuleDump63.method4().method75().method3(var2);
         ThreadModuleDump63.method4().method75().method21();
      }
   }

   @CallbackJS("apply")
   public static void method5(String var0) {
      SavedSkin var1 = method7(var0);
      SavedSkinManager var2 = ThreadModuleDump63.method4().method75();
      if (var1 != null && var1 != var2.method26()) {
         OutfitManager var3 = ThreadModuleDump63.method4().method55();
         if (var3.method17() != null) {
            Outfit var4 = var3.method17().method5();
            SkinUploadService.method1(
               var1,
               false,
               false,
               () -> {
                  method8(var1);
                  var4.method12(var1);
                  var4.method3();
                  ThreadModuleDump63.method4().method75().method21();
                  if (ThreadModuleDump63.method7() != null) {
                     ThreadModuleDump63.method7()
                        .bridge$setSkinLocation(
                           HologramsIterator2.method18(var2.method26().getHash(), var2.method26().getUrl()),
                           var2.method26().method2().toString().equals("classic") ? "default" : var2.method26().method2().toString()
                        );
                  }
               },
               var0x -> ThreadModuleDump63.method4().method69().method10(new com.moonsworth.lunar.client.ui.notification.Notification(NotificationType.ERROR, var0x))
            );
         }
      }
   }

   @CallbackJS("delete")
   public static void delete(String var0) {
      SavedSkin var1 = method7(var0);
      if (var1 != null) {
         SavedSkinManager var2 = Client.method109().method75();
         if (var2.method26() == var1) {
            SavedSkin var3 = var2.method11();
            SkinUploadService.method1(var3, false, false, () -> {
               method8(var3);
               Client.method109().method75().method19(var1);
            }, var0x -> ThreadModuleDump63.method4().method69().method3(var0x));
         } else {
            Client.method109().method75().method19(var1);
         }

         ThreadModuleDump63.method4().method75().method21();
      }
   }

   @CallbackJS("add")
   public static void add(String var0) {
      if (!var0.isEmpty()) {
         Set var1 = Client.method109().method75().method2().values().stream().map(SavedSkin::getUrl).collect(Collectors.toSet());
         if (!var0.startsWith("http://") && !var0.startsWith("https://")) {
            try {
               SavedSkin var2 = Client.method109().method75().method6(var0);
               if (var1.contains(var2.getUrl())) {
                  ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerAlreadySaved"));
               } else {
                  ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerAdded", var2.getName()));
               }
            } catch (SkinLoadException var3) {
               ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", var3.getMessage()));
               var3.printStackTrace();
            } catch (Exception var4) {
               ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerUnknownError"));
               var4.printStackTrace();
            }

            ThreadModuleDump63.method4().method75().method21();
         } else {
            SkinUploadService.method2(SkinType.CLASSIC, var0, false, true, () -> {
               method8(null);
               SavedSkin var1x = Client.method109().method75().method12("New skin");
               Client.method109().method75().method22(var1x);
               if (var1.contains(var1x.getUrl())) {
                  ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerAlreadySaved"));
               } else {
                  ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerAdded", var1x.getName()));
               }
            }, var0x -> ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", var0x)));
         }
      }
   }

   @CallbackJS("rename")
   public static void method6(String var0, String var1) {
      if (!var1.isEmpty()) {
         SavedSkin var2 = method7(var0);
         if (var2 != null) {
            var2.setName(var1);
            ThreadModuleDump63.method4().method75().method21();
         }
      }
   }

   @CallbackJS("save")
   public static void save() {
      ThreadModuleDump63.method4().method75().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      SavedSkinManager var0 = ThreadModuleDump63.method4().method75();
      if (var0.method28() != null && !var0.method28().equals(var0.method26().method2().toString())) {
         SkinUploadService.method1(
            var0.method26(),
            false,
            false,
            () -> method8(var0.method26()),
            var1 -> {
               ThreadModuleDump63.method4().method69().method10(new com.moonsworth.lunar.client.ui.notification.Notification(NotificationType.ERROR, var1));
               Arrays.stream(SkinType.values())
                  .filter(var1x -> var1x.toString().equals(var0.method28()))
                  .findFirst()
                  .ifPresent(var1x -> var0.method26().method2(var1x));
            }
         );
         if (ThreadModuleDump63.method7() != null) {
            ThreadModuleDump63.method7()
               .bridge$setSkinLocation(
                  HologramsIterator2.method18(var0.method26().getHash(), var0.method26().getUrl()),
                  var0.method26().method2().toString().equals("classic") ? "default" : var0.method26().method2().toString()
               );
         }
      }
   }

   private static SavedSkin method7(String var0) {
      for (SavedSkin var2 : ThreadModuleDump63.method4().method75().method2().values()) {
         if (var2.getHash().equals(var0)) {
            return var2;
         }
      }

      return null;
   }

   private static void method8(SavedSkin var0) {
      if (var0 != null) {
         ThreadModuleDump63.method4().method75().method22(var0);
         HologramsIterator2.method14()
            .bridge$setSkinLocation(
               HologramsIterator2.method18(var0.getHash(), var0.getUrl()), var0.method2().toString().equals("classic") ? "default" : var0.method2().toString()
            );
      }

      if (ThreadModuleDump63.MC_VERSION <= 32) {
         ThreadModuleDump63.method3().bridge$getProfileProperties().clear();
      }

      MinecraftSessionService var1 = ThreadModuleDump63.method3().bridge$getSessionService();
      ((Bridge3_28)var1).bridge$refresh(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile());
   }
}
