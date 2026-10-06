package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventChunkBase.EventEntityChunkEnter;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockModified;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunkLifecycle.EventChunkUnloaded;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunkLifecycle.EventChunkLoaded;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.util.Bridge2Handler;
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
public abstract class ChunkMixin2 implements Itemcounter2 {
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

   @Shadow
   public abstract void generateSkylightMap();

   @Inject(method = {"setLightValue$v1_7", "setLightFor$v1_8", "generateSkylightMap", "relightBlock"}, at = @At("RETURN"))
   private void lunar$onSetLight(CallbackInfo var1) {
      ThreadModuleDump63.method4().method40().method92().setNeedsUpdate(true);
   }

   @Annotation2(max = 0)
   @Inject(
      method = "setBlockIDWithMetadata$v1_7",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/world/chunk/Chunk;storageArrays:[Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;",
         ordinal = 0
      )
   )
   private void lunar$onBlockChanged$v1_7(
      int var1, int var2, int var3, Block var4, int var5, CallbackInfoReturnable<Boolean> var6, @Local(ordinal = 1) Block var7
   ) {
      if (this.world.isRemote) {
         ClientEventBus.method29()
            .method12(
               EventBlockModified.class, () -> new EventBlockModified(Bridge.method8().method4(var1, var2, var3), new Bridge2Handler(var7), new Bridge2Handler(var4))
            );
      }
   }

   @Annotation2(min = 1)
   @Inject(
      method = "setBlockState$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;", ordinal = 0)
   )
   private void lunar$onBlockChanged$v1_8(BlockPos var1, IBlockState var2, CallbackInfoReturnable<IBlockState> var3, @Local(ordinal = 1) IBlockState var4) {
      if (this.world.isRemote) {
         ClientEventBus.method29().method12(EventBlockModified.class, () -> new EventBlockModified((Vector3iBridge)var1, (Bridge2_17)var4, (Bridge2_17)var2));
      }
   }

   @Annotation2(min = 1)
   @Redirect(method = "recheckGaps", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isAreaLoaded$v1_8(Lnet/minecraft/util/math/BlockPos;I)Z"))
   private boolean lunar$recheckGaps(World var1, BlockPos var2, int var3, boolean var4) {
      return this.lunar$recheckGaps$lightingMod(var1, var4) ? false : var1.isAreaLoaded(var2, var3);
   }

   @Annotation2(max = 0)
   @Redirect(method = "recheckGaps", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;doChunksNearChunkExist$v1_7(IIII)Z"))
   private boolean lunar$recheckGaps(World var1, int var2, int var3, int var4, int var5, boolean var6) {
      return this.lunar$recheckGaps$lightingMod(var1, var6) ? false : var1.doChunksNearChunkExist$v1_7(var2, var3, var4, var5);
   }

   @Unique
   private boolean lunar$recheckGaps$lightingMod(World var1, boolean var2) {
      if (var1.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         return false;
      }

      if (!var2) {
         this.isGapLightingUpdated = false;
      }

      return true;
   }

   @Inject(method = "checkSkylightNeighborHeight", at = @At("HEAD"), cancellable = true)
   private void lunar$checkSkylightNeighborHeight(int var1, int var2, int var3, CallbackInfo var4) {
      if (this.world.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         var4.cancel();
      }
   }

   @Inject(method = "updateSkylightNeighborHeight", at = @At("HEAD"), cancellable = true)
   private void lunar$updateSkylightNeighborHeight(int var1, int var2, int var3, int var4, CallbackInfo var5) {
      if (this.world.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         var5.cancel();
      }
   }

   @Inject(method = "enqueueRelightChecks", at = @At("HEAD"), cancellable = true)
   private void lunar$enqueueRelightChecks(CallbackInfo var1) {
      if (this.world.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         var1.cancel();
      }
   }

   @Inject(method = {"checkLight$v1_12()V", "func_150809_p$v1_7"}, at = @At("HEAD"), cancellable = true)
   private void lunar$checkLight(CallbackInfo var1) {
      if (this.world.isRemote) {
         this.isTerrainPopulated = true;
         this.isLightPopulated = true;
         if (ThreadModuleDump63.method4().method40().method56().method13()) {
            var1.cancel();
         }
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "relightBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/world/WorldProvider;hasNoSky:Z", ordinal = 1))
   public boolean impl$relightBlock$getHasNoSky(WorldProvider var1) {
      return this.lunar$skipLightUpdates(var1);
   }

   @Annotation2(min = 5)
   @Redirect(method = "relightBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;hasSkyLight$v1_12()Z", ordinal = 1))
   private boolean lunar$relightBlock$hasSkyLight(WorldProvider var1) {
      return this.lunar$skipLightUpdates(var1);
   }

   @Annotation2(1)
   @Redirect(method = "relightBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;getHasNoSky()Z", ordinal = 1))
   private boolean lunar$relightBlock$hasSkyLight$v1_8(WorldProvider var1) {
      return this.lunar$skipLightUpdates(var1);
   }

   @Unique
   private boolean lunar$skipLightUpdates(WorldProvider var1) {
      if (this.world.isRemote && ThreadModuleDump63.method4().method40().method56().method13()) {
         return ThreadModuleDump63.MC_VERSION < 5;
      } else if (ThreadModuleDump63.MC_VERSION >= 5) {
         return var1.hasSkyLight$v1_12();
      } else {
         return ThreadModuleDump63.MC_VERSION == 1 ? var1.getHasNoSky() : var1.hasNoSky;
      }
   }

   @Annotation2(min = 1)
   @ModifyVariable(
      method = "setBlockState$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;set(IIILnet/minecraft/block/state/IBlockState;)V")
   )
   private boolean lunar$setBlockState$lightCheck1(boolean var1) {
      return var1 || this.world.isRemote && ThreadModuleDump63.method4().method40().method56().method13();
   }

   @Annotation2(min = 1)
   @Redirect(method = "setBlockState$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;generateSkylightMap()V"))
   private void lunar$setBlockState$lightCheck2(Chunk var1) {
      if (!this.world.isRemote || !ThreadModuleDump63.method4().method40().method56().method13()) {
         this.generateSkylightMap();
      }
   }

   @Inject(method = {"onLoad$v1_12", "onChunkLoad$v1_7"}, at = @At("TAIL"))
   private void lunar$chunkLoadEvent(CallbackInfo var1) {
      if (this.world.isRemote) {
         ClientEventBus.method29().method12(EventChunkLoaded.class, () -> new EventChunkLoaded(this));
      }
   }

   @Inject(method = "addEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;addedToChunk:Z", opcode = 181))
   private void lunar$entityEntersChunkEvent(Entity var1, CallbackInfo var2) {
      if (this.world.isRemote) {
         ClientEventBus.method29().method12(EventEntityChunkEnter.class, () -> new EventEntityChunkEnter((BridgeExtension)var1, this.x, this.z));
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "onUnload$v1_12", at = @At("RETURN"))
   private void lunar$onChunkUnload(CallbackInfo var1) {
      if (this.world.isRemote) {
         ClientEventBus.method29().method12(EventChunkUnloaded.class, () -> new EventChunkUnloaded(this));
      }
   }
}
