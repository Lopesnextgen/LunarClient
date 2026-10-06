package com.moonsworth.lunar.ichor;

import org.spongepowered.asm.service.IMixinServiceBootstrap;

public class IchorServiceBootstrap implements IMixinServiceBootstrap {
   public IchorServiceBootstrap() {
   }

   public String getName() {
      return "Ichor";
   }

   public String getServiceClassName() {
      return "com.moonsworth.lunar.ichor.mixin.service.IchorMixinService";
   }

   public void bootstrap() {
   }
}
