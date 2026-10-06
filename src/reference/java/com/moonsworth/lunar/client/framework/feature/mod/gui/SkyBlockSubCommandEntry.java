package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommand;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class SkyBlockSubCommandEntry extends GuiWidget {
   private final SkyBlockCommand field16;
   private final SkyBlockCommand field17;
   private AnimatedValue field18 = new AnimatedValue(1613047077, -1711276033);
   private AnimatedValue field19 = new AnimatedValue(Integer.MIN_VALUE, -1610612736);

   public SkyBlockSubCommandEntry(Bridge7_8 bridge7_81, SkyBlockCommand fishing_22, SkyBlockCommand fishing_23) {
      super(null);
      this.field16 = fishing_22;
      this.field17 = fishing_23;
      this.method4(
         (arg2x, arg3x) -> {
            if (this.field17 == null) {
               if (bridge7_81 != null) {
                  Ref.method3().bridge$displayScreen(Bridge.method8().method18(bridge7_81));
                  return true;
               } else {
                  return false;
               }
            } else {
               if (arg3x == 1) {
                  Ref.method3()
                     .bridge$displayScreen(Bridge.method8().method18(new SkyBlockCommandOptionsGui(Ref.method3().bridge$getCurrentScreen(), this.field17)));
                  return true;
               }

               if (!this.field17.method3()) {
                  Ref.method7().bridge$sendCommand("/" + this.field17.getCommand());
               } else if (!this.field17.method4()) {
                  Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyBlockSubCommandSelectGui(bridge7_81, this.field17, this.field17.method1())));
               } else {
                  Ref.method3()
                     .bridge$displayScreen(Bridge.method8().method18(new SkyBlockSubCommandEditorGui(Ref.method3().bridge$getCurrentScreen(), this.field17)));
               }

               return true;
            }
         }
      );
   }

   public void update() {
   }

   public void method3(MixinHelper_4 mixinhelper_41, Data2 data22, boolean flag3) {
      int number4 = this.field18.method2(flag3 && this.method3(data22));
      int number5 = this.field19.method2(flag3 && this.method3(data22));
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(mixinhelper_41, this.x, this.y, this.width, this.height, 4.0F, number4, 553648127, number5);
      String text6 = "<";
      if (this.field17 != null) {
         text6 = this.field17.getPrettyName();
         if (this.field17.method3()) {
            text6 = "> " + text6;
         }
      }

      float value7 = FontRegistry.method17().method4(text6);
      if (value7 + 12.0F > this.width) {
         FontRegistry.method6().method11(mixinhelper_41, text6, this.x + 6.0F, this.y + 5.0F, -1073741825, 805306368);
      } else {
         FontRegistry.method17().method11(mixinhelper_41, text6, this.x + 6.0F, this.y + 4.0F, -1073741825, 805306368);
      }
   }

   public void method4(char character1, KeyCode bridgetype_82) {
   }

   public void close() {
   }

   @Generated
   public SkyBlockCommand method3() {
      return this.field17;
   }

   @Generated
   public void method4(AnimatedValue hologramsimpl81) {
      this.field18 = hologramsimpl81;
   }

   @Generated
   public void method5(AnimatedValue hologramsimpl81) {
      this.field19 = hologramsimpl81;
   }
}
