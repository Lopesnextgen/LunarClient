package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge9_7;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.RenderGlobal.ContainerLocalRenderInformation;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin implements Bridge14_3 {
   @Annotation2(max = 0)
   @Shadow
   public int renderersLoaded$v1_7;
   @Annotation2(max = 0)
   @Shadow
   public int renderersBeingRendered$v1_7;
   @Annotation2(min = 1)
   @Shadow
   public ViewFrustum viewFrustum;
   @Shadow
   public List<ContainerLocalRenderInformation> renderInfos;
   @Shadow
   public boolean displayListEntitiesDirty;
   @Shadow
   public int countEntitiesRendered;
   @Shadow
   public Framebuffer entityOutlineFramebuffer;
   @Shadow
   public int renderDistanceChunks;
   @Shadow
   public Set<TileEntity> setTileEntities;
   @Shadow
   public List tileEntities$v1_7;
   @Annotation2(max = 0)
   @Shadow
   public WorldRenderer[] worldRenderers$v1_7;
   @Unique
   public int bridge$countPlayersRendered;
   @Unique
   private ICamera bridge$lastCamera;
   @Unique
   private final List<Bridge9_7> lunar$renderChunks = new ArrayList<>();

   @Shadow
   public abstract void loadRenderers();

   @Override
   public int bridge$getMaximumRenderCount() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.viewFrustum.renderChunks.length : this.renderersLoaded$v1_7;
   }

   @Override
   public int bridge$getUnculledRenderCount() {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return this.renderersBeingRendered$v1_7;
      }

      int var1 = 0;

      for (ContainerLocalRenderInformation var3 : this.renderInfos) {
         if (bridge$filterChunks_v1_8(var3)) {
            var1++;
         }
      }

      return var1;
   }

   @Override
   public int bridge$getRenderedEntityCount() {
      return this.countEntitiesRendered;
   }

   @Override
   public int bridge$getRenderedBlockEntityCount() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.setTileEntities.size() : this.tileEntities$v1_7.size();
   }

   @Override
   public int bridge$getRenderedPlayersCount() {
      return this.bridge$countPlayersRendered;
   }

   @Unique
   @Annotation2(min = 1)
   private static boolean bridge$filterChunks_v1_8(ContainerLocalRenderInformation var0) {
      RenderChunk var1 = var0.renderChunk;
      CompiledChunk var2 = var1.compiledChunk;
      return var2 != CompiledChunk.DUMMY && !var2.isEmpty();
   }

   @Override
   public void bridge$setNeedsFullRenderChunkUpdate(boolean var1) {
      this.displayListEntitiesDirty = var1;
   }

   @Override
   public void bridge$reloadChunks() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Minecraft.theMinecraft.addScheduledTask(this::loadRenderers);
      } else {
         Minecraft.theMinecraft.addScheduledTask(this::loadRenderers);
      }
   }

   @Override
   public List<Bridge9_7> bridge$getRenderChunks() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.renderInfos : this.lunar$renderChunks;
   }

   @Override
   public boolean bridge$isVisible(AxisAlignedBBBridge var1) {
      return this.bridge$lastCamera.isBoundingBoxInFrustum((AxisAlignedBB)var1);
   }

   @Override
   public boolean bridge$isBlockVisible(int var1, int var2, int var3) {
      return this.bridge$lastCamera instanceof Frustum var4
         ? var4.isBoxInFrustum(var1, var2, var3, var1 + 1.0, var2 + 1.0, var3 + 1.0)
         : this.bridge$lastCamera.isBoundingBoxInFrustum(new AxisAlignedBB(var1, var2, var3, var1 + 1.0, var2 + 1.0, var3 + 1.0));
   }

   @Override
   public Bridge3_24 bridge$entityTarget() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return (Bridge3_24)this.entityOutlineFramebuffer;
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   @Annotation2(max = 0)
   @Inject(
      method = "renderEntities$v1_7",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderGlobal;countEntitiesRendered:I", opcode = 181, ordinal = 0)
   )
   private void bridge$resetCount(EntityLivingBase var1, ICamera var2, float var3, CallbackInfo var4) {
      this.bridge$countPlayersRendered = 0;
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "renderEntities$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;renderEntitySimple$v1_7(Lnet/minecraft/entity/Entity;F)Z")
   )
   private boolean bridge$countRenderedPlayers$v1_7(RenderManager var1, Entity var2, float var3) {
      if (var2 instanceof Bridge6_10) {
         this.bridge$countPlayersRendered++;
      }

      return true;
   }

   @Annotation2(1)
   @Inject(
      method = "renderEntities$v1_8",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderGlobal;countEntitiesRendered:I", opcode = 181, ordinal = 0)
   )
   private void bridge$resetCount(Entity var1, ICamera var2, float var3, CallbackInfo var4) {
      this.bridge$countPlayersRendered = 0;
   }

   @Annotation2(1)
   @WrapWithCondition(
      method = "renderEntities$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;renderEntitySimple(Lnet/minecraft/entity/Entity;F)Z")
   )
   private boolean bridge$countRenderedPlayers$v1_8(RenderManager var1, Entity var2, float var3) {
      if (var2 instanceof Bridge6_10) {
         this.bridge$countPlayersRendered++;
      }

      return true;
   }

   @Annotation2(min = 5)
   @WrapWithCondition(
      method = "renderEntities$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;renderEntityStatic$v1_12(Lnet/minecraft/entity/Entity;FZ)V")
   )
   private boolean bridge$countRenderedPlayers$v1_12(RenderManager var1, Entity var2, float var3, boolean var4) {
      if (var2 instanceof Bridge6_10) {
         this.bridge$countPlayersRendered++;
      }

      return true;
   }

   @Annotation2(max = 0)
   @Inject(method = "renderEntities$v1_7", at = @At("HEAD"))
   private void bridge$cacheLastCamera(EntityLivingBase var1, ICamera var2, float var3, CallbackInfo var4) {
      this.bridge$lastCamera = var2;
   }

   @Annotation2(min = 1)
   @Inject(method = "setupTerrain$v1_8", at = @At("HEAD"))
   private void bridge$cacheLastCamera(Entity var1, double var2, ICamera var4, int var5, boolean var6, CallbackInfo var7) {
      this.bridge$lastCamera = var4;
   }

   @Override
   public boolean bridge$isInViewDistance(Horsestats20Extension var1, Horsestats20Extension var2) {
      int var3 = this.renderDistanceChunks - 3;
      int var4 = Math.max(0, Math.abs(var2.bridge$getX() - var1.bridge$getX()) - 1);
      int var5 = Math.max(0, Math.abs(var2.bridge$getZ() - var1.bridge$getZ()) - 1);
      long var6 = Math.max(0, Math.max(var4, var5) - 1);
      long var8 = Math.min(var4, var5);
      long var10 = var8 * var8 + var6 * var6;
      return var10 < (long)var3 * var3;
   }

   @Annotation2(max = 0)
   @Inject(method = "clipRenderersByFrustum$v1_7", at = @At("RETURN"))
   private void lunar$onClip(CallbackInfo var1) {
      this.lunar$renderChunks.clear();
      WorldRenderer[] var2 = this.worldRenderers$v1_7;
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            WorldRenderer var4 = var2[var3];
            if (var4 != null && var4.isInFrustum$v1_7 && var4.isVisible$v1_7) {
               this.lunar$renderChunks.add((Bridge9_7)var4);
            }
         }
      }
   }
}
