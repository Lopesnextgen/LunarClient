package com.moonsworth.lunar.client.network.ipc;

import com.lunarclient.gameipc.paynow.v1.OpenPayNowJsCheckoutRequest;
import com.lunarclient.gameipc.tebex.v1.OpenTebexJsCheckoutRequest;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.EmptyScreen;
import com.moonsworth.lunar.client.network.ipc.WebSocketClientIterator;
import java.util.Optional;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.Ref;

public final class CheckoutUtils {
   public static void method1(String text0, String text) {
      Optional optional2 = Ref.method6();
      if (optional2.isEmpty()) {
         LunarLogger.method6("Tebex", "Ignoring open request as we have no launcher IPC connection", new Object[0]);
      } else {
         LunarLogger.method4("Tebex", "Opening checkout for basket " + text0 + " in locale " + text, new Object[0]);
         WebSocketClientIterator websocketclientiterator3 = (WebSocketClientIterator)optional2.get();
         websocketclientiterator3.method14().openTebexJsCheckout(null, OpenTebexJsCheckoutRequest.newBuilder().setBasketIdent(text0).setLocale(text).build(), arg0x -> {
            LunarLogger.method4("Tebex", "Sent open request to launcher. Response: " + arg0x.getStatus(), new Object[0]);
            if (arg0x.getOpenMinecraftScreen()) {
               LunarLogger.method4("Tebex", "Opening Minecraft screen to prevent movement", new Object[0]);
               Ref.method3().bridge$submit(() -> {
                  Bridge5Extension62 bridge5extension620xx = Bridge.method8().method18(new EmptyScreen());
                  Ref.method3().bridge$displayScreen(bridge5extension620xx);
               });
            } else {
               LunarLogger.method4("Tebex", "Skipping opening Minecraft screen", new Object[0]);
            }
         });
      }
   }

   public static void method2(String text0) {
      Optional optional1 = Ref.method6();
      if (optional1.isEmpty()) {
         LunarLogger.method6("PayNow", "Ignoring open request as we have no launcher IPC connection", new Object[0]);
      } else {
         LunarLogger.method4("PayNow", "Opening checkout for token " + text0, new Object[0]);
         WebSocketClientIterator websocketclientiterator2 = (WebSocketClientIterator)optional1.get();
         websocketclientiterator2.method15().openPayNowJsCheckout(null, OpenPayNowJsCheckoutRequest.newBuilder().setCheckoutToken(text0).build(), arg0x -> {
            LunarLogger.method4("PayNow", "Sent open request to launcher. Response: " + arg0x.getStatus(), new Object[0]);
            if (arg0x.getOpenMinecraftScreen()) {
               LunarLogger.method4("PayNow", "Opening Minecraft screen to prevent movement", new Object[0]);
               Ref.method3().bridge$submit(() -> {
                  Bridge5Extension62 bridge5extension620xx = Bridge.method8().method18(new EmptyScreen());
                  Ref.method3().bridge$displayScreen(bridge5extension620xx);
               });
            } else {
               LunarLogger.method4("PayNow", "Skipping opening Minecraft screen", new Object[0]);
            }
         });
      }
   }

   @Generated
   private CheckoutUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
