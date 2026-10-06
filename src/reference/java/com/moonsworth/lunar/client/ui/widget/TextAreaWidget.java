package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class TextAreaWidget extends TextLabelWidget {
   public TextAreaWidget(GuiWidget var1, String var2) {
      super(var1, var2, FontRegistry.field10);
      this.field22 = new AnimatedValue(483186431, 651484414);
      this.field24 = true;
   }

   @Override
   protected void method4(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean flag) {
      int var4 = this.field22.method2(this.active || flag && this.method1(var2));
      LcuiScreen.method117(var1, this.x, this.y, this.width, this.height, 8.0F, var4);
   }
}
