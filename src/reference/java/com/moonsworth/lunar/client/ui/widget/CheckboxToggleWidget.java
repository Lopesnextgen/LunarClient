package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class CheckboxToggleWidget extends OptionWidget<ToggleOption> {
   public CheckboxToggleWidget(ToggleOption var1, GuiWidget var2) {
      super(var1, var2);
      this.method4((var1x, var2x) -> {
         var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(!(Boolean)var1.get());
         return true;
      });
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method2(var1, var2, var3, this.getHeight());
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = FontRegistry.method14().method4(this.option.getName());
      if (var4 + 10.0F > this.width - 20.0F) {
         FontRegistry.method10().method13(var1, this.option.getName(), this.x + 20.0F, this.y + 3.0F, -4079426);
      } else {
         FontRegistry.method14().method13(var1, this.option.getName(), this.x + 20.0F, this.y + 1.5F, -4079426);
      }

      LcuiScreen.method101(var1, this.x, this.y + 2.0F, 10.0F, 10.0F, 5.0F, var3 && this.method3(var2) ? -11561732 : -1437625092, true, true, true, true);
      LcuiScreen.method51(var1, this.x + 1.0F, this.y + 3.0F, 8.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      if ((Boolean)this.option.get()) {
         LcuiScreen.method39(var1, ResourceLocationBridge.create("lunar", "icons/settings/checked-14x14.png"), 3.0F, this.x + 2.0F, this.y + 3.5F, -1);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
