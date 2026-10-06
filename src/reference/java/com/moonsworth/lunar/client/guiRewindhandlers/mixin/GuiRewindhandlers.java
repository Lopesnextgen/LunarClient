package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.moonsworth.lunar.bridge.Bridge11_4;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.ServerPingEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class GuiRewindhandlers implements EventRegistrar {
   private static final int field1 = 20;
   private static final int field2 = 60;
   private Bridge11_4 field3;
   private int field4;
   private int field5;
   private int field6;
   private boolean field7;
   private boolean field8;

   public GuiRewindhandlers() {
      this.handle(EventClientTick.class, this::method1);
      this.handle(ServerPingEvent.class, this::method2);
   }

   private void method1(EventClientTick var1) {
      if (ThreadModuleDump63.method8() != null) {
         this.field6 = 0;
         this.field7 = ThreadModuleDump63.method3().bridge$getIntegratedServer() == null;
      }

      if (!(ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge11_4 var3)) {
         this.field3 = null;
      } else {
         GeneralSettings var4 = ThreadModuleDump63.method4().method41().method6();
         boolean var5 = this.field7
            && var4.method54().get()
            && var3.bridge$canReconnect()
            && !var3.bridge$isReconnectCancelled()
            && this.field6 < var4.method56().get();
         if (!var5) {
            if (this.field3 != null) {
               var3.bridge$setReconnectButtonText(ThreadModuleDump63.method4().method67().method2("gui.components", "reconnect"));
               var3.bridge$setCancelButtonVisible(false);
               this.field3 = null;
            }
         } else {
            if (this.field3 != var3) {
               this.field3 = var3;
               this.field4 = this.method3(var4) * 20;
               this.field5 = -1;
               var3.bridge$setCancelButtonVisible(true);
            }

            if (--this.field4 <= 0) {
               this.field3 = null;
               this.field6++;
               this.field8 = true;
               var3.bridge$reconnect();
            } else {
               int var6 = (this.field4 + 20 - 1) / 20;
               if (var6 != this.field5) {
                  this.field5 = var6;
                  var3.bridge$setReconnectButtonText(ThreadModuleDump63.method4().method67().method2("gui.components", "reconnectSeconds", var6));
               }
            }
         }
      }
   }

   private void method2(ServerPingEvent var1) {
      if (this.field8) {
         this.field8 = false;
      } else {
         this.field6 = 0;
      }
   }

   private int method3(GeneralSettings var1) {
      int var2 = var1.method55().get() * (1 << this.field6);
      return Math.min(var2, 60);
   }
}
