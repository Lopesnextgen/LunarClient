package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommand;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class SkyBlockCommandEntry extends GuiWidget {
   private final SkyBlockCommand field16;
   private AnimatedValue field17 = new AnimatedValue(1613047077, -1711276033);
   private AnimatedValue field18 = new AnimatedValue(Integer.MIN_VALUE, -1610612736);

   public SkyBlockCommandEntry(SkyBlockCommand fishing_21) {
      super(null);
      this.field16 = fishing_21;
      this.method4(
         (arg1x, arg2) -> {
            if (arg2 == 1) {
               Ref.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new SkyBlockCommandOptionsGui(Ref.method3().bridge$getCurrentScreen(), this.field16)));
               return true;
            }

            if (!this.field16.method3()) {
               Ref.method7().bridge$sendCommand("/" + this.field16.getCommand());
            } else if (!this.field16.method4()) {
               Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyBlockSubCommandSelectGui(new SkyBlockCommandsGui(), this.field16, this.field16.method1())));
            } else {
               Ref.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new SkyBlockSubCommandEditorGui(Ref.method3().bridge$getCurrentScreen(), this.field16)));
            }

            return true;
         }
      );
   }

   public void update() {
   }

   public void method3(MixinHelper_4 mixinhelper_41, Data2 data22, boolean flag3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(
         mixinhelper_41,
         this.x,
         this.y,
         this.width,
         this.height,
         4.0F,
         this.field17.method2(flag3 && this.method3(data22)),
         553648127,
         this.field18.method2(flag3 && this.method3(data22))
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(mixinhelper_41, this.field16.method1(), this.x, this.y, 22.0F, 22.0F, -1);
      FontRegistry.method17().method11(mixinhelper_41, this.field16.getPrettyName(), this.x + 30.0F, this.y + 4.0F, -1073741825, 805306368);
      if (!this.field16.method3()) {
         FontRegistry.method7().method11(mixinhelper_41, "> Run Command", this.x + 30.0F, this.y + 12.0F, Integer.MAX_VALUE, 805306368);
      } else {
         FontRegistry.method7().method11(mixinhelper_41, "Select Sub-Command", this.x + 30.0F, this.y + 12.0F, Integer.MAX_VALUE, 805306368);
      }
   }

   public void method4(char character1, KeyCode bridgetype_82) {
   }

   public void close() {
   }

   @Generated
   public SkyBlockCommand method3() {
      return this.field16;
   }

   @Generated
   public void method4(AnimatedValue hologramsimpl81) {
      this.field17 = hologramsimpl81;
   }

   @Generated
   public void method5(AnimatedValue hologramsimpl81) {
      this.field18 = hologramsimpl81;
   }
}
