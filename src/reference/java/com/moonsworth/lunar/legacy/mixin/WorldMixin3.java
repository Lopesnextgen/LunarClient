package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityRemoval;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.entity.EventEntityWorldJoin;
import com.moonsworth.lunar.client.event.entity.EventCollisionBoxes;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent.EventProjectileRemoval;
import com.moonsworth.lunar.client.event.mixin.fishing.EventHorizonQuery;
import com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin3 implements Itemcounter6, IBlockAccess {
   @Final
   @Shadow
   public boolean isRemote;
   @Shadow
   public WorldInfo worldInfo;
   @Shadow
   public Set<ChunkCoordIntPair> activeChunkSet;
   @Final
   @Shadow
   public Profiler profiler;
   @Final
   @Shadow
   public List<EntityPlayer> playerEntities;
   @Shadow
   public int ambientTickCountdown;
   @Final
   @Shadow
   public Random rand;
   @Shadow
   public Set activeChunkSet$v1_7;
   @Shadow
   public List playerEntities$v1_7;
   @Final
   @Shadow
   public List<Entity> loadedEntityList;
   @Shadow
   public List loadedEntityList$v1_7;
   @Unique
   private int lunar$playerChunkX;
   @Unique
   private int lunar$playerChunkZ;

   @Shadow
   public abstract void onEntityAdded(Entity var1);

   @Shadow
   public abstract boolean checkLight(BlockPos var1);

   @Shadow
   public abstract boolean updateAllLightTypes$v1_7(int var1, int var2, int var3);

   @ModifyReturnValue(method = "getHorizon", at = @At("RETURN"))
   private double lunar$getHorizon(double var1) {
      if (!this.isRemote) {
         return var1;
      }

      EventHorizonQuery var3 = (EventHorizonQuery)ClientEventBus.method29().method12(EventHorizonQuery.class, () -> {
         EventHorizonQuery var2 = new EventHorizonQuery();
         var2.setValue(var1);
         return var2;
      });
      return var3 != null && this.worldInfo.getTerrainType() != WorldType.FLAT ? var3.getValue() : var1;
   }

   @Annotation2(min = 5)
   @Redirect(method = "markBlocksDirtyVertical", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;hasSkyLight$v1_12()Z"))
   private boolean lunar$markBlocksDirtyVertical$v1_12(WorldProvider var1) {
      return this.isRemote && ThreadModuleDump63.method4().method40().method56().method13() ? false : var1.hasSkyLight$v1_12();
   }

   @Annotation2(1)
   @Redirect(method = "markBlocksDirtyVertical", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;getHasNoSky$v1_8()Z"))
   private boolean lunar$markBlocksDirtyVertical$v1_8(WorldProvider var1) {
      return var1.getHasNoSky() || this.isRemote && ThreadModuleDump63.method4().method40().method56().method13();
   }

   @Annotation2(max = 0)
   @Redirect(method = "markBlocksDirtyVertical", at = @At(value = "FIELD", target = "Lnet/minecraft/world/WorldProvider;nether:Z"))
   public boolean lunar$markBlocksDirtyVertical$getHasNoSky(WorldProvider var1) {
      return var1.hasNoSky || this.isRemote && ThreadModuleDump63.method4().method40().method56().method13();
   }

   @Annotation2(min = 1)
   @Inject(method = "checkLight$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLight$v1_8(BlockPos var1, CallbackInfoReturnable<Boolean> var2) {
      if (this.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         var2.setReturnValue(true);
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "updateAllLightTypes$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLight$v1_7(int var1, int var2, int var3, CallbackInfoReturnable<Boolean> var4) {
      if (this.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         var4.setReturnValue(true);
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "checkLightFor$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLightFor$v1_8(EnumSkyBlock var1, BlockPos var2, CallbackInfoReturnable<Boolean> var3) {
      if (this.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         var3.setReturnValue(true);
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "updateLightByType$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLightFor$v1_7(EnumSkyBlock var1, int var2, int var3, int var4, CallbackInfoReturnable<Boolean> var5) {
      if (this.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         var5.setReturnValue(true);
      }
   }

   @Inject(
      method = {
            "getCollisionBoxes$v1_12(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;",
            "getCollidingBoundingBoxes$v1_8",
            "getCollidingBoundingBoxes$v1_7"
      },
      at = @At("RETURN")
   )
   private void lunar$collisionsEvent(Entity var1, AxisAlignedBB var2, CallbackInfoReturnable<List<AxisAlignedBB>> var3) {
      if (this.isRemote) {
         ClientEventBus.method29().method12(EventCollisionBoxes.class, () -> new EventCollisionBoxes((BridgeExtension)var1, (AxisAlignedBBBridge)var2, (List)var3.getReturnValue()));
      }
   }

   @Annotation2(max = 1)
   @Inject(method = {"getCollisionBoxes$v1_8", "func_147461_a$v1_7"}, at = @At("RETURN"))
   private void lunar$collisionBoxesEvent(AxisAlignedBB var1, CallbackInfoReturnable<List<AxisAlignedBB>> var2) {
      if (this.isRemote) {
         ClientEventBus.method29().method12(EventCollisionBoxes.class, () -> new EventCollisionBoxes(null, (AxisAlignedBBBridge)var1, (List)var2.getReturnValue()));
      }
   }

   @Unique
   private void lunar$entityJoinWorldEvent(Entity var1, CallbackInfoReturnable<Boolean> var2) {
      if (this.isRemote) {
         EventEntityWorldJoin var3 = (EventEntityWorldJoin)ClientEventBus.method29().method12(EventEntityWorldJoin.class, () -> new EventEntityWorldJoin((BridgeExtension)var1, this));
         if (var3 != null && var3.isCancelled() && !var1.forceSpawn && !(var1 instanceof EntityPlayer)) {
            var2.setReturnValue(false);
         }
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "spawnEntity$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getChunk$v1_12(II)Lnet/minecraft/world/chunk/Chunk;", shift = Shift.BEFORE),
      cancellable = true
   )
   private void lunar$entityJoinWorldEvent$v1_12(Entity var1, CallbackInfoReturnable<Boolean> var2) {
      this.lunar$entityJoinWorldEvent(var1, var2);
   }

   @Annotation2(max = 1)
   @Inject(
      method = "spawnEntityInWorld$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getChunkFromChunkCoords$v1_7(II)Lnet/minecraft/world/chunk/Chunk;", shift = Shift.BEFORE),
      cancellable = true
   )
   private void lunar$entityJoinWorldEvent$v1_7(Entity var1, CallbackInfoReturnable<Boolean> var2) {
      this.lunar$entityJoinWorldEvent(var1, var2);
   }

   @Unique
   private void lunar$entityJoinWorldEvent(Collection<Entity> var1, CallbackInfo var2) {
      if (this.isRemote) {
         for (Entity var4 : var1) {
            EventEntityWorldJoin var5 = (EventEntityWorldJoin)ClientEventBus.method29()
               .method12(EventEntityWorldJoin.class, () -> new EventEntityWorldJoin((BridgeExtension)var4, this));
            if (var5 != null && !var5.isCancelled()) {
               if (ThreadModuleDump63.MC_VERSION >= 1) {
                  this.loadedEntityList.add(var4);
               } else {
                  this.loadedEntityList$v1_7.add(var4);
               }

               this.onEntityAdded(var4);
            }
         }

         var2.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "loadEntities$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$entityJoinWorldEvent$v1_8(Collection<Entity> var1, CallbackInfo var2) {
      this.lunar$entityJoinWorldEvent(var1, var2);
   }

   @Annotation2(max = 0)
   @Inject(method = "addLoadedEntities$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$entityJoinWorldEvent$v1_7(List<Entity> var1, CallbackInfo var2) {
      this.lunar$entityJoinWorldEvent(var1, var2);
   }

   @Inject(
      method = "joinEntityInSurroundings",
      at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;loadedEntityList:Ljava/util/List;", opcode = 180, ordinal = 1),
      cancellable = true
   )
   private void lunar$entityJoinWorldEvent(Entity var1, CallbackInfo var2) {
      if (this.isRemote) {
         EventEntityWorldJoin var3 = (EventEntityWorldJoin)ClientEventBus.method29().method12(EventEntityWorldJoin.class, () -> new EventEntityWorldJoin((BridgeExtension)var1, this));
         if (var3 != null && var3.isCancelled()) {
            var2.cancel();
         }
      }
   }

   @Inject(method = "removeEntity", at = @At("TAIL"))
   private void lunar$entityRemoveEvents(Entity var1, CallbackInfo var2) {
      if (this.isRemote) {
         if (var1 instanceof Bridge6_10 var3) {
            ClientEventBus.method29().method12(EventPlayerRemoval.class, () -> new EventPlayerRemoval(var3));
         } else if (var1 instanceof BridgeExtension2_3 var4) {
            ClientEventBus.method29().method12(EventProjectileRemoval.class, () -> new EventProjectileRemoval(var4));
         } else {
            ClientEventBus.method29().method12(EventEntityRemoval.class, () -> new EventEntityRemoval((BridgeExtension)var1));
         }
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "setActivePlayerChunksAndCheckLight$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$fasterChunksCalculation(CallbackInfo var1) {
      if (this.isRemote) {
         var1.cancel();
         Minecraft var2 = Minecraft.getMinecraft();
         Object var3 = ThreadModuleDump63.MC_VERSION >= 1 ? var2.thePlayer : var2.thePlayer$v1_7;
         Set var4 = ThreadModuleDump63.MC_VERSION >= 1 ? this.activeChunkSet : this.activeChunkSet$v1_7;
         int var5 = MathHelper.floor_double(((EntityPlayerSP)var3).posX / 16.0);
         int var6 = MathHelper.floor_double(((EntityPlayerSP)var3).posZ / 16.0);
         if (var5 != this.lunar$playerChunkX || var6 != this.lunar$playerChunkZ) {
            this.lunar$playerChunkX = var5;
            this.lunar$playerChunkZ = var6;
            var4.clear();
            int var7 = var2.gameSettings.renderDistanceChunks;
            this.profiler.startSection("buildList");
            int var8 = MathHelper.floor_double(((EntityPlayerSP)var3).posX / 16.0);
            int var9 = MathHelper.floor_double(((EntityPlayerSP)var3).posZ / 16.0);

            for (int var10 = -var7; var10 <= var7; var10++) {
               for (int var11 = -var7; var11 <= var7; var11++) {
                  var4.add(new ChunkCoordIntPair(var10 + var8, var11 + var9));
               }
            }

            this.profiler.endSection();
         }

         if (this.ambientTickCountdown > 0) {
            this.ambientTickCountdown--;
         }

         this.profiler.startSection("playerCheckLight");
         List var13 = ThreadModuleDump63.MC_VERSION >= 1 ? this.playerEntities : this.playerEntities$v1_7;
         if (!var13.isEmpty()) {
            int var14 = this.rand.nextInt(var13.size());
            EntityPlayer var15 = (EntityPlayer)var13.get(var14);
            int var16 = MathHelper.floor_double(var15.posX) + this.rand.nextInt(11) - 5;
            int var17 = MathHelper.floor_double(var15.posY) + this.rand.nextInt(11) - 5;
            int var12 = MathHelper.floor_double(var15.posZ) + this.rand.nextInt(11) - 5;
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               this.checkLight(new BlockPos(var16, var17, var12));
            } else {
               this.updateAllLightTypes$v1_7(var16, var17, var12);
            }
         }

         this.profiler.endSection();
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "spawnParticle$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$onSpawnParticle$v1_7(String var1, double var2, double var4, double var6, double var8, double var10, double var12, CallbackInfo var14) {
      if (this.isRemote) {
         com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn var15 = (com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn)ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn.class,
               () -> {
                  HorsestatsType var13 = HorsestatsType.getParticleFromName(var1);
                  if (var13 == null) {
                     if (!LunarBuildData.field4) {
                        throw new EnumConstantNotPresentException(HorsestatsType2.class, var1);
                     } else {
                        return null;
                     }
                  } else {
                     float var14x = 0.0F;
                     float var15x = 0.0F;
                     float var16 = 0.0F;
                     if (var13 == HorsestatsType.CRIT) {
                        var14x = 0.9F;
                        var15x = 0.9F;
                        var16 = 0.9F;
                     } else if (var13 == HorsestatsType.CRIT_MAGIC) {
                        var14x = 0.3F;
                        var15x = 0.71999997F;
                        var16 = 0.9F;
                     } else if (var13 == HorsestatsType.FALLING_DUST) {
                        var14x = (float)var8;
                        var15x = (float)var10;
                        var16 = (float)var12;
                     }

                     return new com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn(
                        var13.asModernParticle(), var2, var4, var6, (float)var8, (float)var10, (float)var12, var14x, var15x, var16, 0.0F
                     );
                  }
               }
            );
         if (var15 != null && var15.isCancelled()) {
            var14.cancel();
         }
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "spawnParticle$v1_8(IZDDDDDD[I)V", at = @At("HEAD"), cancellable = true)
   private void lunar$onSpawnParticle$v1_8(
      int var1, boolean var2, double var3, double var5, double var7, double var9, double var11, double var13, int[] var15, CallbackInfo var16
   ) {
      if (this.isRemote) {
         com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn var17 = (com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn)ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn.class,
               () -> {
                  HorsestatsType var13x = HorsestatsType.getParticleFromId(var1);
                  if (var13x == null) {
                     if (!LunarBuildData.field4) {
                        throw new EnumConstantNotPresentException(HorsestatsType2.class, String.valueOf(var1));
                     } else {
                        return null;
                     }
                  } else {
                     float var14 = 0.0F;
                     float var15x = 0.0F;
                     float var16x = 0.0F;
                     if (var13x == HorsestatsType.CRIT) {
                        var14 = 0.9F;
                        var15x = 0.9F;
                        var16x = 0.9F;
                     } else if (var13x == HorsestatsType.CRIT_MAGIC) {
                        var14 = 0.3F;
                        var15x = 0.71999997F;
                        var16x = 0.9F;
                     } else if (var13x == HorsestatsType.FALLING_DUST) {
                        var14 = (float)var9;
                        var15x = (float)var11;
                        var16x = (float)var13;
                     }

                     return new com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn(
                        var13x.asModernParticle(), var3, var5, var7, (float)var9, (float)var11, (float)var13, var14, var15x, var16x, 0.0F
                     );
                  }
               }
            );
         if (var17 != null && var17.isCancelled()) {
            var16.cancel();
         }
      }
   }

   @Inject(method = "getRainStrength", at = @At("TAIL"), cancellable = true)
   private void lunar$getRainStrength(float var1, CallbackInfoReturnable<Float> var2) {
      if (this.bridge$isRemote()) {
         if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
            WeatherChanger var3 = ThreadModuleDump63.method4().method40().method55();
            if (var3.isEnabled() && var3.method14().get() == WeatherChanger.Type.RAIN) {
               var2.setReturnValue(var3.method13());
            }
         }
      }
   }
}
