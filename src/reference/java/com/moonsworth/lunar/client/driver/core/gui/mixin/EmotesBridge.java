package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGift;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftProvider;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerContextLegacy;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerSectionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.HashSet;
import java.util.Optional;

public class EmotesBridge implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   private static long field1 = 0L;

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method45().method15();
   }

   @Override
   public JsonElement provide() {
      ThreadModuleDump63.method4().method45().method22();
      ThreadModuleDump63.method4().method45().method21();
      return this.provide();
   }

   @Override
   public void method2(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (DriverViewportLegacy.method50().method63() == DriverRouteRegistryLegacy.field14
         && DriverViewportLegacy.method50().method61() != DriverRouteRegistryLegacy.field13
         && DriverViewportLegacy.method50().method64() != DriverOverlayRegistryLegacy.field4
         && ThreadModuleDump63.method4().method41().method8().method17().method8() == var1
         && var4 == 0) {
         ThreadModuleDump63.method3().bridge$displayScreen(null);
      }
   }

   @CallbackJS("emote")
   public static void method3(Integer var0) {
      if (ThreadModuleDump63.method8() != null) {
         if (System.currentTimeMillis() - field1 >= 500L) {
            field1 = System.currentTimeMillis();
            LcuiScreen.method15();
            EmoteManager var1 = ThreadModuleDump63.method4().method45();
            var1.method20(var0).ifPresent(var1x -> {
               Emote var2 = var1.method14(var1x.getEmoteId(), var1x.getJamId());
               if (var2 != null) {
                  var1.method3(var2.getId(), var2.getMetadata(), var1x.getJamId());
                  ThreadModuleDump63.method3().bridge$displayScreen(null);
               }
            });
         }
      }
   }

   @CallbackJS("selectEmote")
   public static void method4(Integer var0) {
      if (var0 != -1 && ThreadModuleDump63.method8() != null && ThreadModuleDump63.method11() == null) {
         method3(var0);
      }
   }

   @CallbackJS("showEmoteLocker")
   public static void method5() {
      LcuiScreen.method15();
      DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field13, new LockerContextLegacy(LockerSectionLegacy.EMOTES, null, false));
   }

   @CallbackJS("unequipAll")
   public static void method7() {
      ThreadModuleDump63.method4().method45().method25().clear();
      ThreadModuleDump63.method4().method45().method22();
      ThreadModuleDump63.method4().method45().method21();
      ThreadModuleDump63.method4().method45().method5();
   }

   @CallbackJS("remove")
   public static void method7(Integer var0) {
      ThreadModuleDump63.method4()
         .method45()
         .method20(var0)
         .ifPresent(
            var1 -> {
               ThreadModuleDump63.method4().method45().method25().remove(var1);
               Emote var2 = Client.method109().method45().method13(var1.getEmoteId());
               if (var2 != null) {
                  ThreadModuleDump63.method4()
                     .method69()
                     .method6(NotificationType.INFO, "Removed Emote", var2.getName() + " has been removed from slot " + (var0 + 1) + ".");
               }
            }
         );
      ThreadModuleDump63.method4().method45().method22();
      ThreadModuleDump63.method4().method45().method21();
      ThreadModuleDump63.method4().method45().method5();
   }

   @CallbackJS("add")
   public static void method8(Integer var0, Integer var1, Integer var2) {
      EmoteManager var3 = ThreadModuleDump63.method4().method45();
      if (var1 < 0) {
         HashSet var4 = new HashSet();

         for (EmoteGiftProvider var6 : var3.method25()) {
            var4.add(var6.getSlotId());
         }

         int var11 = 0;

         while (var4.contains(var11)) {
            var11++;
         }

         var1 = var11;
      }

      int var10 = var1;
      Optional var12 = var3.method24().stream().filter(var1x -> var1x.id() == var2).findFirst();
      if (!var12.isEmpty()) {
         Optional var13 = var3.method25().stream().filter(var1x -> var1x.getSlotId() == var10).findFirst();
         Optional var7 = var3.method25().stream().filter(var1x -> var1x.getSlotId() == var0).findFirst();
         if (var7.isEmpty()) {
            var13.ifPresent(var1x -> var3.method25().remove(var1x));
            EmoteGiftProvider var8;
            var3.method25().add(var8 = new EmoteGiftProvider(var2, var1, 0));
            var8.method3((EmoteGift)var12.get());
            Emote var9 = Client.method109().method45().method13(var2);
            if (var9 != null) {
               ThreadModuleDump63.method4().method69().method6(NotificationType.INFO, "Added Emote", var9.getName() + " has been added to slot " + (var1 + 1) + "!");
            }
         } else {
            ((EmoteGiftProvider)var7.get()).method1(var1);
            var13.ifPresent(var1x -> var1x.method1(var0));
         }

         var3.method22();
         ThreadModuleDump63.method4().method45().method21();
         var3.method5();
      }
   }
}
