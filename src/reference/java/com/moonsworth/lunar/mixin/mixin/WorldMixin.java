package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.client.util.game.PredicateEntitySelector;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = World.class, priority = 201)
public abstract class WorldMixin implements Itemcounter6 {
   @Shadow
   public abstract List getEntitiesWithinAABBExcludingEntity(Entity var1, AxisAlignedBB var2, IEntitySelector var3);

   @Shadow
   public abstract BiomeGenBase getBiomeGenForCoords(int var1, int var2);

   @Override
   public List<BridgeExtension> bridge$getEntities(AxisAlignedBBBridge var1, Predicate<? super BridgeExtension> var2) {
      return this.getEntitiesWithinAABBExcludingEntity(null, (AxisAlignedBB)var1, new PredicateEntitySelector(var2));
   }

   @Override
   public Itemcounter_3 bridge$getBiome(int var1, int var2, int var3) {
      return (Itemcounter_3)this.getBiomeGenForCoords(var1, var3);
   }
}
