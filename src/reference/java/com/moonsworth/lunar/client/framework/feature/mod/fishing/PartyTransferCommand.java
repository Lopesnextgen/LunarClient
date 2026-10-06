package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.listener.PartyState;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Locale;

public class PartyTransferCommand extends SkyBlockChatCommand {
   public PartyTransferCommand() {
   }

   @Override
   public String getCommand() {
      return "partytransfer";
   }

   @Override
   public List<String> getAliases() {
      return List.of("pt", "ptme");
   }

   @Override
   public boolean method2() {
      return true;
   }

   @Override
   public boolean method3() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      String text5 = null;
      if (items3.length == 0) {
         text5 = text2;
      } else {
         PartyState rewindhandlers36 = (PartyState)Ref.method4().method40().method82().method187().method13().method7().orElse(null);
         if (rewindhandlers36 == null) {
            return;
         }

         String text7 = items3[0].toLowerCase(Locale.ROOT);

         for (String text9 : rewindhandlers36.method1()) {
            String text10 = text9.toLowerCase(Locale.ROOT);
            if (text10.equals(text7)) {
               text5 = text9;
               break;
            }

            if (text10.startsWith(text7)) {
               if (text5 != null) {
                  Ref.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("multiplePartyMembers", new Object[]{items3[0]}));
                  return;
               }

               text5 = text9;
            }
         }
      }

      this.method7("/p transfer " + text5);
   }
}
