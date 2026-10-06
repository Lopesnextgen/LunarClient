package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;

public class ApplyToAllNumberWidget<T extends Number & Comparable<T>> extends NumberOptionWidget<T> {
   private final TextLabelWidget field20;
   private final ThreadModuleDump51 field21;

   public ApplyToAllNumberWidget(ClientOption<T> var1, GuiWidget var2, ThreadModuleDump51 var3) {
      super(var1, var2, true);
      this.field21 = var3;
      this.field20 = new TextLabelWidget(this, "applyToAll", FontRegistry.method9());
      this.field20.method18(-0.7F);
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         (var1x, var2x) -> this.field20.method3(var1x) ? this.field21.accept(var1x, var2x) : this.method4(var1x, var2x)
      );
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1 + 24.0F, var2, var3 - 104.0F);
      this.field20.method2(var1 + var3 - 70.0F, var2, 70.0F, 12.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x - 24.0F, this.y + 1.5F, -4079426);
      this.method3(var1, var2, var3);
      this.field16.method3(var1, var2, var3);
      if (this.field18 != null && !this.option.isDefault()) {
         this.field18.method3(var1, var2, var3);
      }

      this.field20.method3(var1, var2, var3);
   }

   @Override
   public boolean method1(MarkerModel.Data2 var1) {
      return this.ORCICOCIOIIHCOOROIICOHCCRORCHH()
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() > this.x - 24.0F
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < this.x - 24.0F + this.width + 104.0F
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() > this.y
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + this.height;
   }
}
