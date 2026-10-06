package com.moonsworth.lunar.client.framework.feature.quickplay.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Calculator2Updater extends GuiWidget {
   private final Quickplay field16;
   private final Quickplay field17;
   private String field18;
   private AnimatedValue field19 = new AnimatedValue(1613047077, -1711276033);
   private AnimatedValue field20 = new AnimatedValue(Integer.MIN_VALUE, -1610612736);
   private boolean field21;

   public Calculator2Updater(Bridge7_8 var1, Quickplay var2, Quickplay var3, String var4, String var5) {
      super(null);
      this.field16 = var2;
      this.field17 = var3;
      this.field18 = var5;
      this.field21 = this.field17 != null && this.field17.method4() != null && !this.field17.method4().isEmpty() && !var4.isEmpty();
      this.method4(
         (var3x, var4x) -> {
            if (this.field17 != null && !this.field21 && var4x == 1) {
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new Bridge7Iterator(ThreadModuleDump63.method3().bridge$getCurrentScreen(), this.field17)));
               return true;
            }

            if (this.field16 == null) {
               if (var4.isEmpty()) {
                  ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var1));
               } else {
                  ThreadModuleDump63.method7().bridge$sendCommand(var4);
               }

               return true;
            } else if (this.field21) {
               ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Bridge7Iterator3(var1, this.field17, this.field16.method1())));
               return true;
            } else {
               ThreadModuleDump63.method7().bridge$sendCommand(var4);
               return true;
            }
         }
      );
   }

   public void update() {
   }

   public void method3(MixinHelper_4 var1, Data2 var2, boolean var3) {
      int var4 = this.field19.method2(var3 && this.method3(var2));
      int var5 = this.field20.method2(var3 && this.method3(var2));
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(var1, this.x, this.y, this.width, this.height, 4.0F, var4, 553648127, var5);
      String var6 = this.field18;
      if (this.field21) {
         var6 = "> " + var6;
      }

      float var7 = FontRegistry.method17().method4(var6);
      if (var7 + 12.0F > this.width) {
         FontRegistry.method6().method11(var1, var6, this.x + 6.0F, this.y + 5.0F, -1073741825, 805306368);
      } else {
         FontRegistry.method17().method11(var1, var6, this.x + 6.0F, this.y + 4.0F, -1073741825, 805306368);
      }
   }

   public void method4(char var1, KeyCode var2) {
   }

   public void close() {
   }

   @Generated
   public Quickplay method3() {
      return this.field17;
   }

   @Generated
   public void method4(AnimatedValue var1) {
      this.field19 = var1;
   }

   @Generated
   public void method5(AnimatedValue var1) {
      this.field20 = var1;
   }
}
