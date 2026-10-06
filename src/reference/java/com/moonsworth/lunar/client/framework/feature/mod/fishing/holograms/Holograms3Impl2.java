package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import lombok.Generated;

public class Holograms3Impl2 extends Holograms3_5 {
   private final com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 field2;
   private final com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 field3;

   @Override
   public void method1(Holograms4Iterator var1) {
      var1.method30().method11(this.field3);
   }

   @Override
   public void method2(Holograms4Iterator var1) {
      var1.method30().method11(this.field2);
   }

   @Generated
   public Holograms3Impl2(
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 var1,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 hologramsType2
   ) {
      this.field2 = var1;
      this.field3 = hologramsType2;
   }
}
