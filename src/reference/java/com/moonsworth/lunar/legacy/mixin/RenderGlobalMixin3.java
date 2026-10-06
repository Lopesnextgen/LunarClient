package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
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

@Annotation2(min = 1)
@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin3 {
   @Unique
   private int lunar$chunksToWait;

   @Redirect(
      method = "renderSky(Lnet/minecraft/client/renderer/BufferBuilder;FZ)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderDistance:I", opcode = 180, remap = false)
   )
   @Dynamic
   private int lunar$distanceOverride(RenderGlobal var1) {
      return 256;
   }

   @Redirect(
      method = "renderSky(FI)V",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;renderDistanceChunks:I")),
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderGlobal;vboEnabled:Z", ordinal = 0)
   )
   @Dynamic
   private boolean lunar$fixVBO(RenderGlobal var1) {
      return false;
   }

   @WrapOperation(
      method = "updateChunks$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/chunk/ChunkRenderDispatcher;updateChunkLater(Lnet/minecraft/client/renderer/chunk/RenderChunk;)Z"
      )
   )
   private boolean lunar$rewindFlawlessChunk(ChunkRenderDispatcher var1, RenderChunk var2, Operation<Boolean> var3) {
      return FlawlessFrames.get() ? var1.updateChunkNow(var2) : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @Inject(method = "updateChunks$v1_8", at = @At("HEAD"))
   private void lunar$beginLazyChunkLoading$v1_8(CallbackInfo var1, @Share("lazyAmt") LocalIntRef var2) {
      var2.set(FlawlessFrames.get() ? 1 : ThreadModuleDump63.method4().method41().method7().method22().get().getAmount());
   }

   @Inject(
      method = "updateChunks$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/chunk/RenderChunk;isChunkRegionEmpty()Z"),
      cancellable = true
   )
   private void lunar$onUpdateRenderers$v1_8(long var1, CallbackInfo var3, @Share("lazyAmt") LocalIntRef var4) {
      int var5 = var4.get();
      if (var5 != 1) {
         if (this.lunar$chunksToWait <= 0) {
            this.lunar$chunksToWait = var5;
         } else {
            this.lunar$chunksToWait--;
            var3.cancel();
         }
      }
   }

   @Annotation2(1)
   @WrapOperation(method = "updateChunks$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/chunk/RenderChunk;isChunkRegionEmpty()Z"))
   private boolean lunar$onUpdateRenderers$flawless$v1_8(RenderChunk var1, Operation<Boolean> var2) {
      return FlawlessFrames.get() || (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderEntities$v1_8",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entityOutlines", ordinal = 1)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;disableDepth()V", ordinal = 0)
   )
   private void lunar$renderGlowingEntities$disableDepth(Entity var1, ICamera var2, float var3, CallbackInfo var4) {
      GlStateManager.depthFunc(519);
   }

   @Inject(
      method = "renderEntities$v1_8",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entityOutlines", ordinal = 1)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;enableDepth()V", ordinal = 0)
   )
   private void lunar$renderGlowingEntities$enableDepth(Entity var1, ICamera var2, float var3, CallbackInfo var4) {
      GlStateManager.depthFunc(515);
   }
}
