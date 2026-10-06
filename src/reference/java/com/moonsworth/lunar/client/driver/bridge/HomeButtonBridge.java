package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.network.server.ServerDiscoveryManager;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.driver.DriverScreen;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.bridge.ButtonProvider;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import com.moonsworth.lunar.client.network.websocket.ConnectionState;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.Nullable;

public class HomeButtonBridge extends com.moonsworth.lunar.client.driver.bridge.ButtonProviderGui implements Translatable {
   public HomeButtonBridge() {
      this.method1(
         ButtonProvider.method8()
            .method1("gui.components.singleplayer")
            .method2(PhosphorIcon.PI_USER_USER03_SOLID)
            .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreen.SINGLEPLAYER))
            .method12()
      );
      this.method1(
         ButtonProvider.method8()
            .method1("gui.components.multiplayer")
            .method2(PhosphorIcon.PI_USER_THREE_SOLID)
            .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreen.MULTIPLAYER))
            .method12()
      );
      this.method1(
         ButtonProvider.method8().method1("gui.components.discover").method6(ButtonProvider.Type.EMPHASIS).method2(PhosphorIcon.PI_EARTH_GLOBE_SOLID).method9(() -> {
            if (!Ref.method4().method43().method23()) {
               AccountBridge.method2();
            } else {
               LcuiScreen.method15();
               DriverViewportLegacy.method50().method16(DriverRouteRegistry.field6);
            }
         }).method12()
      );
      this.method1(
         ButtonProvider.method8()
            .method1("settings.labels.store")
            .method2(PhosphorIcon.PI_SHOPPING_CART_SOLID)
            .method6(ButtonProvider.Type.STORE)
            .method9(
               () -> {
                  LcuiScreen.method15();
                  BrowserUtils.method7(
                     Client.method109().method64().IORHHHROCRRHORHRCHCCHHIHICCRCO().getOrDefault("store", "https://store.lunarclient.com/"),
                     Initiator.INITIATOR_HOME_BUTTON
                  );
               }
            )
            .method12()
      );
   }

   @Override
   public JsonElement provide() {
      return this.method128();
   }

   @Nullable
   @Override
   public JsonElement method128() {
      ButtonProvider gui2task1 = (ButtonProvider)this.RIHCRCRCOOHRCOOOIHORHHHHOHCCCC.get("settings.labels.store");
      if (gui2task1 != null && Ref.method4() != null) {
         gui2task1.method8(Ref.method4().method72().method10());
      }

      ButtonProvider gui2task2 = (ButtonProvider)this.RIHCRCRCOOHRCOOOIHORHHHHOHCCCC.get("gui.components.discover");
      if (gui2task2 != null) {
         String text3 = null;
         if (Ref.method4() != null) {
            AssetServerClient entityrenderer44 = Ref.method4().method35();
            ServerDiscoveryManager foghandler2525 = Ref.method4().method79();
            if (entityrenderer44 != null && entityrenderer44.method112() == ConnectionState.READY && foghandler2525 != null && foghandler2525.method18() == ConnectionState.READY) {
               text3 = Integer.toString(foghandler2525.getTotalServers());
            }
         }

         gui2task2.method8(text3);
      }

      JsonObject json7 = new JsonObject();
      JsonArray array8 = new JsonArray();

      for (ButtonProvider gui2task6 : this.RIHCRCRCOOHRCOOOIHORHHHHOHCCCC.values()) {
         if (gui2task6.method12().getAsBoolean()) {
            array8.add(gui2task6.provide());
         }
      }

      json7.add("buttons", array8);
      return json7;
   }

   public String getLanguagePath() {
      return "gui.components";
   }
}
