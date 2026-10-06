package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import lombok.Generated;

public class Holograms3Impl7 extends Holograms3_5 {
   private final Nameplate4 field2;

   @Override
   public void method1(Holograms4Iterator var1) {
      var1.method28().remove(this.field2);
   }

   @Override
   public void method2(Holograms4Iterator var1) {
      var1.method28().add(this.field2);
   }

   @Generated
   public Holograms3Impl7(Nameplate4 var1) {
      this.field2 = var1;
   }
}
