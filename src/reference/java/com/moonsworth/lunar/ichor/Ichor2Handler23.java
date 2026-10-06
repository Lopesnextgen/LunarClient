package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.loader.Ichor4Type;

public class Ichor2Handler23 extends com.moonsworth.lunar.ichor.util.Ichor2Handler2 implements MixinHelper_2 {
   private final Config field3;

   public Ichor2Handler23(Config config) {
      super("net.minecraft.*|com.mojang.*|net.optifine.*|[^\\/]+");
      this.field3 = config;
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.POST_REMAP};
   }

   @Override
   public boolean method4() {
      return this.field3.method21();
   }
}
