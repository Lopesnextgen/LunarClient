package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.DynamicDropdownOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.List;

public class SliderOptionWidget extends OptionWidget<DynamicDropdownOption> {
   private final IconWidget field16 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-left-18x18.png"), 4.5F);
   private final IconWidget field17 = new IconWidget(this, ResourceLocationBridge.create("lunar", "icons/settings/arrow-right-18x18.png"), 4.5F);

   public SliderOptionWidget(DynamicDropdownOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field16.method4((var1x, var2x) -> {
         List var3;
         try {
            var3 = (List)var1.method7().call();
         } catch (Exception var5) {
            throw new RuntimeException(var5);
         }

         if (var3.isEmpty()) {
            return true;
         }

         int var4 = Math.max(0, var3.indexOf(var1.get()));
         if (var4 - 1 < 0) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC((String)var3.get(var3.size() - 1));
         } else {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC((String)var3.get(var4 - 1));
         }

         return true;
      });
      this.field17.method4((var1x, var2x) -> {
         List var3;
         try {
            var3 = (List)var1.method7().call();
         } catch (Exception var5) {
            throw new RuntimeException(var5);
         }

         if (var3.isEmpty()) {
            return true;
         }

         int var4 = Math.max(0, var3.indexOf(var1.get()));
         if (var4 + 1 >= var3.size()) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC((String)var3.get(0));
         } else {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC((String)var3.get(var4 + 1));
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
      this.field16.method1(var1 + var3 - 155.0F, var2);
      this.field17.method1(var1 + var3 - 30.0F, var2);
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
      String var4 = this.option.method8() != null ? (String)this.option.method8().apply((String)this.option.get()) : (String)this.option.get();
      if (FontRegistry.method17().method4(var4) > 110.0F) {
         var4 = FontRegistry.method17().method21(var4, 110.0F - FontRegistry.method17().method4("...")) + "...";
      }

      FontRegistry.method17().method14(var1, var4, this.x + this.width - 84.0F, this.y + 2.5F, -1346256706);
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
