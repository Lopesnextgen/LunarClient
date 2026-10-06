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
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class QuickplayModeEntry extends GuiWidget {
   private final Quickplay field16;
   private final Quickplay field17;
   private String field18;
   private AnimatedValue field19 = new AnimatedValue(1613047077, -1711276033);
   private AnimatedValue field20 = new AnimatedValue(Integer.MIN_VALUE, -1610612736);
   private boolean field21;

   public QuickplayModeEntry(Bridge7_8 bridge7_81, Quickplay quickplay2, Quickplay quickplay3, String text4, String text5) {
      super(null);
      this.field16 = quickplay2;
      this.field17 = quickplay3;
      this.field18 = text5;
      this.field21 = this.field17 != null && this.field17.method4() != null && !this.field17.method4().isEmpty() && !text4.isEmpty();
      this.method4(
         (arg3x, arg4x) -> {
            if (this.field17 != null && !this.field21 && arg4x == 1) {
               Ref.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new QuickplayOverlay(Ref.method3().bridge$getCurrentScreen(), this.field17)));
               return true;
            }

            if (this.field16 == null) {
               if (text4.isEmpty()) {
                  Ref.method3().bridge$displayScreen(Bridge.method8().method18(bridge7_81));
               } else {
                  Ref.method7().bridge$sendCommand(text4);
               }

               return true;
            } else if (this.field21) {
               Ref.method3().bridge$displayScreen(Bridge.method8().method18(new QuickplayModesScreen(bridge7_81, this.field17, this.field16.method1())));
               return true;
            } else {
               Ref.method7().bridge$sendCommand(text4);
               return true;
            }
         }
      );
   }

   public void update() {
   }

   public void method3(MixinHelper_4 mixinhelper_41, Data2 data22, boolean flag3) {
      int number4 = this.field19.method2(flag3 && this.method3(data22));
      int number5 = this.field20.method2(flag3 && this.method3(data22));
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(mixinhelper_41, this.x, this.y, this.width, this.height, 4.0F, number4, 553648127, number5);
      String text6 = this.field18;
      if (this.field21) {
         text6 = "> " + text6;
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
   public Quickplay method3() {
      return this.field17;
   }

   @Generated
   public void method4(AnimatedValue hologramsimpl81) {
      this.field19 = hologramsimpl81;
   }

   @Generated
   public void method5(AnimatedValue hologramsimpl81) {
      this.field20 = hologramsimpl81;
   }
}
