package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.account.AuthUtil;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Optional;

public class AccountBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return Client.method109().method43().method15();
   }

   @CallbackJS("addAccount")
   public static void method2() {
      Client.method109().method43().method28(true);
      AuthUtil.method1(var0 -> {
         Client.method109().method43().method28(false);
         if (var0.isSuccessful()) {
            ThreadModuleDump63.method4().method43().method13();
            ThreadModuleDump63.method3().bridge$displayScreen(null);
         } else {
            String var1 = Client.method109().method67().method2(var0.method1().getLanguagePath(), var0.method1().getId(), new Object[0]);
            Client.method109().method69().method7(NotificationType.ERROR, var1);
         }
      });
   }

   @CallbackJS("removeAccount")
   public static String method3(String var0) {
      return method5(var0).filter(var0x -> Client.method109().method43().method21(var0x)).map(var0x -> var0x.method10().getId()).orElse(null);
   }

   @CallbackJS("selectAccount")
   public static String method4(String var0) {
      Client.method109().method43().method27(true);
      Client.method109().method43().method29(var0, true);
      Optional var1 = method5(var0);
      if (var1.isEmpty()) {
         Client.method109().method43().method27(false);
         return null;
      } else {
         AccountSession var2 = (AccountSession)var1.get();
         if (var2.getAccessToken() == null) {
            Client.method109().method43().method29(var0, false);
            Client.method109().method43().method30(var0, true);
            Client.method109().method43().method27(false);
            return null;
         } else {
            var2.method2(var1x -> ThreadModuleDump63.method3().bridge$submit(() -> {
               Client.method109().method43().method27(false);
               Client.method109().method43().method29(var0, false);
               if (var1x.isSuccessful()) {
                  Client.method109().method43().method30(var0, false);
                  Client.method109().method43().method13();
                  LcuiScreen.method15();
               } else {
                  if (var1x.method1() != null) {
                     ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Failed to sign in", var1x.method1().toString());
                  }

                  Client.method109().method43().method30(var0, true);
               }
            }));
            return var2.method10().getId();
         }
      }
   }

   private static Optional<AccountSession> method5(String var0) {
      return Client.method109().method43().method2().values().stream().filter(var1 -> var1.method10().getId().equals(var0.replaceAll("-", ""))).findAny();
   }
}
