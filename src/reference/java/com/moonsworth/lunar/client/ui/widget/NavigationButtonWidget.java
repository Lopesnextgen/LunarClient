package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.NamedDropdownOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.List;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public class NavigationButtonWidget<T> extends OptionWidget<NamedDropdownOption<T>> {
   private final IconWidget field16;
   private final IconWidget field17;
   private final ToggleButtonWidget field18;
   private final Function<T, String> field19;

   public NavigationButtonWidget(NamedDropdownOption<T> var1, GuiWidget var2, @Nullable Function<T, String> var3) {
      super(var1, var2);
      this.field19 = var3;
      this.field16 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-left-18x18.png"), 4.5F);
      this.field17 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-right-18x18.png"), 4.5F);
      this.field18 = new ToggleButtonWidget(this, "resetToDefaults", LcuiScreen.field1);
      this.field16.method4((var1x, var2x) -> {
         List var3x = var1.getOptions();
         if (var3x.isEmpty()) {
            return true;
         }

         int var4 = var1.getOptions().indexOf(var1.get());
         if (var4 - 1 < 0) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.getOptions().get(var3x.size() - 1));
         } else {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.getOptions().get(var4 - 1));
         }

         return true;
      });
      this.field17.method4((var1x, var2x) -> {
         List var3x = var1.getOptions();
         if (var3x.isEmpty()) {
            return true;
         }

         int var4 = var1.getOptions().indexOf(var1.get());
         if (var4 + 1 > var3x.size() - 1) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.getOptions().get(0));
         } else {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.getOptions().get(var4 + 1));
         }

         return true;
      });
      this.field18.method4((var1x, var2x) -> {
         this.method7();
         return true;
      });
      this.method4((var1x, var2x) -> {
         if (this.field16.method3(var1x)) {
            return this.field16.method6(var1x, var2x);
         } else if (this.field17.method3(var1x)) {
            return this.field17.method6(var1x, var2x);
         } else {
            return this.field18.method3(var1x) ? this.field18.method6(var1x, var2x) : false;
         }
      });
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method2(var1, var2, var3, this.getHeight());
      this.field16.method1(var1 + var3 - 115.0F, var2);
      this.field17.method1(var1 + var3 - 30.0F, var2);
      this.field18.method2(var1 + var3 - 9.0F, var2 + 3.0F, 8.0F, 8.0F);
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
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      this.field16.method3(var1, var2, var3);
      this.field17.method3(var1, var2, var3);
      if (!this.option.isDefault()) {
         this.field18.method3(var1, var2, var3);
      }

      String var4 = this.field19 == null ? this.option.get().toString() : this.field19.apply((T)this.option.get());
      FontRegistry.method17().method14(var1, var4, this.x + this.width - 64.0F, this.y + 2.5F, -1346256706);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Override
   public boolean method5(MarkerModel.Data2 var1) {
      return this.method3(var1) && var1.method9() < this.field16.getX() - 5.0F;
   }
}
