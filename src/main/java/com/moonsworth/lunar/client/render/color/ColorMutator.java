package com.moonsworth.lunar.client.render.color;

import java.awt.Color;
import org.jetbrains.annotations.Range;

public interface ColorMutator {
   void method1(int number1);

   void method2(int number1);

   void method3(@Range(from = 0L, to = 255L) int number1);

   void method4(@Range(from = 0L, to = 255L) int number1);

   void method5(@Range(from = 0L, to = 255L) int number1);

   void method6(@Range(from = 0L, to = 255L) int number1);

   default void method7(@Range(from = 0L, to = 1L) float value1) {
      this.method3((int)(value1 * 255.0F));
   }

   default void method8(@Range(from = 0L, to = 1L) float value1) {
      this.method4((int)(value1 * 255.0F));
   }

   default void method9(@Range(from = 0L, to = 1L) float value1) {
      this.method5((int)(value1 * 255.0F));
   }

   default void method10(@Range(from = 0L, to = 1L) float value1) {
      this.method6((int)(value1 * 255.0F));
   }

   void method11(@Range(from = 0L, to = 1L) float value1);

   void method12(@Range(from = 0L, to = 1L) float value1);

   void method13(@Range(from = 0L, to = 1L) float value1);

   default void method14(@Range(from = 0L, to = 1L) float value1, @Range(from = 0L, to = 1L) float value2, @Range(from = 0L, to = 1L) float value3) {
      this.method2(Color.HSBtoRGB(value1, value2, value3));
   }
}
