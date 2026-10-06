package com.moonsworth.lunar.client.render.color;

import com.moonsworth.lunar.client.util.math.ColorUtils;
import org.jetbrains.annotations.Range;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;

public interface AnimatedColor extends RewindhandlersExtension {
   @Override
   boolean method14();

   @Range(from = 1L, to = 100L) int method11();

   default @Range(from = 0L, to = 255L) int method3(float value1) {
      return ColorUtils.method1(this.method1(value1));
   }

   default @Range(from = 0L, to = 255L) int method4(float value1) {
      return ColorUtils.method2(this.method1(value1));
   }

   default @Range(from = 0L, to = 255L) int method5(float value1) {
      return ColorUtils.method3(this.method1(value1));
   }

   default @Range(from = 0L, to = 255L) int method6(float value1) {
      return this.method10() ? ColorUtils.method4(this.method1(value1)) : 255;
   }

   default @Range(from = 0L, to = 1L) float method7(float value1) {
      return ColorUtils.method41(this.method1(value1));
   }

   default @Range(from = 0L, to = 1L) float method8(float value1) {
      return ColorUtils.method43(this.method1(value1));
   }

   default @Range(from = 0L, to = 1L) float method9(float value1) {
      return ColorUtils.method45(this.method1(value1));
   }

   default float[] method10(float value1) {
      return ColorUtils.method39(this.method1(value1));
   }

   static AnimatedColor method11(int number0, ColorAnimation gui2extension1, @Range(from = 1L, to = 100L) int number2) {
      return new AnimatedColorImpl(number0, gui2extension1, number2);
   }

   static AnimatedColor method12() {
      return method11(-65536, ColorAnimation.WAVE, 50);
   }
}
