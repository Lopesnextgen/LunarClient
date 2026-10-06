package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_2;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Calculator2Updater extends GuiWidget {
   private final Fishing_2 field16;
   private AnimatedValue field17 = new AnimatedValue(1613047077, -1711276033);
   private AnimatedValue field18 = new AnimatedValue(Integer.MIN_VALUE, -1610612736);

   public Calculator2Updater(Fishing_2 var1) {
      super(null);
      this.field16 = var1;
      this.method4(
         (var1x, var2) -> {
            if (var2 == 1) {
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new Gui2(ThreadModuleDump63.method3().bridge$getCurrentScreen(), this.field16)));
               return true;
            }

            if (!this.field16.method3()) {
               ThreadModuleDump63.method7().bridge$sendCommand("/" + this.field16.getCommand());
            } else if (!this.field16.method4()) {
               ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Gui_2(new Gui3(), this.field16, this.field16.method1())));
            } else {
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new Gui4(ThreadModuleDump63.method3().bridge$getCurrentScreen(), this.field16)));
            }

            return true;
         }
      );
   }

   public void update() {
   }

   public void method3(MixinHelper_4 var1, Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(
         var1,
         this.x,
         this.y,
         this.width,
         this.height,
         4.0F,
         this.field17.method2(var3 && this.method3(var2)),
         553648127,
         this.field18.method2(var3 && this.method3(var2))
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field16.method1(), this.x, this.y, 22.0F, 22.0F, -1);
      FontRegistry.method17().method11(var1, this.field16.getPrettyName(), this.x + 30.0F, this.y + 4.0F, -1073741825, 805306368);
      if (!this.field16.method3()) {
         FontRegistry.method7().method11(var1, "> Run Command", this.x + 30.0F, this.y + 12.0F, Integer.MAX_VALUE, 805306368);
      } else {
         FontRegistry.method7().method11(var1, "Select Sub-Command", this.x + 30.0F, this.y + 12.0F, Integer.MAX_VALUE, 805306368);
      }
   }

   public void method4(char var1, KeyCode var2) {
   }

   public void close() {
   }

   @Generated
   public Fishing_2 method3() {
      return this.field16;
   }

   @Generated
   public void method4(AnimatedValue var1) {
      this.field17 = var1;
   }

   @Generated
   public void method5(AnimatedValue var1) {
      this.field18 = var1;
   }
}
