package com.moonsworth.lunar.client.render.color;

import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.Color;
import java.util.function.BiFunction;
import lombok.Generated;

public enum ColorAnimation implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   WAVE("wave", PhosphorIcon.PI_MOTION_EASE_IN_OUT_STROKE, (arg0, arg1) -> {
      double value2 = (100.1F - arg1.method11()) / 100.0F;
      double value4 = 2.0E7 * value2;
      double value6 = 1.0E10 * value2;
      float value8 = (float)((Ref.method15() + arg0.floatValue() * value4) / value6 % 1.0);
      return arg1.getAlpha() << 24 | Color.HSBtoRGB(value8, arg1.RCOIOHHCHRCCCRCRHIORIRHROIRCOO(), arg1.HHCRRHIOICRCHCCIRHIIIRICOHCIRO()) & 16777215;
   }),
   SHIFT("shift", PhosphorIcon.PI_REPEAT_SQUARE_STROKE, (arg0, arg1) -> {
      double value2 = (100.1F - arg1.method11()) / 100.0F;
      double value4 = 1.0E10 * value2;
      float value6 = (float)(Ref.method15() / value4 % 1.0);
      return arg1.getAlpha() << 24 | Color.HSBtoRGB(value6, arg1.RCOIOHHCHRCCCRCRHIORIRHROIRCOO(), arg1.HHCRRHIOICRCHCCIRHIIIRICOHCIRO()) & 16777215;
   });

   private final String id;
   private final PhosphorIcon icon;
   private final BiFunction<Float, AnimatedColor, Integer> color;

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   public String id() {
      return this.id;
   }

   @Generated
   public PhosphorIcon icon() {
      return this.icon;
   }

   @Generated
   public BiFunction<Float, AnimatedColor, Integer> color() {
      return this.color;
   }

   @Generated
   ColorAnimation(String text3, PhosphorIcon markerstype4, BiFunction<Float, AnimatedColor, Integer> function5) {
      this.id = text3;
      this.icon = markerstype4;
      this.color = function5;
   }
}
