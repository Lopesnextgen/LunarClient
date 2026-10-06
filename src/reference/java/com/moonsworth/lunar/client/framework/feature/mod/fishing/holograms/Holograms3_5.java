package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public abstract class Holograms3_5 {
   private final long field1 = ThreadModuleDump63.method3().bridge$getSystemTime();

   public abstract void method1(Holograms4Iterator var1);

   public abstract void method2(Holograms4Iterator var1);

   @Generated
   public long getTimestamp() {
      return this.field1;
   }
}
