package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.world.WorldInfoBridge;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorldInfo.class)
public abstract class WorldInfoMixin implements WorldInfoBridge {
   @Shadow
   public int spawnX;
   @Shadow
   public int spawnY;
   @Shadow
   public int spawnZ;
   @Shadow
   public long totalTime;

   public WorldInfoMixin() {
   }

   @Shadow
   public abstract boolean isRaining();

   public int bridge$getSpawnX() {
      return this.spawnX;
   }

   public int bridge$getSpawnY() {
      return this.spawnY;
   }

   public int bridge$getSpawnZ() {
      return this.spawnZ;
   }

   public long bridge$getGameTime() {
      return this.totalTime;
   }

   public boolean bridge$isRaining() {
      return this.isRaining();
   }
}
