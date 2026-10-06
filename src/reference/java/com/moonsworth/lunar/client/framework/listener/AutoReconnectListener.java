package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.Bridge11_4;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerPing;
import com.moonsworth.lunar.client.framework.Ref;

public class AutoReconnectListener implements EventBusAccess {
   private static final int field1 = 20;
   private static final int field2 = 60;
   private Bridge11_4 field3;
   private int field4;
   private int field5;
   private int field6;
   private boolean field7;
   private boolean field8;

   public AutoReconnectListener() {
      this.handle(EventTick.class, this::method1);
      this.handle(EventServerPing.class, this::method2);
   }

   private void method1(EventTick event) {
      if (Ref.method8() != null) {
         this.field6 = 0;
         this.field7 = Ref.method3().bridge$getIntegratedServer() == null;
      }

      if (!(Ref.method3().bridge$getCurrentScreen() instanceof Bridge11_4 bridge11_43)) {
         this.field3 = null;
      } else {
         GeneralSettings fogloader224 = Ref.method4().method41().method6();
         boolean flag5 = this.field7
            && (Boolean)fogloader224.method54().get()
            && bridge11_43.bridge$canReconnect()
            && !bridge11_43.bridge$isReconnectCancelled()
            && this.field6 < (Integer)fogloader224.method56().get();
         if (!flag5) {
            if (this.field3 != null) {
               bridge11_43.bridge$setReconnectButtonText(Ref.method4().method67().method2("gui.components", "reconnect", new Object[0]));
               bridge11_43.bridge$setCancelButtonVisible(false);
               this.field3 = null;
            }
         } else {
            if (this.field3 != bridge11_43) {
               this.field3 = bridge11_43;
               this.field4 = this.method3(fogloader224) * 20;
               this.field5 = -1;
               bridge11_43.bridge$setCancelButtonVisible(true);
            }

            if (--this.field4 <= 0) {
               this.field3 = null;
               this.field6++;
               this.field8 = true;
               bridge11_43.bridge$reconnect();
            } else {
               int number6 = (this.field4 + 20 - 1) / 20;
               if (number6 != this.field5) {
                  this.field5 = number6;
                  bridge11_43.bridge$setReconnectButtonText(Ref.method4().method67().method2("gui.components", "reconnectSeconds", new Object[]{number6}));
               }
            }
         }
      }
   }

   private void method2(EventServerPing event) {
      if (this.field8) {
         this.field8 = false;
      } else {
         this.field6 = 0;
      }
   }

   private int method3(GeneralSettings settings) {
      int number2 = (Integer)settings.method55().get() * (1 << this.field6);
      return Math.min(number2, 60);
   }
}
