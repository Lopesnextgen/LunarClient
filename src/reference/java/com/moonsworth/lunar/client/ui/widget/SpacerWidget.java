package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class SpacerWidget extends OptionWidget<LabelOption> {
   public SpacerWidget(LabelOption var1, GuiWidget var2) {
      super(var1, var2);
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 12.0F;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean flag) {
      FontRegistry.method9().method13(var1, this.option.getName().toUpperCase(), this.x + (this.option.method7() ? 0.0F : 8.0F), this.y + 3.0F, -4079426);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
