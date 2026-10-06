package com.moonsworth.lunar.legacy.mixin;

import com.google.common.base.Predicate;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter3_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter5_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2_3;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldEffectRecord;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.util.Bridge2Handler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin implements Itemcounter6, IBlockAccess {
   @Final
   @Shadow
   public boolean isRemote;
   @Shadow
   public WorldInfo worldInfo;
   @Final
   @Shadow
   public WorldProvider provider;
   @Shadow
   public List<Entity> loadedEntityList$v1_7;
   @Final
   @Shadow
   public List<Entity> loadedEntityList;
   @Final
   @Shadow
   public ISaveHandler saveHandler;
   @Shadow
   public EnumDifficulty difficultySetting$v1_7;
   @Shadow
   public List loadedTileEntityList$v1_7;
   @Shadow
   public List<TileEntity> loadedTileEntityList;
   @Shadow
   public IChunkProvider chunkProvider;
   @Unique
   private UUID bridge$uuid;

   @Shadow
   public abstract EntityPlayer getPlayerEntityByUUID(UUID var1);

   @Shadow
   public abstract void setWorldTime(long var1);

   @Shadow
   public abstract void playSound(double var1, double var3, double var5, String var7, float var8, float var9, boolean var10);

   @Shadow
   public abstract void playSound(double var1, double var3, double var5, SoundEvent var7, SoundCategory var8, float var9, float var10, boolean var11);

   @Shadow
   public abstract Chunk getChunk$v1_12(BlockPos var1);

   @Shadow
   public abstract Chunk getChunkFromBlockCoords(BlockPos var1);

   @Shadow
   public abstract Chunk getChunkFromBlockCoords(int var1, int var2);

   @Shadow
   public abstract WorldChunkManagerHell getBiomeProvider$v1_12();

   @Shadow
   public abstract WorldChunkManager getWorldChunkManager();

   @Shadow
   public abstract void spawnParticle(String var1, double var2, double var4, double var6, double var8, double var10, double var12);

   @Shadow
   public abstract void spawnParticle(
      EnumParticleTypes var1, boolean var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15
   );

   @Shadow
   public abstract void spawnParticle(EnumParticleTypes var1, double var2, double var4, double var6, double var8, double var10, double var12, int... var14);

   @Shadow
   public abstract IBlockState getBlockState(BlockPos var1);

   @Shadow
   public abstract Block getBlock(int var1, int var2, int var3);

   @Shadow
   public abstract boolean isBlockLoaded(BlockPos var1);

   @Shadow
   public abstract boolean blockExists$v1_7(int var1, int var2, int var3);

   @Shadow
   public abstract List<AxisAlignedBB> getCollidingBoundingBoxes(Entity var1, AxisAlignedBB var2);

   @Shadow
   public abstract List<AxisAlignedBB> getCollidingBoundingBoxes(Entity var1, AxisAlignedBB var2);

   @Shadow
   public abstract List<AxisAlignedBB> getCollisionBoxes(Entity var1, AxisAlignedBB var2);

   @Shadow
   public abstract int getLightBrightnessForSkyBlocks$v1_7(int var1, int var2, int var3, int var4);

   @Shadow
   public abstract Chunk getChunkFromChunkCoords(int var1, int var2);

   @Shadow
   public abstract Chunk getChunk$v1_12(int var1, int var2);

   @Shadow
   public abstract List<Entity> getEntitiesWithinAABB(Class<? extends Entity> var1, AxisAlignedBB var2, Predicate<? super Entity> var3);

   @Shadow
   public abstract boolean isChunkLoaded(int var1, int var2, boolean var3);

   @Shadow
   public abstract boolean chunkExists(int var1, int var2);

   @Shadow
   public abstract Scoreboard getScoreboard();

   @Shadow
   public abstract TileEntity getTileEntity(int var1, int var2, int var3);

   @Shadow
   public abstract TileEntity getTileEntity(BlockPos var1);

   @Shadow
   public abstract Entity getEntityByID(int var1);

   @Shadow
   public abstract EntityPlayer getPlayerEntityByName(String var1);

   @Shadow
   public abstract boolean checkNoEntityCollision(AxisAlignedBB var1, Entity var2);

   @Shadow
   public abstract List<AxisAlignedBB> getCollisionBoxes(AxisAlignedBB var1);

   @Shadow
   public abstract boolean checkBlockCollision(AxisAlignedBB var1);

   @Shadow
   public abstract long getWorldTime();

   @Shadow
   public abstract boolean isRaining();

   @Shadow
   public abstract List func_147461_a$v1_7(AxisAlignedBB var1);

   @Shadow
   public abstract boolean getCollisionBoxes(Entity var1, AxisAlignedBB var2, boolean var3, List<AxisAlignedBB> var4);

   @Override
   public Optional<Bridge6_10> bridge$getPlayerByUniqueId(UUID var1) {
      return Optional.ofNullable((Bridge6_10)this.getPlayerEntityByUUID(var1));
   }

   @Override
   public Optional<Bridge6_10> bridge$getPlayerByName(String var1) {
      return Optional.ofNullable((Bridge6_10)this.getPlayerEntityByName(var1));
   }

   @Override
   public Optional<BridgeExtension> bridge$getEntityById(int var1) {
      return Optional.ofNullable((BridgeExtension)this.getEntityByID(var1));
   }

   @Override
   public void bridge$playSound(double var1, double var3, double var5, String var7, float var8, float var9, boolean var10) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.playSound(var1, var3, var5, (SoundEvent)SoundEvent.REGISTRY.getObject(new ResourceLocation(var7)), SoundCategory.NEUTRAL$v1_12, var8, var9, var10);
      } else {
         this.playSound(var1, var3, var5, var7, var8, var9, var10);
      }
   }

   @Override
   public List<Bridge6_10> bridge$getPlayerEntities() {
      ArrayList var1 = new ArrayList();

      for (Entity var3 : this.lunar$getLoadedEntityList()) {
         if (var3 instanceof EntityPlayer var4) {
            var1.add((Bridge6_10)var4);
         }
      }

      return var1;
   }

   @Override
   public List<BridgeExtension> bridge$getEntities() {
      ArrayList var1 = new ArrayList(this.lunar$getLoadedEntityList().size());

      for (Entity var3 : this.lunar$getLoadedEntityList()) {
         var1.add((BridgeExtension)var3);
      }

      return var1;
   }

   private List<Entity> lunar$getLoadedEntityList() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.loadedEntityList : this.loadedEntityList$v1_7;
   }

   @Override
   public List<BridgeExtension> bridge$getEntities(AxisAlignedBBBridge var1, java.util.function.Predicate<? super BridgeExtension> var2) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.getEntitiesWithinAABB(Entity.class, (AxisAlignedBB)var1, var1x -> var2.test((BridgeExtension)var1x))
         : null;
   }

   @Override
   public List<HitcolorExtension> bridge$getBlockEntities() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? this.loadedTileEntityList$v1_7 : this.loadedTileEntityList;
   }

   @Override
   public Collection<HitcolorExtension> bridge$getBlockEntities(int var1, int var2) {
      Chunk var3;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var3 = this.getChunk$v1_12(var1, var2);
      } else {
         var3 = this.getChunkFromChunkCoords(var1, var2);
      }

      return ThreadModuleDump63.MC_VERSION >= 1 ? var3.chunkTileEntityMap.values() : var3.chunkTileEntityMap$v1_7.values();
   }

   @Override
   public boolean bridge$isRemote() {
      return this.isRemote;
   }

   @Override
   public boolean bridge$isChunkLoaded(int var1, int var2) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.isChunkLoaded(var1, var2, false) : this.chunkExists(var1, var2);
   }

   @Override
   public Itemcounter2 bridge$getChunkFromBlockCoords(Vector3ic var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return (Itemcounter2)this.getChunk$v1_12(new BlockPos(var1.x(), var1.y(), var1.z()));
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1
            ? (Itemcounter2)this.getChunkFromBlockCoords(new BlockPos(var1.x(), var1.y(), var1.z()))
            : (Itemcounter2)this.getChunkFromBlockCoords(var1.x(), var1.z());
      }
   }

   @Override
   public Itemcounter2 bridge$getChunk(int var1, int var2) {
      return ThreadModuleDump63.MC_VERSION >= 5 ? (Itemcounter2)this.getChunk$v1_12(var1, var2) : (Itemcounter2)this.getChunkFromChunkCoords(var1, var2);
   }

   @Override
   public Itemcounter3_3 bridge$getWorldChunkManager() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? (Itemcounter3_3)this.getBiomeProvider$v1_12() : (Itemcounter3_3)this.getWorldChunkManager();
   }

   @Override
   public void bridge$setWorldTime(long var1) {
      this.setWorldTime(var1);
   }

   @Override
   public long bridge$getWorldTime() {
      return this.getWorldTime();
   }

   @Override
   public Itemcounter5_2 bridge$getWorldInfo() {
      return (Itemcounter5_2)this.worldInfo;
   }

   @Override
   public int bridge$getDimensionId() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.provider.getDimensionType$v1_12().id : this.provider.dimensionId;
   }

   @Override
   public String bridge$getDimensionKey() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return switch (this.provider.getDimensionType$v1_12()) {
            case OVERWORLD -> "minecraft:overworld";
            case NETHER -> "minecraft:the_nether";
            case THE_END -> "minecraft:the_end";
            default -> throw new IncompatibleClassChangeError();
         };
      } else {
         return switch (this.provider.dimensionId) {
            case -1 -> "minecraft:the_nether";
            case 0 -> "minecraft:overworld";
            case 1 -> "minecraft:the_end";
            default -> null;
         };
      }
   }

   @Override
   public void bridge$spawnParticle(
      HorsestatsType2 var1, boolean var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15
   ) {
      if (var1.isAvailable()) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            this.spawnParticle(EnumParticleTypes.getParticleFromId(var1.getDataProvider().getId()), var2, var3, var5, var7, var9, var11, var13, var15);
         } else {
            this.spawnParticle(var1.getDataProvider().getName(), var3, var5, var7, var9, var11, var13);
         }
      }
   }

   @Override
   public Itemcounter_3 bridge$getBiome(int var1, int var2, int var3) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return (Itemcounter_3)this.getBiome(new BlockPos(var1, var2, var3));
      } else {
         throw new RuntimeException("bridge$getBiome() Should be implemented in their own mixin for 1.7 & 1.8!");
      }
   }

   @Override
   public Bridge2_17 bridge$getBlockState(int var1, int var2, int var3) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Bridge2_17)this.getBlockState(new BlockPos(var1, var2, var3))
         : new Bridge2Handler(this.getBlock(var1, var2, var3));
   }

   @Override
   public Bridge2_17 bridge$getBlockState(double var1, double var3, double var5) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Bridge2_17)this.getBlockState(new BlockPos(var1, var3, var5))
         : new Bridge2Handler(this.getBlock(MathHelper.floor_double(var1), MathHelper.floor_double(var3), MathHelper.floor_double(var5)));
   }

   @Override
   public Bridge3_23 bridge$getBlockAt(int var1, int var2, int var3) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Bridge3_23)this.getBlockState(new BlockPos(var1, var2, var3)).getBlock()
         : (Bridge3_23)this.getBlock(var1, var2, var3);
   }

   @Override
   public Bridge3_23 bridge$getBlockAt(double var1, double var3, double var5) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Bridge3_23)this.getBlockState(new BlockPos(var1, var3, var5)).getBlock()
         : (Bridge3_23)this.getBlock(MathHelper.floor_double(var1), MathHelper.floor_double(var3), MathHelper.floor_double(var5));
   }

   @Override
   public boolean bridge$isBlockLoaded(Vector3iBridge var1) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.isBlockLoaded((BlockPos)var1)
         : this.blockExists$v1_7(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   @Override
   public List<AxisAlignedBBBridge> bridge$getBlockCollisionBoxes(@Nullable BridgeExtension var1, AxisAlignedBBBridge var2) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         ArrayList var3 = new ArrayList();
         this.getCollisionBoxes((Entity)var1, (AxisAlignedBB)var2, false, var3);
         return var3;
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? this.getCollisionBoxes((AxisAlignedBB)var2) : this.func_147461_a$v1_7((AxisAlignedBB)var2);
      }
   }

   @Override
   public List<AxisAlignedBBBridge> bridge$getCollisionBoxes(@Nullable BridgeExtension var1, AxisAlignedBBBridge var2) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.getCollisionBoxes((Entity)var1, (AxisAlignedBB)var2);
      } else if (var1 == null) {
         return ThreadModuleDump63.MC_VERSION >= 1 ? this.getCollisionBoxes((AxisAlignedBB)var2) : this.func_147461_a$v1_7((AxisAlignedBB)var2);
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1
            ? this.getCollidingBoundingBoxes((Entity)var1, (AxisAlignedBB)var2)
            : this.getCollidingBoundingBoxes((Entity)var1, (AxisAlignedBB)var2);
      }
   }

   @Override
   public int bridge$getPackedLight(Vector3iBridge var1) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.getCombinedLight((BlockPos)var1, 0)
         : this.getLightBrightnessForSkyBlocks$v1_7(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ(), 0);
   }

   @Override
   public boolean bridge$isInWater(double var1, double var3, double var5) {
      Block var7 = ThreadModuleDump63.MC_VERSION >= 1
         ? this.getBlockState(new BlockPos(var1, var3, var5)).getBlock()
         : this.getBlock(MathHelper.floor_double(var1), MathHelper.floor_double(var3), MathHelper.floor_double(var5));
      return ThreadModuleDump63.MC_VERSION >= 1
         ? var7 == Blocks.water || var7 == Blocks.flowing_water
         : var7 == Blocks.water$v1_7 || var7 == Blocks.flowing_water$v1_7;
   }

   @Override
   public File bridge$getWorldDirectory() {
      return this.saveHandler.getWorldDirectory();
   }

   @Override
   public UUID bridge$getWorldId() {
      if (this.bridge$uuid == null) {
         File var1 = new File(this.saveHandler.getWorldDirectory(), "uuid.txt");

         try {
            if (var1.exists()) {
               this.bridge$uuid = UUID.fromString(Files.readString(var1.toPath()));
            } else {
               this.bridge$uuid = UUID.randomUUID();
               Files.writeString(var1.toPath(), this.bridge$uuid.toString());
            }
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }

      return this.bridge$uuid;
   }

   @Unique
   private void lunar$worldEffectEvent(Bridge6_10 var1, int var2, Horsestats20Extension2 var3, int var4) {
      ThreadModuleDump63.method3()
         .bridge$submit(() -> ClientEventBus.method29().method12(EventWorldEffectRecord.class, () -> new EventWorldEffectRecord(var1, var2, var3, var4)));
   }

   @Annotation2(0)
   @Inject(method = "playAuxSFXAtEntity$v1_7", at = @At("HEAD"))
   private void lunar$worldEffectEvent$v1_7(EntityPlayer var1, int var2, int var3, int var4, int var5, int var6, CallbackInfo var7) {
      this.lunar$worldEffectEvent((Bridge6_10)var1, var2, (Horsestats20Extension2)(new Vector3i(var3, var4, var5)), var6);
   }

   @Annotation2(min = 1)
   @Inject(
      method = {"playAuxSFXAtEntity$v1_8", "playEvent$v1_12(Lnet/minecraft/entity/player/EntityPlayer;ILnet/minecraft/util/math/BlockPos;I)V"},
      at = @At("HEAD")
   )
   private void lunar$worldEffectEvent$v1_8(EntityPlayer var1, int var2, BlockPos var3, int var4, CallbackInfo var5) {
      this.lunar$worldEffectEvent((Bridge6_10)var1, var2, (Horsestats20Extension2)var3, var4);
   }

   @Override
   public List<Bridge3_21> bridge$getScoreboardPackets() {
      ArrayList var1 = new ArrayList();
      ServerScoreboard var2 = new ServerScoreboard(null);
      ((ScoreboardMixin3)var2).bridge$setObjectiveDisplaySlots(this.getScoreboard().objectiveDisplaySlots);
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         ((ScoreboardMixin3)var2).bridge$setField96544c$v1_7(this.getScoreboard().field_96544_c$v1_7);
      } else {
         ((ScoreboardMixin3)var2).bridge$setEntitiesScoreObjectives$v1_8(this.getScoreboard().entitiesScoreObjectives);
      }

      for (ScorePlayerTeam var5 : ThreadModuleDump63.MC_VERSION == 0 ? this.getScoreboard().getTeams() : this.getScoreboard().getTeams()) {
         if (ThreadModuleDump63.MC_VERSION < 1 || var5.chatFormat != null) {
            var1.add((Bridge3_21)(new S3EPacketTeams(var5, 0)));
         }
      }

      for (ScoreObjective var6 : (ThreadModuleDump63.MC_VERSION == 0 ? this.getScoreboard().scoreObjectives$v1_7 : this.getScoreboard().scoreObjectives)
         .values()) {
         if (ThreadModuleDump63.MC_VERSION <= 0) {
            var1.addAll(var2.func_96550_d(var6));
         } else if (ThreadModuleDump63.MC_VERSION == 1) {
            var1.addAll(var2.func_96550_d(var6));
         } else {
            var1.addAll(var2.getCreatePackets$v1_12(var6));
         }
      }

      return var1;
   }

   @Override
   public HitcolorExtension bridge$getBlockEntity(Vector3iBridge var1) {
      return ThreadModuleDump63.MC_VERSION < 1
         ? (HitcolorExtension)this.getTileEntity(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ())
         : (HitcolorExtension)this.getTileEntity((BlockPos)var1);
   }

   @Override
   public Lighting4 bridge$getScoreBoard() {
      return (Lighting4)this.getScoreboard();
   }

   @Override
   public boolean bridge$noCollision(@Nullable BridgeExtension var1, AxisAlignedBBBridge var2) {
      return this.checkBlockCollision((AxisAlignedBB)var2) ? false : this.checkNoEntityCollision((AxisAlignedBB)var2, (Entity)var1);
   }

   @Override
   public ItemcounterType2_3 bridge$getPathTypeFromState(Vector3iBridge var1) {
      Bridge2_17 var2 = this.method2(var1);
      Bridge3_23 var3 = var2.bridge$getBlock();
      Block var4 = (Block)var3;
      if (var3.bridge$isAir()) {
         return ItemcounterType2_3.OPEN;
      }

      if (ThreadModuleDump63.MC_VERSION >= 1 ? var4 != Blocks.cactus : var4 != Blocks.cactus$v1_7) {
         if (var4 == Blocks.cocoa) {
            return ItemcounterType2_3.COCOA;
         }

         if (ThreadModuleDump63.MC_VERSION >= 1
            ? var4 != Blocks.lava && var4 != Blocks.flowing_lava
            : var4 != Blocks.lava$v1_7 && var4 != Blocks.flowing_lava$v1_7) {
            if (var4 != Blocks.fire && (ThreadModuleDump63.MC_VERSION < 5 || var4 != Blocks.MAGMA$v1_12)) {
               if (var4 == Blocks.waterlily || var4 instanceof BlockTrapDoor) {
                  return ItemcounterType2_3.TRAPDOOR;
               }

               if (var4 instanceof BlockDoor) {
                  return var2.bridge$getOpenValue() ? ItemcounterType2_3.DOOR_OPEN : ItemcounterType2_3.DOOR_CLOSED;
               }

               if (var4 instanceof BlockRailBase) {
                  return ItemcounterType2_3.RAIL;
               }

               if (var4 instanceof BlockLeaves) {
                  return ItemcounterType2_3.LEAVES;
               }

               if ((!(var4 instanceof BlockFenceGate) || var2.bridge$getOpenValue()) && !(var4 instanceof BlockFence) && !(var4 instanceof BlockWall)) {
                  if (!var2.bridge$isPathfindable(this, var1, ItemcounterType_3.LAND)) {
                     return ItemcounterType2_3.BLOCKED;
                  } else {
                     return (
                           ThreadModuleDump63.MC_VERSION >= 1
                              ? var4 != Blocks.water && var4 != Blocks.flowing_water
                              : var4 != Blocks.water$v1_7 && var4 != Blocks.flowing_water$v1_7
                        )
                        ? ItemcounterType2_3.OPEN
                        : ItemcounterType2_3.WATER;
                  }
               } else {
                  return ItemcounterType2_3.FENCE;
               }
            } else {
               return ItemcounterType2_3.DAMAGE_FIRE;
            }
         } else {
            return ItemcounterType2_3.LAVA;
         }
      } else {
         return ItemcounterType2_3.DAMAGE_OTHER;
      }
   }

   @Override
   public boolean bridge$isRaining() {
      return this.worldInfo.isRaining();
   }

   @Override
   public boolean bridge$isThundering() {
      return this.worldInfo.isThundering();
   }
}
