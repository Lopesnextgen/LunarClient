package com.moonsworth.lunar.client.framework.feature.keystrokes;

import java.util.function.Function;
import lombok.Generated;

public enum KeystrokeTimerType implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   HALF("half", HalfKeystrokeTimer::new),
   FULL("complete", FullKeystrokeTimer::new);

   private final String id;
   private final Function<Double, KeystrokeTimer> factory;

   public KeystrokeTimer create(double value1) {
      return this.factory.apply(value1);
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   KeystrokeTimerType(String text3, Function<Double, KeystrokeTimer> function4) {
      this.id = text3;
      this.factory = function4;
   }
}
