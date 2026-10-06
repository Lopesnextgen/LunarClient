package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.lunarclient.websocket.spray.v1.EquippedSpray;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.SprayManager;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.SprayEntry;
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

public class SpraysBridge implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method46().method15();
   }

   @CallbackJS("spray")
   public static void method2(Integer var0) {
      if (!(Boolean)ThreadModuleDump63.method4().method41().method6().method35().get()) {
         ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Sprays are disabled, you must enable sprays first");
      } else if (ThreadModuleDump63.method8() != null && ThreadModuleDump63.method4().method41().method6().method8(ThreadModuleDump63.method7())) {
         LcuiScreen.method15();
         SprayManager var1 = ThreadModuleDump63.method4().method46();
         var1.method38(var0).ifPresent(var1x -> var1.method16(true, false, (var2, var3, var4, var5, var6, var7, var8) -> {
            float var9 = SprayManager.method23(var7);
            SprayEntry var10 = var1.method5(var1x.getSprayId());
            SprayPlacementTracker var11 = var1.method14(var10, var6, var4, var9);
            if (var11 != null) {
               var1.method2(var3.bridge$getUniqueID(), var11, var1.getMaxActiveSprays(), true);
            } else {
               ThreadModuleDump63.method4().method69().method7(NotificationType.WARNING, "Failed to create spray");
            }
         }, ThreadModuleDump63.method3().bridge$getTimer().method1()));
      }
   }

   @CallbackJS("selectSpray")
   public static void method3(Integer var0) {
      if (var0 != -1 && ThreadModuleDump63.method8() != null && ThreadModuleDump63.method11() == null) {
         method2(var0);
      }
   }

   @CallbackJS("unequipAll")
   public static void method4() {
      ThreadModuleDump63.method4().method46().method42().clear();
      ThreadModuleDump63.method4().method46().method22();
      ThreadModuleDump63.method4().method46().method21();
      ThreadModuleDump63.method4().method46().method2();
   }

   @CallbackJS("remove")
   public static void method5(Integer var0) {
      ThreadModuleDump63.method4()
         .method46()
         .method38(var0)
         .ifPresent(
            var1 -> {
               ThreadModuleDump63.method4().method46().method42().remove(var1);
               Client.method109()
                  .method46()
                  .method41()
                  .keySet()
                  .stream()
                  .filter(var1x -> var1.getSprayId() == var1x.getId())
                  .findFirst()
                  .ifPresent(
                     var1x -> ThreadModuleDump63.method4()
                        .method69()
                        .method6(NotificationType.INFO, "Removed Spray", var1x.getName() + " has been removed from slot " + (var0 + 1) + ".")
                  );
            }
         );
      ThreadModuleDump63.method4().method46().method22();
      ThreadModuleDump63.method4().method46().method21();
      ThreadModuleDump63.method4().method46().method2();
   }

   @CallbackJS("showSprayLocker")
   public static void method7() {
      LcuiScreen.method15();
      DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field13, new LockerContextLegacy(LockerSectionLegacy.SPRAYS, null, false));
   }

   @CallbackJS("add")
   public static void method7(Integer var0, Integer var1, Integer var2) {
      SprayManager var3 = ThreadModuleDump63.method4().method46();
      if (var1 < 0) {
         HashSet var4 = new HashSet();

         for (EquippedSpray var6 : var3.method42()) {
            var4.add(var6.getSlotNumber());
         }

         int var10 = 0;

         while (var4.contains(var10)) {
            var10++;
         }

         var1 = var10;
      }

      int var9 = var1;
      Optional var11 = var3.method41().keySet().stream().filter(var1x -> var1x.getId() == var2).findFirst();
      if (!var11.isEmpty()) {
         Optional var12 = var3.method42().stream().filter(var1x -> var1x.getSlotNumber() == var9).findFirst();
         Optional var7 = var3.method42().stream().filter(var1x -> var1x.getSlotNumber() == var0).findFirst();
         if (var7.isEmpty()) {
            var12.ifPresent(var1x -> var3.method42().remove(var1x));
            var3.method42().add(EquippedSpray.newBuilder().setSprayId(var2).setSlotNumber(var1).build());
            SprayEntry var8 = Client.method109().method46().method41().keySet().stream().filter(var1x -> var1x.getId() == var2).findFirst().orElse(null);
            if (var8 != null) {
               ThreadModuleDump63.method4().method69().method6(NotificationType.INFO, "Added Spray", var8.getName() + " has been added to slot " + (var1 + 1) + "!");
            }
         } else {
            var12.ifPresentOrElse(var2x -> {
               EquippedSpray var3x = var2x.toBuilder().setSlotNumber(var0).build();
               var3.method42().remove(var2x);
               var3.method42().add(var3x);
            }, () -> var3.method42().remove(var7.get()));
            var3.method42().add(((EquippedSpray)var7.get()).toBuilder().setSlotNumber(var9).build());
         }

         var3.method22();
         var3.method21();
         var3.method2();
      }
   }

   @Override
   public void method2(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (DriverViewportLegacy.method50().method63() == DriverRouteRegistryLegacy.field15
         && DriverViewportLegacy.method50().method61() != DriverRouteRegistryLegacy.field13
         && DriverViewportLegacy.method50().method64() != DriverOverlayRegistryLegacy.field4
         && ThreadModuleDump63.method4().method41().method8().method18().method8() == var1
         && var4 == 0) {
         ThreadModuleDump63.method3().bridge$displayScreen(null);
      }
   }
}
