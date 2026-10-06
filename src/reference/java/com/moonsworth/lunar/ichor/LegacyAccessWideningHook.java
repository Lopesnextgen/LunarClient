package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.loader.PipelineStage;

public class LegacyAccessWideningHook extends com.moonsworth.lunar.ichor.util.ClassNameRegexFilter implements AccessWideningHook {
   private final Config field3;

   public LegacyAccessWideningHook(Config config1) {
      super("net.minecraft.*|com.mojang.*|net.optifine.*|[^\\/]+");
      this.field3 = config1;
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.POST_REMAP};
   }

   public boolean method4() {
      return this.field3.method21();
   }
}
