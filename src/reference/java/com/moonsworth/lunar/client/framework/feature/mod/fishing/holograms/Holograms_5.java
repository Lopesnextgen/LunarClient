package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Holograms_5 {
   private final long field1 = ThreadModuleDump63.method3().bridge$getSystemTime();
   private final HologramsType5 field2;
   private final HologramsType5 field3;

   public Holograms_5(HologramsType5 var1, HologramsType5 hologramsType5) {
      this.field2 = var1;
      this.field3 = hologramsType5;
   }

   public void method1(Rewindhandlers var1) {
      var1.method2(this.field3);
   }

   public void method2(Rewindhandlers var1) {
      var1.method2(this.field2);
   }

   @Generated
   public long getTimestamp() {
      return this.field1;
   }
}
