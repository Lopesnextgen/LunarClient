package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.account.AuthUtil;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Optional;

public class AccountBridge implements DriverGuiExtension, GuiIterator.Extension {
   public AccountBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method43().method15();
   }

   @CallbackJS("addAccount")
   public static void method2() {
      Client.method109().method43().method28(true);
      AuthUtil.method1(arg0 -> {
         Client.method109().method43().method28(false);
         if (arg0.isSuccessful()) {
            Ref.method4().method43().method13();
            Ref.method3().bridge$displayScreen(null);
         } else {
            String text1 = Client.method109().method67().method2(arg0.method1().getLanguagePath(), arg0.method1().getId(), new Object[0]);
            Client.method109().method69().method7(NotificationType.ERROR, text1);
         }
      });
   }

   @CallbackJS("removeAccount")
   public static String method3(String text0) {
      return method5(text0).filter(arg0x -> Client.method109().method43().method21(arg0x)).map(arg0x -> arg0x.method10().getId()).orElse(null);
   }

   @CallbackJS("selectAccount")
   public static String method4(String text0) {
      Client.method109().method43().method27(true);
      Client.method109().method43().method29(text0, true);
      Optional optional1 = method5(text0);
      if (optional1.isEmpty()) {
         Client.method109().method43().method27(false);
         return null;
      } else {
         AccountSession lighting3loader22 = (AccountSession)optional1.get();
         if (lighting3loader22.getAccessToken() == null) {
            Client.method109().method43().method29(text0, false);
            Client.method109().method43().method30(text0, true);
            Client.method109().method43().method27(false);
            return null;
         } else {
            lighting3loader22.method2(arg1x -> Ref.method3().bridge$submit(() -> {
               Client.method109().method43().method27(false);
               Client.method109().method43().method29(text0, false);
               if (arg1x.isSuccessful()) {
                  Client.method109().method43().method30(text0, false);
                  Client.method109().method43().method13();
                  LcuiScreen.method15();
               } else {
                  if (arg1x.method1() != null) {
                     Ref.method4().method69().method6(NotificationType.ERROR, "Failed to sign in", arg1x.method1().toString());
                  }

                  Client.method109().method43().method30(text0, true);
               }
            }));
            return lighting3loader22.method10().getId();
         }
      }
   }

   private static Optional<AccountSession> method5(String text0) {
      return Client.method109().method43().method2().values().stream().filter(arg1 -> arg1.method10().getId().equals(text0.replaceAll("-", ""))).findAny();
   }
}
