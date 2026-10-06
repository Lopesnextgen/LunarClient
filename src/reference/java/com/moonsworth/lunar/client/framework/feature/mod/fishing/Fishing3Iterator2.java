package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Locale;

public class Fishing3Iterator2 extends Fishing3_3 {
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
   public void method6(String text, String text2, String[] items, Gui2Extension gui2) {
      if (items.length >= 1) {
         Rewindhandlers3 var5 = (Rewindhandlers3)ThreadModuleDump63.method4().method40().method82().method187().method13().method7().orElse(null);
         if (var5 != null) {
            String var6 = items[0].toLowerCase(Locale.ROOT);
            String var7 = null;

            for (String var9 : var5.method1()) {
               String var10 = var9.toLowerCase(Locale.ROOT);
               if (var10.equals(var6)) {
                  var7 = var9;
                  break;
               }

               if (var10.startsWith(var6)) {
                  if (var7 != null) {
                     ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("multiplePartyMembers", new Object[]{items[0]}));
                     return;
                  }

                  var7 = var9;
               }
            }

            this.method7("/p kick " + var7);
         }
      }
   }
}
