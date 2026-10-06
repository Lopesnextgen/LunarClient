package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldClient.class)
public abstract class WorldClientMixin extends World implements WorldBridgeExtension {
   @Shadow
   public ChunkProviderClient clientChunkProvider;
   @Shadow
   public Set entityList;

   public WorldClientMixin() {
   }

   @Shadow
   public abstract void sendQuittingDisconnectingPacket();

   public ScoreboardBridge bridge$getScoreboard() {
      return (ScoreboardBridge)this.getScoreboard();
   }

   @Inject(method = "sendQuittingDisconnectingPacket()V", at = @At("HEAD"))
   private void lunar$sendQuittingDisconnectingPacket(CallbackInfo callback1) {
      EventDisconnect.method1();
   }

   public void bridge$disconnect() {
      this.sendQuittingDisconnectingPacket();
   }

   public int bridge$getLoadedChunkCount() {
      return Ref.MC_VERSION <= 1 ? this.clientChunkProvider.getLoadedChunkCount() : this.clientChunkProvider.loadedChunks$v1_12.size();
   }

   public long bridge$getGameTime() {
      return this.worldInfo.totalTime;
   }

   public long bridge$getDayTime() {
      return this.worldInfo.getWorldTime();
   }

   public boolean bridge$isRaining() {
      return this.isRaining();
   }

   public boolean bridge$isSnowing(Horsestats20Extension2 horsestats20extension21) {
      BiomeGenBase biomegenbase2 = (BiomeGenBase)this.method1(horsestats20extension21);
      float value3;
      if (Ref.MC_VERSION == 5) {
         value3 = biomegenbase2.getTemperature$v1_12((BlockPos)horsestats20extension21);
      } else if (Ref.MC_VERSION == 1) {
         value3 = biomegenbase2.getFloatTemperature((BlockPos)horsestats20extension21);
      } else {
         value3 = biomegenbase2.getFloatTemperature(horsestats20extension21.bridge$getX(), horsestats20extension21.bridge$getY(), horsestats20extension21.bridge$getZ());
      }

      return value3 < 0.15F;
   }

   public boolean bridge$isThundering() {
      return this.isThundering();
   }

   public boolean bridge$canMonstersSpawn(Horsestats20Extension2 horsestats20extension21) {
      return Ref.MC_VERSION == 0
         ? SpawnerAnimals.canCreatureTypeSpawnAtLocation(EnumCreatureType.monster$v1_7, this, horsestats20extension21.bridge$getX(), horsestats20extension21.bridge$getY() + 1, horsestats20extension21.bridge$getZ())
         : SpawnerAnimals.canCreatureTypeSpawnAtLocation(SpawnPlacementType.ON_GROUND, this, (BlockPos)horsestats20extension21.bridge$above());
   }

   public int bridge$calculateSkylightSubtract(long number1) {
      float value3 = this.provider.calculateCelestialAngle(number1, 1.0F);
      float value4 = 1.0F - (MathHelper.cos(value3 * (float) Math.PI * 2.0F) * 2.0F + 0.5F);
      value4 = 1.0F - Math.max(0.0F, Math.min(1.0F, value4));
      value4 = (float)(value4 * (1.0 - this.rainingStrength * 5.0F / 16.0));
      value4 = (float)(value4 * (1.0 - this.thunderingStrength * 5.0F / 16.0));
      value4 = 1.0F - value4;
      return (int)(value4 * 11.0F);
   }

   public Iterable<BridgeExtension> bridge$entitiesForRendering() {
      return this.entityList;
   }

   public int bridge$getLightLevel(Horsestats20Extension2 horsestats20extension21, boolean flag2, boolean flag3) {
      Chunk chunk4;
      if (Ref.MC_VERSION <= 0) {
         chunk4 = this.getChunkFromBlockCoords(horsestats20extension21.bridge$getX(), horsestats20extension21.bridge$getZ());
      } else if (Ref.MC_VERSION <= 1) {
         chunk4 = this.getChunkFromBlockCoords((BlockPos)horsestats20extension21);
      } else {
         chunk4 = this.getChunk$v1_12((BlockPos)horsestats20extension21);
      }

      if (Ref.MC_VERSION == 0) {
         int number5 = horsestats20extension21.bridge$getX() & 15;
         int number6 = horsestats20extension21.bridge$getZ() & 15;
         if (flag2 && flag3) {
            int number7 = chunk4.getSavedLightValue$v1_7(EnumSkyBlock.Block$v1_7, number5, horsestats20extension21.bridge$getY(), number6);
            int number8 = chunk4.getSavedLightValue$v1_7(EnumSkyBlock.Sky$v1_7, number5, horsestats20extension21.bridge$getY(), number6);
            return Math.max(number7, number8);
         } else if (!flag2 && !flag3) {
            throw new IllegalStateException("bridge$getLightLevel requires either block or sky to be true!");
         } else {
            return chunk4.getSavedLightValue$v1_7(flag3 ? EnumSkyBlock.Sky$v1_7 : EnumSkyBlock.Block$v1_7, number5, horsestats20extension21.bridge$getY(), number6);
         }
      } else if (flag2) {
         return flag3 ? chunk4.getLightSubtracted((BlockPos)horsestats20extension21, 0) : chunk4.getLightFor(EnumSkyBlock.BLOCK, (BlockPos)horsestats20extension21);
      } else if (flag3) {
         return chunk4.getLightFor(EnumSkyBlock.SKY, (BlockPos)horsestats20extension21);
      } else {
         throw new IllegalStateException("bridge$getLightLevel requires either block or sky to be true!");
      }
   }

   public Map<Object, MapDataBridge> bridge$getAllMapData() {
      HashMap map1 = new HashMap();
      Map map2 = Ref.MC_VERSION >= 1 ? this.mapStorage.loadedDataMap : this.mapStorage.loadedDataMap$v1_7;

      for (Object obj4 : map2.values()) {
         if (obj4 instanceof MapData mapdata5) {
            String text6 = mapdata5.mapName;
            if (text6.startsWith("map_")) {
               text6 = text6.substring(4);
            }

            try {
               map1.put(Integer.parseInt(text6), (MapDataBridge)obj4);
            } catch (NumberFormatException numberformatexception8) {
            }
         }
      }

      return map1;
   }

   public int bridge$getFoliageColor(Horsestats20Extension2 horsestats20extension21) {
      if (Ref.MC_VERSION >= 1) {
         return BiomeColorHelper.getFoliageColorAtPos(this, (BlockPos)horsestats20extension21);
      }

      int number2 = horsestats20extension21.bridge$getX();
      int number3 = horsestats20extension21.bridge$getY();
      int number4 = horsestats20extension21.bridge$getZ();
      int number5 = 0;
      int number6 = 0;
      int number7 = 0;

      for (int index8 = -1; index8 <= 1; index8++) {
         for (int index9 = -1; index9 <= 1; index9++) {
            int number10 = this.getBiomeGenForCoords(number2 + index9, number4 + index8).getBiomeFoliageColor$v1_7(number2 + index9, number3, number4 + index8);
            number5 += (number10 & 0xFF0000) >> 16;
            number6 += (number10 & 0xFF00) >> 8;
            number7 += number10 & 0xFF;
         }
      }

      return (number5 / 9 & 0xFF) << 16 | (number6 / 9 & 0xFF) << 8 | number7 / 9 & 0xFF;
   }
}
