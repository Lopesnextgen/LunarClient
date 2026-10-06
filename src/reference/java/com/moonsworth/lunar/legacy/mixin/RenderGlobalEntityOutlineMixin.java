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
import com.moonsworth.lunar.bridge.FrustumBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.TileEntityBeaconRendererBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.bridge.optifine.OptifineConfigBridge;
import com.moonsworth.lunar.client.network.apollo.BeamApolloHandler;
import com.moonsworth.lunar.client.replay.render.WorldRenderHandler;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderBlockOutline.EventRenderBlockOutlineLegacy;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntities;
import com.moonsworth.lunar.client.event.mixin.highlight.EventSetupTerrain;
import com.moonsworth.lunar.client.event.mixin.highlight.EventChunkReload;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger;
import com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger.Type;
import com.moonsworth.lunar.client.render.pipeline.RenderStage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
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
public abstract class RenderGlobalEntityOutlineMixin implements Bridge14_3 {
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
   @VersionGate(max = 0)
   @Unique
   private Framebuffer lunar$entityOutlineFramebuffer;
   @VersionGate(max = 0)
   @Unique
   private ShaderGroup lunar$entityOutlineShader;
   @VersionGate(max = 1)
   @Unique
   private boolean lunar$entityOutlinesRendered;
   @Unique
   private int lunar$chunksToWait;

   public RenderGlobalEntityOutlineMixin() {
   }

   @Shadow
   public abstract void renderSkyEnd();

   @VersionGate(0)
   @ModifyExpressionValue(method = "renderSky$v1_7", at = @At(value = "FIELD", target = "Lnet/minecraft/world/WorldProvider;dimensionId$v1_7:I"))
   private int lunar$skyHook$v1_7(int number1) {
      if (!this.mc.theWorld.provider.isSurfaceWorld()) {
         return number1;
      }

      TimeChanger timechanger2 = Ref.method4().method40().method40();
      if (timechanger2.isEnabled()) {
         Type type3 = (Type)timechanger2.method17().get();
         if (type3 == Type.NETHER) {
            return 0;
         }

         if (type3 == Type.END) {
            return 1;
         }
      }

      return number1;
   }

   @VersionGate(0)
   @WrapOperation(method = "renderSky$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;isSurfaceWorld()Z"))
   private boolean lunar$skyHookNether$v1_7(WorldProvider worldprovider1, Operation<Boolean> operation2) {
      boolean flag3 = (Boolean)operation2.call(new Object[]{worldprovider1});
      if (!flag3) {
         return false;
      }

      TimeChanger timechanger4 = Ref.method4().method40().method40();
      return timechanger4.isEnabled() ? timechanger4.method17().get() != Type.NETHER : true;
   }

   @VersionGate(min = 1)
   @Inject(
      method = "renderSky$v1_8(FI)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;isSurfaceWorld()Z", shift = Shift.AFTER),
      cancellable = true
   )
   private void lunar$skyHook$v1_8(float value1, int number2, CallbackInfo callback3) {
      TimeChanger timechanger4 = Ref.method4().method40().method40();
      if (timechanger4.isEnabled()) {
         Type type5 = (Type)timechanger4.method17().get();
         if (type5 == Type.NETHER) {
            callback3.cancel();
         } else if (type5 == Type.END) {
            callback3.cancel();
            this.renderSkyEnd();
         }
      }
   }

   @VersionGate(max = 0)
   @Redirect(
      method = "drawSelectionBox",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox(Lnet/minecraft/util/AxisAlignedBB;I)V")
   )
   private void lunar$drawSelectionBox(AxisAlignedBB box1, int number2, EntityPlayer player3, MovingObjectPosition hit4, int number5, float value6) {
      if (!lunar$drawSelectionBoundingBox(box1)) {
         RenderGlobal.drawOutlinedBoundingBox(box1, number2);
      }
   }

   @VersionGate(1)
   @Inject(method = "drawSelectionBoundingBox$v1_8", at = @At("HEAD"), cancellable = true)
   private static void lunar$drawSelectionBoundingBox_v1_8(AxisAlignedBB box0, CallbackInfo callback1) {
      if (lunar$drawSelectionBoundingBox(box0)) {
         callback1.cancel();
      }
   }

   @VersionGate(min = 5)
   @Inject(method = "drawSelectionBoundingBox$v1_12", at = @At("HEAD"), cancellable = true)
   private static void lunar$drawSelectionBoundingBox_v1_12(AxisAlignedBB box0, float value1, float value2, float value3, float value4, CallbackInfo callback5) {
      if (lunar$drawSelectionBoundingBox(box0)) {
         callback5.cancel();
      }
   }

   @Unique
   private static boolean lunar$drawSelectionBoundingBox(AxisAlignedBB box0) {
      EventRenderBlockOutlineLegacy data41 = (EventRenderBlockOutlineLegacy)LunarEventBus.method29().method12(EventRenderBlockOutlineLegacy.class, () -> new EventRenderBlockOutlineLegacy(AbstractRenderContext.method32(), (AxisAlignedBBBridge)box0));
      return data41 != null && data41.isCancelled();
   }

   @Unique
   private void lunar$renderBeam(ICamera icamera1, float value2, World world3) {
      Ref.method4()
         .method84()
         .method3(BeamModule.class)
         .ifPresent(
            arg4 -> {
               BeamApolloHandler highlight3iterator65 = (BeamApolloHandler)arg4;
               double value6 = this.mc.gameSettings.renderDistanceChunks * 16;
               TileEntityBeaconRendererBridge bridge3_348;
               if (Ref.MC_VERSION >= 5) {
                  bridge3_348 = (TileEntityBeaconRendererBridge)TileEntityRendererDispatcher.theMinecraft.getRenderer$v1_12(TileEntityBeacon.class);
               } else if (Ref.MC_VERSION >= 1) {
                  bridge3_348 = (TileEntityBeaconRendererBridge)TileEntityRendererDispatcher.theMinecraft.getSpecialRendererByClass(TileEntityBeacon.class);
               } else {
                  bridge3_348 = (TileEntityBeaconRendererBridge)TileEntityRendererDispatcher.theMinecraft.getSpecialRendererByClass(TileEntityBeacon.class);
               }

               highlight3iterator65.method4()
                  .forEach(
                     (arg6x, arg7) -> {
                        Vector3d vector3d8x = ((FrustumBridge)icamera1).bridge$getPosition();
                        ApolloBlockLocation apolloblocklocation9 = arg7.getLocation();
                        if (vector3d8x.x() <= apolloblocklocation9.getX() + value6
                           && vector3d8x.x() >= apolloblocklocation9.getX() - value6
                           && vector3d8x.z() <= apolloblocklocation9.getZ() + value6
                           && vector3d8x.z() >= apolloblocklocation9.getZ() - value6) {
                           bridge3_348.bridge$renderBeacon(
                              (Itemcounter6)world3, apolloblocklocation9.getX() - vector3d8x.x(), apolloblocklocation9.getY() - vector3d8x.y(), apolloblocklocation9.getZ() - vector3d8x.z(), value2, 1.0, arg7.getColor(), 1024.0
                           );
                        }
                     }
                  );
            }
         );
   }

   @VersionGate(min = 1)
   @Inject(method = "renderEntities$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;", ordinal = 0))
   public void apollo$renderBeacon$v1_8(Entity entity1, ICamera icamera2, float value3, CallbackInfo callback4) {
      this.lunar$renderBeam(icamera2, value3, entity1.worldObj);
   }

   @VersionGate(max = 0)
   @Inject(
      method = "renderEntities$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderHelper;enableStandardItemLighting()V", shift = Shift.AFTER)
   )
   public void apollo$renderBeacon$v1_7(EntityLivingBase entity1, ICamera icamera2, float value3, CallbackInfo callback4) {
      this.lunar$renderBeam(icamera2, value3, entity1.world);
   }

   @Unique
   private boolean lunar$areOutlinesSupported() {
      return OpenGlHelper.framebufferSupported && OpenGlHelper.shadersSupported;
   }

   @VersionGate(max = 0)
   @Unique
   private boolean lunar$shouldRenderEntityOutlines() {
      return this.lunar$entityOutlineFramebuffer != null && this.lunar$entityOutlineShader != null && this.mc.thePlayer$v1_7 != null;
   }

   @VersionGate(max = 0)
   @Nullable
   public Bridge3_24 bridge$entityTarget() {
      return (Bridge3_24)this.lunar$entityOutlineFramebuffer;
   }

   @VersionGate(max = 0)
   public void bridge$makeEntityOutlineShader() {
      if (this.lunar$areOutlinesSupported()) {
         if (ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
            ShaderLinkHelper.setNewStaticShaderLinkHelper();
         }

         ResourceLocation location1 = new ResourceLocation("shaders/post/lunar_entity_outline_1_7.json");

         try {
            this.lunar$entityOutlineShader = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), location1);
            this.lunar$entityOutlineShader.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            this.lunar$entityOutlineFramebuffer = this.lunar$entityOutlineShader.getFramebuffer("final");
         } catch (JsonSyntaxException | IOException jsonsyntaxexception3) {
            System.out.printf("Failed to load shader: " + location1 + "%n", jsonsyntaxexception3);
            this.lunar$entityOutlineShader = null;
            this.lunar$entityOutlineFramebuffer = null;
         }
      } else {
         this.lunar$entityOutlineShader = null;
         this.lunar$entityOutlineFramebuffer = null;
      }
   }

   @VersionGate(max = 0)
   public void bridge$renderEntityOutlineFramebuffer() {
      if (this.lunar$shouldRenderEntityOutlines()) {
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 0, 1);
         ((Bridge3_24)this.lunar$entityOutlineFramebuffer).bridge$frameBufferRender(this.mc.displayWidth, this.mc.displayHeight, false);
         GL11.glDisable(3042);
      }
   }

   @VersionGate(max = 0)
   public void bridge$bindEntityOutlineFbs(int number1, int number2) {
      if (this.lunar$areOutlinesSupported() && this.lunar$entityOutlineShader != null) {
         this.lunar$entityOutlineShader.createBindFramebuffers(number1, number2);
      }
   }

   @VersionGate(max = 0)
   @Redirect(
      method = "renderEntities$v1_7",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entities"), to = @At(value = "CONSTANT", args = "stringValue=blockentities")),
      at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0)
   )
   private int lunar$customEntityRendering(
      List<Entity> list1,
      EntityLivingBase entity2,
      ICamera icamera3,
      float value4,
      @Local(ordinal = 0) double value5,
      @Local(ordinal = 1) double value7,
      @Local(ordinal = 2) double value9
   ) {
      if (!this.lunar$shouldRenderEntityOutlines()) {
         return list1.size();
      }

      ArrayList list11 = new ArrayList();

      for (Entity entity13 : list1) {
         boolean flag14 = entity13.isInRangeToRender3d(value5, value7, value9)
            && (entity13.ignoreFrustumCheck || icamera3.isBoundingBoxInFrustum(entity13.boundingBox) || entity13.riddenByEntity == this.mc.thePlayer$v1_7);
         if (!flag14 && entity13 instanceof EntityLiving entity15 && entity15.getLeashed() && entity15.getLeashedToEntity() != null) {
            Entity entity16 = entity15.getLeashedToEntity();
            flag14 = icamera3.isBoundingBoxInFrustum(entity16.boundingBox);
         }

         if (flag14
            && (entity13 != this.mc.renderViewEntity$v1_7 || this.mc.gameSettings.thirdPersonView != 0 || this.mc.renderViewEntity$v1_7.isPlayerSleeping())
            && this.world.blockExists$v1_7(MathHelper.floor_double(entity13.posX), 0, MathHelper.floor_double(entity13.posZ))) {
            RenderManager.theMinecraft.renderEntitySimple(entity13, value4);
            this.countEntitiesRendered++;
            if (((BridgeExtension)entity13).bridge$isGlowing()) {
               list11.add(entity13);
            }
         }
      }

      if (!list11.isEmpty() || this.lunar$entityOutlinesRendered) {
         if (this.lunar$isRenderEntityOutlines()) {
            this.lunar$entityOutlineFramebuffer.framebufferClear();
            this.lunar$entityOutlinesRendered = !list11.isEmpty();
            if (!list11.isEmpty()) {
               GL11.glDepthFunc(519);
               GL11.glDisable(2912);
               this.lunar$entityOutlineFramebuffer.bindFramebuffer(false);
               RenderHelper.disableStandardItemLighting();
               ((EntityRenderDispatcherBridge)RenderManager.theMinecraft).bridge$setRenderOutlines(true);

               for (Entity entity19 : list11) {
                  RenderManager.theMinecraft.renderEntitySimple(entity19, value4);
               }

               ((EntityRenderDispatcherBridge)RenderManager.theMinecraft).bridge$setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               GL11.glDepthMask(false);
               this.lunar$entityOutlineShader.loadShaderGroup(value4);
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
            this.lunar$entityOutlinesRendered = !list11.isEmpty();
            if (!list11.isEmpty()) {
               GL11.glDisable(2912);
               GL11.glDisable(2929);
               this.mc.entityRenderer.disableLightmap(0.0);
               RenderHelper.disableStandardItemLighting();
               ((EntityRenderDispatcherBridge)RenderManager.theMinecraft).bridge$setRenderOutlines(true);

               for (Entity entity20 : list11) {
                  RenderManager.theMinecraft.renderEntityStatic(entity20, value4, false);
               }

               ((EntityRenderDispatcherBridge)RenderManager.theMinecraft).bridge$setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               this.mc.entityRenderer.enableLightmap(0.0);
               GL11.glEnable(2929);
               GL11.glEnable(2912);
            }
         }
      }

      return 0;
   }

   @VersionGate(1)
   @ModifyExpressionValue(
      method = "renderEntities",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;isRenderEntityOutlines()Z")
   )
   private boolean lunar$moveEntityOutlineRendering(boolean flag1) {
      return false;
   }

   @VersionGate(1)
   @Redirect(
      method = "renderEntities$v1_8",
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entities"), to = @At(value = "CONSTANT", args = "stringValue=blockentities")),
      at = @At(value = "INVOKE", target = "Ljava/util/Iterator;hasNext()Z", ordinal = 0)
   )
   private boolean lunar$renderGlowing(
      Iterator iterator1,
      Entity entity2,
      ICamera icamera3,
      float value4,
      @Local(ordinal = 0) double value5,
      @Local(ordinal = 1) double value7,
      @Local(ordinal = 2) double value9,
      @Local List<Entity> list11
   ) {
      if (iterator1.hasNext()) {
         return true;
      }

      this.world.profiler.endStartSection("entityOutlines");
      ArrayList list12 = new ArrayList();
      if (this.mc.thePlayer.isSpectator() && this.mc.gameSettings.keyBindSpectatorOutlines.isKeyDown()) {
         for (Entity entity19 : list11) {
            boolean flag15 = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isPlayerSleeping();
            boolean flag16 = entity19.isInRangeToRender3d(value5, value7, value9)
               && (entity19.ignoreFrustumCheck || icamera3.isBoundingBoxInFrustum(entity19.getEntityBoundingBox()) || entity19.riddenByEntity == this.mc.thePlayer)
               && entity19 instanceof EntityPlayer;
            if ((entity19 != this.mc.getRenderViewEntity() || this.mc.gameSettings.thirdPersonView != 0 || flag15) && flag16) {
               list12.add(entity19);
            }
         }
      } else {
         for (Entity entity14 : list11) {
            if (((BridgeExtension)entity14).bridge$isGlowing()
               && (entity14 != this.mc.getRenderViewEntity() || this.mc.gameSettings.thirdPersonView != 0)
               && entity14.isInRangeToRender3d(value5, value7, value9)
               && (entity14.ignoreFrustumCheck || icamera3.isBoundingBoxInFrustum(entity14.getEntityBoundingBox()))) {
               list12.add(entity14);
            }
         }
      }

      boolean flag18 = !list12.isEmpty();
      if (flag18 || this.lunar$entityOutlinesRendered) {
         if (this.isRenderEntityOutlines()) {
            this.entityOutlineFramebuffer.framebufferClear();
            this.lunar$entityOutlinesRendered = flag18;
            if (flag18) {
               GlStateManager.depthFunc(519);
               GlStateManager.disableFog();
               this.entityOutlineFramebuffer.bindFramebuffer(false);
               RenderHelper.disableStandardItemLighting();
               this.renderManager$v1_8.setRenderOutlines(true);

               for (Entity entity22 : list12) {
                  this.renderManager$v1_8.renderEntitySimple(entity22, value4);
               }

               this.renderManager$v1_8.setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               GlStateManager.depthMask(false);
               this.entityOutlineShader.loadShaderGroup(value4);
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
            ShadersBridge slayer321 = null;
            if (Bridge.method5().isPresent() && ((OptifineBridge)Bridge.method5().get()).getConfig().hasShaders()) {
               slayer321 = ((OptifineBridge)Bridge.method5().get()).getShaders();
            }

            this.lunar$entityOutlinesRendered = flag18;
            if (flag18) {
               if (slayer321 != null) {
                  slayer321.beginEntitiesGlowing();
               }

               GlStateManager.disableFog();
               GlStateManager.depthFunc(519);
               GlStateManager.disableDepth();
               this.mc.entityRenderer.disableLightmap();
               RenderHelper.disableStandardItemLighting();
               this.renderManager$v1_8.setRenderOutlines(true);

               for (Entity entity24 : list12) {
                  if (slayer321 != null) {
                     slayer321.nextEntity((BridgeExtension)entity2);
                  }

                  this.renderManager$v1_8.renderEntityStatic(entity24, value4, false);
               }

               this.renderManager$v1_8.setRenderOutlines(false);
               RenderHelper.enableStandardItemLighting();
               this.mc.entityRenderer.enableLightmap();
               GlStateManager.depthFunc(515);
               GlStateManager.enableDepth();
               GlStateManager.enableFog();
               if (slayer321 != null) {
                  slayer321.endEntitiesGlowing();
               }
            }
         }
      }

      return false;
   }

   @Unique
   public boolean lunar$isRenderEntityOutlines() {
      if (Bridge.method5().isPresent()) {
         OptifineConfigBridge slayer41 = ((OptifineBridge)Bridge.method5().get()).getConfig();
         if (slayer41.hasFastRender() || slayer41.hasShaders() || slayer41.hasAntiAliasing()) {
            return false;
         }
      }

      return Ref.MC_VERSION >= 1
         ? this.entityOutlineFramebuffer != null && this.entityOutlineShader != null && this.mc.thePlayer != null
         : this.lunar$entityOutlineFramebuffer != null && this.lunar$entityOutlineShader != null && this.mc.thePlayer$v1_7 != null;
   }

   @VersionGate(1)
   @Overwrite
   public boolean isRenderEntityOutlines() {
      return this.lunar$isRenderEntityOutlines();
   }

   @VersionGate(min = 1)
   @Inject(method = "updateChunks$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"))
   @MixinCondition(absent = "optifine")
   private void lunar$beginLazyChunkLoading$v1_8(CallbackInfo callback1, @Share("lazyAmt") LocalIntRef localintref2) {
      localintref2.set(((com.moonsworth.lunar.client.config.PerformanceSettings.Type)Ref.method4().method41().method7().method22().get()).getAmount());
   }

   @VersionGate(min = 1)
   @Inject(method = "updateChunks$v1_8", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;"), cancellable = true)
   @MixinCondition(absent = "optifine")
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

   @VersionGate(max = 0)
   @Inject(method = "renderEntities$v1_7", at = @At(value = "CONSTANT", args = "stringValue=entities", shift = Shift.AFTER, ordinal = 0))
   private void lunar$onEntityRendering$v1_7(EntityLivingBase entity1, ICamera icamera2, float value3, CallbackInfo callback4) {
      LunarEventBus.method29()
         .method12(
            EventRenderEntities.class,
            () -> new EventRenderEntities(
               BridgeExtension3_5.method12(value3), RenderManager.renderPosX$v1_7, RenderManager.renderPosY$v1_7, RenderManager.renderPosZ$v1_7
            )
         );
   }

   @VersionGate(min = 1)
   @Inject(method = "renderEntities$v1_8", at = @At(value = "CONSTANT", args = "stringValue=entities", shift = Shift.AFTER, ordinal = 0))
   private void lunar$onEntityRendering$v1_8(
      Entity entity1,
      ICamera icamera2,
      float value3,
      CallbackInfo callback4,
      @Local(ordinal = 3) double value5,
      @Local(ordinal = 4) double value7,
      @Local(ordinal = 5) double value9
   ) {
      LunarEventBus.method29().method12(EventRenderEntities.class, () -> new EventRenderEntities(BridgeExtension3_5.method12(value3), value5, value7, value9));
   }

   @VersionGate(min = 5)
   @Inject(
      method = "renderEntities$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;preRenderDamagedBlocks$v1_8()V", ordinal = 0)
   )
   private void lunar$drawSubmits$v1_12(Entity entity1, ICamera icamera2, float value3, CallbackInfo callback4) {
      Ref.method4().method103().method3(RenderStage.RENDERING_OPAQUE);
      Ref.method4().method103().method3(RenderStage.RENDERING_TRANSLUCENT);
      Ref.method4().method103().method3(RenderStage.POST_RENDER);
   }

   @VersionGate(0)
   @Inject(method = "destroyBlockPartially$v1_7", at = @At("HEAD"))
   private void lunar$destroyBlockInWorldPartially(int number1, int number2, int number3, int number4, int number5, CallbackInfo callback6) {
      LunarEventBus.method29().method12(EventBlockBreakProgress.class, () -> new EventBlockBreakProgress(number1, (Horsestats20Extension2)(new Vector3i(number2, number3, number4)), number5));
   }

   @VersionGate(min = 1)
   @Inject(method = "sendBlockBreakProgress$v1_8", at = @At("HEAD"))
   private void lunar$sendBlockBreakProgress(int number1, BlockPos pos2, int number3, CallbackInfo callback4) {
      LunarEventBus.method29().method12(EventBlockBreakProgress.class, () -> new EventBlockBreakProgress(number1, (Horsestats20Extension2)pos2, number3));
   }

   @Inject(method = "loadRenderers", at = @At("RETURN"))
   private void lunar$onChunkReload(CallbackInfo callback1) {
      if (this.world != null) {
         LunarEventBus.method29().method12(EventChunkReload.class, EventChunkReload::new);
      }
   }

   @VersionGate(min = 1)
   @Inject(method = "setupTerrain$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", ordinal = 0))
   private void lunar$onApplyFrustum(CallbackInfo callback1) {
      LunarEventBus.method29().method12(EventSetupTerrain.class, EventSetupTerrain::new);
   }

   @VersionGate(max = 0)
   @Inject(method = "renderSortedRenderers$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindRenderBlocks$v1_7(int number1, int number2, int number3, double value4, CallbackInfoReturnable<Integer> callbackinforeturnable6) {
      RewindMod rewind7 = Ref.method4().method40().method85();
      if (rewind7.method19()) {
         WorldRenderHandler rewindhandlers3impl68 = rewind7.method35().method52();
         if ((Boolean)rewindhandlers3impl68.method14().get() && !(Boolean)rewindhandlers3impl68.method16().get()) {
            callbackinforeturnable6.setReturnValue(0);
         }

         if ((Boolean)rewindhandlers3impl68.method15().get() && (Boolean)rewindhandlers3impl68.method28().get()) {
            callbackinforeturnable6.setReturnValue(0);
         }
      }
   }
}
