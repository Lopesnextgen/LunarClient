package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;

public class ApplyToAllInfoWidget extends HoverInfoOptionWidget {
   private final TextLabelWidget field18;
   private final ThreadModuleDump51 field19;

   public ApplyToAllInfoWidget(ToggleOption var1, GuiWidget var2, ThreadModuleDump51 var3) {
      super(var1, var2);
      this.field19 = var3;
      this.field18 = new TextLabelWidget(this, "applyToAll", FontRegistry.method9());
      this.field18.method18(-0.7F);
      this.method4(
         (var1x, var2x) -> this.field18.method3(var1x) ? this.field19.accept(var1x, var2x) : this.method3(var1x, var2x)
      );
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3);
      this.field18.method2(var1 + var3 - 70.0F, var2, 70.0F, 12.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      super.method3(var1, var2, var3);
      this.field18.method3(var1, var2, var3);
   }
}
