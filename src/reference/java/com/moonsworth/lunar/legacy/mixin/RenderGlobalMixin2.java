package com.moonsworth.lunar.legacy.mixin;

import com.google.gson.JsonSyntaxException;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.CullingFrustumBridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge3_34;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.client.Highlight3Iterator6;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl6;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakingProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockOutlineRender.EventBlockOutlineRenderLegacy;
import com.moonsworth.lunar.client.event.mixin.highlight.EntitiesRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.SetupTerrainEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ChunkReloadEvent;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger;
import com.moonsworth.lunar.client.tps.TpsType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin2 implements Bridge14_3 {
   @Final
   @Shadow
   public Minecraft mc;
   @Shadow
   public WorldClient world;
   @Shadow
   public int countEntitiesRendered;
   @Shadow
   public Framebuffer entityOutlineFramebuffer;
   @Final
   @Shadow
   public RenderManager renderManager$v1_8;
   @Shadow
   public ShaderGroup entityOutlineShader;
   @Annotation2(max = 0)
   @Unique
   private Framebuffer lunar$entityOutlineFramebuffer;
   @Annotation2(max = 0)
   @Unique
   private ShaderGroup lunar$entityOutlineShader;
   @Annotation2(max = 1)
   @Unique
   private boolean lunar$entityOutlinesRendered;
   @Unique
   private int lunar$chunksToWait;

   @Shadow
   public abstract void renderSkyEnd();

   @Annotation2(0)
   @ModifyExpressionValue(method = "renderSky$v1_7", at = @At(value = "FIELD", target = "Lnet/minecraft/world/WorldProvider;dimensionId$v1_7:I"))
   private int lunar$skyHook$v1_7(int var1) {
      if (!this.mc.theWorld.provider.isSurfaceWorld()) {
         return var1;
      }

      TimeChanger var2 = ThreadModuleDump63.method4().method40().method40();
      if (var2.isEnabled()) {
         TimeChanger.Type var3 = var2.method17().get();
         if (var3 == TimeChanger.Type.NETHER) {
            return 0;
         }

         if (var3 == TimeChanger.Type.END) {
            return 1;
         }
      }

      return var1;
   }

   @Annotation2(0)
   @WrapOperation(method = "renderSky$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;isSurfaceWorld()Z"))
   private boolean lunar$skyHookNether$v1_7(WorldProvider var1, Operation<Boolean> var2) {
      boolean var3 = (Boolean)var2.call(new Object[]{var1});
      if (!var3) {
         return false;
      }

      TimeChanger var4 = ThreadModuleDump63.method4().method40().method40();
      return var4.isEnabled() ? var4.method17().get() != TimeChanger.Type.NETHER : true;
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderSky$v1_8(FI)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;isSurfaceWorld()Z", shift = Shift.AFTER),
      cancellable = true
   )
   private void lunar$skyHook$v1_8(float var1, int var2, CallbackInfo var3) {
      TimeChanger var4 = ThreadModuleDump63.method4().method40().method40();
      if (var4.isEnabled()) {
         TimeChanger.Type var5 = var4.method17().get();
         if (var5 == TimeChanger.Type.NETHER) {
            var3.cancel();
         } else if (var5 == TimeChanger.Type.END) {
            var3.cancel();
            this.renderSkyEnd();
         }
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "drawSelectionBox",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox(Lnet/minecraft/util/AxisAlignedBB;I)V")
   )
   private void lunar$drawSelectionBox(AxisAlignedBB var1, int var2, EntityPlayer var3, MovingObjectPosition var4, int var5, float var6) {
      if (!lunar$drawSelectionBoundingBox(var1)) {
         RenderGlobal.drawOutlinedBoundingBox(var1, var2);
      }
   }

   @Annotation2(1)
   @Inject(method = "drawSelectionBoundingBox$v1_8", at = @At("HEAD"), cancellable = true)
   private static void lunar$drawSelectionBoundingBox_v1_8(AxisAlignedBB var0, CallbackInfo var1) {
      if (lunar$drawSelectionBoundingBox(var0)) {
         var1.cancel();
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "drawSelectionBoundingBox$v1_12", at = @At("HEAD"), cancellable = true)
   private static void lunar$drawSelectionBoundingBox_v1_12(AxisAlignedBB var0, float var1, float var2, float var3, float var4, CallbackInfo var5) {
      if (lunar$drawSelectionBoundingBox(var0)) {
         var5.cancel();
      }
   }

   @Unique
   private static boolean lunar$drawSelectionBoundingBox(AxisAlignedBB var0) {
      EventBlockOutlineRenderLegacy var1 = (EventBlockOutlineRenderLegacy)ClientEventBus.method29().method12(EventBlockOutlineRenderLegacy.class, () -> new EventBlockOutlineRenderLegacy(AbstractRenderContext.method32(), (AxisAlignedBBBridge)var0));
      return var1 != null && var1.isCancelled();
   }

   @Unique
   private void lunar$renderBeam(ICamera var1, float var2, World var3) {
      ThreadModuleDump63.method4()
         .method84()
         .method3(BeamModule.class)
         .ifPresent(
            var4 -> {
               Highlight3Iterator6 var5 = (Highlight3Iterator6)var4;
               double var6 = this.mc.gameSettings.renderDistanceChunks * 16;
               Bridge3_34 var8;
               if (ThreadModuleDump63.MC_VERSION >= 5) {
                  var8 = (Bridge3_34)TileEntityRendererDispatcher.theMinecraft.getRenderer$v1_12(TileEntityBeacon.class);
               } else if (ThreadModuleDump63.MC_VERSION >= 1) {
                  var8 = (Bridge3_34)TileEntityRendererDispatcher.theMinecraft.getSpecialRendererByClass(TileEntityBeacon.class);
               } else {
                  var8 = (Bridge3_34)TileEntityRendererDispatcher.theMinecraft.getSpecialRendererByClass(TileEntityBeacon.class);
               }

               var5.method4()
                  .forEach(
                     (var6x, var7) -> {
                        Vector3d var8x = ((CullingFrustumBridge)var1).bridge$getPosition();
                        ApolloBlockLocation var9 = var7.getLocation();
                        if (var8x.x() <= var9.getX() + var6
                           && var8x.x() >= var9.getX() - var6
                           && var8x.z() <= var9.getZ() + var6
                           && var8x.z() >= var9.getZ() - var6) {
                           var8.bridge$renderBeacon(
                              (Itemcounter6)var3, var9.getX() - var8x.x(), var9.getY() - var8x.y(), var9.getZ() - var8x.z(), var2, 1.0, var7.getColor(), 1024.0
                           );
                        }
                     }
                  );
            }
         );
   }

   @Annotation2(min = 1)
   @Inject(method = "renderEntities$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;", ordinal = 0))
   public void apollo$renderBeacon$v1_8(Entity var1, ICamera var2, float var3, CallbackInfo var4) {
      this.lunar$renderBeam(var2, var3, var1.worldObj);
   }

   @Annotation2(max = 0)
   @Inject(
      method = "renderEntities$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderHelper;enableStandardItemLighting()V", shift = Shift.AFTER)
   )
   public void apollo$renderBeacon$v1_7(EntityLivingBase var1, ICamera var2, float var3, CallbackInfo var4) {
      this.lunar$renderBeam(var2, var3, var1.world);
   }

   @Unique
   private boolean lunar$areOutlinesSupported() {
      return OpenGlHelper.framebufferSupported && OpenGlHelper.shadersSupported;
   }

   @Annotation2(max = 0)
   @Unique
   private boolean lunar$shouldRenderEntityOutlines() {
      return this.lunar$entityOutlineFramebuffer != null && this.lunar$entityOutlineShader != null && this.mc.thePlayer$v1_7 != null;
   }

   @Annotation2(max = 0)
   @Nullable
   public Bridge3_24 bridge$entityTarget() {
      return (Bridge3_24)this.lunar$entityOutlineFramebuffer;
   }

   @Annotation2(max = 0)
   public void bridge$makeEntityOutlineShader() {
      if (this.lunar$areOutlinesSupported()) {
         if (ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
            ShaderLinkHelper.setNewStaticShaderLinkHelper();
         }

         ResourceLocation var1 = new ResourceLocation("shaders/post/lunar_entity_outline_1_7.json");

         try {
            this.lunar$entityOutlineShader = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), var1);
            this.lunar$entityOutlineShader.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            this.lunar$entityOutlineFramebuffer = this.lunar$entityOutlineShader.getFramebuffer("final");
         } catch (JsonSyntaxException | IOException var3) {
            System.out.printf("Failed to load shader: " + var1 + "%n", var3);
            this.lunar$entityOutlineShader = null;
            this.lunar$entityOutlineFramebuffer = null;
         }
      } else {
         this.lunar$entityOutlineShader = null;
         this.lunar$entityOutlineFramebuffer = null;
      }
   }

   @Annotation2(max = 0)
   public void bridge$renderEntityOutlineFramebuffer() {
      if (this.lunar$shouldRenderEntityOutlines()) {
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 0, 1);
         ((Bridge3_24)this.lunar$entityOutlineFramebuffer).bridge$frameBufferRender(this.mc.displayWidth, this.mc.displayHeight, false);
         GL11.glDisable(3042);
      }
   }

   @Annotation2(max = 0)
   public void bridge$bindEntityOutlineFbs(int var1, int var2) {
      if (this.lunar$areOutlinesSupported() && this.lunar$entityOutlineShader != null) {
         this.lunar$entityOutlineShader.createBindFramebuffers(var1, var2);
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderEntities$v1_7",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entities"), to = @At(value = "CONSTANT", args = "stringValue=blockentities")),
      at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0)
   )
   private int lunar$customEntityRendering(
      List<Entity> var1,
      EntityLivingBase var2,
      ICamera var3,
      float var4,
      @Local(ordinal = 0) double var5,
      @Local(ordinal = 1) double var7,
      @Local(ordinal = 2) double var9
   ) {
      if (!this.lunar$shouldRenderEntityOutlines()) {
         return var1.size();
      }

      ArrayList var11 = new ArrayList();

      for (Entity var13 : var1) {
         boolean var14 = var13.isInRangeToRender3d(var5, var7, var9)
            && (var13.ignoreFrustumCheck || var3.isBoundingBoxInFrustum(var13.boundingBox) || var13.riddenByEntity == this.mc.thePlayer$v1_7);
         if (!var14 && var13 instanceof EntityLiving var15 && var15.getLeashed() && var15.getLeashedToEntity() != null) {
            Entity var16 = var15.getLeashedToEntity();
            var14 = var3.isBoundingBoxInFrustum(var16.boundingBox);
         }

         if (var14
            && (var13 != this.mc.renderViewEntity$v1_7 || this.mc.gameSettings.thirdPersonView != 0 || this.mc.renderViewEntity$v1_7.isPlayerSleeping())
            && this.world.blockExists$v1_7(MathHelper.floor_double(var13.posX), 0, MathHelper.floor_double(var13.posZ))) {
            RenderManager.theMinecraft.renderEntitySimple(var13, var4);
            this.countEntitiesRendered++;
            if (((BridgeExtension)var13).bridge$isGlowing()) {
               var11.add(var13);
            }
         }
      }

      if (!var11.isEmpty() || this.lunar$entityOutlinesRendered) {
         if (this.lunar$isRenderEntityOutlines()) {
            this.lunar$entityOutlineFramebuffer.framebufferClear();
            this.lunar$entityOutlinesRendered = !var11.isEmpty();
            if (!var11.isEmpty()) {
               GL11.glDepthFunc(519);
               GL11.glDisable(2912);
               this.lunar$entityOutlineFramebuffer.bindFramebuffer(false);
               RenderHelper.disableStandardItemLighting();
               ((Bridge2_43)RenderManager.theMinecraft).bridge$setRenderOutlines(true);

               for (Entity var19 : var11) {
                  RenderManager.theMinecraft.renderEntitySimple(var19, var4);
               }

               ((Bridge2_43)RenderManager.theMinecraft).bridge$setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               GL11.glDepthMask(false);
               this.lunar$entityOutlineShader.loadShaderGroup(var4);
               GL11.glEnable(2896);
               GL11.glDepthMask(true);
               GL11.glEnable(2912);
               GL11.glEnable(3042);
               GL11.glEnable(2903);
               GL11.glDepthFunc(515);
               GL11.glEnable(2929);
               GL11.glEnable(3008);
            }

            this.mc.getFramebuffer().bindFramebuffer(false);
         } else {
            this.lunar$entityOutlinesRendered = !var11.isEmpty();
            if (!var11.isEmpty()) {
               GL11.glDisable(2912);
               GL11.glDisable(2929);
               this.mc.entityRenderer.disableLightmap(0.0);
               RenderHelper.disableStandardItemLighting();
               ((Bridge2_43)RenderManager.theMinecraft).bridge$setRenderOutlines(true);

               for (Entity var20 : var11) {
                  RenderManager.theMinecraft.renderEntityStatic(var20, var4, false);
               }

               ((Bridge2_43)RenderManager.theMinecraft).bridge$setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               this.mc.entityRenderer.enableLightmap(0.0);
               GL11.glEnable(2929);
               GL11.glEnable(2912);
            }
         }
      }

      return 0;
   }

   @Annotation2(1)
   @ModifyExpressionValue(
      method = "renderEntities",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;isRenderEntityOutlines()Z")
   )
   private boolean lunar$moveEntityOutlineRendering(boolean var1) {
      return false;
   }

   @Annotation2(1)
   @Redirect(
      method = "renderEntities$v1_8",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entities"), to = @At(value = "CONSTANT", args = "stringValue=blockentities")),
      at = @At(value = "INVOKE", target = "Ljava/util/Iterator;hasNext()Z", ordinal = 0)
   )
   private boolean lunar$renderGlowing(
      Iterator var1,
      Entity var2,
      ICamera var3,
      float var4,
      @Local(ordinal = 0) double var5,
      @Local(ordinal = 1) double var7,
      @Local(ordinal = 2) double var9,
      @Local List<Entity> var11
   ) {
      if (var1.hasNext()) {
         return true;
      }

      this.world.profiler.endStartSection("entityOutlines");
      ArrayList var12 = new ArrayList();
      if (this.mc.thePlayer.isSpectator() && this.mc.gameSettings.keyBindSpectatorOutlines.isKeyDown()) {
         for (Entity var19 : var11) {
            boolean var15 = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isPlayerSleeping();
            boolean var16 = var19.isInRangeToRender3d(var5, var7, var9)
               && (var19.ignoreFrustumCheck || var3.isBoundingBoxInFrustum(var19.getEntityBoundingBox()) || var19.riddenByEntity == this.mc.thePlayer)
               && var19 instanceof EntityPlayer;
            if ((var19 != this.mc.getRenderViewEntity() || this.mc.gameSettings.thirdPersonView != 0 || var15) && var16) {
               var12.add(var19);
            }
         }
      } else {
         for (Entity var14 : var11) {
            if (((BridgeExtension)var14).bridge$isGlowing()
               && (var14 != this.mc.getRenderViewEntity() || this.mc.gameSettings.thirdPersonView != 0)
               && var14.isInRangeToRender3d(var5, var7, var9)
               && (var14.ignoreFrustumCheck || var3.isBoundingBoxInFrustum(var14.getEntityBoundingBox()))) {
               var12.add(var14);
            }
         }
      }

      boolean var18 = !var12.isEmpty();
      if (var18 || this.lunar$entityOutlinesRendered) {
         if (this.isRenderEntityOutlines()) {
            this.entityOutlineFramebuffer.framebufferClear();
            this.lunar$entityOutlinesRendered = var18;
            if (var18) {
               GlStateManager.depthFunc(519);
               GlStateManager.disableFog();
               this.entityOutlineFramebuffer.bindFramebuffer(false);
               RenderHelper.disableStandardItemLighting();
               this.renderManager$v1_8.setRenderOutlines(true);

               for (Entity var22 : var12) {
                  this.renderManager$v1_8.renderEntitySimple(var22, var4);
               }

               this.renderManager$v1_8.setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               GlStateManager.depthMask(false);
               this.entityOutlineShader.loadShaderGroup(var4);
               GlStateManager.enableLighting();
               GlStateManager.depthMask(true);
               GlStateManager.enableFog();
               GlStateManager.enableBlend();
               GlStateManager.enableColorMaterial();
               GlStateManager.depthFunc(515);
               GlStateManager.enableDepth();
               GlStateManager.enableAlpha();
            }

            this.mc.getFramebuffer().bindFramebuffer(false);
         } else {
            Slayer3 var21 = null;
            if (Bridge.method5().isPresent() && ((Slayer2)Bridge.method5().get()).getConfig().hasShaders()) {
               var21 = ((Slayer2)Bridge.method5().get()).getShaders();
            }

            this.lunar$entityOutlinesRendered = var18;
            if (var18) {
               if (var21 != null) {
                  var21.beginEntitiesGlowing();
               }

               GlStateManager.disableFog();
               GlStateManager.depthFunc(519);
               GlStateManager.disableDepth();
               this.mc.entityRenderer.disableLightmap();
               RenderHelper.disableStandardItemLighting();
               this.renderManager$v1_8.setRenderOutlines(true);

               for (Entity var24 : var12) {
                  if (var21 != null) {
                     var21.nextEntity((BridgeExtension)var2);
                  }

                  this.renderManager$v1_8.renderEntityStatic(var24, var4, false);
               }

               this.renderManager$v1_8.setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               this.mc.entityRenderer.enableLightmap();
               GlStateManager.depthFunc(515);
               GlStateManager.enableDepth();
               GlStateManager.enableFog();
               if (var21 != null) {
                  var21.endEntitiesGlowing();
               }
            }
         }
      }

      return false;
   }

   @Unique
   public boolean lunar$isRenderEntityOutlines() {
      if (Bridge.method5().isPresent()) {
         Slayer4 var1 = ((Slayer2)Bridge.method5().get()).getConfig();
         if (var1.hasFastRender() || var1.hasShaders() || var1.hasAntiAliasing()) {
            return false;
         }
      }

      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.entityOutlineFramebuffer != null && this.entityOutlineShader != null && this.mc.thePlayer != null
         : this.lunar$entityOutlineFramebuffer != null && this.lunar$entityOutlineShader != null && this.mc.thePlayer$v1_7 != null;
   }

   @Annotation2(1)
   @Overwrite
   public boolean isRenderEntityOutlines() {
      return this.lunar$isRenderEntityOutlines();
   }

   @Annotation2(min = 1)
   @Inject(method = "updateChunks$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"))
   @Annotation_2(absent = "optifine")
   private void lunar$beginLazyChunkLoading$v1_8(CallbackInfo var1, @Share("lazyAmt") LocalIntRef var2) {
      var2.set(ThreadModuleDump63.method4().method41().method7().method22().get().getAmount());
   }

   @Annotation2(min = 1)
   @Inject(method = "updateChunks$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;"), cancellable = true)
   @Annotation_2(absent = "optifine")
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

   @Annotation2(max = 0)
   @Inject(method = "renderEntities$v1_7", at = @At(value = "CONSTANT", args = "stringValue=entities", shift = Shift.AFTER, ordinal = 0))
   private void lunar$onEntityRendering$v1_7(EntityLivingBase var1, ICamera var2, float var3, CallbackInfo var4) {
      ClientEventBus.method29()
         .method12(
            EntitiesRenderEvent.class,
            () -> new EntitiesRenderEvent(
               BridgeExtension3_5.method12(var3), RenderManager.renderPosX$v1_7, RenderManager.renderPosY$v1_7, RenderManager.renderPosZ$v1_7
            )
         );
   }

   @Annotation2(min = 1)
   @Inject(method = "renderEntities$v1_8", at = @At(value = "CONSTANT", args = "stringValue=entities", shift = Shift.AFTER, ordinal = 0))
   private void lunar$onEntityRendering$v1_8(
      Entity var1,
      ICamera var2,
      float var3,
      CallbackInfo var4,
      @Local(ordinal = 3) double var5,
      @Local(ordinal = 4) double var7,
      @Local(ordinal = 5) double var9
   ) {
      ClientEventBus.method29().method12(EntitiesRenderEvent.class, () -> new EntitiesRenderEvent(BridgeExtension3_5.method12(var3), var5, var7, var9));
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderEntities$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;preRenderDamagedBlocks$v1_8()V", ordinal = 0)
   )
   private void lunar$drawSubmits$v1_12(Entity var1, ICamera var2, float var3, CallbackInfo var4) {
      ThreadModuleDump63.method4().method103().method3(TpsType.RENDERING_OPAQUE);
      ThreadModuleDump63.method4().method103().method3(TpsType.RENDERING_TRANSLUCENT);
      ThreadModuleDump63.method4().method103().method3(TpsType.POST_RENDER);
   }

   @Annotation2(0)
   @Inject(method = "destroyBlockPartially$v1_7", at = @At("HEAD"))
   private void lunar$destroyBlockInWorldPartially(int var1, int var2, int var3, int var4, int var5, CallbackInfo var6) {
      ClientEventBus.method29().method12(EventBlockBreakingProgress.class, () -> new EventBlockBreakingProgress(var1, (Horsestats20Extension2)(new Vector3i(var2, var3, var4)), var5));
   }

   @Annotation2(min = 1)
   @Inject(method = "sendBlockBreakProgress$v1_8", at = @At("HEAD"))
   private void lunar$sendBlockBreakProgress(int var1, BlockPos var2, int var3, CallbackInfo var4) {
      ClientEventBus.method29().method12(EventBlockBreakingProgress.class, () -> new EventBlockBreakingProgress(var1, (Horsestats20Extension2)var2, var3));
   }

   @Inject(method = "loadRenderers", at = @At("RETURN"))
   private void lunar$onChunkReload(CallbackInfo var1) {
      if (this.world != null) {
         ClientEventBus.method29().method12(ChunkReloadEvent.class, ChunkReloadEvent::new);
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "setupTerrain$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", ordinal = 0))
   private void lunar$onApplyFrustum(CallbackInfo var1) {
      ClientEventBus.method29().method12(SetupTerrainEvent.class, SetupTerrainEvent::new);
   }

   @Annotation2(max = 0)
   @Inject(method = "renderSortedRenderers$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindRenderBlocks$v1_7(int var1, int var2, int var3, double var4, CallbackInfoReturnable<Integer> var6) {
      Rewind var7 = ThreadModuleDump63.method4().method40().method85();
      if (var7.method19()) {
         RewindHandlers3Impl6 var8 = var7.method35().method52();
         if (var8.method14().get() && !var8.method16().get()) {
            var6.setReturnValue(0);
         }

         if (var8.method15().get() && var8.method28().get()) {
            var6.setReturnValue(0);
         }
      }
   }
}
