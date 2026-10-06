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

public class ByteOption extends AbstractByteOption {
   protected ByteOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Byte> var2, byte var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getValueAsString() {
      return this.get() + "";
   }

   public void method1(@NotNull Byte var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         if (var2.method1() && var1.compareTo((Byte)var2.getMin()) < 0) {
            var1 = (Byte)var2.getMin();
         }

         if (var2.method2() && var1.compareTo((Byte)var2.getMax()) > 0) {
            var1 = (Byte)var2.getMax();
         }
      }

      super.method10(var1);
   }

   public void method2(ByteOption var1) {
      NumberRule var2 = (NumberRule)this.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (var2 != null) {
         this.method1(ThreadModuleDump67.method4(var1.get(), (Byte)var2.getMin(), (Byte)var2.getMax()));
      } else {
         this.method1(var1.get());
      }
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new NumberOptionWidget<>(this, var1);
   }

   public static class Data extends ByteRangeOptionBuilder<ByteOption.Data, ByteOption> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      protected ByteOption method11() {
         return new ByteOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7);
      }
   }
}
