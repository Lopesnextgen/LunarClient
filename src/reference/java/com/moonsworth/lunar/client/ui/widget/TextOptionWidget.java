package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ButtonOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class TextOptionWidget extends OptionWidget<ButtonOption> {
   private final float field16;
   private final TextLabelWidget field17;

   public TextOptionWidget(ButtonOption var1, GuiWidget var2, float var3) {
      super(var1, var2);
      this.field17 = new TextLabelWidget(this, var1.getName(), FontRegistry.method9());
      this.field17.method18(-0.7F);
      this.field16 = var3;
      this.method4((var2x, var3x) -> {
         if (this.field17.method3(var2x)) {
            var1.method7().run();
            return true;
         } else {
            return false;
         }
      });
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
      this.field17.update();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.field17.method2(this.x, this.y, this.field16, 12.0F);
      this.field17.method3(var1, var2, var3);
   }

   @Override
   public boolean method5(MarkerModel.Data2 var1) {
      return super.method5(var1) && this.field17.method3(var1);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
