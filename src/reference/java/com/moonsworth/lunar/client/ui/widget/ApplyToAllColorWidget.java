package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;

public class ApplyToAllColorWidget extends ColorPickerOptionWidget {
   private final TextLabelWidget field36;
   private final ThreadModuleDump51 field37;

   public ApplyToAllColorWidget(ColorOption var1, GuiWidget var2, ThreadModuleDump51 var3) {
      super(var1, var2);
      this.field37 = var3;
      this.field36 = new TextLabelWidget(this, "applyToAll", FontRegistry.method9());
      this.field36.method18(-0.7F);
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         (var1x, var2x) -> this.field36.method3(var1x) ? this.field37.accept(var1x, var2x) : this.method4(var1x, var2x)
      );
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3 - 80.0F);
      this.field36.method2(var1 + var3 - 70.0F, var2, 70.0F, 12.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      super.method3(var1, var2, var3);
      this.field36.method3(var1, var2, var3);
   }

   @Override
   public boolean method1(MarkerModel.Data2 var1) {
      return this.ORCICOCIOIIHCOOROIICOHCCRORCHH()
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() > this.x
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < this.x + this.width + 80.0F
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() > this.y
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + this.height;
   }
}
