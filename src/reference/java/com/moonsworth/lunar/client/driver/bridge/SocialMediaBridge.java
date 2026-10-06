package com.moonsworth.lunar.client.driver.bridge;

import com.lunarclient.common.v1.UserSocialPlatform;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.lunarclient.websocket.socials.v1.UnlinkSocialRequest;
import com.lunarclient.websocket.socials.v1.UnlinkSocialResponse.Status;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.account.LinkedAccount;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class SocialMediaBridge implements DriverGuiExtension, GuiIterator.Extension {
   public SocialMediaBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Ref.method4().method101().method15();
   }

   @CallbackJS("openSocialMedia")
   public static void method2(UserSocialPlatform usersocialplatform0) {
      LinkedAccount gui2handler1 = (LinkedAccount)Ref.method4().method101().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(usersocialplatform0);
      if (gui2handler1 != null) {
         Ref.method5().ifPresent(arg1x -> arg1x.method115().method1("GAME_SOCIAL_LINK", arg1xx -> {
            if (arg1xx != null) {
               String text2 = "https://www.lunarclient.com/" + gui2handler1.getId().toLowerCase(Locale.ROOT) + "-link/";
               String text3 = URLEncoder.encode(arg1xx, StandardCharsets.UTF_8);
               String text4 = URLEncoder.encode(Ref.method3().bridge$getSession().bridge$getProfile().getId().toString(), StandardCharsets.UTF_8);
               String text5 = URLEncoder.encode(Ref.method3().bridge$getSession().bridge$getUsername(), StandardCharsets.UTF_8);
               boolean flag6 = Client.method109().method54().method9();
               String text7 = text2 + "?token=" + text3 + "&uuid=" + text4 + "&username=" + text5 + "&premium=" + flag6;
               if (BrowserUtils.method7(text7, Initiator.INITIATOR_SOCIAL_LINK)) {
                  Ref.method4().method69().method3("Opened link in browser");
               } else {
                  ClipboardUtils.method2(text7);
                  Ref.method4().method69().method3("Copied link to clipboard");
               }
            }
         }));
      }
   }

   @CallbackJS("unlinkSocial")
   public static void method3(UserSocialPlatform usersocialplatform0) {
      Ref.method5().ifPresent(arg1 -> arg1.method104().unlinkSocial(null, UnlinkSocialRequest.newBuilder().setPlatform(usersocialplatform0).build(), arg1x -> {
         if (arg1x.getStatus() == Status.STATUS_OK) {
            LinkedAccount gui2handler2 = (LinkedAccount)Ref.method4().method101().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(usersocialplatform0);
            if (gui2handler2 != null) {
               gui2handler2.method1(null);
            }

            Ref.method4().method101().method5(true);
            Ref.method4().method69().method3("Unlinked social account");
         }
      }));
   }
}
