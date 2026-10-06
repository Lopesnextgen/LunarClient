package com.moonsworth.lunar.client.util.rewindhandlers;

import java.awt.Color;
import org.jetbrains.annotations.Range;

public interface Rewindhandlers {
   void method1(int var1);

   void method2(int var1);

   void method3(@Range(from = 0L, to = 255L) int var1);

   void method4(@Range(from = 0L, to = 255L) int var1);

   void method5(@Range(from = 0L, to = 255L) int var1);

   void method6(@Range(from = 0L, to = 255L) int var1);

   default void method7(@Range(from = 0L, to = 1L) float var1) {
      this.method3((int)(var1 * 255.0F));
   }

   default void method8(@Range(from = 0L, to = 1L) float var1) {
      this.method4((int)(var1 * 255.0F));
   }

   default void method9(@Range(from = 0L, to = 1L) float var1) {
      this.method5((int)(var1 * 255.0F));
   }

   default void method10(@Range(from = 0L, to = 1L) float var1) {
      this.method6((int)(var1 * 255.0F));
   }

   void method11(@Range(from = 0L, to = 1L) float var1);

   void method12(@Range(from = 0L, to = 1L) float var1);

   void method13(@Range(from = 0L, to = 1L) float var1);

   default void method14(@Range(from = 0L, to = 1L) float var1, @Range(from = 0L, to = 1L) float var2, @Range(from = 0L, to = 1L) float var3) {
      this.method2(Color.HSBtoRGB(var1, var2, var3));
   }
}
