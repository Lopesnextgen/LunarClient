package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Locale;

public class Fishing3Iterator_2 extends Fishing3_3 {
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
   public void method6(String text, String text2, String[] items, Gui2Extension gui2) {
      String var5 = null;
      if (items.length == 0) {
         var5 = text2;
      } else {
         Rewindhandlers3 var6 = (Rewindhandlers3)ThreadModuleDump63.method4().method40().method82().method187().method13().method7().orElse(null);
         if (var6 == null) {
            return;
         }

         String var7 = items[0].toLowerCase(Locale.ROOT);

         for (String var9 : var6.method1()) {
            String var10 = var9.toLowerCase(Locale.ROOT);
            if (var10.equals(var7)) {
               var5 = var9;
               break;
            }

            if (var10.startsWith(var7)) {
               if (var5 != null) {
                  ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("multiplePartyMembers", new Object[]{items[0]}));
                  return;
               }

               var5 = var9;
            }
         }
      }

      this.method7("/p transfer " + var5);
   }
}
