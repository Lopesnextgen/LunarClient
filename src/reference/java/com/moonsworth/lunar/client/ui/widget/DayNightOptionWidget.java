package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class DayNightOptionWidget extends OptionWidget<IntegerOption> {
   private final ResourceLocationBridge field16 = ResourceLocationBridge.create("lunar", "icons/sun-64.png");
   private final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "icons/moon-64.png");
   private final NumberSliderWidget<Integer> field18;

   public DayNightOptionWidget(IntegerOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field18 = new NumberSliderWidget<>(var1, this);
      this.option = var1;
      this.method4((var1x, var2x) -> this.field18.method6(var1x) ? this.field18.method13(var1x, var2x) : false);
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3);
      this.field18.method2(var1 + var3 / 3.0F, var2 + 16.0F, var3 - var3 / 3.0F, 14.0F);
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 30.0F;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      LcuiScreen.method31(var1, this.field17, this.field18.getX(), this.y + 3.0F, 7.5F, 7.5F, -1);
      LcuiScreen.method66(var1, this.field18.getX() + 3.5F, this.y + 13.0F, this.field18.getX() + 4.5F, this.y + 16.0F, 1623310526);
      LcuiScreen.method31(var1, this.field16, this.field18.getX() + this.field18.getWidth() / 2.0F - 9.5F, this.y + 2.0F, 10.0F, 10.0F, -1);
      LcuiScreen.method66(
         var1,
         this.field18.getX() + this.field18.getWidth() / 2.0F - 3.5F,
         this.y + 13.0F,
         this.field18.getX() + this.field18.getWidth() / 2.0F - 4.5F,
         this.y + 16.0F,
         1623310526
      );
      LcuiScreen.method31(var1, this.field17, this.field18.getX() + this.field18.getWidth() - 7.0F, this.y + 3.0F, 7.5F, 7.5F, -1);
      LcuiScreen.method66(
         var1,
         this.field18.getX() + this.field18.getWidth() - 3.5F,
         this.y + 13.0F,
         this.field18.getX() + this.field18.getWidth() - 4.5F,
         this.y + 16.0F,
         1623310526
      );
      this.field18.method3(var1, var2, var3);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
