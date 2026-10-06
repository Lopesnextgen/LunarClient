package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.utils.MolangUtils;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.cosmetics.molang.MolangBuiltin;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.GeckolibCosmeticManager;
import com.moonsworth.lunar.client.framework.Ref;

public class MolangLightLevel implements MolangBuiltin {
   public MolangLightLevel() {
   }

   public boolean method1(int number1) {
      return number1 == 2;
   }

   public boolean method3(int number1) {
      return true;
   }

   public static double call(double value0, double value2) {
      if (GeckolibCosmeticManager.method17() != null && Ref.method8() != null) {
         boolean flag4 = MolangUtils.doubleToBoolean(value0);
         boolean flag5 = MolangUtils.doubleToBoolean(value2);
         Bridge5_11 bridge5_116 = GeckolibCosmeticManager.method17();
         return Ref.method8().bridge$getLightLevel(bridge5_116.bridge$getBlockPos().bridge$above(), flag4, flag5);
      } else {
         return 15.0;
      }
   }
}
