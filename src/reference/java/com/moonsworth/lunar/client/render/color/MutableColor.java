package com.moonsworth.lunar.client.render.color;

import com.moonsworth.lunar.client.util.math.ColorUtils;
import org.jetbrains.annotations.Range;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;

public interface MutableColor extends RewindhandlersExtension, ColorMutator {
   @Override
   default void method2(int number1) {
      number1 &= 16777215;
      number1 |= this.method10() ? this.getColor() & 0xFF000000 : -16777216;
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(number1);
   }

   @Override
   default void method3(@Range(from = 0L, to = 255L) int number1) {
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(ColorUtils.method19(this.getColor(), number1));
   }

   @Override
   default void method4(@Range(from = 0L, to = 255L) int number1) {
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(ColorUtils.method20(this.getColor(), number1));
   }

   @Override
   default void method5(@Range(from = 0L, to = 255L) int number1) {
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(ColorUtils.method21(this.getColor(), number1));
   }

   @Override
   default void method6(@Range(from = 0L, to = 255L) int number1) {
      if (this.method10()) {
         this.IHORHICICIHRCOCRROCHHOROCHCHCR(ColorUtils.method22(this.getColor(), number1));
      }
   }

   @Override
   default void method11(@Range(from = 0L, to = 1L) float value1) {
      float[] items2 = ColorUtils.method40(this.getRed(), this.getGreen(), this.getBlue());
      this.method2(ColorUtils.method38(value1, items2[1], items2[2]));
   }

   @Override
   default void method12(@Range(from = 0L, to = 1L) float value1) {
      float[] items2 = ColorUtils.method40(this.getRed(), this.getGreen(), this.getBlue());
      this.method2(ColorUtils.method38(items2[0], value1, items2[2]));
   }

   @Override
   default void method13(@Range(from = 0L, to = 1L) float value1) {
      float[] items2 = ColorUtils.method40(this.getRed(), this.getGreen(), this.getBlue());
      this.method2(ColorUtils.method38(items2[0], items2[1], value1));
   }
}
