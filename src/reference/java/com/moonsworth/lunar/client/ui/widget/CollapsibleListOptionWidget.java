package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.CategoryOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.Locale;

public class CollapsibleListOptionWidget extends ListOptionWidget<CategoryOption> implements WidgetHooks {
   public CollapsibleListOptionWidget(CategoryOption var1, GuiWidget var2) {
      super(var1, var2);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = FontRegistry.method9().method13(var1, this.option.getName().toUpperCase(Locale.ROOT), this.x, this.y + 1.5F, -4079426);
      if ((Boolean)this.option.get()) {
         LcuiScreen.method31(var1, ResourceLocationBridge.create("lunar", "icons/down-arrow-32x32.png"), var4 + 3.0F, this.y + 1.5F, 7.0F, 7.0F, -1);
      } else {
         LcuiScreen.method31(var1, ResourceLocationBridge.create("lunar", "icons/right-arrow-32x32.png"), var4 + 3.0F, this.y + 1.5F, 7.0F, 7.0F, -1);
      }

      if ((Boolean)this.option.get()) {
         this.method3(var1, var2);
      }

      super.method3(var1, var2, var3);
   }

   @Override
   protected float getWidth2() {
      return 100.0F;
   }

   @Override
   protected float getHeight2() {
      return 10.0F;
   }

   @Override
   protected boolean method7(MarkerModel.Data2 var1) {
      this.option.OIRHOOIICOCIOOHICRRRICORIHHIHC(!(Boolean)this.option.get());
      return super.method7(var1);
   }

   @Override
   protected float method8() {
      return 10.0F;
   }

   @Override
   protected float method10() {
      return -20.0F;
   }

   @Override
   public float getHeight() {
      return this.option.get() ? this.height : this.getHeight2();
   }
}
