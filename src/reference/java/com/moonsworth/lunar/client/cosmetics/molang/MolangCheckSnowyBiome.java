package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.utils.MolangUtils;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.world.Biome;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import com.moonsworth.lunar.client.cosmetics.molang.MolangBuiltin;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.GeckolibCosmeticManager;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.ichor.util.KeepName;
import org.joml.Vector3i;

public class MolangCheckSnowyBiome implements MolangBuiltin {
   public MolangCheckSnowyBiome() {
   }

   public boolean method1(int number1) {
      return number1 == 0;
   }

   public boolean method3(int number1) {
      return true;
   }

   @KeepName
   public static double call() {
      if (GeckolibCosmeticManager.method17() != null && GeckolibCosmeticManager.method17().bridge$getWorld() != null) {
         Bridge5_11 bridge5_110 = GeckolibCosmeticManager.method17();
         int number1 = MathUtils.method9(bridge5_110.bridge$getPosX());
         int number2 = (int)bridge5_110.bridge$getBoundingBox().bridge$getMinY();
         int number3 = MathUtils.method9(bridge5_110.bridge$getPosZ());
         Vector3i vector3i4 = new Vector3i(number1, number2, number3);
         BiomeBridge itemcounter_35 = bridge5_110.bridge$getWorld()
            .bridge$getChunkFromBlockCoords(vector3i4)
            .bridge$getBiome(vector3i4, bridge5_110.bridge$getWorld().bridge$getWorldChunkManager());
         Biome itemcountertype_26 = Biome.fromBiomeBridge(itemcounter_35);
         if (itemcountertype_26.isSnowy()) {
            return MolangUtils.booleanToFloat(true);
         }
      }

      return MolangUtils.booleanToFloat(false);
   }
}
