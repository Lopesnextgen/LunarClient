package com.moonsworth.lunar.client.framework.feature.quickplay.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Calculator2Updater2 extends GuiWidget {
   private final Quickplay field16;
   private AnimatedValue field17 = new AnimatedValue(1613047077, -1711276033);
   private AnimatedValue field18 = new AnimatedValue(Integer.MIN_VALUE, -1610612736);
   private final boolean field19;

   public Calculator2Updater2(Quickplay var1) {
      super(null);
      this.field16 = var1;
      this.field19 = this.field16.method4() == null || this.field16.method4().isEmpty();
      this.method4(
         (var1x, var2) -> {
            if (var2 == 1) {
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new Bridge7Iterator(ThreadModuleDump63.method3().bridge$getCurrentScreen(), this.field16)));
               return true;
            }

            if (this.field19) {
               ThreadModuleDump63.method7().bridge$sendCommand(this.field16.method2());
            } else {
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new Bridge7Iterator3(new Bridge7Iterator2(), this.field16, this.field16.method1())));
            }

            return true;
         }
      );
   }

   public void update() {
   }

   public void method3(MixinHelper_4 var1, Data2 var2, boolean var3) {
      int var4 = this.field17.method2(var3 && this.method3(var2));
      int var5 = this.field18.method2(var3 && this.method3(var2));
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(var1, this.x, this.y, this.width, this.height, 4.0F, var4, 553648127, var5);
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field16.method1(), this.x, this.y, 22.0F, 22.0F, -1);
      FontRegistry.method17().method11(var1, this.field16.getName(), this.x + 30.0F, this.y + 4.0F, -1073741825, 805306368);
      if (this.field19) {
         FontRegistry.method7().method11(var1, "> Go To Lobby", this.x + 30.0F, this.y + 12.0F, Integer.MAX_VALUE, 805306368);
      } else {
         FontRegistry.method7().method11(var1, "Select Mode", this.x + 30.0F, this.y + 12.0F, Integer.MAX_VALUE, 805306368);
      }
   }

   public void method4(char var1, KeyCode var2) {
   }

   public void close() {
   }

   @Generated
   public void method3(AnimatedValue var1) {
      this.field17 = var1;
   }

   @Generated
   public void method4(AnimatedValue var1) {
      this.field18 = var1;
   }
}
