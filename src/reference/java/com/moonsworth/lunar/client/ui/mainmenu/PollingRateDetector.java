package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.Notification;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ResolutionChangeEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.function.Consumer;
import lombok.Generated;

public class PollingRateDetector implements EventRegistrar {
   private static boolean isRunning = false;
   private static final int field1 = 8;
   private static final int field2 = 560;
   private static final int field3 = 125;
   private static final int field4 = 70;
   private boolean reset = false;
   private final Consumer<ResolutionChangeEvent> field5 = var1 -> this.reset = true;

   public void start() {
      if (GraphicsEnvironment.isHeadless()) {
         Slayer.method3("Unable to start polling detection thread in headless client!", new Object[0]);
      } else if (!isRunning) {
         isRunning = true;
         Thread var1 = new Thread(this::method1);
         var1.setName("PollingRateDetectionThread");
         var1.start();
         this.handle(ResolutionChangeEvent.class, this.field5);
      }
   }

   public void method1() {
      boolean var1 = false;
      int var2 = 32;
      int var3 = 0;
      long var4 = 0L;
      long var6 = 0L;
      Point var8 = null;
      long var9 = System.currentTimeMillis();
      int var11 = 0;
      int var12 = 0;

      while (true) {
         if (this.reset) {
            this.reset = false;
            var3 = 0;
            var4 = 0L;
            var6 = 0L;
            var8 = null;
            var9 = System.currentTimeMillis();
            var11 = 0;
            var12 = 0;
         }

         if (var11 > 0) {
            var11--;
         } else {
            if (var12 > 100) {
               var1 = true;
            }

            try {
               Thread.sleep(1L);
            } catch (InterruptedException var15) {
            }

            var11 = var12;
         }

         PointerInfo var13 = MouseInfo.getPointerInfo();
         if (var13 != null) {
            Point var14 = var13.getLocation();
            if (!var14.equals(var8)) {
               var8 = var14;
               var6++;
            }

            var4++;
            if (System.currentTimeMillis() - var9 >= 125L) {
               if (var4 / 2L < 70L) {
                  var12++;
               }

               if (var6 - 1L >= 70L) {
                  ThreadModuleDump63.method3()
                     .bridge$submit(
                        () -> {
                           Notification var0 = new Notification(
                              NotificationType.WARNING.getIcon(),
                              ThreadModuleDump63.method4().method67().method2("polling_rate", "title", new Object[0]),
                              ThreadModuleDump63.method4().method67().method2("polling_rate", "desc", new Object[0])
                           );
                           var0.method10(6000L);
                           Client.method109().method69().method10(var0);
                           ThreadModuleDump63.method4().method86().method2("event:lunar.polling_rate", 1);
                        }
                     );
                  break;
               }

               if (var6 + 14L >= 70L) {
                  var12 += 2;
               } else if (var6 + 35L >= 70L) {
                  var12++;
               } else if (var6 != 0L) {
                  if (++var3 > 30) {
                     break;
                  }
               } else {
                  var3 = 0;
               }

               var6 = 0L;
               var4 = 0L;
               if (var1) {
                  if (var2-- == 0) {
                     break;
                  }
               } else if (ThreadModuleDump63.method11() != MainMenuHomeScreen.class) {
                  var1 = true;
               }

               var9 = System.currentTimeMillis();
            }
         }
      }

      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method6(ResolutionChangeEvent.class, this.field5));
   }
}
