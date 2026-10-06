package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.NumberOptionWidget;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class FloatOption extends AbstractFloatOption {
   protected FloatOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Float> var2, float var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getValueAsString() {
      float var1 = this.get();
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      return var2 != null && var2.method6() ? Math.round(var1) + "" : String.format("%.2f", var1);
   }

   public void method1(@NotNull Float var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         if (var2.method1() && var1.compareTo((Float)var2.getMin()) < 0) {
            var1 = (Float)var2.getMin();
         }

         if (var2.method2() && var1.compareTo((Float)var2.getMax()) > 0) {
            var1 = (Float)var2.getMax();
         }

         if (var2.method3() != 0) {
            float var3 = (float)Math.round(var1 * var2.method3()) / var2.method3();
            super.method10(var3);
            return;
         }
      }

      super.method10(var1);
   }

   public void method2(FloatOption var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         this.method1(ThreadModuleDump67.method3(var1.get(), (Float)var2.getMin(), (Float)var2.getMax()));
      } else {
         this.method1(var1.get());
      }
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new NumberOptionWidget<>(this, var1);
   }

   public static class Data extends FloatSliderOptionBuilder<FloatOption.Data, FloatOption> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      protected FloatOption method11() {
         return new FloatOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7);
      }
   }
}
