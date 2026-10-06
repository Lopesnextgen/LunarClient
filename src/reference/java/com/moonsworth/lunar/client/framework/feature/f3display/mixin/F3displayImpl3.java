package com.moonsworth.lunar.client.framework.feature.f3display.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class F3displayImpl3 extends F3display {
   private static final long field8 = 100L;
   public long field9 = 0L;

   public F3displayImpl3() {
      super(240, "GPU");
      this.field3 = 100;
      this.method3(12, "1.19");
   }

   @Override
   protected int[] method8() {
      int var1 = this.method9();
      return var1 >= 120 ? new int[]{100} : new int[]{25, 50};
   }

   @Override
   public String method11(int var1) {
      return var1 + "%";
   }

   public void method3() {
      long var1 = System.currentTimeMillis();
      if (var1 - this.field9 >= 100L) {
         this.field9 = var1;
         this.method2((int)ThreadModuleDump63.method3().bridge$getGpuUtilization());
      }
   }
}
