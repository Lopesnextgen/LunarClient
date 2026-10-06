package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class ColorPickerWidget extends GuiWidget {
   final ColorOption field16;

   public ColorPickerWidget(GuiWidget var1, ColorOption var2) {
      super(var1);
      this.field16 = var2;
      this.method4((var2x, var3) -> {
         ((ColorPickerOptionWidget)var1).method2(this);
         return true;
      });
   }

   public int method1(float var1) {
      return this.field16.method14(var1);
   }

   @Override
   public void update() {
   }

   public ColorPickerWidget method2() {
      return new ColorPickerWidget(this.field4, this.field16);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      LcuiScreen.method101(var1, this.x, this.y, this.width, this.height, 5.0F, this.field16.method14(this.x + this.y), true, true, true, true);
      LcuiScreen.method51(var1, this.x + 1.0F, this.y + 1.0F, this.width - 2.0F, this.height - 2.0F, 2.5F, -1342177281, true, true, true, true);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
