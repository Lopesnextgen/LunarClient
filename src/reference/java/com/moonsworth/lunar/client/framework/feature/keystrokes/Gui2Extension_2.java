package com.moonsworth.lunar.client.framework.feature.keystrokes;

import java.util.function.Function;
import lombok.Generated;

public enum Gui2Extension_2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   HALF("half", Keystrokes2Handler2::new),
   FULL("complete", Keystrokes2Handler::new);

   private final String id;
   private final Function<Double, Keystrokes2> factory;

   public Keystrokes2 create(double value) {
      return this.factory.apply(value);
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension_2(String text, Function<Double, Keystrokes2> function) {
      this.id = text;
      this.factory = function;
   }
}
