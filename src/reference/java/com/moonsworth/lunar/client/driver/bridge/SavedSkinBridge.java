package com.moonsworth.lunar.client.driver.bridge;

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
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class SavedSkinBridge implements DriverGuiExtension, GuiIterator.Extension {
   public SavedSkinBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method75().method15();
   }

   @Override
   public JsonElement provide() {
      Ref.method4().method75().method21();
      return this.RIROCIRRICRRHOOICOROOCHIOOIHHR();
   }

   @Override
   public void method5(List<Path> list1) {
      if (list1.isEmpty()) {
         Ref.method4()
            .method69()
            .method7(NotificationType.ERROR, Ref.method4().method67().method2("popups", "skinDropEmpty", new Object[0]));
      } else if (list1.size() > 1) {
         Ref.method4()
            .method69()
            .method7(NotificationType.ERROR, Ref.method4().method67().method2("popups", "skinDropAmount", new Object[0]));
         Ref.method4()
            .method69()
            .method7(NotificationType.ERROR, Ref.method4().method67().method2("popups", "skinDropFileType", new Object[0]));
      } else {
         for (Path path3 : list1) {
            String text4 = path3.getFileName().toString();
            String text5 = "";
            int index6 = text4.lastIndexOf(46);
            if (index6 > 0 && index6 < text4.length() - 1) {
               text5 = text4.substring(index6 + 1).toLowerCase(Locale.ROOT);
            }

            if (!text5.equals("png") && !text5.equals("jpg") && !text5.equals("jpeg")) {
               Ref.method4()
                  .method69()
                  .method7(NotificationType.ERROR, Ref.method4().method67().method2("popups", "skinDropFileType", new Object[0]));
            } else {
               try {
                  byte[] items7 = Files.readAllBytes(path3);
                  SkinUploadService.method3(SkinType.CLASSIC, items7, false, arg0 -> {
                     if (arg0.has("skins")) {
                        JsonArray array1x = arg0.getAsJsonArray("skins");
                        array1x.forEach(arg0x -> {
                           if (arg0x.isJsonObject()) {
                              JsonObject json1xx = arg0x.getAsJsonObject();
                              String text2 = json1xx.get("url").getAsString();
                              SavedSkin gui2handler3x = new SavedSkin(text2, SkinType.CLASSIC, "New skin");
                              Client.method109().method75().method22(gui2handler3x);
                              if (!Client.method109().method75().IORHHHROCRRHORHRCHCCHHIHICCRCO().containsKey(gui2handler3x.getHash())) {
                                 Ref.method4().method69().method3(NotificationManager.method15("skinChangerAdded", new Object[]{gui2handler3x.getName()}));
                                 Client.method109().method75().IORHHHROCRRHORHRCHCCHHIHICCRCO().put(gui2handler3x.getHash(), gui2handler3x);
                              } else {
                                 Ref.method4().method69().method3(NotificationManager.method15("skinChangerAlreadySaved", new Object[0]));
                              }

                              method8(gui2handler3x);
                              Client.method109().method75().method21();
                           }
                        });
                     }
                  }, arg0 -> Ref.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", new Object[]{arg0})));
               } catch (IOException exception8) {
                  Ref.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", new Object[]{exception8.getMessage()}));
               }
            }
         }
      }
   }

   @CallbackJS("updateSkinType")
   public static void method3(String text0, String text1) {
      SkinType gui2type2 = Arrays.stream(SkinType.values()).filter(arg1x -> arg1x.toString().equals(text1)).findFirst().orElse(null);
      if (gui2type2 != null) {
         SavedSkin gui2handler3 = method7(text0);
         if (gui2handler3 != null) {
            gui2handler3.method2(gui2type2);
            Ref.method4().method75().method21();
         }
      }
   }

   @CallbackJS("setFavorite")
   public static void method4(String text0, Boolean flag1) {
      SavedSkin gui2handler2 = method7(text0);
      if (gui2handler2 != null) {
         Ref.method4().method75().method3(gui2handler2);
         Ref.method4().method75().method21();
      }
   }

   @CallbackJS("apply")
   public static void method5(String text0) {
      SavedSkin gui2handler1 = method7(text0);
      SavedSkinManager holograms172 = Ref.method4().method75();
      if (gui2handler1 != null && gui2handler1 != holograms172.method26()) {
         OutfitManager foghandler263 = Ref.method4().method55();
         if (foghandler263.method17() != null) {
            Outfit gui2iterator4 = foghandler263.method17().method5();
            SkinUploadService.method1(
               gui2handler1,
               false,
               false,
               () -> {
                  method8(gui2handler1);
                  gui2iterator4.method12(gui2handler1);
                  gui2iterator4.method3();
                  Ref.method4().method75().method21();
                  if (Ref.method7() != null) {
                     Ref.method7()
                        .bridge$setSkinLocation(
                           HologramsIterator2.method18(holograms172.method26().getHash(), holograms172.method26().getUrl()),
                           holograms172.method26().method2().toString().equals("classic") ? "default" : holograms172.method26().method2().toString()
                        );
                  }
               },
               arg0x -> Ref.method4().method69().method10(new com.moonsworth.lunar.client.ui.notification.Notification(NotificationType.ERROR, arg0x))
            );
         }
      }
   }

   @CallbackJS("delete")
   public static void delete(String text0) {
      SavedSkin gui2handler1 = method7(text0);
      if (gui2handler1 != null) {
         SavedSkinManager holograms172 = Client.method109().method75();
         if (holograms172.method26() == gui2handler1) {
            SavedSkin gui2handler3 = holograms172.method11();
            SkinUploadService.method1(gui2handler3, false, false, () -> {
               method8(gui2handler3);
               Client.method109().method75().method19(gui2handler1);
            }, arg0x -> Ref.method4().method69().method3(arg0x));
         } else {
            Client.method109().method75().method19(gui2handler1);
         }

         Ref.method4().method75().method21();
      }
   }

   @CallbackJS("add")
   public static void add(String text0) {
      if (!text0.isEmpty()) {
         Set set1 = Client.method109().method75().IORHHHROCRRHORHRCHCCHHIHICCRCO().values().stream().map(SavedSkin::getUrl).collect(Collectors.toSet());
         if (!text0.startsWith("http://") && !text0.startsWith("https://")) {
            try {
               SavedSkin gui2handler2 = Client.method109().method75().method6(text0);
               if (set1.contains(gui2handler2.getUrl())) {
                  Ref.method4().method69().method3(NotificationManager.method15("skinChangerAlreadySaved", new Object[0]));
               } else {
                  Ref.method4().method69().method3(NotificationManager.method15("skinChangerAdded", new Object[]{gui2handler2.getName()}));
               }
            } catch (SkinLoadException fishingexception3) {
               Ref.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", new Object[]{fishingexception3.getMessage()}));
               fishingexception3.printStackTrace();
            } catch (Exception exception4) {
               Ref.method4().method69().method3(NotificationManager.method15("skinChangerUnknownError", new Object[0]));
               exception4.printStackTrace();
            }

            Ref.method4().method75().method21();
         } else {
            SkinUploadService.method2(SkinType.CLASSIC, text0, false, true, () -> {
               method8(null);
               SavedSkin gui2handler1x = Client.method109().method75().method12("New skin");
               Client.method109().method75().method22(gui2handler1x);
               if (set1.contains(gui2handler1x.getUrl())) {
                  Ref.method4().method69().method3(NotificationManager.method15("skinChangerAlreadySaved", new Object[0]));
               } else {
                  Ref.method4().method69().method3(NotificationManager.method15("skinChangerAdded", new Object[]{gui2handler1x.getName()}));
               }
            }, arg0x -> Ref.method4().method69().method3(NotificationManager.method15("skinChangerCouldntAdd", new Object[]{arg0x})));
         }
      }
   }

   @CallbackJS("rename")
   public static void method6(String text0, String text1) {
      if (!text1.isEmpty()) {
         SavedSkin gui2handler2 = method7(text0);
         if (gui2handler2 != null) {
            gui2handler2.setName(text1);
            Ref.method4().method75().method21();
         }
      }
   }

   @CallbackJS("save")
   public static void save() {
      Ref.method4().method75().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      SavedSkinManager holograms170 = Ref.method4().method75();
      if (holograms170.method28() != null && !holograms170.method28().equals(holograms170.method26().method2().toString())) {
         SkinUploadService.method1(
            holograms170.method26(),
            false,
            false,
            () -> method8(holograms170.method26()),
            arg1 -> {
               Ref.method4().method69().method10(new com.moonsworth.lunar.client.ui.notification.Notification(NotificationType.ERROR, arg1));
               Arrays.stream(SkinType.values())
                  .filter(arg1x -> arg1x.toString().equals(holograms170.method28()))
                  .findFirst()
                  .ifPresent(arg1x -> holograms170.method26().method2(arg1x));
            }
         );
         if (Ref.method7() != null) {
            Ref.method7()
               .bridge$setSkinLocation(
                  HologramsIterator2.method18(holograms170.method26().getHash(), holograms170.method26().getUrl()),
                  holograms170.method26().method2().toString().equals("classic") ? "default" : holograms170.method26().method2().toString()
               );
         }
      }
   }

   private static SavedSkin method7(String text0) {
      for (SavedSkin gui2handler2 : Ref.method4().method75().IORHHHROCRRHORHRCHCCHHIHICCRCO().values()) {
         if (gui2handler2.getHash().equals(text0)) {
            return gui2handler2;
         }
      }

      return null;
   }

   private static void method8(SavedSkin gui2handler0) {
      if (gui2handler0 != null) {
         Ref.method4().method75().method22(gui2handler0);
         HologramsIterator2.method14()
            .bridge$setSkinLocation(
               HologramsIterator2.method18(gui2handler0.getHash(), gui2handler0.getUrl()), gui2handler0.method2().toString().equals("classic") ? "default" : gui2handler0.method2().toString()
            );
      }

      if (Ref.MC_VERSION <= 32) {
         Ref.method3().bridge$getProfileProperties().clear();
      }

      MinecraftSessionService minecraftsessionservice1 = Ref.method3().bridge$getSessionService();
      ((Bridge3_28)minecraftsessionservice1).bridge$refresh(Ref.method3().bridge$getSession().bridge$getProfile());
   }
}
