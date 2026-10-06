package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class CycleOptionWidget<T extends Enum<T> & OptionEnumValue> extends OptionWidget<EnumOption<T>> {
   private final IconWidget field16 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-left-18x18.png"), 4.5F);
   private final IconWidget field17 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-right-18x18.png"), 4.5F);

   public CycleOptionWidget(EnumOption<T> var1, GuiWidget var2) {
      super(var1, var2);
      Class var3 = ((Enum)var1.get()).getClass();
      Enum[] var4 = (Enum[])var3.getEnumConstants();
      int var5 = var4.length;
      this.field16.method4((var3x, var4x) -> {
         int var5x = ((Enum)var1.get()).ordinal();
         if (var5x - 1 < 0) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var4[var5 - 1]);
         } else {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var4[var5x - 1]);
         }

         return true;
      });
      this.field17.method4((var3x, var4x) -> {
         int var5x = ((Enum)var1.get()).ordinal();
         if (var5x + 1 >= var5) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var4[0]);
         } else {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var4[var5x + 1]);
         }

         return true;
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
      this.field16.method1(var1 + var3 - 60.0F, var2);
      this.field17.method1(var1 + var3 - 22.0F, var2);
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
      FontRegistry.method12().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      this.field16.method3(var1, var2, var3);
      this.field17.method3(var1, var2, var3);
      FontRegistry.method17().method14(var1, ((Enum)this.option.get()).toString(), this.x + this.width - 34.0F, this.y + 2.5F, -1346256706);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
