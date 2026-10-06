package com.moonsworth.lunar.client.util.rewindhandlers;

import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import org.jetbrains.annotations.Range;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;

public interface RewindhandlersExtension3 extends RewindhandlersExtension {
   @Override
   boolean method14();

   @Range(from = 1L, to = 100L) int method11();

   default @Range(from = 0L, to = 255L) int method3(float var1) {
      return ThreadModuleDump23.method1(this.method1(var1));
   }

   default @Range(from = 0L, to = 255L) int method4(float var1) {
      return ThreadModuleDump23.method2(this.method1(var1));
   }

   default @Range(from = 0L, to = 255L) int method5(float var1) {
      return ThreadModuleDump23.method3(this.method1(var1));
   }

   default @Range(from = 0L, to = 255L) int method6(float var1) {
      return this.method14() ? ThreadModuleDump23.method4(this.method1(var1)) : 255;
   }

   default @Range(from = 0L, to = 1L) float method7(float var1) {
      return ThreadModuleDump23.method41(this.method1(var1));
   }

   default @Range(from = 0L, to = 1L) float method8(float var1) {
      return ThreadModuleDump23.method43(this.method1(var1));
   }

   default @Range(from = 0L, to = 1L) float method9(float var1) {
      return ThreadModuleDump23.method45(this.method1(var1));
   }

   default float[] method10(float var1) {
      return ThreadModuleDump23.method39(this.method1(var1));
   }

   static RewindhandlersExtension3 method11(int var0, Gui2Extension var1, @Range(from = 1L, to = 100L) int var2) {
      return new RewindhandlersExtension32(var0, var1, var2);
   }

   static RewindhandlersExtension3 method12() {
      return method11(-65536, Gui2Extension.WAVE, 50);
   }
}
