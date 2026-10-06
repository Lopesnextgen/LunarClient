package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.lunarclient.common.v1.UserSocialPlatform;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.lunarclient.websocket.socials.v1.UnlinkSocialRequest;
import com.lunarclient.websocket.socials.v1.UnlinkSocialResponse.Status;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.account.LinkedAccount;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class SocialMediaBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return ThreadModuleDump63.method4().method101().method15();
   }

   @CallbackJS("openSocialMedia")
   public static void method2(UserSocialPlatform var0) {
      LinkedAccount var1 = (LinkedAccount)ThreadModuleDump63.method4().method101().method3().get(var0);
      if (var1 != null) {
         ThreadModuleDump63.method5().ifPresent(var1x -> var1x.method115().method1("GAME_SOCIAL_LINK", var1xx -> {
            if (var1xx != null) {
               String var2 = "https://www.lunarclient.com/" + var1.getId().toLowerCase(Locale.ROOT) + "-link/";
               String var3 = URLEncoder.encode(var1xx, StandardCharsets.UTF_8);
               String var4 = URLEncoder.encode(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId().toString(), StandardCharsets.UTF_8);
               String var5 = URLEncoder.encode(ThreadModuleDump63.method3().bridge$getSession().bridge$getUsername(), StandardCharsets.UTF_8);
               boolean var6 = Client.method109().method54().method9();
               String var7 = var2 + "?token=" + var3 + "&uuid=" + var4 + "&username=" + var5 + "&premium=" + var6;
               if (ThreadModuleDump61.method7(var7, Initiator.INITIATOR_SOCIAL_LINK)) {
                  ThreadModuleDump63.method4().method69().method3("Opened link in browser");
               } else {
                  ThreadModuleDump68.setClipboardString(var7);
                  ThreadModuleDump63.method4().method69().method3("Copied link to clipboard");
               }
            }
         }));
      }
   }

   @CallbackJS("unlinkSocial")
   public static void method3(UserSocialPlatform var0) {
      ThreadModuleDump63.method5().ifPresent(var1 -> var1.method104().unlinkSocial(null, UnlinkSocialRequest.newBuilder().setPlatform(var0).build(), var1x -> {
         if (var1x.getStatus() == Status.STATUS_OK) {
            LinkedAccount var2 = (LinkedAccount)ThreadModuleDump63.method4().method101().method3().get(var0);
            if (var2 != null) {
               var2.method1(null);
            }

            ThreadModuleDump63.method4().method101().method5(true);
            ThreadModuleDump63.method4().method69().method3("Unlinked social account");
         }
      }));
   }
}
