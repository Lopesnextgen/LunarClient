package com.moonsworth.lunar.client.rewindhandlers;

import com.lunarclient.apollo.button.v1.ButtonClientAction;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.gui.prompt.PromptAction;
import com.moonsworth.lunar.client.gui.prompt.RunCommandPrompt;
import com.moonsworth.lunar.client.gui.prompt.OpenUrlPrompt;
import com.moonsworth.lunar.client.ui.MinimapScreen;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.net.URI;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class Rewindhandlers2 {
   public static final String field1 = "*";

   public static boolean method1(String var0, String var1) {
      Bridge5_12 var2 = ThreadModuleDump63.method3();
      Bridge5Extension6 var3 = var2.bridge$getCurrentScreen();
      if (var2.bridge$getGameSettings().bridge$isChatLinks() && !var1.isEmpty()) {
         URI var4 = Rewindhandlers3.method8(var1);
         if (var4 == null) {
            return false;
         }

         ApolloModuleManager var5 = ThreadModuleDump63.method4().method84();
         AtomicBoolean var6 = new AtomicBoolean(false);
         Runnable var7 = () -> {
            if (ThreadModuleDump63.method4().method41().method6().method60().get()) {
               var2.bridge$displayScreen(Bridge.method8().method33(var3, var1, var4, false));
               var6.set(true);
               var5.method15(var0, "OpenUrlPrompt");
            } else {
               ThreadModuleDump61.method7(var1, Initiator.INITIATOR_UNSPECIFIED);
               var2.bridge$displayScreen(var3);
               var5.method15(var0, "OpenUrl");
            }
         };
         if (!ThreadModuleDump63.method4().method93().method4(var4, var7, () -> var2.bridge$displayScreen(var3))) {
            var7.run();
         }

         return var6.get();
      } else {
         return false;
      }
   }

   public static boolean method2(String var0, String var1) {
      if (!var1.startsWith("/")) {
         return false;
      }

      Bridge5_12 var2 = ThreadModuleDump63.method3();
      Bridge5Extension6 var3 = var2.bridge$getCurrentScreen();
      TranslationManager var4 = Client.method109().method67();
      String var5 = var4.method2("gui.apollo.module.inventory.runCommandPrompt", "text", var1);
      String var6 = var4.method2("gui.apollo.module.inventory.runCommandPrompt", "confirmButton");
      String var7 = var4.method2("gui.apollo.module.inventory.runCommandPrompt", "cancelButton");
      var2.bridge$displayScreen(Bridge.method8().method34("", var5, var6, var7, () -> {
         ThreadModuleDump63.method7().bridge$sendCommand(var1);
         var2.bridge$displayScreen(var3);
      }, () -> var2.bridge$displayScreen(var3)));
      ThreadModuleDump63.method4().method84().method15(var0, "RunCommand");
      return true;
   }

   public static void method3(String var0, String var1) {
      if (var1.startsWith("/")) {
         ApolloModuleManager var2 = ThreadModuleDump63.method4().method84();
         if (method6(var1, ThreadModuleDump63.method4().method41().method6().method87())) {
            ThreadModuleDump63.method7().bridge$sendCommand(var1);
            var2.method15(var0, "ButtonRunCommand");
         } else {
            Bridge5_12 var3 = ThreadModuleDump63.method3();
            Bridge5Extension6 var4 = var3.bridge$getCurrentScreen();
            method8(new RunCommandPrompt(var1), "gui.apollo.button.runCommandPrompt", var1, () -> {
               ThreadModuleDump63.method7().bridge$sendCommand(var1);
               var3.bridge$displayScreen(var4);
            }, () -> var3.bridge$displayScreen(var4));
            var2.method15(var0, "ButtonRunCommandPrompt");
         }
      }
   }

   public static void method4(String var0, String var1) {
      Bridge5_12 var2 = ThreadModuleDump63.method3();
      Bridge5Extension6 var3 = var2.bridge$getCurrentScreen();
      if (var2.bridge$getGameSettings().bridge$isChatLinks() && !var1.isEmpty() && !"*".equals(var1)) {
         URI var4 = Rewindhandlers3.method8(var1);
         if (var4 != null) {
            ApolloModuleManager var5 = ThreadModuleDump63.method4().method84();
            Runnable var6 = () -> {
               if (method6(var1, ThreadModuleDump63.method4().method41().method6().method88())) {
                  ThreadModuleDump61.method7(var1, Initiator.INITIATOR_UNSPECIFIED);
                  var2.bridge$displayScreen(var3);
                  var5.method15(var0, "ButtonOpenUrl");
               } else {
                  method8(new OpenUrlPrompt(var1), "gui.apollo.button.openUrlPrompt", var1, () -> {
                     ThreadModuleDump61.method7(var1, Initiator.INITIATOR_UNSPECIFIED);
                     var2.bridge$displayScreen(var3);
                  }, () -> var2.bridge$displayScreen(var3));
                  var5.method15(var0, "ButtonOpenUrlPrompt");
               }
            };
            if (!ThreadModuleDump63.method4().method93().method4(var4, var6, () -> var2.bridge$displayScreen(var3))) {
               var6.run();
            }
         }
      }
   }

   public static void method5(String var0, ButtonClientAction var1) {
      switch (var1) {
         case BUTTON_CLIENT_ACTION_OPEN_MINIMAP_VIEW:
            Minimap var3 = ThreadModuleDump63.method4().method40().method94();
            if (var3 != null && var3.isEnabled()) {
               ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new MinimapScreen(var3)));
               ThreadModuleDump63.method4().method84().method15(var0, "ButtonClientAction");
            }
            break;
         case BUTTON_CLIENT_ACTION_OPEN_WAYPOINTS_MENU:
            DriverViewportLegacy var2 = DriverViewportLegacy.method50();
            if (var2 != null) {
               var2.method16(DriverRouteRegistryLegacy.field17);
               ThreadModuleDump63.method4().method84().method15(var0, "ButtonClientAction");
            }
      }
   }

   private static boolean method6(String var0, ListOption<String> var1) {
      String var2 = method7(var0);
      return var2 == null ? false : var1.contains(var2) || var1.contains(method7("*"));
   }

   @Nullable
   public static String method7(String var0) {
      Bridge3_19 var1 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
      if (var1 == null) {
         return null;
      }

      String var2 = var1.bridge$serverIP().toLowerCase(Locale.ROOT);
      return var2 + " " + var0;
   }

   private static void method8(PromptAction var0, String var1, String var2, Runnable var3, Runnable var4) {
      TranslationManager var5 = ThreadModuleDump63.method4().method67();
      com.moonsworth.lunar.client.gui.prompt.ActivePrompt var6 = ThreadModuleDump63.method4().method92();
      var6.method1(var0);
      ThreadModuleDump63.method3()
         .bridge$displayScreen(
            Bridge.method8()
               .method34(
                  var5.method2(var1, "header"),
                  var5.method2(var1, "warning", var2),
                  var5.method2(var1, "confirmButton"),
                  var5.method2(var1, "denyButton"),
                  () -> {
                     var6.method2();
                     var3.run();
                  },
                  () -> {
                     var6.method2();
                     var4.run();
                  }
               )
         );
   }

   @Generated
   private Rewindhandlers2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
