package com.moonsworth.lunar.loader.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.MixinInternal3;
import lombok.Generated;

public abstract class MixinInternal3Handler implements MixinInternal3 {
   protected Config field1;

   public String method1() {
      return "mixins." + this.field1.method47() + ".json";
   }

   public String method2(String var1) {
      return "mixins." + this.field1.method47() + "_" + var1 + ".json";
   }

   @Generated
   public MixinInternal3Handler(Config var1) {
      this.field1 = var1;
   }
}
