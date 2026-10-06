package com.moonsworth.lunar.client.util.rewindhandlers;

import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.util.function.BiFunction;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   WAVE("wave", PhosphorIconLegacy.PI_MOTION_EASE_IN_OUT_STROKE, (var0, var1) -> {
      double var2 = (100.1F - var1.method11()) / 100.0F;
      double var4 = 2.0E7 * var2;
      double var6 = 1.0E10 * var2;
      float var8 = (float)((ThreadModuleDump63.method15() + var0.floatValue() * var4) / var6 % 1.0);
      return var1.getAlpha() << 24 | Color.HSBtoRGB(var8, var1.RCOIOHHCHRCCCRCRHIORIRHROIRCOO(), var1.HHCRRHIOICRCHCCIRHIIIRICOHCIRO()) & 16777215;
   }),
   SHIFT("shift", PhosphorIconLegacy.PI_REPEAT_SQUARE_STROKE, (var0, var1) -> {
      double var2 = (100.1F - var1.method11()) / 100.0F;
      double var4 = 1.0E10 * var2;
      float var6 = (float)(ThreadModuleDump63.method15() / var4 % 1.0);
      return var1.getAlpha() << 24 | Color.HSBtoRGB(var6, var1.RCOIOHHCHRCCCRCRHIORIRHROIRCOO(), var1.HHCRRHIOICRCHCCIRHIIIRICOHCIRO()) & 16777215;
   });

   private final String id;
   private final PhosphorIconLegacy icon;
   private final BiFunction<Float, RewindhandlersExtension3, Integer> color;

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   @Override
   public String id() {
      return this.id;
   }

   @Generated
   @Override
   public PhosphorIconLegacy icon() {
      return this.icon;
   }

   @Generated
   public BiFunction<Float, RewindhandlersExtension3, Integer> color() {
      return this.color;
   }

   @Generated
   Gui2Extension(String var3, PhosphorIconLegacy var4, BiFunction<Float, RewindhandlersExtension3, Integer> var5) {
      this.id = var3;
      this.icon = var4;
      this.color = var5;
   }
}
