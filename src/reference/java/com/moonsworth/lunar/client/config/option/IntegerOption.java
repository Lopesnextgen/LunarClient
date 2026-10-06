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

public class IntegerOption extends AbstractIntegerOption {
   protected IntegerOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Integer> var2, int var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getValueAsString() {
      return this.get() + "";
   }

   public void method1(@NotNull Integer var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         if (var2.method1() && var1.compareTo((Integer)var2.getMin()) < 0) {
            var1 = (Integer)var2.getMin();
         }

         if (var2.method2() && var1.compareTo((Integer)var2.getMax()) > 0) {
            var1 = (Integer)var2.getMax();
         }
      }

      super.method10(var1);
   }

   public void method2(IntegerOption var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         this.method1(ThreadModuleDump67.method6(var1.get(), (Integer)var2.getMin(), (Integer)var2.getMax()));
      } else {
         this.method1(var1.get());
      }
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new NumberOptionWidget<>(this, var1);
   }

   public static class Data extends IntegerRangeOptionBuilder<IntegerOption.Data, IntegerOption> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      protected IntegerOption method11() {
         return new IntegerOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7);
      }
   }
}
