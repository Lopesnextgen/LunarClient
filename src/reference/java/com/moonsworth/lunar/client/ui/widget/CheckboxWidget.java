package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class CheckboxWidget extends OptionWidget<ToggleOption> {
   private Runnable field16;

   public CheckboxWidget(ToggleOption var1, GuiWidget var2) {
      super(var1, var2);
      this.method4((var2x, var3) -> {
         var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(!(Boolean)var1.get());
         if (this.field16 != null) {
            this.field16.run();
         }

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
      FontRegistry.method12().method13(var1, this.option.getName(), this.x + 13.0F, this.y + 1.5F, -4079426);
      LcuiScreen.method101(var1, this.x, this.y + 2.0F, 8.0F, 8.0F, 4.0F, var3 && this.method3(var2) ? -11561732 : -1437625092, true, true, true, true);
      LcuiScreen.method51(var1, this.x + 1.0F, this.y + 3.0F, 6.0F, 6.0F, 2.0F, 905969663, true, true, true, true);
      if ((Boolean)this.option.get()) {
         LcuiScreen.method39(var1, ResourceLocationBridge.create("lunar", "icons/settings/checked-14x14.png"), 3.0F, this.x + 1.0F, this.y + 2.5F, -1);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Generated
   public void method4(Runnable var1) {
      this.field16 = var1;
   }
}
