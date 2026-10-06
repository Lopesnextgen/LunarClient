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

public class DoubleOption extends AbstractDoubleOption {
   protected DoubleOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Double> var2, double var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getValueAsString() {
      double var1 = this.get();
      NumberRule var3 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      return var3 != null && var3.method6() ? Math.round(var1) + "" : String.format("%.2f", var1);
   }

   public void method1(@NotNull Double var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         if (var2.method1() && var1.compareTo((Double)var2.getMin()) < 0) {
            var1 = (Double)var2.getMin();
         }

         if (var2.method2() && var1.compareTo((Double)var2.getMax()) > 0) {
            var1 = (Double)var2.getMax();
         }

         if (var2.method3() != 0) {
            double var3 = (double)Math.round(var1 * var2.method3()) / var2.method3();
            super.method10(var3);
            return;
         }
      }

      super.method10(var1);
   }

   public void method2(DoubleOption var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         this.method1(ThreadModuleDump67.method2(var1.get(), (Double)var2.getMin(), (Double)var2.getMax()));
      } else {
         this.method1(var1.get());
      }
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new NumberOptionWidget<>(this, var1);
   }

   public static class Data extends DoubleRangeOptionBuilder<DoubleOption.Data, DoubleOption> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      protected DoubleOption method11() {
         return new DoubleOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7);
      }
   }
}
