package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import lombok.Generated;

public class Holograms3Impl6 extends Holograms3_5 {
   private final String field2;
   private final String newName;

   @Override
   public void method1(Holograms4Iterator var1) {
      var1.method30().setDisplayName(this.newName);
   }

   @Override
   public void method2(Holograms4Iterator var1) {
      var1.method30().setDisplayName(this.field2);
   }

   @Generated
   public Holograms3Impl6(String var1, String text) {
      this.field2 = var1;
      this.newName = text;
   }
}
