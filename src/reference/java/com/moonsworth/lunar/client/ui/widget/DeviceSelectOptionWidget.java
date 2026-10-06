package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.DropdownOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class DeviceSelectOptionWidget<T> extends OptionWidget<DropdownOption<T>> {
   private final IconWidget field16 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-left-18x18.png"), 4.5F);
   private final IconWidget field17 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-right-18x18.png"), 4.5F);

   public DeviceSelectOptionWidget(DropdownOption<T> var1, GuiWidget var2) {
      super(var1, var2);
      this.field16.method4((var1x, var2x) -> {
         int var3 = var1.method7().indexOf(var1.get());
         if (var3 - 1 >= 0) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.method7().get(var3 - 1));
            return true;
         } else {
            return false;
         }
      });
      this.field17.method4((var1x, var2x) -> {
         int var3 = var1.method7().indexOf(var1.get());
         if (var3 + 1 < var1.method7().size()) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.method7().get(var3 + 1));
            return true;
         } else {
            return false;
         }
      });
      this.method4((var1x, var2x) -> {
         if (this.field16.method3(var1x)) {
            return this.field16.method6(var1x, var2x);
         } else {
            return this.field17.method3(var1x) ? this.field17.method6(var1x, var2x) : false;
         }
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
      String var4 = "No Device Selected";
      if (this.option.get() != null) {
         var4 = this.option.get().toString();
      }

      if (var4.length() > 30) {
         var4 = var4.substring(0, 30) + "...";
      }

      float var5 = FontRegistry.method17().method13(var1, var4, this.x + this.width - FontRegistry.method17().method4(var4) - 10.0F, this.y + 2.5F, -1346256706);
      this.field16.method1(var5 - FontRegistry.method17().method4(var4) - 12.0F, this.y);
      this.field17.method1(var5, this.y);
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      this.field16.method6(this.option.get() == null || this.option.method7().indexOf(this.option.get()) == 0);
      this.field16.method3(var1, var2, var3);
      this.field17.method6(this.option.get() == null || this.option.method7().indexOf(this.option.get()) == this.option.method7().size() - 1);
      this.field17.method3(var1, var2, var3);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
