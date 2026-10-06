package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.SExtension;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EntityRenderBaseEvent;
import com.moonsworth.lunar.client.mod.movement.freelook.Freelook;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.click.Click11;
import com.moonsworth.lunar.client.util.click.Click4;
import com.moonsworth.lunar.client.util.click.Click4Impl;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Map;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.Render_v1_7;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderManager.class)
public abstract class RenderManagerFreelookMixin implements Bridge2_43 {
   @Final
   @Shadow
   public Map<String, RenderPlayer> skinMap;
   @Final
   @Shadow
   public RenderPlayer playerRenderer;
   @Shadow
   public static RenderManager instance;
   @Shadow
   public boolean renderOutlines;
   @Annotation2(max = 1)
   @Unique
   private boolean lunar$renderOutlines;

   @Shadow
   public abstract Render getEntityClassRenderObject(Class<? extends Entity> var1);

   @Annotation2(min = 1)
   @Redirect(method = "cacheActiveRenderInfo$v1_8", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationYaw:F"))
   private float lunar$cacheActiveRenderInfo$rotationYaw$v1_8(Entity var1) {
      return this.lunar$freelookYaw(var1.rotationYaw);
   }

   @Annotation2(min = 1)
   @Redirect(method = "cacheActiveRenderInfo$v1_8", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationYaw:F"))
   private float lunar$cacheActiveRenderInfo$prevRotationYaw$v1_8(Entity var1) {
      return this.lunar$freelookYaw(var1.prevRotationYaw);
   }

   @Annotation2(max = 0)
   @Redirect(method = "cacheActiveRenderInfo$v1_7", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;rotationYaw:F"))
   private float lunar$cacheActiveRenderInfo$rotationYaw$v1_7(EntityLivingBase var1) {
      return this.lunar$freelookYaw(var1.rotationYaw);
   }

   @Annotation2(max = 0)
   @Redirect(method = "cacheActiveRenderInfo$v1_7", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;prevRotationYaw:F"))
   private float lunar$cacheActiveRenderInfo$prevRotationYaw$v1_7(EntityLivingBase var1) {
      return this.lunar$freelookYaw(var1.prevRotationYaw);
   }

   @Annotation2(min = 1)
   @Redirect(method = "cacheActiveRenderInfo$v1_8", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationPitch:F"))
   private float lunar$cacheActiveRenderInfo$rotationPitch$v1_8(Entity var1) {
      return this.lunar$freelookPitch(var1.rotationPitch);
   }

   @Annotation2(min = 1)
   @Redirect(method = "cacheActiveRenderInfo$v1_8", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationPitch:F"))
   private float lunar$cacheActiveRenderInfo$prevRotationPitch$v1_8(Entity var1) {
      return this.lunar$freelookPitch(var1.prevRotationPitch);
   }

   @Annotation2(max = 0)
   @Redirect(method = "cacheActiveRenderInfo$v1_7", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;rotationPitch:F"))
   private float lunar$cacheActiveRenderInfo$rotationPitch$v1_7(EntityLivingBase var1) {
      return this.lunar$freelookPitch(var1.rotationPitch);
   }

   @Annotation2(max = 0)
   @Redirect(method = "cacheActiveRenderInfo$v1_7", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;prevRotationPitch:F"))
   private float lunar$cacheActiveRenderInfo$prevRotationPitch$v1_7(EntityLivingBase var1) {
      return this.lunar$freelookPitch(var1.prevRotationPitch);
   }

   @Unique
   private float lunar$freelookYaw(float var1) {
      Freelook var2 = ThreadModuleDump63.method4().method40().method31();
      return var2.isEnabled() && var2.isActive() ? var2.getRotationYaw() : var1;
   }

   @Unique
   private float lunar$freelookPitch(float var1) {
      Freelook var2 = ThreadModuleDump63.method4().method40().method31();
      return var2.isEnabled() && var2.isActive() ? var2.getRotationPitch() : var1;
   }

   @Annotation2(max = 0)
   @Override
   public void bridge$setRenderOutlines(boolean var1) {
      this.lunar$renderOutlines = var1;
   }

   @Annotation2(max = 0)
   @Inject(
      method = "doRenderEntity$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_7;doRender(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   @Dynamic
   private void lunar$setRenderOutline$v1_7(
      Entity var1,
      double var2,
      double var4,
      double var6,
      float var8,
      float var9,
      boolean var10,
      CallbackInfoReturnable<Boolean> var11,
      @Local Render_v1_7 var12
   ) {
      if (var12 instanceof RendererLivingEntity var13) {
         ((SExtension)var13).bridge$setRenderOutlines(this.lunar$renderOutlines);
      }
   }

   @Annotation2(1)
   @Inject(
      method = "doRenderEntity$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_8;doRender(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   @Dynamic
   private void setLunar$renderOutlines$v1_8(
      Entity var1, double var2, double var4, double var6, float var8, float var9, boolean var10, CallbackInfoReturnable<Boolean> var11, @Local Render var12
   ) {
      if (var12 instanceof RendererLivingEntity var13) {
         ((SExtension)var13).bridge$setRenderOutlines(this.renderOutlines);
      }
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "doRenderEntity$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_7;doRenderShadowAndFire(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   @Dynamic
   private boolean lunar$dontRenderShadowIfOutline$v1_7(Render_v1_7 var1, Entity var2, double var3, double var5, double var7, float var9, float var10) {
      return !this.lunar$renderOutlines;
   }

   @Annotation2(1)
   @WrapWithCondition(
      method = "doRenderEntity$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_8;doRenderShadowAndFire(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   private boolean lunar$dontRenderShadowIfOutline$v1_8(Render var1, Entity var2, double var3, double var5, double var7, float var9, float var10) {
      return !this.renderOutlines;
   }

   @Annotation2(max = 1)
   @Inject(method = "doRenderEntity$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$onEntityRenderPre$v1_7(
      Entity var1, double var2, double var4, double var6, float var8, float var9, boolean var10, CallbackInfoReturnable<Boolean> var11
   ) {
      EntityRenderBaseEvent.EntityRenderEvent var12 = ClientEventBus.method29().method12(EntityRenderBaseEvent.EntityRenderEvent.class, () -> new EntityRenderBaseEvent.EntityRenderEvent((BridgeExtension)var1));
      if (var12 != null && var12.isCancelled()) {
         var11.setReturnValue(false);
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "renderEntity$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$onEntityRenderPre$v1_12(Entity var1, double var2, double var4, double var6, float var8, float var9, boolean var10, CallbackInfo var11) {
      EntityRenderBaseEvent.EntityRenderEvent var12 = ClientEventBus.method29().method12(EntityRenderBaseEvent.EntityRenderEvent.class, () -> new EntityRenderBaseEvent.EntityRenderEvent((BridgeExtension)var1));
      if (var12 != null && var12.isCancelled()) {
         var11.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "getEntityRenderObject$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$getEntityClassRenderObject$v1_8(Entity var1, CallbackInfoReturnable<Render> var2) {
      if (var1 instanceof Click11 var3) {
         Click4 var4 = var3.lunar$getCache();
         if (var1 instanceof AbstractClientPlayer var5) {
            Click4Impl var6 = (Click4Impl)var4;
            String var7 = var5.getSkinType();
            if (var7 == null) {
               var6.method2(this.playerRenderer);
            } else if (!var7.equals(var6.skinType)) {
               var6.skinType = var7;
               RenderPlayer var8 = this.skinMap.get(var7);
               if (var8 == null) {
                  var8 = this.playerRenderer;
               }

               var6.method2(var8);
            }

            var2.setReturnValue((Render)var6.method1());
         } else {
            Render var9 = var4.method1();
            if (var9 == null) {
               var9 = var4.method2(this.getEntityClassRenderObject((Class<? extends Entity>)var1.getClass()));
            }

            var2.setReturnValue(var9);
         }
      }
   }
}
