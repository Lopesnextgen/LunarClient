package com.moonsworth.lunar.client.config.option.trait;

import com.moonsworth.lunar.client.config.option.TraitBuilder;
import java.util.function.Supplier;

public class BuilderTraitType<T, Builder extends TraitBuilder<T>> extends TraitType<T> {
   private final Supplier<Builder> field2;

   public BuilderTraitType(int value, Supplier<Builder> supplier2) {
      super(value);
      this.field2 = supplier2;
   }

   public Builder method1() {
      return this.field2.get();
   }
}
