package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.network.server.ServerDiscoveryManager;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.DriverScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.Gui2Task;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.Nullable;

public class HomeButtonBridgeLegacy extends com.moonsworth.lunar.client.driver.core.gui.GuiExtension implements Calculator2 {
   public HomeButtonBridgeLegacy() {
      this.method1(
         Gui2Task.method8()
            .method1("gui.components.singleplayer")
            .method2(PhosphorIconLegacy.PI_USER_USER03_SOLID)
            .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreenLegacy.SINGLEPLAYER))
            .method12()
      );
      this.method1(
         Gui2Task.method8()
            .method1("gui.components.multiplayer")
            .method2(PhosphorIconLegacy.PI_USER_THREE_SOLID)
            .method9(() -> DriverViewportLegacy.method50().method56().method3(DriverScreenLegacy.MULTIPLAYER))
            .method12()
      );
      this.method1(
         Gui2Task.method8().method1("gui.components.discover").method6(Gui2Task.Type.EMPHASIS).method2(PhosphorIconLegacy.PI_EARTH_GLOBE_SOLID).method9(() -> {
            if (!ThreadModuleDump63.method4().method43().method23()) {
               AccountBridgeLegacy.method2();
            } else {
               LcuiScreen.method15();
               DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field6);
            }
         }).method12()
      );
      this.method1(
         Gui2Task.method8()
            .method1("settings.labels.store")
            .method2(PhosphorIconLegacy.PI_SHOPPING_CART_SOLID)
            .method6(Gui2Task.Type.STORE)
            .method9(
               () -> {
                  LcuiScreen.method15();
                  ThreadModuleDump61.method7(
                     Client.method109().method64().method3().getOrDefault("store", "https://store.lunarclient.com/"),
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
      Gui2Task var1 = (Gui2Task)this.field1.get("settings.labels.store");
      if (var1 != null && ThreadModuleDump63.method4() != null) {
         var1.method8(ThreadModuleDump63.method4().method72().method10());
      }

      Gui2Task var2 = (Gui2Task)this.field1.get("gui.components.discover");
      if (var2 != null) {
         String var3 = null;
         if (ThreadModuleDump63.method4() != null) {
            EntityRenderer4 var4 = ThreadModuleDump63.method4().method35();
            ServerDiscoveryManager var5 = ThreadModuleDump63.method4().method79();
            if (var4 != null && var4.method112() == EntityRendererType2.READY && var5 != null && var5.method18() == EntityRendererType2.READY) {
               var3 = Integer.toString(var5.getTotalServers());
            }
         }

         var2.method8(var3);
      }

      JsonObject var7 = new JsonObject();
      JsonArray var8 = new JsonArray();

      for (Gui2Task var6 : this.field1.values()) {
         if (var6.method12().getAsBoolean()) {
            var8.add(var6.provide());
         }
      }

      var7.add("buttons", var8);
      return var7;
   }

   public String getLanguagePath() {
      return "gui.components";
   }
}
