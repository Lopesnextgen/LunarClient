package com.moonsworth.lunar.client.framework;

import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.mod.hud.directionhud.DirectionHud;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public final class ReducedDebugInfoNotifier {
   public static long field1 = -1L;

   public static boolean method1() {
      return ThreadModuleDump63.method3().bridge$isReducedDebugInfo();
   }

   public static void method2() {
      if (method1()) {
         method3();
      }

      DirectionHud var0 = ThreadModuleDump63.method4().method40().method27();
      Alert2 var1 = (Alert2)var0.method7(Framework.field4);
      if (var1 != null) {
         if (method1()) {
            var1.method4(var0, null);
         } else {
            var1.method5(var0);
         }
      }
   }

   public static void method3() {
      long var0 = System.currentTimeMillis();
      if (field1 == -1L || var0 - field1 > 1800000L) {
         NotificationManager var2 = ThreadModuleDump63.method4().method69();
         var2.method7(NotificationType.WARNING, NotificationManager.method15("reducedDebugInfo"));
         field1 = var0;
      }
   }

   public static void bootstrap() {
      ClientEventBus.method29().method2(ServerJoinEvent.class, var0 -> method2());
   }
}
