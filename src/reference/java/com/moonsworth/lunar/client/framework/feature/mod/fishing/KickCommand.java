package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.listener.PartyState;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Locale;

public class KickCommand extends SkyBlockChatCommand {
   public KickCommand() {
   }

   @Override
   public String getCommand() {
      return "kick";
   }

   @Override
   public List<String> getAliases() {
      return List.of("k");
   }

   @Override
   public boolean method2() {
      return true;
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      if (items3.length >= 1) {
         PartyState rewindhandlers35 = (PartyState)Ref.method4().method40().method82().method187().method13().method7().orElse(null);
         if (rewindhandlers35 != null) {
            String text6 = items3[0].toLowerCase(Locale.ROOT);
            String text7 = null;

            for (String text9 : rewindhandlers35.method1()) {
               String text10 = text9.toLowerCase(Locale.ROOT);
               if (text10.equals(text6)) {
                  text7 = text9;
                  break;
               }

               if (text10.startsWith(text6)) {
                  if (text7 != null) {
                     Ref.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("multiplePartyMembers", new Object[]{items3[0]}));
                     return;
                  }

                  text7 = text9;
               }
            }

            this.method7("/p kick " + text7);
         }
      }
   }
}
