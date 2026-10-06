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

public class MolangCheckBiome implements MolangBuiltin {
   public MolangCheckBiome() {
   }

   public boolean method1(int number1) {
      return number1 == 1;
   }

   public boolean method3(int number1) {
      return true;
   }

   @KeepName
   public static double call(double value0) {
      if (value0 % 1.0 == 0.0 && GeckolibCosmeticManager.method17() != null && GeckolibCosmeticManager.method17().bridge$getWorld() != null) {
         Bridge5_11 bridge5_112 = GeckolibCosmeticManager.method17();
         int number3 = MathUtils.method9(bridge5_112.bridge$getPosX());
         int number4 = (int)bridge5_112.bridge$getBoundingBox().bridge$getMinY();
         int number5 = MathUtils.method9(bridge5_112.bridge$getPosZ());
         Vector3i vector3i6 = new Vector3i(number3, number4, number5);
         BiomeBridge itemcounter_37 = bridge5_112.bridge$getWorld()
            .bridge$getChunkFromBlockCoords(vector3i6)
            .bridge$getBiome(vector3i6, bridge5_112.bridge$getWorld().bridge$getWorldChunkManager());
         Biome itemcountertype_28 = Biome.fromBiomeBridge(itemcounter_37);
         if (itemcountertype_28.getLegacyId().isPresent() && (Integer)itemcountertype_28.getLegacyId().get() == (int)value0) {
            return MolangUtils.booleanToFloat(true);
         }
      }

      return MolangUtils.booleanToFloat(false);
   }
}
