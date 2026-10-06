package com.moonsworth.lunar.client.util.rewindhandlers;

import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import org.jetbrains.annotations.Range;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;

public interface RewindhandlersExtension4 extends RewindhandlersExtension, Rewindhandlers {
   @Override
   default void method2(int var1) {
      var1 &= 16777215;
      var1 |= this.method14() ? this.getColor() & 0xFF000000 : -16777216;
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(var1);
   }

   @Override
   default void method3(@Range(from = 0L, to = 255L) int var1) {
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(ThreadModuleDump23.method19(this.getColor(), var1));
   }

   @Override
   default void method4(@Range(from = 0L, to = 255L) int var1) {
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(ThreadModuleDump23.method20(this.getColor(), var1));
   }

   @Override
   default void method5(@Range(from = 0L, to = 255L) int var1) {
      this.IHORHICICIHRCOCRROCHHOROCHCHCR(ThreadModuleDump23.method21(this.getColor(), var1));
   }

   @Override
   default void method6(@Range(from = 0L, to = 255L) int var1) {
      if (this.method14()) {
         this.IHORHICICIHRCOCRROCHHOROCHCHCR(ThreadModuleDump23.method22(this.getColor(), var1));
      }
   }

   @Override
   default void method11(@Range(from = 0L, to = 1L) float var1) {
      float[] var2 = ThreadModuleDump23.method40(this.getRed(), this.getGreen(), this.getBlue());
      this.method2(ThreadModuleDump23.method38(var1, var2[1], var2[2]));
   }

   @Override
   default void method12(@Range(from = 0L, to = 1L) float var1) {
      float[] var2 = ThreadModuleDump23.method40(this.getRed(), this.getGreen(), this.getBlue());
      this.method2(ThreadModuleDump23.method38(var2[0], var1, var2[2]));
   }

   @Override
   default void method13(@Range(from = 0L, to = 1L) float var1) {
      float[] var2 = ThreadModuleDump23.method40(this.getRed(), this.getGreen(), this.getBlue());
      this.method2(ThreadModuleDump23.method38(var2[0], var2[1], var1));
   }
}
