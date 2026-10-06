package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.cosmetics.CosmeticPreviewManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.UUID;

public class CosmeticPreviewBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return ThreadModuleDump63.method4().method56().method15();
   }

   @CallbackJS("exitPreview")
   public static void method2() {
      CosmeticPreviewManager var0 = ThreadModuleDump63.method4().method56();
      if (var0 != null) {
         var0.method9();
         if (ThreadModuleDump63.method8() != null) {
            ThreadModuleDump63.method8().bridge$disconnect();
            ThreadModuleDump63.method3().bridge$clearLevel();
            ThreadModuleDump63.method3().bridge$displayScreen(null);
         }

         DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field4);
      }
   }

   @CallbackJS("openStore")
   public static void method3() {
      CosmeticPreviewManager var0 = ThreadModuleDump63.method4().method56();
      if (var0 != null) {
         ThreadModuleDump61.method7("https://store.lunarclient.com/", Initiator.INITIATOR_STORE_PREVIEW);
      }
   }

   @CallbackJS("openPreviewModal")
   public static void method4() {
      CosmeticPreviewManager var0 = ThreadModuleDump63.method4().method56();
      if (var0 != null) {
         var0.method5();
      }
   }

   @CallbackJS("checkout")
   public static void method5(UUID var0) {
      CosmeticPreviewManager var1 = ThreadModuleDump63.method4().method56();
      if (var1 != null) {
         var1.method8(var0);
      }
   }

   @CallbackJS("joinWorld")
   public static void method6(UUID var0) {
      CosmeticPreviewManager var1 = ThreadModuleDump63.method4().method56();
      if (var1 != null) {
         var1.method6(var0);
      }
   }
}
