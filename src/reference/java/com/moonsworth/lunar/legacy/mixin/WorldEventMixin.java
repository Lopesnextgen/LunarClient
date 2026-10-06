package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.particle.LegacyParticleType;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.entity.EventEntityCollisionBoxes;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileRemove;
import com.moonsworth.lunar.client.event.mixin.fishing.EventGetHorizon;
import com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger;
import com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger.Type;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
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
public abstract class WorldEventMixin implements Itemcounter6, IBlockAccess {
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

   public WorldEventMixin() {
   }

   @Shadow
   public abstract void onEntityAdded(Entity entity1);

   @Shadow
   public abstract boolean checkLight(BlockPos pos1);

   @Shadow
   public abstract boolean updateAllLightTypes$v1_7(int number1, int number2, int number3);

   @ModifyReturnValue(method = "getHorizon", at = @At("RETURN"))
   private double lunar$getHorizon(double value1) {
      if (!this.isRemote) {
         return value1;
      }

      EventGetHorizon highlightimpl183 = (EventGetHorizon)LunarEventBus.method29().method12(EventGetHorizon.class, () -> {
         EventGetHorizon highlightimpl182 = new EventGetHorizon();
         highlightimpl182.setValue(value1);
         return highlightimpl182;
      });
      return highlightimpl183 != null && this.worldInfo.getTerrainType() != WorldType.FLAT ? highlightimpl183.getValue() : value1;
   }

   @VersionGate(min = 5)
   @Redirect(method = "markBlocksDirtyVertical", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;hasSkyLight$v1_12()Z"))
   private boolean lunar$markBlocksDirtyVertical$v1_12(WorldProvider worldprovider1) {
      return this.isRemote && Ref.method4().method40().method56().method13() ? false : worldprovider1.hasSkyLight$v1_12();
   }

   @VersionGate(1)
   @Redirect(method = "markBlocksDirtyVertical", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;getHasNoSky$v1_8()Z"))
   private boolean lunar$markBlocksDirtyVertical$v1_8(WorldProvider worldprovider1) {
      return worldprovider1.getHasNoSky() || this.isRemote && Ref.method4().method40().method56().method13();
   }

   @VersionGate(max = 0)
   @Redirect(method = "markBlocksDirtyVertical", at = @At(value = "FIELD", target = "Lnet/minecraft/world/WorldProvider;nether:Z"))
   public boolean lunar$markBlocksDirtyVertical$getHasNoSky(WorldProvider worldprovider1) {
      return worldprovider1.hasNoSky || this.isRemote && Ref.method4().method40().method56().method13();
   }

   @VersionGate(min = 1)
   @Inject(method = "checkLight$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLight$v1_8(BlockPos pos1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (this.isRemote && Ref.method4().method40().method56().method13()) {
         callbackinforeturnable2.setReturnValue(true);
      }
   }

   @VersionGate(max = 0)
   @Inject(method = "updateAllLightTypes$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLight$v1_7(int number1, int number2, int number3, CallbackInfoReturnable<Boolean> callbackinforeturnable4) {
      if (this.isRemote && Ref.method4().method40().method56().method13()) {
         callbackinforeturnable4.setReturnValue(true);
      }
   }

   @VersionGate(min = 1)
   @Inject(method = "checkLightFor$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLightFor$v1_8(EnumSkyBlock enumskyblock1, BlockPos pos2, CallbackInfoReturnable<Boolean> callbackinforeturnable3) {
      if (this.isRemote && Ref.method4().method40().method56().method13()) {
         callbackinforeturnable3.setReturnValue(true);
      }
   }

   @VersionGate(max = 0)
   @Inject(method = "updateLightByType$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$checkLightFor$v1_7(EnumSkyBlock enumskyblock1, int number2, int number3, int number4, CallbackInfoReturnable<Boolean> callbackinforeturnable5) {
      if (this.isRemote && Ref.method4().method40().method56().method13()) {
         callbackinforeturnable5.setReturnValue(true);
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
   private void lunar$collisionsEvent(Entity entity1, AxisAlignedBB box2, CallbackInfoReturnable<List<AxisAlignedBB>> callbackinforeturnable3) {
      if (this.isRemote) {
         LunarEventBus.method29().method12(EventEntityCollisionBoxes.class, () -> new EventEntityCollisionBoxes((BridgeExtension)entity1, (AxisAlignedBBBridge)box2, (List)callbackinforeturnable3.getReturnValue()));
      }
   }

   @VersionGate(max = 1)
   @Inject(method = {"getCollisionBoxes$v1_8", "func_147461_a$v1_7"}, at = @At("RETURN"))
   private void lunar$collisionBoxesEvent(AxisAlignedBB box1, CallbackInfoReturnable<List<AxisAlignedBB>> callbackinforeturnable2) {
      if (this.isRemote) {
         LunarEventBus.method29().method12(EventEntityCollisionBoxes.class, () -> new EventEntityCollisionBoxes(null, (AxisAlignedBBBridge)box1, (List)callbackinforeturnable2.getReturnValue()));
      }
   }

   @Unique
   private void lunar$entityJoinWorldEvent(Entity entity1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (this.isRemote) {
         EventEntityJoinWorld highlightimpl203 = (EventEntityJoinWorld)LunarEventBus.method29().method12(EventEntityJoinWorld.class, () -> new EventEntityJoinWorld((BridgeExtension)entity1, this));
         if (highlightimpl203 != null && highlightimpl203.isCancelled() && !entity1.forceSpawn && !(entity1 instanceof EntityPlayer)) {
            callbackinforeturnable2.setReturnValue(false);
         }
      }
   }

   @VersionGate(min = 5)
   @Inject(
      method = "spawnEntity$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getChunk$v1_12(II)Lnet/minecraft/world/chunk/Chunk;", shift = Shift.BEFORE),
      cancellable = true
   )
   private void lunar$entityJoinWorldEvent$v1_12(Entity entity1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      this.lunar$entityJoinWorldEvent(entity1, callbackinforeturnable2);
   }

   @VersionGate(max = 1)
   @Inject(
      method = "spawnEntityInWorld$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getChunkFromChunkCoords$v1_7(II)Lnet/minecraft/world/chunk/Chunk;", shift = Shift.BEFORE),
      cancellable = true
   )
   private void lunar$entityJoinWorldEvent$v1_7(Entity entity1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      this.lunar$entityJoinWorldEvent(entity1, callbackinforeturnable2);
   }

   @Unique
   private void lunar$entityJoinWorldEvent(Collection<Entity> list1, CallbackInfo callback2) {
      if (this.isRemote) {
         for (Entity entity4 : list1) {
            EventEntityJoinWorld highlightimpl205 = (EventEntityJoinWorld)LunarEventBus.method29()
               .method12(EventEntityJoinWorld.class, () -> new EventEntityJoinWorld((BridgeExtension)entity4, this));
            if (highlightimpl205 != null && !highlightimpl205.isCancelled()) {
               if (Ref.MC_VERSION >= 1) {
                  this.loadedEntityList.add(entity4);
               } else {
                  this.loadedEntityList$v1_7.add(entity4);
               }

               this.onEntityAdded(entity4);
            }
         }

         callback2.cancel();
      }
   }

   @VersionGate(min = 1)
   @Inject(method = "loadEntities$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$entityJoinWorldEvent$v1_8(Collection<Entity> list1, CallbackInfo callback2) {
      this.lunar$entityJoinWorldEvent(list1, callback2);
   }

   @VersionGate(max = 0)
   @Inject(method = "addLoadedEntities$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$entityJoinWorldEvent$v1_7(List<Entity> list1, CallbackInfo callback2) {
      this.lunar$entityJoinWorldEvent(list1, callback2);
   }

   @Inject(
      method = "joinEntityInSurroundings",
      at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;loadedEntityList:Ljava/util/List;", opcode = 180, ordinal = 1),
      cancellable = true
   )
   private void lunar$entityJoinWorldEvent(Entity entity1, CallbackInfo callback2) {
      if (this.isRemote) {
         EventEntityJoinWorld highlightimpl203 = (EventEntityJoinWorld)LunarEventBus.method29().method12(EventEntityJoinWorld.class, () -> new EventEntityJoinWorld((BridgeExtension)entity1, this));
         if (highlightimpl203 != null && highlightimpl203.isCancelled()) {
            callback2.cancel();
         }
      }
   }

   @Inject(method = "removeEntity", at = @At("TAIL"))
   private void lunar$entityRemoveEvents(Entity entity1, CallbackInfo callback2) {
      if (this.isRemote) {
         if (entity1 instanceof Bridge6_10 bridge6_103) {
            LunarEventBus.method29().method12(EventPlayerRemove.class, () -> new EventPlayerRemove(bridge6_103));
         } else if (entity1 instanceof BridgeExtension2_3 bridgeextension2_34) {
            LunarEventBus.method29().method12(EventProjectileRemove.class, () -> new EventProjectileRemove(bridgeextension2_34));
         } else {
            LunarEventBus.method29().method12(EventEntityRemove.class, () -> new EventEntityRemove((BridgeExtension)entity1));
         }
      }
   }

   @VersionGate(max = 1)
   @Inject(method = "setActivePlayerChunksAndCheckLight$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$fasterChunksCalculation(CallbackInfo callback1) {
      if (this.isRemote) {
         callback1.cancel();
         Minecraft minecraft2 = Minecraft.getMinecraft();
         Object obj3 = Ref.MC_VERSION >= 1 ? minecraft2.thePlayer : minecraft2.thePlayer$v1_7;
         Set set4 = Ref.MC_VERSION >= 1 ? this.activeChunkSet : this.activeChunkSet$v1_7;
         int number5 = MathHelper.floor_double(((EntityPlayerSP)obj3).posX / 16.0);
         int number6 = MathHelper.floor_double(((EntityPlayerSP)obj3).posZ / 16.0);
         if (number5 != this.lunar$playerChunkX || number6 != this.lunar$playerChunkZ) {
            this.lunar$playerChunkX = number5;
            this.lunar$playerChunkZ = number6;
            set4.clear();
            int number7 = minecraft2.gameSettings.renderDistanceChunks;
            this.profiler.startSection("buildList");
            int number8 = MathHelper.floor_double(((EntityPlayerSP)obj3).posX / 16.0);
            int number9 = MathHelper.floor_double(((EntityPlayerSP)obj3).posZ / 16.0);

            for (int index10 = -number7; index10 <= number7; index10++) {
               for (int index11 = -number7; index11 <= number7; index11++) {
                  set4.add(new ChunkCoordIntPair(index10 + number8, index11 + number9));
               }
            }

            this.profiler.endSection();
         }

         if (this.ambientTickCountdown > 0) {
            this.ambientTickCountdown--;
         }

         this.profiler.startSection("playerCheckLight");
         List list13 = Ref.MC_VERSION >= 1 ? this.playerEntities : this.playerEntities$v1_7;
         if (!list13.isEmpty()) {
            int index14 = this.rand.nextInt(list13.size());
            EntityPlayer player15 = (EntityPlayer)list13.get(index14);
            int number16 = MathHelper.floor_double(player15.posX) + this.rand.nextInt(11) - 5;
            int number17 = MathHelper.floor_double(player15.posY) + this.rand.nextInt(11) - 5;
            int number12 = MathHelper.floor_double(player15.posZ) + this.rand.nextInt(11) - 5;
            if (Ref.MC_VERSION >= 1) {
               this.checkLight(new BlockPos(number16, number17, number12));
            } else {
               this.updateAllLightTypes$v1_7(number16, number17, number12);
            }
         }

         this.profiler.endSection();
      }
   }

   @VersionGate(max = 0)
   @Inject(method = "spawnParticle$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$onSpawnParticle$v1_7(String text1, double value2, double value4, double value6, double value8, double value10, double value12, CallbackInfo callback14) {
      if (this.isRemote) {
         com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle highlightimpl1515 = (com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle)LunarEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle.class,
               () -> {
                  LegacyParticleType horsestatstype13 = LegacyParticleType.getParticleFromName(text1);
                  if (horsestatstype13 == null) {
                     if (!LunarBuildData.field4) {
                        throw new EnumConstantNotPresentException(ParticleType.class, text1);
                     } else {
                        return null;
                     }
                  } else {
                     float value14x = 0.0F;
                     float value15x = 0.0F;
                     float value16 = 0.0F;
                     if (horsestatstype13 == LegacyParticleType.CRIT) {
                        value14x = 0.9F;
                        value15x = 0.9F;
                        value16 = 0.9F;
                     } else if (horsestatstype13 == LegacyParticleType.CRIT_MAGIC) {
                        value14x = 0.3F;
                        value15x = 0.71999997F;
                        value16 = 0.9F;
                     } else if (horsestatstype13 == LegacyParticleType.FALLING_DUST) {
                        value14x = (float)value8;
                        value15x = (float)value10;
                        value16 = (float)value12;
                     }

                     return new com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle(
                        horsestatstype13.asModernParticle(), value2, value4, value6, (float)value8, (float)value10, (float)value12, value14x, value15x, value16, 0.0F
                     );
                  }
               }
            );
         if (highlightimpl1515 != null && highlightimpl1515.isCancelled()) {
            callback14.cancel();
         }
      }
   }

   @VersionGate(min = 1)
   @Inject(method = "spawnParticle$v1_8(IZDDDDDD[I)V", at = @At("HEAD"), cancellable = true)
   private void lunar$onSpawnParticle$v1_8(
      int number1, boolean flag2, double value3, double value5, double value7, double value9, double value11, double value13, int[] items15, CallbackInfo callback16
   ) {
      if (this.isRemote) {
         com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle highlightimpl1517 = (com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle)LunarEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle.class,
               () -> {
                  LegacyParticleType horsestatstype13x = LegacyParticleType.getParticleFromId(number1);
                  if (horsestatstype13x == null) {
                     if (!LunarBuildData.field4) {
                        throw new EnumConstantNotPresentException(ParticleType.class, String.valueOf(number1));
                     } else {
                        return null;
                     }
                  } else {
                     float value14 = 0.0F;
                     float value15x = 0.0F;
                     float value16x = 0.0F;
                     if (horsestatstype13x == LegacyParticleType.CRIT) {
                        value14 = 0.9F;
                        value15x = 0.9F;
                        value16x = 0.9F;
                     } else if (horsestatstype13x == LegacyParticleType.CRIT_MAGIC) {
                        value14 = 0.3F;
                        value15x = 0.71999997F;
                        value16x = 0.9F;
                     } else if (horsestatstype13x == LegacyParticleType.FALLING_DUST) {
                        value14 = (float)value9;
                        value15x = (float)value11;
                        value16x = (float)value13;
                     }

                     return new com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle(
                        horsestatstype13x.asModernParticle(), value3, value5, value7, (float)value9, (float)value11, (float)value13, value14, value15x, value16x, 0.0F
                     );
                  }
               }
            );
         if (highlightimpl1517 != null && highlightimpl1517.isCancelled()) {
            callback16.cancel();
         }
      }
   }

   @Inject(method = "getRainStrength", at = @At("TAIL"), cancellable = true)
   private void lunar$getRainStrength(float value1, CallbackInfoReturnable<Float> callbackinforeturnable2) {
      if (this.bridge$isRemote()) {
         if (Ref.method4() != null && Ref.method4().method40() != null) {
            WeatherChanger weatherchanger3 = Ref.method4().method40().method55();
            if (weatherchanger3.isEnabled() && weatherchanger3.method14().get() == Type.RAIN) {
               callbackinforeturnable2.setReturnValue(weatherchanger3.method13());
            }
         }
      }
   }
}
