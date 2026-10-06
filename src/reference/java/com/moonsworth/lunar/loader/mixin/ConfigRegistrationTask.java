package com.moonsworth.lunar.loader.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.MixinRegistrationTask;
import lombok.Generated;

public abstract class ConfigRegistrationTask implements MixinRegistrationTask {
   protected Config field1;

   public String method1() {
      return "mixins." + this.field1.method47() + ".json";
   }

   public String method2(String text) {
      return "mixins." + this.field1.method47() + "_" + text + ".json";
   }

   @Generated
   public ConfigRegistrationTask(Config config1) {
      this.field1 = config1;
   }
}
