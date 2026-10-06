package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BiomeGenBase.class)
public abstract class BiomeGenBaseMixin implements BiomeBridge {
   @Final
   @Shadow
   public String biomeName;
   @VersionGate(max = 1)
   @Final
   @Shadow
   public int biomeID;

   public BiomeGenBaseMixin() {
   }

   @Shadow
   public static int getIdForBiome$v1_12(BiomeGenBase biomegenbase0) {
      return 0;
   }

   @Shadow
   public abstract float getTemperature$v1_12(BlockPos pos1);

   @Shadow
   public abstract float getFloatTemperature(int number1, int number2, int number3);

   @Shadow
   public abstract float getFloatTemperature(BlockPos pos1);

   public String bridge$getBiomeName() {
      return this.biomeName;
   }

   public Optional<Integer> bridge$getBiomeID() {
      return Ref.MC_VERSION >= 5 ? Optional.of(getIdForBiome$v1_12((BiomeGenBase)this)) : Optional.of(this.biomeID);
   }

   public float bridge$getTemperature(Horsestats20Extension2 horsestats20extension21) {
      if (Ref.MC_VERSION == 0) {
         return this.getFloatTemperature(horsestats20extension21.bridge$getX(), horsestats20extension21.bridge$getY(), horsestats20extension21.bridge$getZ());
      } else {
         return Ref.MC_VERSION == 1 ? this.getFloatTemperature((BlockPos)horsestats20extension21) : this.getTemperature$v1_12((BlockPos)horsestats20extension21);
      }
   }
}
