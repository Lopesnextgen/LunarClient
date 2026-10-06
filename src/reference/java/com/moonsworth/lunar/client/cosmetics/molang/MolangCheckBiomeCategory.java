package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.utils.MolangUtils;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.world.BiomeCategory;
import com.moonsworth.lunar.bridge.world.Biome;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import com.moonsworth.lunar.client.cosmetics.molang.MolangBuiltin;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.GeckolibCosmeticManager;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.ichor.util.KeepName;
import org.joml.Vector3i;

public class MolangCheckBiomeCategory implements MolangBuiltin {
   private final BiomeCategory field1;

   public MolangCheckBiomeCategory(BiomeCategory itemcountertype2_21) {
      this.field1 = itemcountertype2_21;
   }

   public boolean method1(int number1) {
      return number1 == 0;
   }

   public boolean method2(int number1) {
      return true;
   }

   @KeepName
   public double call() {
      Bridge5_11 bridge5_111 = GeckolibCosmeticManager.method17();
      if (bridge5_111 != null && !bridge5_111.OIHOORHOCRCOCIORRHOROOOORROIIH() && bridge5_111.bridge$getWorld() != null) {
         int number2 = MathUtils.method9(bridge5_111.bridge$getPosX());
         int number3 = (int)bridge5_111.bridge$getBoundingBox().bridge$getMinY();
         int number4 = MathUtils.method9(bridge5_111.bridge$getPosZ());
         Vector3i vector3i5 = new Vector3i(number2, number3, number4);
         BiomeBridge itemcounter_36 = bridge5_111.bridge$getWorld()
            .bridge$getChunkFromBlockCoords(vector3i5)
            .bridge$getBiome(vector3i5, bridge5_111.bridge$getWorld().bridge$getWorldChunkManager());
         Biome itemcountertype_27 = Biome.fromBiomeBridge(itemcounter_36);
         if (this.field1.equals(itemcountertype_27.getCategory())) {
            return MolangUtils.booleanToFloat(true);
         }
      }

      return MolangUtils.booleanToFloat(false);
   }
}
