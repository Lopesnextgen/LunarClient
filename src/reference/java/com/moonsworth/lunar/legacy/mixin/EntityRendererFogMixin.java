package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_52;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Highlight3Iterator31;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.UnfocusedFpsLimiter;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.Module2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl6;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Updater;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent;
import com.moonsworth.lunar.client.event.render.EventFogSetup;
import com.moonsworth.lunar.client.event.combat.EventTotemActivation;
import com.moonsworth.lunar.client.event.render.EventCameraEyeHeight;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldRender;
import com.moonsworth.lunar.client.event.mixin.highlight.FovModifierEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.FovRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.PostProcessEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.CameraOffsetEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.movement.freelook.Freelook;
import com.moonsworth.lunar.client.mod.movement.zoom.Zoom;
import com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger;
import com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump64;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import com.moonsworth.lunar.legacy.wrapper.LegacyRenderTypeFactory;
import java.util.Objects;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererFogMixin {
   private boolean renderingWorldDirections;
   @Final
   @Shadow
   public Minecraft mc;
   @Shadow
   public float smoothCamFilterX;
   @Shadow
   public float smoothCamFilterY;
   @Final
   @Shadow
   public MouseFilter mouseFilterXAxis;
   @Final
   @Shadow
   public MouseFilter mouseFilterYAxis;
   @Shadow
   public float fovModifierHandPrev;
   @Shadow
   public float fovModifierHand;
   @Unique
   private float timesUpdated;
   @Shadow
   public Entity pointedEntity;
   @Unique
   private Integer lunar$inventoryGuiScale;
   @Unique
   private float lunar$inventoryScaleFactor = 1.0F;
   @Unique
   private Bridge3_24 lunar$fastRenderCapture;
   @Shadow
   public float farPlaneDistance;
   @Shadow
   public boolean cloudFog;
   @Shadow
   public float fogColorRed;
   @Shadow
   public float fogColorGreen;
   @Shadow
   public float fogColorBlue;
   @Shadow
   public ShaderGroup shaderGroup;

   @Shadow
   public abstract void orientCamera(float var1);

   @Shadow
   public abstract void setupOverlayRendering();

   @Inject(method = "updateFogColor", at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/Vec3d;x:D", shift = Shift.BEFORE, ordinal = 0))
   private void lunar$skyColorHook(float var1, CallbackInfo var2, @Local Vec3 var3) {
      TimeChanger var4 = ThreadModuleDump63.method4().method40().method40();
      if (var4.isEnabled() && var4.method17().get() == TimeChanger.Type.NETHER && this.lunar$isOverworld(this.mc.theWorld)) {
         ((Vec3Mixin)var3).bridge$setX(0.0);
         ((Vec3Mixin)var3).bridge$setY(0.0);
         ((Vec3Mixin)var3).bridge$setZ(0.0);
      }
   }

   @Inject(method = "updateFogColor", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;renderDistanceChunks:I", ordinal = 1))
   private void lunar$fogHook(float var1, CallbackInfo var2) {
      TimeChanger var3 = ThreadModuleDump63.method4().method40().method40();
      TimeChanger.Type var4 = var3.method17().get();
      if (var3.isEnabled() && this.lunar$isOverworld(this.mc.theWorld) && var4 != TimeChanger.Type.DEFAULT) {
         Vec3 var5;
         if (var4 == TimeChanger.Type.NETHER) {
            var5 = this.lunar$getNetherFog();
         } else {
            var5 = this.lunar$getEndFog(this.mc.theWorld.getCelestialAngle(var1));
         }

         this.fogColorRed = (float)var5.xCoord;
         this.fogColorGreen = (float)var5.yCoord;
         this.fogColorBlue = (float)var5.zCoord;
      }
   }

   @Unique
   private Vec3 lunar$getEndFog(float var1) {
      float var2 = MathHelper.cos(var1 * (float) (Math.PI * 2)) * 2.0F + 0.5F;
      var2 = Math.max(0.0F, Math.min(1.0F, var2));
      float var3 = 0.627451F;
      float var4 = 0.5019608F;
      float var5 = 0.627451F;
      var3 *= var2 * 0.0F + 0.15F;
      var4 *= var2 * 0.0F + 0.15F;
      var5 *= var2 * 0.0F + 0.15F;
      return new Vec3(var3, var4, var5);
   }

   @Unique
   private Vec3 lunar$getNetherFog() {
      return new Vec3(0.2F, 0.03F, 0.03F);
   }

   @Unique
   private boolean lunar$isOverworld(World var1) {
      return ThreadModuleDump63.MC_VERSION == 5 ? var1.provider.getDimensionType$v1_12() == DimensionType.OVERWORLD : var1.provider.dimensionId == 0;
   }

   @ModifyExpressionValue(
      method = "updateFogColor",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;renderDistanceChunks:I", ordinal = 1)
   )
   private int lunar$skyFogHook(int var1) {
      TimeChanger var2 = ThreadModuleDump63.method4().method40().method40();
      return var1 < 4 || var2.isEnabled() && var2.method17().get() != TimeChanger.Type.DEFAULT ? 0 : var1;
   }

   @Annotation2(max = 0)
   @ModifyExpressionValue(
      method = "updateFovModifierHand",
      at = @At(target = "Lnet/minecraft/client/entity/EntityPlayerSP;getFOVMultiplier()F", value = "INVOKE")
   )
   private float lunar$updateFovModifierHand(float var1) {
      FovRenderEvent var2 = ClientEventBus.method29().method12(FovRenderEvent.class, () -> new FovRenderEvent(var1));
      if (var2 == null) {
         return var1;
      } else {
         return var2.isCancelled() ? 1.0F : var2.method1();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "updateFogColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getRainStrength(F)F", ordinal = 0))
   private void lunar$renderDistanceFogColorEvent$v1_8(float var1, CallbackInfo var2) {
      this.lunar$renderDistanceFogColorEvent(var1);
   }

   @Annotation2(max = 0)
   @Inject(
      method = "updateFogColor",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;fogColorBlue:F", ordinal = 2, opcode = 181, shift = Shift.AFTER)
   )
   private void lunar$renderDistanceFogColorEvent$v1_7(float var1, CallbackInfo var2) {
      this.lunar$renderDistanceFogColorEvent(var1);
   }

   @Unique
   private void lunar$renderDistanceFogColorEvent(float var1) {
      EventFogSetup.EventFogTint var2 = ClientEventBus.method29()
         .method12(
            EventFogSetup.EventFogTint.class,
            () -> new EventFogSetup.EventFogTint(EventFogSetup.FogSource.RENDER_DISTANCE, var1, this.fogColorRed, this.fogColorGreen, this.fogColorBlue)
         );
      if (var2 != null) {
         this.fogColorRed = var2.method3();
         this.fogColorGreen = var2.method4();
         this.fogColorBlue = var2.method5();
      }
   }

   @Annotation2(max = 0)
   @ModifyArg(method = "updateFogColor", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glClearColor(FFFF)V"), index = 3)
   private float lunar$opaqueWorldClearAlpha$v1_7(float var1) {
      return 1.0F;
   }

   @Annotation2(min = 0)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z", ordinal = 0),
         to = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;cloudFog:Z", ordinal = 0)
      ),
      at = {
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 0),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 2)
      },
      require = 0
   )
   private boolean lunar$blindnessFogStartEvent(int var1, float var2) {
      return this.lunar$blindnessFogStartEvent(var2);
   }

   @Annotation2(1)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(to = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;cloudFog:Z", ordinal = 0)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogStart(F)V")
   )
   private boolean lunar$blindnessFogStartEvent_v1_8(float var1) {
      return this.lunar$blindnessFogStartEvent(var1);
   }

   @Annotation2(min = 5)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(
         from = @At(value = "FIELD", target = "Lnet/minecraft/init/MobEffects;BLINDNESS:Lnet/minecraft/potion/Potion;", ordinal = 0),
         to = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;cloudFog:Z", ordinal = 0)
      ),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogStart(F)V")
   )
   private boolean lunar$blindnessFogStartEvent_v1_12(float var1) {
      return this.lunar$blindnessFogStartEvent(var1);
   }

   private boolean lunar$blindnessFogStartEvent(float var1) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.BLINDNESS, EventFogSetup.Type.START, var1);
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z", ordinal = 0),
         to = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;cloudFog:Z", ordinal = 0)
      ),
      at = {
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 1),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 3)
      }
   )
   private boolean lunar$blindnessFogEndEvent(int var1, float var2) {
      return this.lunar$blindnessFogEndEvent(var2);
   }

   @Annotation2(1)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(to = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;cloudFog:Z", ordinal = 0)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogEnd(F)V")
   )
   private boolean lunar$blindnessFogEndEvent_v1_8(float var1) {
      return this.lunar$blindnessFogEndEvent(var1);
   }

   @Annotation2(min = 5)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(
         from = @At(value = "FIELD", target = "Lnet/minecraft/init/MobEffects;BLINDNESS:Lnet/minecraft/potion/Potion;", ordinal = 0),
         to = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;cloudFog:Z", ordinal = 0)
      ),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogEnd(F)V")
   )
   private boolean lunar$blindnessFogEndEvent_v1_12(float var1) {
      return this.lunar$blindnessFogEndEvent(var1);
   }

   private boolean lunar$blindnessFogEndEvent(float var1) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.BLINDNESS, EventFogSetup.Type.END, var1);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogDensity(F)V", ordinal = 0))
   private boolean lunar$thickFogDensityEvent(float var1) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.RENDER_DISTANCE, EventFogSetup.Type.DENSITY, var1);
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;cloudFog:Z")),
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 0)
   )
   private boolean lunar$thickFogDensityEvent(int var1, float var2) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.RENDER_DISTANCE, EventFogSetup.Type.DENSITY, var2);
   }

   @Unique
   private boolean lunar$postEventEntityFogRender(EventFogSetup.FogSource var1, EventFogSetup.Type var2, float var3) {
      EventFogSetup.Data var4 = ClientEventBus.method29()
         .method12(EventFogSetup.Data.class, () -> new EventFogSetup.Data(var1, this.farPlaneDistance, this.cloudFog, var2, var3));
      return var4 == null || !var4.isCancelled();
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "setupFog",
      at = {
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 8),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 9)
      }
   )
   private boolean lunar$waterFogDensityEvent(int var1, float var2) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.WATER, EventFogSetup.Type.DENSITY, var2);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(
         from = @At(value = "FIELD", target = "Lnet/minecraft/block/material/Material;WATER:Lnet/minecraft/block/material/Material;", ordinal = 0),
         to = @At(value = "FIELD", target = "Lnet/minecraft/block/material/Material;LAVA:Lnet/minecraft/block/material/Material;", ordinal = 0)
      ),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogDensity(F)V")
   )
   private boolean lunar$waterFogDensityEvent(float var1) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.WATER, EventFogSetup.Type.DENSITY, var1);
   }

   @Annotation2(max = 0)
   @WrapWithCondition(method = "setupFog", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 10))
   private boolean lunar$lavaFogDensityEvent(int var1, float var2) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.LAVA, EventFogSetup.Type.DENSITY, var2);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/block/material/Material;LAVA:Lnet/minecraft/block/material/Material;", ordinal = 0)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogDensity(F)V", ordinal = 0)
   )
   private boolean lunar$lavaFogDensityEvent(float var1) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.LAVA, EventFogSetup.Type.DENSITY, var1);
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;farPlaneDistance:F", ordinal = 1)),
      at = {
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 0),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 2),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 4)
      }
   )
   private boolean lunar$renderDistanceFogStartEvent(int var1, float var2) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.RENDER_DISTANCE, EventFogSetup.Type.START, var2);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;farPlaneDistance:F", ordinal = 1)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogStart(F)V")
   )
   private boolean lunar$renderDistanceFogStartEvent(float var1) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.RENDER_DISTANCE, EventFogSetup.Type.START, var1);
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;farPlaneDistance:F", ordinal = 1)),
      at = {
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 1),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 3),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 5)
      }
   )
   private boolean lunar$renderDistanceFogEndEvent(int var1, float var2) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.RENDER_DISTANCE, EventFogSetup.Type.END, var2);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(
      method = "setupFog",
      slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;farPlaneDistance:F", ordinal = 1)),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;setFogEnd(F)V")
   )
   private boolean lunar$renderDistanceFogEndEvent(float var1) {
      return this.lunar$postEventEntityFogRender(EventFogSetup.FogSource.RENDER_DISTANCE, EventFogSetup.Type.END, var1);
   }

   @Annotation2(min = 1)
   @ModifyArg(
      method = "orientCamera",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 4),
      index = 1
   )
   private float lunar$onTranslateEyeHeight(float var1) {
      float var2 = -var1;
      Entity var3 = this.mc.getRenderViewEntity();
      if (var3 == Minecraft.getMinecraft().thePlayer && !this.renderingWorldDirections) {
         float var4 = var3.getEyeHeight();
         EventCameraEyeHeight var5 = ClientEventBus.method29().method12(EventCameraEyeHeight.class, () -> new EventCameraEyeHeight(var4, 0.08F));
         if (var5 != null && var5.isModified()) {
            var2 = var5.getEyeHeight();
         } else {
            var2 = var4;
         }

         if (((EntityLivingBase)var3).isPlayerSleeping()) {
            var2 = (float)(var2 + 1.0);
         }
      }

      return -var2;
   }

   @Annotation2(1)
   @Redirect(method = "renderWorldDirections$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;orientCamera(F)V"))
   private void lunar$renderWorldDirections(EntityRenderer var1, float var2) {
      this.renderingWorldDirections = true;
      this.orientCamera(var2);
      this.renderingWorldDirections = false;
   }

   @Inject(method = "addRainParticles", at = @At("HEAD"), cancellable = true)
   private void lunar$preventRainParticles(CallbackInfo var1) {
      WeatherChanger var2 = ThreadModuleDump63.method4().method40().method55();
      if (var2.isEnabled() && var2.method14().get() == WeatherChanger.Type.CLEAR) {
         var1.cancel();
      }
   }

   @Inject(method = "renderRainSnow", at = @At("HEAD"), cancellable = true)
   private void lunar$optifineWeatherControl(float var1, CallbackInfo var2) {
      if (Bridge.method5().isPresent() && !Bridge.method5().get().getConfig().isWeatherEnabled()) {
         var2.cancel();
      }

      WeatherChanger var3 = ThreadModuleDump63.method4().method40().method55();
      if (var3.isEnabled() && var3.method14().get() == WeatherChanger.Type.CLEAR) {
         var2.cancel();
      }
   }

   @ModifyExpressionValue(method = "renderRainSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getRainStrength(F)F"))
   private float lunar$modifyRainStrength(float var1) {
      WeatherChanger var2 = Client.method109().method40().method55();
      return var2.method3(var1) ? var2.method13() : var1;
   }

   @Annotation2(min = 1)
   @ModifyExpressionValue(method = "renderRainSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;canRain$v1_8()Z"))
   private boolean lunar$canRain(boolean var1) {
      WeatherChanger var2 = Client.method109().method40().method55();
      if (var2.isEnabled()) {
         switch ((WeatherChanger.Type)var2.method14().get()) {
            case RAIN:
            case SNOW:
               return true;
            case CLEAR:
               return false;
         }
      }

      return var1;
   }

   @Annotation2(max = 1)
   @ModifyExpressionValue(
      method = "renderRainSnow",
      at = @At(value = "INVOKE", target = "net/minecraft/world/biome/WorldChunkManager.getTemperatureAtHeight (FI)F")
   )
   private float lunar$forceRainInAllBiomes_v1_7(float var1) {
      WeatherChanger var2 = Client.method109().method40().method55();
      if (var2.isEnabled()) {
         switch ((WeatherChanger.Type)var2.method14().get()) {
            case RAIN:
               return 0.15F;
            case SNOW:
               return 0.0F;
         }
      }

      return var1;
   }

   @Annotation2(min = 5)
   @ModifyExpressionValue(
      method = "renderRainSnow",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeProvider;getTemperatureAtHeight$v1_12(FI)F")
   )
   private float lunar$forceRainInAllBiomes_v1_12(float var1) {
      WeatherChanger var2 = Client.method109().method40().method55();
      if (var2.isEnabled()) {
         switch ((WeatherChanger.Type)var2.method14().get()) {
            case RAIN:
               return 0.15F;
            case SNOW:
               return 0.0F;
         }
      }

      return var1;
   }

   @Annotation2(max = 0)
   @Redirect(method = "renderRainSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F$v1_7(FFFF)V"))
   private void lunar$changeRainColor(Tessellator var1, float var2, float var3, float var4, float var5) {
      WeatherChanger var6 = Client.method109().method40().method55();
      if (var6.isEnabled()) {
         int var7 = var6.method16().method1(0.0F);
         var1.setColorRGBA_F$v1_7(ThreadModuleDump23.method5(var7), ThreadModuleDump23.greenFloat(var7), ThreadModuleDump23.method7(var7), var5);
      } else {
         var1.setColorRGBA_F$v1_7(var2, var3, var4, var5);
      }
   }

   @Annotation2(min = 1)
   @Redirect(
      method = "renderRainSnow",
      require = 4,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;color$v1_8(FFFF)Lnet/minecraft/client/renderer/BufferBuilder;")
   )
   private WorldRenderer lunar$changeRainColor(WorldRenderer var1, float var2, float var3, float var4, float var5) {
      WeatherChanger var6 = Client.method109().method40().method55();
      if (var6.isEnabled()) {
         int var7 = var6.method16().method1(0.0F);
         var1.color(ThreadModuleDump23.method5(var7), ThreadModuleDump23.greenFloat(var7), ThreadModuleDump23.method7(var7), var5);
      } else {
         var1.color(var2, var3, var4, var5);
      }

      return var1;
   }

   @Annotation2(max = 0)
   @ModifyArg(method = "hurtCameraEffect", at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glRotatef (FFFF)V"), index = 0)
   private float lunar$applyHurtShakeIntensity_v1_7(float var1) {
      return var1 * ThreadModuleDump63.method4().method40().method81().method13();
   }

   @Annotation2(min = 1)
   @ModifyArg(method = "hurtCameraEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V"), index = 0)
   private float lunar$applyHurtShakeIntensity_v1_8(float var1) {
      return var1 * ThreadModuleDump63.method4().method40().method81().method13();
   }

   @Annotation2(max = 1)
   @WrapWithCondition(
      method = "setupCameraTransform",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;setupViewBobbing$v1_7(F)V")
   )
   private boolean lunar$applyViewBobbing_v1_7(EntityRenderer var1, float var2) {
      return !ThreadModuleDump63.method4().method40().method84().method47();
   }

   @Annotation2(min = 5)
   @WrapWithCondition(
      method = "setupCameraTransform",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;applyBobbing$v1_12(F)V")
   )
   private boolean lunar$applyViewBobbing_v1_12(EntityRenderer var1, float var2) {
      return !ThreadModuleDump63.method4().method40().method84().method47();
   }

   @Inject(method = {"updateCameraAndRender$v1_7", "updateCameraAndRender$v1_8"}, at = @At("HEAD"))
   private void lunar$limitFps(CallbackInfo var1) {
      UnfocusedFpsLimiter.method1();
   }

   @Inject(
      method = {"updateCameraAndRender$v1_7", "updateCameraAndRender$v1_8"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;bindFramebuffer(Z)V", shift = Shift.AFTER)
   )
   private void lunar$postEffectRenderingEvent(CallbackInfo var1) {
      if (Bridge.method22().method1()) {
         ClientEventBus.method29()
            .method12(PostProcessEvent.class, () -> new PostProcessEvent(AbstractRenderContext.method32(), Bridge.method9().bridge$getMainRenderTarget()));
         if (this.lunar$fastRenderCapture != null) {
            this.lunar$fastRenderCapture.bridge$delete();
            this.lunar$fastRenderCapture = null;
         }
      } else if (Bridge.method22().method3()) {
         PostProcessEvent var2 = ClientEventBus.method29()
            .method12(
               PostProcessEvent.class,
               () -> {
                  Bridge3_24 var1x = this.lunar$getFastRenderCaptureTarget();
                  GL30.glBindFramebuffer(36008, 0);
                  GL30.glBindFramebuffer(36009, ((Framebuffer)var1x).framebufferObject);
                  GL30.glBlitFramebuffer(
                     0, 0, this.mc.displayWidth, this.mc.displayHeight, 0, 0, var1x.bridge$framebufferWidth(), var1x.bridge$framebufferHeight(), 16384, 9728
                  );
                  GL30.glBindFramebuffer(36160, 0);
                  return new PostProcessEvent(AbstractRenderContext.method32(), var1x);
               }
            );
         if (var2 != null && var2.isModified()) {
            GL30.glBindFramebuffer(36160, 0);
            var2.method3().bridge$blitToScreen(0, 0, this.mc.displayWidth, this.mc.displayHeight, false);
         }
      }
   }

   @Unique
   private Bridge3_24 lunar$getFastRenderCaptureTarget() {
      int var1 = this.mc.displayWidth;
      int var2 = this.mc.displayHeight;
      if (this.lunar$fastRenderCapture != null
         && this.lunar$fastRenderCapture.bridge$framebufferWidth() == var1
         && this.lunar$fastRenderCapture.bridge$framebufferHeight() == var2) {
         return this.lunar$fastRenderCapture;
      }

      if (this.lunar$fastRenderCapture == null) {
         this.lunar$fastRenderCapture = Bridge_52.method2().method1(var1, var2).method9(true).method3();
      } else {
         this.lunar$fastRenderCapture.bridge$createBindFramebuffer(var1, var2);
      }

      return this.lunar$fastRenderCapture;
   }

   @Annotation2(max = 0)
   @Inject(method = "renderWorld", at = @At("HEAD"))
   private void lunar$beginLazyChunkLoading$v1_7(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method41().method7().method22().get().getAmount() > 30) {
         this.timesUpdated = 0.0F;
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderWorld",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;updateRenderers(Lnet/minecraft/entity/EntityLivingBase;Z)Z")
   )
   private boolean lunar$onUpdateRenderers$v1_7(RenderGlobal var1, EntityLivingBase var2, boolean var3) {
      boolean var4 = this.timesUpdated == 0.0F && var1.updateRenderers$v1_7(var2, var3);
      this.timesUpdated++;
      if (this.timesUpdated >= ThreadModuleDump63.method4().method41().method7().method22().get().getAmount()) {
         this.timesUpdated = 0.0F;
      }

      return var4;
   }

   @Annotation2(min = 1)
   @ModifyArg(method = "hurtCameraEffect(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sin(F)F"))
   private float lunar$oneSevenHurtShake(float var1) {
      if (ThreadModuleDump63.method4().method40().method98().method16().method14()) {
         float var2 = (float)Math.sqrt(Math.sqrt(var1 / Math.PI));
         var2 /= 1.15F;
         return (float)(var2 * var2 * var2 * var2 * Math.PI);
      } else {
         return var1;
      }
   }

   @ModifyExpressionValue(method = "getFOVModifier(FZ)F", at = @At(value = "FIELD", target = "net/minecraft/client/settings/GameSettings.fovSetting : F"))
   private float lunar$eventFovConstant(float var1, float var2, boolean var3) {
      FovModifierEvent var4 = ClientEventBus.method29()
         .method12(FovModifierEvent.Data.class, () -> new FovModifierEvent.Data(var1, var2, this.fovModifierHandPrev, this.fovModifierHand));
      return var4 == null ? var1 : var4.method1();
   }

   @ModifyReturnValue(method = "getFOVModifier(FZ)F", at = @At("RETURN"))
   private float lunar$eventFovReturn(float var1, float var2, boolean var3) {
      FovModifierEvent var4 = ClientEventBus.method29().method12(FovModifierEvent.FovModifierPostEvent.class, () -> new FovModifierEvent.FovModifierPostEvent(var1, var2, 0.0F, 0.0F));
      return var4 == null ? var1 : var4.method1();
   }

   @Annotation2(max = 0)
   @Inject(method = "updateRenderer", at = @At("TAIL"))
   private void lunar$smoothCamera(CallbackInfo var1) {
      if (!this.mc.gameSettings.smoothCamera) {
         this.smoothCamFilterX = 0.0F;
         this.smoothCamFilterY = 0.0F;
         this.mouseFilterXAxis.field_76336_a = 0.0F;
         this.mouseFilterXAxis.field_76334_b = 0.0F;
         this.mouseFilterXAxis.field_76335_c = 0.0F;
         this.mouseFilterYAxis.field_76336_a = 0.0F;
         this.mouseFilterYAxis.field_76334_b = 0.0F;
         this.mouseFilterYAxis.field_76335_c = 0.0F;
      }
   }

   @ModifyExpressionValue(
      method = {"updateCameraAndRender$v1_7", "updateCameraAndRender$v1_8"},
      at = @At(value = "FIELD", opcode = 180, target = "Lnet/minecraft/client/settings/GameSettings;mouseSensitivity:F")
   )
   private float lunar$adjustCameraSensitivity$v1_8(float var1) {
      Zoom var2 = ThreadModuleDump63.method4().method40().method50();
      return var2.isEnabled() && var2.isActive() ? var1 * var2.method14() : var1;
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderWorldPass$v1_8",
      at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", args = "ldc=hand")
   )
   private void lunar$renderWorldLast$v1_8(int var1, float var2, long var3, CallbackInfo var5) {
      Minecraft.getMinecraft().mcProfiler.endStartSection("event3d");
      this.lunar$updateClippingHelper(var2);
      HudRenderLegacyEvent.method2(var2);
      ClientEventBus.method29().method12(EventWorldRender.class, () -> new EventWorldRender((Bridge14_3)this.mc.renderGlobal, var2));
   }

   @Annotation2(max = 0)
   @Inject(
      method = "renderWorld",
      at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", args = "ldc=hand")
   )
   private void lunar$renderWorldLast$v1_7(float var1, long var2, CallbackInfo var4) {
      Minecraft.getMinecraft().mcProfiler.endStartSection("event3d");
      this.lunar$updateClippingHelper(var1);
      HudRenderLegacyEvent.method2(var1);
      ClientEventBus.method29().method12(EventWorldRender.class, () -> new EventWorldRender((Bridge14_3)this.mc.renderGlobal, var1));
   }

   @Unique
   private void lunar$updateClippingHelper(float var1) {
      Minecraft var2 = this.mc;
      Object var3 = ThreadModuleDump63.MC_VERSION >= 1 ? var2.renderViewEntity : var2.renderViewEntity$v1_7;
      ClippingHelperImpl var4 = ClippingHelperImpl.theMinecraft;
      var2.mcProfiler.endStartSection("event3d");
      double var5 = ThreadModuleDump67.method15(((Entity)var3).prevPosX, ((Entity)var3).posX, var1);
      double var7 = ThreadModuleDump67.method15(((Entity)var3).prevPosY, ((Entity)var3).posY, var1) + var3.getEyeHeight();
      double var9 = ThreadModuleDump67.method15(((Entity)var3).prevPosZ, ((Entity)var3).posZ, var1);
      ThreadModuleDump64.method1(var5, var7, var9, (var1x, var2x) -> {
         System.arraycopy(var4.modelviewMatrix, 0, var1x.array(), 0, var4.modelviewMatrix.length);
         System.arraycopy(var4.projectionMatrix, 0, var2x.array(), 0, var4.projectionMatrix.length);
      });
   }

   @Unique
   private void lunar$callGuiDrawScreenEvents(GuiScreen var1, int var2, int var3, float var4) {
      int var5;
      int var6;
      if (this.lunar$inventoryGuiScale$pre()) {
         var5 = (int)(var2 / this.lunar$inventoryScaleFactor);
         var6 = (int)(var3 / this.lunar$inventoryScaleFactor);
      } else {
         var5 = var2;
         var6 = var3;
      }

      BridgeExtension3_5 var7 = AbstractRenderContext.method32();

      try {
         ClientEventBus.method29()
            .method16(
               ContainerSlotRenderEvent.ContainerSlotPreEvent.class,
               ContainerSlotRenderEvent.ContainerSlotPostEvent.class,
               var5x -> var5x.wrapEvents(
                  () -> new ContainerSlotRenderEvent.ContainerSlotPreEvent(new MarkerModel.Data4(var5, var6), var4, (Bridge5Extension6)var1, var7, new LegacyGuiGraphicsBridge(var7)),
                  () -> new ContainerSlotRenderEvent.ContainerSlotPostEvent(new MarkerModel.Data4(var5, var6), var4, (Bridge5Extension6)var1, var7, new LegacyGuiGraphicsBridge(var7))
               ),
               () -> var1.drawScreen(var5, var6, var4)
            );
      } finally {
         Gui2.method10();
      }

      this.lunar$inventoryGuiScale$post();
   }

   @Unique
   private boolean lunar$inventoryGuiScale$pre() {
      Bridge5Extension6 var1 = (Bridge5Extension6)this.mc.currentScreen;
      int var2 = var1 == null ? 0 : var1.bridge$getInventoryScale();
      if (var2 <= 0) {
         this.lunar$inventoryScaleFactor = 1.0F;
         return false;
      } else {
         this.lunar$inventoryGuiScale = this.mc.gameSettings.guiScale;
         this.mc.gameSettings.guiScale = var2;
         this.lunar$inventoryScaleFactor = (float)var2 / LcuiScreen.method151().method3();
         var1.bridge$setInventoryScaleFactor(this.lunar$inventoryScaleFactor);
         Bridge.method42().method4();
         Bridge.method42().bridge$scale(this.lunar$inventoryScaleFactor, this.lunar$inventoryScaleFactor, 1.0F);
         return true;
      }
   }

   @Unique
   private void lunar$inventoryGuiScale$post() {
      if (this.lunar$inventoryGuiScale != null) {
         this.mc.gameSettings.guiScale = this.lunar$inventoryGuiScale;
         Bridge.method42().method5();
         this.lunar$inventoryGuiScale = null;
         this.lunar$inventoryScaleFactor = 1.0F;
         Bridge5Extension6 var1 = (Bridge5Extension6)this.mc.currentScreen;
         if (var1 != null) {
            var1.bridge$setInventoryScaleFactor(1.0F);
         }
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "updateCameraAndRender$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V"))
   private void lunar$updateCameraAndRender$drawScreen$v1_7(GuiScreen var1, int var2, int var3, float var4) {
      if (DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40()) {
         var2 = 0;
         var3 = 0;
      }

      try {
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
         this.lunar$callGuiDrawScreenEvents(var1, var2, var3, var4);
      } catch (Throwable var11) {
         CrashReport var6 = CrashReport.makeCrashReport(var11, "Rendering screen");
         CrashReportCategory var7 = var6.makeCategory("Screen render details");
         String var8 = this.mc.currentScreen.getClass().getCanonicalName();
         var7.addCrashSectionCallable("Screen name", () -> var8);
         String var9 = String.format("Scaled: (%d, %d). Absolute: (%d, %d)", var2, var3, Mouse.getX(), Mouse.getY());
         var7.addCrashSectionCallable("Mouse location", () -> var9);
         String var10 = String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", 0, 0, this.mc.displayWidth, this.mc.displayHeight, 0);
         var7.addCrashSectionCallable("Screen size", () -> var10);
         throw new ReportedException(var6);
      }
   }

   @Annotation2(min = 1)
   @Redirect(method = "updateCameraAndRender$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V"))
   @Annotation_2(absent = "forge")
   private void lunar$updateCameraAndRender$drawScreen$v1_8(GuiScreen var1, int var2, int var3, float var4) {
      if (DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40()) {
         var2 = 0;
         var3 = 0;
      }

      try {
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
         this.lunar$callGuiDrawScreenEvents(var1, var2, var3, var4);
      } catch (Throwable var11) {
         CrashReport var6 = CrashReport.makeCrashReport(var11, "Rendering screen");
         CrashReportCategory var7 = var6.makeCategory("Screen render details");
         String var8 = this.mc.currentScreen.getClass().getCanonicalName();
         String var9 = String.format("Scaled: (%d, %d). Absolute: (%d, %d)", var2, var3, Mouse.getX(), Mouse.getY());
         String var10 = String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", 0, 0, this.mc.displayWidth, this.mc.displayHeight, 0);
         if (ThreadModuleDump63.MC_VERSION <= 0) {
            var7.addCrashSectionCallable("Screen name", () -> var8);
            var7.addCrashSectionCallable("Mouse location", () -> var9);
            var7.addCrashSectionCallable("Screen size", () -> var10);
         } else if (ThreadModuleDump63.MC_VERSION == 1) {
            var7.addCrashSectionCallable("Screen name", () -> var8);
            var7.addCrashSectionCallable("Mouse location", () -> var9);
            var7.addCrashSectionCallable("Screen size", () -> var10);
         } else {
            var7.addDetail$v1_12("Screen name", () -> var8);
            var7.addDetail$v1_12("Mouse location", () -> var9);
            var7.addDetail$v1_12("Screen size", () -> var10);
         }

         throw new ReportedException(var6);
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "updateCameraAndRender$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityClientPlayerMP;setAngles$v1_7(FF)V")
   )
   private void lunar$setAngles$v1_7(EntityClientPlayerMP var1, float var2, float var3) {
      if (var1 != null) {
         com.moonsworth.lunar.client.event.input.EventMouseDelta var4 = ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.input.EventMouseDelta.class,
               () -> new com.moonsworth.lunar.client.event.input.EventMouseDelta(var2, var3)
            );
         if (var4 != null) {
            if (!var4.isCancelled() && !ThreadModuleDump63.method4().method40().method31().isActive()) {
               var1.setAngles(var4.method1(), var4.method2());
            }
         } else {
            var1.setAngles(var2, var3);
         }
      }
   }

   @Annotation2(1)
   @Redirect(method = "updateCameraAndRender$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setAngles(FF)V"))
   private void lunar$setAngles$v1_8(EntityPlayerSP var1, float var2, float var3) {
      if (var1 != null) {
         com.moonsworth.lunar.client.event.input.EventMouseDelta var4 = ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.input.EventMouseDelta.class,
               () -> new com.moonsworth.lunar.client.event.input.EventMouseDelta(var2, var3)
            );
         if (var4 != null) {
            if (!var4.isCancelled() && !ThreadModuleDump63.method4().method40().method31().isActive()) {
               var1.setAngles(var4.method1(), var4.method2());
            }
         } else {
            var1.setAngles(var2, var3);
         }
      }
   }

   @Annotation2(min = 5)
   @Redirect(method = "updateCameraAndRender$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;turn(FF)V"))
   private void lunar$setAngles$v1_12(EntityPlayerSP var1, float var2, float var3) {
      if (var1 != null) {
         com.moonsworth.lunar.client.event.input.EventMouseDelta var4 = ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.input.EventMouseDelta.class,
               () -> new com.moonsworth.lunar.client.event.input.EventMouseDelta(var2, var3)
            );
         if (var4 != null) {
            if (!var4.isCancelled() && !ThreadModuleDump63.method4().method40().method31().isActive()) {
               var1.turn$v1_12(var4.method1(), var4.method2());
            }
         } else {
            var1.turn$v1_12(var2, var3);
         }
      }
   }

   @ModifyExpressionValue(
      method = "orientCamera",
      at = {
            @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;rotationYaw:F"),
            @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;prevRotationYaw:F"),
            @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationYaw:F"),
            @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationYaw:F")
      },
      require = 2,
      expect = 2
   )
   private float lunar$changeCameraYaw(float var1, float var2) {
      return ThreadModuleDump63.method4().method40().method31().isEnabled() && ThreadModuleDump63.method4().method40().method31().isActive()
         ? ThreadModuleDump63.method4().method40().method31().getRotationYaw()
         : var1;
   }

   @ModifyExpressionValue(
      method = "orientCamera",
      at = {
            @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;rotationPitch:F"),
            @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;prevRotationPitch:F"),
            @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationPitch:F"),
            @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationPitch:F")
      },
      require = 2,
      expect = 2
   )
   private float lunar$changeCameraPitch(float var1, float var2) {
      return ThreadModuleDump63.method4().method40().method31().isEnabled() && ThreadModuleDump63.method4().method40().method31().isActive()
         ? ThreadModuleDump63.method4().method40().method31().getRotationPitch()
         : var1;
   }

   @WrapOperation(
      method = "orientCamera",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 2),
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 2)
      },
      require = 1
   )
   private void lunar$freelookTranslate(float var1, float var2, float var3, Operation<Void> var4) {
      if (Freelook.method16().isActive() && !Freelook.method16().OOHROOORIHORCHCOHOCIOCRHROHCCO()) {
         float var5 = 0.125F;
         var4.call(new Object[]{var1, var2, var3 * (var5 + Freelook.method16().method1() * (1.0F - var5))});
      } else {
         var4.call(new Object[]{var1, var2, var3});
      }
   }

   @Redirect(
      method = "orientCamera",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"
      )
   )
   private MovingObjectPosition lunar$thirdPersonFix(WorldClient var1, Vec3 var2, Vec3 var3) {
      return var1.rayTraceBlocks(var2, var3, false, true, false);
   }

   @Inject(method = "orientCamera", at = @At("HEAD"))
   private void lunar$rewindCameraTiltAndShake(CallbackInfo var1) {
      Rewind var2 = ThreadModuleDump63.method4().method40().method85();
      if (var2.method17(RewindHandlers::method44)) {
         RewindHandlers3Updater var3 = var2.method35().method45();
         float[] var4 = var3.method14();
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.rotate(var4[1], -1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(var4[0], 0.0F, 1.0F, 0.0F);
         } else {
            GL11.glRotatef(var4[1], -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var4[0], 0.0F, 1.0F, 0.0F);
         }

         if (!var3.method15().isFixedToPlayer()) {
            float var5 = var3.method16().get().get(5).floatValue();
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               GlStateManager.rotate(var5, 0.0F, 0.0F, 1.0F);
            } else {
               GL11.glRotatef(var5, 0.0F, 0.0F, 1.0F);
            }
         }
      }
   }

   @Inject(method = {"updateCameraAndRender$v1_7", "updateCameraAndRender$v1_8"}, at = @At("TAIL"))
   private void lunar$onUpdateCameraRender(CallbackInfo var1) {
      float var2 = LcuiScreen.getScale();
      BridgeExtension3_5 var3 = AbstractRenderContext.method32();
      var3.method33();
      var3.push();
      var3.method29(var2, var2);
      Client.method109().method68().method4(var3);
      var3.pop();
   }

   @Annotation2(max = 0)
   @Inject(method = "updateShaderGroupSize", at = @At("RETURN"))
   private void lunar$bindEntityOutlineFramebuffer(int var1, int var2, CallbackInfo var3) {
      if (OpenGlHelper.shadersSupported) {
         ((Bridge14_3)this.mc.renderGlobal).bridge$bindEntityOutlineFbs(var1, var2);
      }
   }

   @Annotation2(max = 0)
   @Inject(
      method = "updateCameraAndRender$v1_7",
      at = @At(
         value = "FIELD",
         opcode = 180,
         target = "Lnet/minecraft/client/renderer/EntityRenderer;shaderGroup:Lnet/minecraft/client/shader/ShaderGroup;",
         shift = Shift.BEFORE,
         ordinal = 0
      )
   )
   private void lunar$renderEntityOutlineFramebuffer(float var1, CallbackInfo var2) {
      ((Bridge14_3)this.mc.renderGlobal).bridge$renderEntityOutlineFramebuffer();
   }

   @Annotation2(min = 1)
   @Inject(
      method = "getMouseOver(F)V",
      at = {
            @At(
               value = "FIELD",
               target = "Lnet/minecraft/client/renderer/EntityRenderer;pointedEntity:Lnet/minecraft/entity/Entity;",
               opcode = 181,
               ordinal = 5
            ),
            @At(
               value = "FIELD",
               target = "Lnet/minecraft/client/Minecraft;objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;",
               shift = Shift.AFTER,
               opcode = 181,
               ordinal = 2
            )
      }
   )
   @Dynamic
   private void apollo$reachCheck$v1_8(CallbackInfo var1, @Local(ordinal = 2) double var2) {
      if (this.pointedEntity instanceof AbstractClientPlayer) {
         Client.method109()
            .method84()
            .<ApolloModuleHandler>method3(PacketEnrichmentModule.class)
            .filter(var0 -> (Boolean)var0.getOptions().get(PacketEnrichmentModule.PLAYER_ATTACK_PACKET))
            .ifPresent(var3 -> ((Highlight3Iterator31)var3).method14().put(this.pointedEntity.getUniqueID(), var2));
      }
   }

   @Inject(method = "getFOVModifier(FZ)F", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindFreeCamFOV(float var1, boolean var2, CallbackInfoReturnable<Float> var3) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method45().method15().isFixedToPlayer())) {
         float var4 = this.mc.gameSettings.fovSetting;
         float var5 = var4;
         FovModifierEvent var6 = ClientEventBus.method29()
            .method12(FovModifierEvent.Data.class, () -> new FovModifierEvent.Data(var5, var1, this.fovModifierHandPrev, this.fovModifierHand));
         if (var6 != null) {
            var4 = var6.method1();
         }

         float var7 = var4;
         var6 = ClientEventBus.method29().method12(FovModifierEvent.FovModifierPostEvent.class, () -> new FovModifierEvent.FovModifierPostEvent(var7, var1, 0.0F, 0.0F));
         if (var6 != null && (var6.isCancelled() || var6.method1() != var4)) {
            var4 = var6.method1();
         }

         var3.setReturnValue(var4);
      }
   }

   @WrapOperation(method = "getFOVModifier", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;currentScreen"))
   @Annotation_2(present = "optifine")
   @Dynamic
   private GuiScreen lunar$rewindAllowZoom(Minecraft var1, Operation<GuiScreen> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? null : (GuiScreen)var2.call(new Object[]{var1});
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "getFOVModifier",
      at = {
            @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z"),
            @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z")
      },
      require = 2
   )
   @Annotation_2(present = "optifine")
   @Dynamic
   private boolean lunar$rewindZoomKey$v1_7(int var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getZoomKey().orElseThrow().bridge$isKeyDown()
         : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(min = 1)
   @WrapOperation(
      method = "getFOVModifier",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/GameSettings;isKeyDown(Lnet/minecraft/client/settings/KeyBinding_v1_8;)Z")
   )
   @Annotation_2(present = "optifine")
   @Dynamic
   private boolean lunar$rewindZoomKey$v1_8(KeyBinding var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? var1.field_146155_p : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(min = 1)
   @Inject(method = "loadShader$v1_8", at = @At("HEAD"))
   private void lunar$clearCurrentlyLoadedShaders(ResourceLocation var1, CallbackInfo var2) {
      if (this.shaderGroup != null) {
         this.shaderGroup.deleteShaderGroup();
      }
   }

   @Inject(method = {"updateCameraAndRender$v1_7", "updateCameraAndRender$v1_8"}, at = @At("HEAD"))
   private void lunar$webosr$renderGameUI$head(CallbackInfo var1, @Share("dummyScreen") LocalRef<DualMarkerScreenLegacy> var2) {
      var2.set(ThreadModuleDump63.method12());
   }

   @Inject(method = {"updateCameraAndRender$v1_7", "updateCameraAndRender$v1_8"}, at = @At("TAIL"))
   private void lunar$webosr$renderGameUI$tail(CallbackInfo var1, @Share("dummyScreen") LocalRef<DualMarkerScreenLegacy> var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      if (var3.method19()) {
         var3.method35().method48().method16();
      }

      int var4 = Mouse.getX();
      int var5 = ThreadModuleDump63.method3().bridge$logicalHeight() - Mouse.getY();
      MarkerModel.Data6 var6 = new MarkerModel.Data6(var4, var5);
      if (var2.get() != null && !((DualMarkerScreenLegacy)var2.get()).method16(var6)) {
         com.moonsworth.lunar.client.driver.core.DriverViewLegacy.method21().method7(new MarkerModel.Data6(-1.0, -1.0));
      } else if (!var3.method17(RewindHandlers::method62)) {
         com.moonsworth.lunar.client.driver.core.DriverViewLegacy.method21().method7(var6);
      }

      if (var2.get() == null || var3.method19()) {
         if (this.mc.gameSettings.hideGUI && this.mc.currentScreen == null && this.mc.theWorld != null) {
            this.setupOverlayRendering();
         }

         BridgeExtension3_5 var7 = AbstractRenderContext.method32();
         var7.push();
         com.moonsworth.lunar.client.driver.core.DriverViewLegacy.method21().method2(new LegacyGuiGraphicsBridge(var7));
         var7.pop();
      }
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "renderWorld",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;sortAndRender$v1_7(Lnet/minecraft/entity/EntityLivingBase;ID)I")
   )
   @Dynamic
   private boolean lunar$rewindRenderBlocks$v1_7(RenderGlobal var1, EntityLivingBase var2, int var3, double var4) {
      return this.lunar$rewindWorldRenderingCheck(var0 -> var0.method16().get()) && this.lunar$rewindChromaKeyingCheck(var0 -> var0.method28().get());
   }

   @Annotation2(min = 1)
   @WrapWithCondition(
      method = "renderWorldPass$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer$v1_8(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I"
      )
   )
   private boolean lunar$rewindRenderBlocks$v1_8(RenderGlobal var1, EnumWorldBlockLayer var2, double var3, int var5, Entity var6) {
      return this.lunar$rewindWorldRenderingCheck(var0 -> var0.method16().get()) && this.lunar$rewindChromaKeyingCheck(var0 -> var0.method28().get());
   }

   @Inject(method = {"renderCloudsCheck$v1_7", "renderCloudsCheck$v1_8", "renderCloudsCheck$v1_12"}, at = @At("HEAD"), cancellable = true)
   private void lunar$rewindRenderClouds(CallbackInfo var1) {
      if (!this.lunar$rewindWorldRenderingCheck(var0 -> var0.method17().get())) {
         var1.cancel();
      }
   }

   @Annotation2(max = 0)
   @WrapWithCondition(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderSky$v1_7(F)V"))
   @Dynamic
   private boolean lunar$rewindRenderSky$v1_7(RenderGlobal var1, float var2) {
      return this.lunar$rewindWorldRenderingCheck(var0 -> var0.method22().get()) && this.lunar$rewindChromaKeyingCheck(var0 -> var0.method27().get());
   }

   @Annotation2(min = 1)
   @WrapWithCondition(method = "renderWorldPass$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderSky$v1_8(FI)V"))
   private boolean lunar$rewindRenderSky$v1_8(RenderGlobal var1, float var2, int var3) {
      return this.lunar$rewindWorldRenderingCheck(var0 -> var0.method22().get()) && this.lunar$rewindChromaKeyingCheck(var0 -> var0.method27().get());
   }

   @WrapWithCondition(
      method = {"renderWorldPass$v1_8", "renderWorld"},
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;renderParticles(Lnet/minecraft/entity/Entity;F)V"),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;renderLitParticles(Lnet/minecraft/entity/Entity;F)V")
      },
      expect = 2,
      require = 2
   )
   private boolean lunar$rewindRenderParticles(EffectRenderer var1, Entity var2, float var3) {
      return this.lunar$rewindWorldRenderingCheck(var0 -> var0.method23().get());
   }

   @Annotation2(min = 1)
   @WrapWithCondition(
      method = "renderWorldPass$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderWorldBorder$v1_8(Lnet/minecraft/entity/Entity;F)V")
   )
   private boolean lunar$rewindWorldBorder(RenderGlobal var1, Entity var2, float var3) {
      return this.lunar$rewindWorldRenderingCheck(var0 -> var0.method25().get());
   }

   @Unique
   private boolean lunar$rewindWorldRenderingCheck(Function<RewindHandlers3Impl6, Boolean> var1) {
      Rewind var2 = ThreadModuleDump63.method4().method40().method85();
      if (var2.method19()) {
         RewindHandlers3Impl6 var3 = var2.method35().method52();
         if (var3.method14().get()) {
            return (Boolean)var1.apply(var3);
         }
      }

      return true;
   }

   @Unique
   private boolean lunar$rewindChromaKeyingCheck(Function<RewindHandlers3Impl6, Boolean> var1) {
      Rewind var2 = ThreadModuleDump63.method4().method40().method85();
      if (var2.method19()) {
         RewindHandlers3Impl6 var3 = var2.method35().method52();
         if (var3.method15().get()) {
            return !(Boolean)var1.apply(var3);
         }
      }

      return true;
   }

   @Annotation2(min = 1)
   @WrapOperation(
      method = "loadShader$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getFramebuffer()Lnet/minecraft/client/shader/Framebuffer;")
   )
   private Framebuffer lunar$rewindFramebuffer(Minecraft var1, Operation<Framebuffer> var2) {
      if (!ThreadModuleDump63.method4().method40().method85().method19()) {
         return (Framebuffer)var2.call(new Object[]{var1});
      }

      RewindHandlers var3 = ThreadModuleDump63.method4().method40().method85().method35();
      return (Framebuffer)var3.method48().method24().method11();
   }

   @Annotation2(min = 1)
   @WrapOperation(method = "loadShader$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/ShaderGroup;createBindFramebuffers(II)V"))
   private void lunar$rewindShadersSize(ShaderGroup var1, int var2, int var3, Operation<Void> var4) {
      if (!ThreadModuleDump63.method4().method40().method85().method19()) {
         var4.call(new Object[]{var1, var2, var3});
      } else {
         RewindHandlers var5 = ThreadModuleDump63.method4().method40().method85().method35();
         Bridge3_24 var6 = var5.method48().method24().method11();
         var4.call(new Object[]{var1, var6.bridge$framebufferTextureWidth(), var6.bridge$framebufferTextureHeight()});
      }
   }

   @Inject(method = "displayItemActivation$v1_12", at = @At("HEAD"))
   @Annotation2(min = 5)
   private void lunar$totemHook(ItemStack var1, CallbackInfo var2) {
      if (Minecraft.getMinecraft().thePlayer != null && Objects.equals(var1.getItem(), Items.TOTEM_OF_UNDYING$v1_12)) {
         ClientEventBus.method29().method12(EventTotemActivation.class, () -> new EventTotemActivation(ThreadModuleDump63.method7()));
      }
   }

   @Inject(
      method = {"updateCameraAndRender$v1_7", "updateCameraAndRender$v1_8"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;currentScreen:Lnet/minecraft/client/gui/GuiScreen;")
   )
   private void lunar$rewindRestoreScreen(CallbackInfo var1) {
      RewindHandlers var2 = ThreadModuleDump63.method4().method40().method85().method35();
      if (var2 != null) {
         var2.method48().method17();
      }
   }

   @Annotation2(min = 1)
   @WrapOperation(method = "renderWorldPass$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSpectator$v1_8()Z"))
   private boolean lunar$rewindForceSpectatorCheck(EntityPlayerSP var1, Operation<Boolean> var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      return var3.method17(var0 -> !var0.method45().method15().isFixedToPlayer()) ? true : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(min = 1)
   @WrapOperation(method = "updateCameraAndRender$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V"))
   @Annotation_2(absent = "forge")
   private void lunar$overwriteGuiRender$lunar(GuiScreen var1, int var2, int var3, float var4, Operation<Void> var5) {
      Module2 var6 = GuiRewindhandlersHandler2.field7.method11();
      if (!var6.method1()) {
         if (var6.method2()) {
            var5.call(new Object[]{var1, -1, -1, var4});
         } else {
            var5.call(new Object[]{var1, var2, var3, var4});
         }
      }

      var6.method3(ThreadModuleDump63.method3().bridge$getCurrentScreen(), new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), var2, var3, var4);
   }

   @Annotation2(1)
   @WrapOperation(
      method = "updateCameraAndRender$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;drawScreen(Lnet/minecraft/client/gui/GuiScreen;IIF)V")
   )
   @Annotation_2(present = "forge", absent = "optifine")
   @Dynamic
   private void lunar$overwriteGuiRender$forge$1_8(GuiScreen var1, int var2, int var3, float var4, Operation<Void> var5) {
      Module2 var6 = GuiRewindhandlersHandler2.field7.method11();
      if (!var6.method1()) {
         if (var6.method2()) {
            var5.call(new Object[]{var1, -1, -1, var4});
         } else {
            var5.call(new Object[]{var1, var2, var3, var4});
         }
      }

      var6.method3(ThreadModuleDump63.method3().bridge$getCurrentScreen(), new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), var2, var3, var4);
   }

   @Annotation2(1)
   @WrapOperation(method = "updateCameraAndRender$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V"))
   @Annotation_2(present = {"forge", "optifine"})
   @Dynamic
   private void lunar$overwriteGuiRender$forgeOptifine$1_8(GuiScreen var1, int var2, int var3, float var4, Operation<Void> var5) {
      Module2 var6 = GuiRewindhandlersHandler2.field7.method11();
      if (!var6.method1()) {
         if (var6.method2()) {
            var5.call(new Object[]{var1, -1, -1, var4});
         } else {
            var5.call(new Object[]{var1, var2, var3, var4});
         }
      }

      var6.method3(ThreadModuleDump63.method3().bridge$getCurrentScreen(), new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), var2, var3, var4);
   }

   @Annotation2(min = 5)
   @WrapOperation(method = "updateCameraAndRender$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V"))
   @Annotation_2(present = "forge")
   private void lunar$overwriteGuiRender$forge$1_12(GuiScreen var1, int var2, int var3, float var4, Operation<Void> var5) {
      Module2 var6 = GuiRewindhandlersHandler2.field7.method11();
      if (!var6.method1()) {
         if (var6.method2()) {
            var5.call(new Object[]{var1, -1, -1, var4});
         } else {
            var5.call(new Object[]{var1, var2, var3, var4});
         }
      }

      var6.method3(ThreadModuleDump63.method3().bridge$getCurrentScreen(), new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), var2, var3, var4);
   }

   @Inject(method = {"enableLightmap$v1_7", "enableLightmap$v1_8"}, at = @At("HEAD"))
   private void lunar$enableLightmap(CallbackInfo var1) {
      LegacyRenderTypeFactory.field9.field10 = true;
   }

   @Inject(method = {"disableLightmap$v1_7", "disableLightmap$v1_8"}, at = @At("HEAD"))
   private void lunar$disableLightmap(CallbackInfo var1) {
      LegacyRenderTypeFactory.field9.field10 = false;
   }

   @Annotation2(max = 0)
   @Inject(method = "orientCamera", at = @At("TAIL"))
   private void lunar$offsetCamera$v1_7(float var1, CallbackInfo var2) {
      CameraOffsetEvent var3 = ClientEventBus.method29().method12(CameraOffsetEvent.class, () -> new CameraOffsetEvent(var1));
      if (var3 != null) {
         GL11.glTranslatef(var3.method3(), var3.method4(), var3.method5());
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "orientCamera", at = @At("TAIL"))
   private void lunar$offsetCamera$v1_8(float var1, CallbackInfo var2) {
      CameraOffsetEvent var3 = ClientEventBus.method29().method12(CameraOffsetEvent.class, () -> new CameraOffsetEvent(var1));
      if (var3 != null) {
         GlStateManager.translate(var3.method3(), var3.method4(), var3.method5());
      }
   }
}
