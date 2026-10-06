package com.moonsworth.lunar.client.framework.feature.keystrokes;

import java.util.function.Supplier;
import lombok.Generated;

public enum Gui2Extension3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   FILL("Fill", Keystrokes17::new),
   SMOOTH_FILL("Smooth Fill", Keystrokes19Impl::new),
   RIPPLE("Ripple", Keystrokes16::new),
   COLLAPSE("Collapse", Keystrokes5::new),
   ZIPPER("Zipper", Keystrokes19Impl3::new),
   TRIANGULATE("Triangulate", Keystrokes12::new),
   SPIRAL("Spiral", Keystrokes11::new),
   SAND("Sand", Keystrokes6::new),
   CIRCULAR_FILL("Circular Fill", Keystrokes7::new),
   MULTI_SQUARE_FILL("Multi Square Fill", Keystrokes14::new),
   HEAD_FILL("Head Fill", Keystrokes18::new),
   CROSS_COLLAPSE("Cross Collapse", Keystrokes8::new),
   CROSS_GROW("Cross Grow", Keystrokes10::new),
   HORIZONTAL_COLLAPSE("Horizontal Collapse", Keystrokes9::new),
   HORIZONTAL_GROW("Horizontal Grow", Keystrokes4::new),
   VERTICAL_COLLAPSE("Vertical Collapse", Keystrokes19Impl2::new),
   VERTICAL_GROW("Vertical Grow", Keystrokes13::new),
   DIAGONAL_COLLAPSE("Diagonal Collapse", Keystrokes19::new),
   DIAGONAL_GROW("Diagonal Grow", Keystrokes15::new);

   private final String id;
   private final Supplier<Keystrokes3> factory;

   public Keystrokes3 create() {
      return this.factory.get();
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension3(String text, Supplier<Keystrokes3> supplier) {
      this.id = text;
      this.factory = supplier;
   }
}
