package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.moonsworth.lunar.client.config.PerformanceSettings.Type;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.util.FlawlessFrames;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(RenderGlobal.class)
public abstract class RenderGlobalFlawlessMixin {
   @Unique
   private int lunar$chunksToWait;

   public RenderGlobalFlawlessMixin() {
   }

   @Redirect(
      method = "renderSky(Lnet/minecraft/client/renderer/BufferBuilder;FZ)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderDistance:I", opcode = 180, remap = false)
   )
   @Dynamic
   private int lunar$distanceOverride(RenderGlobal renderglobal1) {
      return 256;
   }

   @Redirect(
      method = "renderSky(FI)V",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;renderDistanceChunks:I")),
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderGlobal;vboEnabled:Z", ordinal = 0)
   )
   @Dynamic
   private boolean lunar$fixVBO(RenderGlobal renderglobal1) {
      return false;
   }

   @WrapOperation(
      method = "updateChunks$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/chunk/ChunkRenderDispatcher;updateChunkLater(Lnet/minecraft/client/renderer/chunk/RenderChunk;)Z"
      )
   )
   private boolean lunar$rewindFlawlessChunk(ChunkRenderDispatcher chunkrenderdispatcher1, RenderChunk renderchunk2, Operation<Boolean> operation3) {
      return FlawlessFrames.get() ? chunkrenderdispatcher1.updateChunkNow(renderchunk2) : (Boolean)operation3.call(new Object[]{chunkrenderdispatcher1, renderchunk2});
   }

   @Inject(method = "updateChunks$v1_8", at = @At("HEAD"))
   private void lunar$beginLazyChunkLoading$v1_8(CallbackInfo callback1, @Share("lazyAmt") LocalIntRef localintref2) {
      localintref2.set(FlawlessFrames.get() ? 1 : ((Type)Ref.method4().method41().method7().method22().get()).getAmount());
   }

   @Inject(
      method = "updateChunks$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/chunk/RenderChunk;isChunkRegionEmpty()Z"),
      cancellable = true
   )
   private void lunar$onUpdateRenderers$v1_8(long number1, CallbackInfo callback3, @Share("lazyAmt") LocalIntRef localintref4) {
      int number5 = localintref4.get();
      if (number5 != 1) {
         if (this.lunar$chunksToWait <= 0) {
            this.lunar$chunksToWait = number5;
         } else {
            this.lunar$chunksToWait--;
            callback3.cancel();
         }
      }
   }

   @VersionGate(1)
   @WrapOperation(method = "updateChunks$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/chunk/RenderChunk;isChunkRegionEmpty()Z"))
   private boolean lunar$onUpdateRenderers$flawless$v1_8(RenderChunk renderchunk1, Operation<Boolean> operation2) {
      return FlawlessFrames.get() || (Boolean)operation2.call(new Object[]{renderchunk1});
   }

   @VersionGate(min = 5)
   @Inject(
      method = "renderEntities$v1_8",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entityOutlines", ordinal = 1)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;disableDepth()V", ordinal = 0)
   )
   private void lunar$renderGlowingEntities$disableDepth(Entity entity1, ICamera icamera2, float value3, CallbackInfo callback4) {
      GlStateManager.depthFunc(519);
   }

   @Inject(
      method = "renderEntities$v1_8",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entityOutlines", ordinal = 1)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;enableDepth()V", ordinal = 0)
   )
   private void lunar$renderGlowingEntities$enableDepth(Entity entity1, ICamera icamera2, float value3, CallbackInfo callback4) {
      GlStateManager.depthFunc(515);
   }
}
