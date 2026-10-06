package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class ListOptionRowWidget extends ListOptionWidget<LabelOption> implements WidgetHooks {
   public ListOptionRowWidget(LabelOption var1, GuiWidget var2) {
      super(var1, var2);
   }

   @Override
   protected float getHeight2() {
      return 8.0F;
   }

   @Override
   protected boolean method7(MarkerModel.Data2 var1) {
      return false;
   }

   @Override
   protected float method10() {
      return -4.0F;
   }

   @Override
   protected float method9() {
      return 3.0F;
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      var1 += 14.0F;
      super.method1(var1, var2, var3);
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : this.height + 3.0F;
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method9().method13(var1, this.option.getName().toUpperCase(), this.x - 6.0F, this.y + 3.0F, -4079426);
      this.method3(var1, var2);
      super.method3(var1, var2, var3);
   }
}
