package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;

public class ApplyToAllEnumWidget<T extends Enum<T> & OptionEnumValue> extends CyclingEnumOptionWidget<T> {
   private final TextLabelWidget field19;
   private final ThreadModuleDump51 field20;

   public ApplyToAllEnumWidget(EnumOption<T> var1, GuiWidget var2, Enum<T>[] var3, ThreadModuleDump51 var4) {
      super(var1, var2, var3);
      this.field20 = var4;
      this.field19 = new TextLabelWidget(this, "applyToAll", FontRegistry.method9());
      this.field19.method18(-0.7F);
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3 - 80.0F);
      this.field19.method2(var1 + var3 - 70.0F, var2, 70.0F, 12.0F);
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      return this.field19.method3(var1) ? this.field20.accept(var1, var2) : super.method6(var1, var2);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      super.method3(var1, var2, var3);
      this.field19.method3(var1, var2, var3);
   }

   @Override
   public boolean method1(MarkerModel.Data2 var1) {
      return this.ORCICOCIOIIHCOOROIICOHCCRORCHH()
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.x
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + this.width + 80.0F
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > this.y
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + this.height;
   }
}
