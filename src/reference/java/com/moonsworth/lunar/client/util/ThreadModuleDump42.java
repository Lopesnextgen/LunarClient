package com.moonsworth.lunar.client.util;

import com.lunarclient.gameipc.paynow.v1.OpenPayNowJsCheckoutRequest;
import com.lunarclient.gameipc.tebex.v1.OpenTebexJsCheckoutRequest;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.EmptyScreen;
import com.moonsworth.lunar.client.waypoints.WebSocketClientIterator;
import java.util.Optional;
import lombok.Generated;

public final class ThreadModuleDump42 {
   public static void method1(String var0, String var1) {
      Optional var2 = ThreadModuleDump63.method6();
      if (var2.isEmpty()) {
         Slayer.method6("Tebex", "Ignoring open request as we have no launcher IPC connection", new Object[0]);
      } else {
         Slayer.method4("Tebex", "Opening checkout for basket " + var0 + " in locale " + var1, new Object[0]);
         WebSocketClientIterator var3 = (WebSocketClientIterator)var2.get();
         var3.method14().openTebexJsCheckout(null, OpenTebexJsCheckoutRequest.newBuilder().setBasketIdent(var0).setLocale(var1).build(), var0x -> {
            Slayer.method4("Tebex", "Sent open request to launcher. Response: " + var0x.getStatus(), new Object[0]);
            if (var0x.getOpenMinecraftScreen()) {
               Slayer.method4("Tebex", "Opening Minecraft screen to prevent movement", new Object[0]);
               ThreadModuleDump63.method3().bridge$submit(() -> {
                  Bridge5Extension62 var0xx = Bridge.method8().method18(new EmptyScreen());
                  ThreadModuleDump63.method3().bridge$displayScreen(var0xx);
               });
            } else {
               Slayer.method4("Tebex", "Skipping opening Minecraft screen", new Object[0]);
            }
         });
      }
   }

   public static void method2(String var0) {
      Optional var1 = ThreadModuleDump63.method6();
      if (var1.isEmpty()) {
         Slayer.method6("PayNow", "Ignoring open request as we have no launcher IPC connection", new Object[0]);
      } else {
         Slayer.method4("PayNow", "Opening checkout for token " + var0, new Object[0]);
         WebSocketClientIterator var2 = (WebSocketClientIterator)var1.get();
         var2.method15().openPayNowJsCheckout(null, OpenPayNowJsCheckoutRequest.newBuilder().setCheckoutToken(var0).build(), var0x -> {
            Slayer.method4("PayNow", "Sent open request to launcher. Response: " + var0x.getStatus(), new Object[0]);
            if (var0x.getOpenMinecraftScreen()) {
               Slayer.method4("PayNow", "Opening Minecraft screen to prevent movement", new Object[0]);
               ThreadModuleDump63.method3().bridge$submit(() -> {
                  Bridge5Extension62 var0xx = Bridge.method8().method18(new EmptyScreen());
                  ThreadModuleDump63.method3().bridge$displayScreen(var0xx);
               });
            } else {
               Slayer.method4("PayNow", "Skipping opening Minecraft screen", new Object[0]);
            }
         });
      }
   }

   @Generated
   private ThreadModuleDump42() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
