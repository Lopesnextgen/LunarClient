package com.moonsworth.lunar.client.chat;

import com.lunarclient.apollo.button.v1.ButtonClientAction;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.ui.prompt.PromptAction;
import com.moonsworth.lunar.client.ui.prompt.RunCommandPrompt;
import com.moonsworth.lunar.client.ui.prompt.OpenUrlPrompt;
import com.moonsworth.lunar.client.ui.MinimapScreen;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.render.minimap.MinimapMod;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.net.URI;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.network.apollo.ApolloPacketUtils;

public final class ChatActionHandler {
   public static final String field1 = "*";

   public static boolean method1(String text0, String text1) {
      MinecraftBridge bridge5_122 = Ref.method3();
      GuiScreenBridge bridge5extension63 = bridge5_122.bridge$getCurrentScreen();
      if (bridge5_122.bridge$getGameSettings().bridge$isChatLinks() && !text1.isEmpty()) {
         URI uri4 = ApolloPacketUtils.method8(text1);
         if (uri4 == null) {
            return false;
         }

         ApolloModuleManager foghandler25 = Ref.method4().method84();
         AtomicBoolean atomicboolean6 = new AtomicBoolean(false);
         Runnable runnable7 = () -> {
            if ((Boolean)Ref.method4().method41().method6().method60().get()) {
               bridge5_122.bridge$displayScreen(Bridge.method8().method33(bridge5extension63, text1, uri4, false));
               atomicboolean6.set(true);
               foghandler25.method15(text0, "OpenUrlPrompt");
            } else {
               BrowserUtils.method7(text1, Initiator.INITIATOR_UNSPECIFIED);
               bridge5_122.bridge$displayScreen(bridge5extension63);
               foghandler25.method15(text0, "OpenUrl");
            }
         };
         if (!Ref.method4().method93().method4(uri4, runnable7, () -> bridge5_122.bridge$displayScreen(bridge5extension63))) {
            runnable7.run();
         }

         return atomicboolean6.get();
      } else {
         return false;
      }
   }

   public static boolean method2(String text0, String text1) {
      if (!text1.startsWith("/")) {
         return false;
      }

      MinecraftBridge bridge5_122 = Ref.method3();
      GuiScreenBridge bridge5extension63 = bridge5_122.bridge$getCurrentScreen();
      TranslationManager foghandler284 = Client.method109().method67();
      String text5 = foghandler284.method2("gui.apollo.module.inventory.runCommandPrompt", "text", text1);
      String text6 = foghandler284.method2("gui.apollo.module.inventory.runCommandPrompt", "confirmButton");
      String text7 = foghandler284.method2("gui.apollo.module.inventory.runCommandPrompt", "cancelButton");
      bridge5_122.bridge$displayScreen(Bridge.method8().method34("", text5, text6, text7, () -> {
         Ref.method7().bridge$sendCommand(text1);
         bridge5_122.bridge$displayScreen(bridge5extension63);
      }, () -> bridge5_122.bridge$displayScreen(bridge5extension63)));
      Ref.method4().method84().method15(text0, "RunCommand");
      return true;
   }

   public static void method3(String text0, String text1) {
      if (text1.startsWith("/")) {
         ApolloModuleManager foghandler22 = Ref.method4().method84();
         if (method6(text1, Ref.method4().method41().method6().method87())) {
            Ref.method7().bridge$sendCommand(text1);
            foghandler22.method15(text0, "ButtonRunCommand");
         } else {
            MinecraftBridge bridge5_123 = Ref.method3();
            GuiScreenBridge bridge5extension64 = bridge5_123.bridge$getCurrentScreen();
            method8(new RunCommandPrompt(text1), "gui.apollo.button.runCommandPrompt", text1, () -> {
               Ref.method7().bridge$sendCommand(text1);
               bridge5_123.bridge$displayScreen(bridge5extension64);
            }, () -> bridge5_123.bridge$displayScreen(bridge5extension64));
            foghandler22.method15(text0, "ButtonRunCommandPrompt");
         }
      }
   }

   public static void method4(String text0, String text1) {
      MinecraftBridge bridge5_122 = Ref.method3();
      GuiScreenBridge bridge5extension63 = bridge5_122.bridge$getCurrentScreen();
      if (bridge5_122.bridge$getGameSettings().bridge$isChatLinks() && !text1.isEmpty() && !"*".equals(text1)) {
         URI uri4 = ApolloPacketUtils.method8(text1);
         if (uri4 != null) {
            ApolloModuleManager foghandler25 = Ref.method4().method84();
            Runnable runnable6 = () -> {
               if (method6(text1, Ref.method4().method41().method6().method88())) {
                  BrowserUtils.method7(text1, Initiator.INITIATOR_UNSPECIFIED);
                  bridge5_122.bridge$displayScreen(bridge5extension63);
                  foghandler25.method15(text0, "ButtonOpenUrl");
               } else {
                  method8(new OpenUrlPrompt(text1), "gui.apollo.button.openUrlPrompt", text1, () -> {
                     BrowserUtils.method7(text1, Initiator.INITIATOR_UNSPECIFIED);
                     bridge5_122.bridge$displayScreen(bridge5extension63);
                  }, () -> bridge5_122.bridge$displayScreen(bridge5extension63));
                  foghandler25.method15(text0, "ButtonOpenUrlPrompt");
               }
            };
            if (!Ref.method4().method93().method4(uri4, runnable6, () -> bridge5_122.bridge$displayScreen(bridge5extension63))) {
               runnable6.run();
            }
         }
      }
   }

   public static void method5(String text0, ButtonClientAction buttonclientaction1) {
      switch (buttonclientaction1) {
         case BUTTON_CLIENT_ACTION_OPEN_MINIMAP_VIEW:
            MinimapMod minimap3 = Ref.method4().method40().method94();
            if (minimap3 != null && minimap3.isEnabled()) {
               Ref.method3().bridge$displayScreen(Bridge.method8().method18(new MinimapScreen(minimap3)));
               Ref.method4().method84().method15(text0, "ButtonClientAction");
            }
            break;
         case BUTTON_CLIENT_ACTION_OPEN_WAYPOINTS_MENU:
            DriverViewportLegacy highlight3iterator2 = DriverViewportLegacy.method50();
            if (highlight3iterator2 != null) {
               highlight3iterator2.method16(DriverRouteRegistry.field17);
               Ref.method4().method84().method15(text0, "ButtonClientAction");
            }
      }
   }

   private static boolean method6(String text0, ListOption<String> lightingextension4991) {
      String text2 = method7(text0);
      return text2 == null ? false : lightingextension4991.contains(text2) || lightingextension4991.contains(method7("*"));
   }

   @Nullable
   public static String method7(String text0) {
      ServerDataBridge bridge3_191 = Ref.method3().bridge$getCurrentServerData();
      if (bridge3_191 == null) {
         return null;
      }

      String text2 = bridge3_191.bridge$serverIP().toLowerCase(Locale.ROOT);
      return text2 + " " + text0;
   }

   private static void method8(PromptAction animations0, String text1, String text2, Runnable runnable3, Runnable runnable4) {
      TranslationManager foghandler285 = Ref.method4().method67();
      com.moonsworth.lunar.client.ui.prompt.ActivePrompt animations6 = Ref.method4().method92();
      animations6.method1(animations0);
      Ref.method3()
         .bridge$displayScreen(
            Bridge.method8()
               .method34(
                  foghandler285.method2(text1, "header"),
                  foghandler285.method2(text1, "warning", text2),
                  foghandler285.method2(text1, "confirmButton"),
                  foghandler285.method2(text1, "denyButton"),
                  () -> {
                     animations6.method2();
                     runnable3.run();
                  },
                  () -> {
                     animations6.method2();
                     runnable4.run();
                  }
               )
         );
   }

   @Generated
   private ChatActionHandler() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
