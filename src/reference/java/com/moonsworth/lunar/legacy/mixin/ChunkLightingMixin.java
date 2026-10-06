package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityChunkBase.EventEntityChunk;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunk.EventChunkUnload;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunk.EventChunkLoad;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.util.BlockStateBridgeV1_7;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chunk.class)
public abstract class ChunkLightingMixin implements ChunkBridge {
   @Final
   @Shadow
   public World world;
   @Shadow
   public boolean isTerrainPopulated;
   @Shadow
   public boolean isLightPopulated;
   @Shadow
   public boolean isGapLightingUpdated;
   @Final
   @Shadow
   public int x;
   @Final
   @Shadow
   public int z;

   public ChunkLightingMixin() {
   }

   @Shadow
   public abstract void generateSkylightMap();

   @Inject(method = {"setLightValue$v1_7", "setLightFor$v1_8", "generateSkylightMap", "relightBlock"}, at = @At("RETURN"))
   private void lunar$onSetLight(CallbackInfo callback1) {
      Ref.method4().method40().method92().setNeedsUpdate(true);
   }

   @VersionGate(max = 0)
   @Inject(
      method = "setBlockIDWithMetadata$v1_7",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/world/chunk/Chunk;storageArrays:[Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;",
         ordinal = 0
      )
   )
   private void lunar$onBlockChanged$v1_7(
      int number1, int number2, int number3, Block block4, int number5, CallbackInfoReturnable<Boolean> callbackinforeturnable6, @Local(ordinal = 1) Block block7
   ) {
      if (this.world.isRemote) {
         LunarEventBus.method29()
            .method12(
               EventBlockChange.class, () -> new EventBlockChange(Bridge.method8().method4(number1, number2, number3), new BlockStateBridgeV1_7(block7), new BlockStateBridgeV1_7(block4))
            );
      }
   }

   @VersionGate(min = 1)
   @Inject(
      method = "setBlockState$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;", ordinal = 0)
   )
   private void lunar$onBlockChanged$v1_8(BlockPos pos1, IBlockState state2, CallbackInfoReturnable<IBlockState> callbackinforeturnable3, @Local(ordinal = 1) IBlockState state4) {
      if (this.world.isRemote) {
         LunarEventBus.method29().method12(EventBlockChange.class, () -> new EventBlockChange((Vec3iBridge)pos1, (BlockStateBridge)state4, (BlockStateBridge)state2));
      }
   }

   @VersionGate(min = 1)
   @Redirect(method = "recheckGaps", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isAreaLoaded$v1_8(Lnet/minecraft/util/math/BlockPos;I)Z"))
   private boolean lunar$recheckGaps(World world1, BlockPos pos2, int number3, boolean flag4) {
      return this.lunar$recheckGaps$lightingMod(world1, flag4) ? false : world1.isAreaLoaded(pos2, number3);
   }

   @VersionGate(max = 0)
   @Redirect(method = "recheckGaps", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;doChunksNearChunkExist$v1_7(IIII)Z"))
   private boolean lunar$recheckGaps(World world1, int number2, int number3, int number4, int number5, boolean flag6) {
      return this.lunar$recheckGaps$lightingMod(world1, flag6) ? false : world1.doChunksNearChunkExist$v1_7(number2, number3, number4, number5);
   }

   @Unique
   private boolean lunar$recheckGaps$lightingMod(World world1, boolean flag2) {
      if (world1.isRemote && Ref.method4().method40().method56().method13()) {
         return false;
      }

      if (!flag2) {
         this.isGapLightingUpdated = false;
      }

      return true;
   }

   @Inject(method = "checkSkylightNeighborHeight", at = @At("HEAD"), cancellable = true)
   private void lunar$checkSkylightNeighborHeight(int number1, int number2, int number3, CallbackInfo callback4) {
      if (this.world.isRemote && Ref.method4().method40().method56().method13()) {
         callback4.cancel();
      }
   }

   @Inject(method = "updateSkylightNeighborHeight", at = @At("HEAD"), cancellable = true)
   private void lunar$updateSkylightNeighborHeight(int number1, int number2, int number3, int number4, CallbackInfo callback5) {
      if (this.world.isRemote && Ref.method4().method40().method56().method13()) {
         callback5.cancel();
      }
   }

   @Inject(method = "enqueueRelightChecks", at = @At("HEAD"), cancellable = true)
   private void lunar$enqueueRelightChecks(CallbackInfo callback1) {
      if (this.world.isRemote && Ref.method4().method40().method56().method13()) {
         callback1.cancel();
      }
   }

   @Inject(method = {"checkLight$v1_12()V", "func_150809_p$v1_7"}, at = @At("HEAD"), cancellable = true)
   private void lunar$checkLight(CallbackInfo callback1) {
      if (this.world.isRemote) {
         this.isTerrainPopulated = true;
         this.isLightPopulated = true;
         if (Ref.method4().method40().method56().method13()) {
            callback1.cancel();
         }
      }
   }

   @VersionGate(max = 0)
   @Redirect(method = "relightBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/world/WorldProvider;hasNoSky:Z", ordinal = 1))
   public boolean impl$relightBlock$getHasNoSky(WorldProvider worldprovider1) {
      return this.lunar$skipLightUpdates(worldprovider1);
   }

   @VersionGate(min = 5)
   @Redirect(method = "relightBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;hasSkyLight$v1_12()Z", ordinal = 1))
   private boolean lunar$relightBlock$hasSkyLight(WorldProvider worldprovider1) {
      return this.lunar$skipLightUpdates(worldprovider1);
   }

   @VersionGate(1)
   @Redirect(method = "relightBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;getHasNoSky()Z", ordinal = 1))
   private boolean lunar$relightBlock$hasSkyLight$v1_8(WorldProvider worldprovider1) {
      return this.lunar$skipLightUpdates(worldprovider1);
   }

   @Unique
   private boolean lunar$skipLightUpdates(WorldProvider worldprovider1) {
      if (this.world.isRemote && Ref.method4().method40().method56().method13()) {
         return Ref.MC_VERSION < 5;
      } else if (Ref.MC_VERSION >= 5) {
         return worldprovider1.hasSkyLight$v1_12();
      } else {
         return Ref.MC_VERSION == 1 ? worldprovider1.getHasNoSky() : worldprovider1.hasNoSky;
      }
   }

   @VersionGate(min = 1)
   @ModifyVariable(
      method = "setBlockState$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;set(IIILnet/minecraft/block/state/IBlockState;)V")
   )
   private boolean lunar$setBlockState$lightCheck1(boolean flag1) {
      return flag1 || this.world.isRemote && Ref.method4().method40().method56().method13();
   }

   @VersionGate(min = 1)
   @Redirect(method = "setBlockState$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;generateSkylightMap()V"))
   private void lunar$setBlockState$lightCheck2(Chunk chunk1) {
      if (!this.world.isRemote || !Ref.method4().method40().method56().method13()) {
         this.generateSkylightMap();
      }
   }

   @Inject(method = {"onLoad$v1_12", "onChunkLoad$v1_7"}, at = @At("TAIL"))
   private void lunar$chunkLoadEvent(CallbackInfo callback1) {
      if (this.world.isRemote) {
         LunarEventBus.method29().method12(EventChunkLoad.class, () -> new EventChunkLoad(this));
      }
   }

   @Inject(method = "addEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;addedToChunk:Z", opcode = 181))
   private void lunar$entityEntersChunkEvent(Entity entity1, CallbackInfo callback2) {
      if (this.world.isRemote) {
         LunarEventBus.method29().method12(EventEntityChunk.class, () -> new EventEntityChunk((BridgeExtension)entity1, this.x, this.z));
      }
   }

   @VersionGate(min = 5)
   @Inject(method = "onUnload$v1_12", at = @At("RETURN"))
   private void lunar$onChunkUnload(CallbackInfo callback1) {
      if (this.world.isRemote) {
         LunarEventBus.method29().method12(EventChunkUnload.class, () -> new EventChunkUnload(this));
      }
   }
}
