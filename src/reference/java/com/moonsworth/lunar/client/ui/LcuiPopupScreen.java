package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.ArrayList;
import java.util.List;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;

public abstract class LcuiPopupScreen extends LcuiScreen {
   protected final float field19;
   protected final float field20;
   protected float field21;
   protected float field22;
   private List<com.moonsworth.lunar.client.ui.widget.OptionWidget<?>> field23;
   protected float field24 = 20.0F;

   public LcuiPopupScreen(float var1, float var2) {
      this.field19 = var1;
      this.field20 = var2;
   }

   @Override
   protected List<GuiWidget> method4() {
      List var1 = super.method4();
      List var2 = this.method148();
      ArrayList var3 = new ArrayList(var1);
      this.field23 = new ArrayList<>();
      var2.stream().map(var0 -> var0.method18(null)).forEach(var2x -> {
         this.field23.add((com.moonsworth.lunar.client.ui.widget.OptionWidget<?>)var2x);
         var3.add(var2x);
      });
      return var3;
   }

   @Override
   public void initGui() {
      this.field21 = this.method22() / 2.0F - this.field19 / 2.0F;
      this.field22 = this.method23() / 2.0F - this.field20 / 2.0F;
      super.initGui();
   }

   @Override
   public void method1(MixinHelper_4 var1, Bridge_65 var2, float var3) {
      float var4 = 0.0F;
      int var5 = 0;
      float var6 = this.field22 + 27.0F;

      for (com.moonsworth.lunar.client.ui.widget.OptionWidget var8 : this.field23) {
         if (!var8.getOption().isHidden()) {
            boolean var9 = var8.method3();
            boolean var10 = var8.getOption() instanceof LabelOption;
            float var11 = this.field19 - this.field24;
            float var12 = var9 ? var11 / 2.0F : var11;
            if (var5 == 2) {
               var5 = 0;
               var6 += var4;
               var4 = 0.0F;
            }

            if (!var9) {
               var5 = 0;
               var6 += var4;
               var4 = 0.0F;
            }

            if (var8.getHeight() > var4) {
               var4 = var8.getHeight() + 6.0F;
            }

            var8.method1(this.field21 + (var10 ? 0.0F : 15.0F) + var5 * (this.field19 / 2.0F), var6, var12);
            if (!var9) {
               var6 += var4;
               var4 = 0.0F;
            } else {
               if (this.field23.get(this.field23.size() - 1) == var8) {
                  var6 += var4;
               }

               var5++;
            }
         }
      }

      super.method1(var1, var2, var3);
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      var1.method44(var0 -> var0.method29().method18());
      var1.push();
      var1.method38(0.0F, 0.0F, 100.0F);

      for (com.moonsworth.lunar.client.ui.widget.OptionWidget var4 : this.field23) {
         com.moonsworth.lunar.client.ui.widget.OptionWidget var5 = var4.method6(var2);
         if (var5 != null) {
            var5.method3(var1, var2);
            break;
         }
      }

      var1.pop();
   }
}
