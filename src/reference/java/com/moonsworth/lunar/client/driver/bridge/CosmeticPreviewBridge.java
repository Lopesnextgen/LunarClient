package com.moonsworth.lunar.client.driver.bridge;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.cosmetics.CosmeticPreviewManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.UUID;

public class CosmeticPreviewBridge implements DriverGuiExtension, GuiIterator.Extension {
   public CosmeticPreviewBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Ref.method4().method56().method15();
   }

   @CallbackJS("exitPreview")
   public static void method2() {
      CosmeticPreviewManager holograms40 = Ref.method4().method56();
      if (holograms40 != null) {
         holograms40.method9();
         if (Ref.method8() != null) {
            Ref.method8().bridge$disconnect();
            Ref.method3().bridge$clearLevel();
            Ref.method3().bridge$displayScreen(null);
         }

         DriverViewportLegacy.method50().method16(DriverRouteRegistry.field4);
      }
   }

   @CallbackJS("openStore")
   public static void method3() {
      CosmeticPreviewManager holograms40 = Ref.method4().method56();
      if (holograms40 != null) {
         BrowserUtils.method7("https://store.lunarclient.com/", Initiator.INITIATOR_STORE_PREVIEW);
      }
   }

   @CallbackJS("openPreviewModal")
   public static void method4() {
      CosmeticPreviewManager holograms40 = Ref.method4().method56();
      if (holograms40 != null) {
         holograms40.method5();
      }
   }

   @CallbackJS("checkout")
   public static void method5(UUID uuid0) {
      CosmeticPreviewManager holograms41 = Ref.method4().method56();
      if (holograms41 != null) {
         holograms41.method8(uuid0);
      }
   }

   @CallbackJS("joinWorld")
   public static void method6(UUID uuid0) {
      CosmeticPreviewManager holograms41 = Ref.method4().method56();
      if (holograms41 != null) {
         holograms41.method6(uuid0);
      }
   }
}
