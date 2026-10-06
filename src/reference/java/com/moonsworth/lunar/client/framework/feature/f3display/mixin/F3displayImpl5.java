package com.moonsworth.lunar.client.framework.feature.f3display.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class F3displayImpl5 extends F3display {
   public F3displayImpl5() {
      super(method3(), "Ping");
      this.field3 = 250;
      this.method3(19, "1.20.2");
   }

   @Override
   public String method11(int var1) {
      return var1 + "ms";
   }

   @Override
   protected int[] method8() {
      int var1 = this.method3();
      if (var1 > 4000) {
         return new int[]{var1 / 2};
      } else if (var1 > 2000) {
         return new int[]{1000};
      } else if (var1 > 1300) {
         return new int[]{500, 1000};
      } else {
         return var1 > 600 ? new int[]{500} : new int[]{100};
      }
   }

   public static int method3() {
      return ThreadModuleDump63.MC_VERSION >= 19 ? 240 : 12;
   }
}
