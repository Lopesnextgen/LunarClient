package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.SExtension;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg_2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventLivingBase;
import com.moonsworth.lunar.client.mod.combat.hitcolor.HitColor;
import com.moonsworth.lunar.client.mod.combat.reachdisplay.ReachDisplay;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.render.nametag.Nametag;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.MixinCore2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render_v1_7;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(RendererLivingEntity.class)
public abstract class RendererLivingEntityGlowV1_7Mixin extends Render_v1_7 implements SExtension {
   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RendererLivingEntity;getColorMultiplier(Lnet/minecraft/entity/EntityLivingBase;FF)I"
      )
   )
   private int lunar$highlightHook(RendererLivingEntity var1, EntityLivingBase var2, float var3, float var4, Operation<Integer> var5) {
      if (var2.hurtTime <= 0 && var2.deathTime <= 0) {
         ReachDisplay var6 = ThreadModuleDump63.method4().method40().method39();
         if (var6.isEnabled() && var2 instanceof EntityPlayer var7 && var6.method4((Bridge6_10)var7)) {
            return var6.method13().method14(0.0F);
         }
      }

      return (Integer)var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Redirect(
      method = "doRender",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", ordinal = 0),
      slice = @Slice(
         from = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/entity/RendererLivingEntity;renderEquippedItems(Lnet/minecraft/entity/EntityLivingBase;F)V"
         )
      )
   )
   private void lunar$colorPlayerHit(float var1, float var2, float var3, float var4) {
      HitColor var5 = ThreadModuleDump63.method4().method40().method14();
      GL11.glColor4f(var5.method4(var1), var5.method5(var2), var5.method6(var3), var5.method7(var4));
   }

   @Redirect(
      method = "doRender",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", ordinal = 1),
      slice = @Slice(
         from = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/entity/RendererLivingEntity;renderEquippedItems(Lnet/minecraft/entity/EntityLivingBase;F)V"
         )
      )
   )
   private void colorArmorHit(float var1, float var2, float var3, float var4) {
      HitColor var5 = ThreadModuleDump63.method4().method40().method14();
      if (var5.isEnabled()) {
         if (var5.method15().get()) {
            GL11.glColor4f(var5.method4(var1), var5.method5(var2), var5.method6(var3), var5.method7(var4));
         } else {
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);
         }
      } else {
         GL11.glColor4f(var1, var2, var3, var4);
      }
   }

   @Inject(
      method = "doRender",
      at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.getBrightness(F)F"),
      slice = @Slice(
         from = @At(
            value = "INVOKE",
            target = "net/minecraft/client/renderer/entity/RendererLivingEntity.renderEquippedItems (Lnet/minecraft/entity/EntityLivingBase;F)V"
         )
      )
   )
   private void impl$preHurtRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      Pkg_2.field1 = true;
   }

   @Inject(
      method = "doRender",
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V",
         shift = Shift.AFTER,
         ordinal = 0
      ),
      slice = @Slice(
         from = @At(
            value = "INVOKE",
            target = "net/minecraft/client/renderer/entity/RendererLivingEntity.renderEquippedItems (Lnet/minecraft/entity/EntityLivingBase;F)V"
         )
      )
   )
   private void impl$postHurtRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      Pkg_2.field1 = false;
   }

   @Redirect(
      method = {"doRender", "renderLivingAt", "rotateCorpse"},
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glRotatef (FFFF)V", remap = false),
      require = 0
   )
   private void lunar$onRotatef(float var1, float var2, float var3, float var4) {
      GL11.glRotatef(var1, var2, var3, var4);
      if (MixinCore2.field1 != null) {
         MixinCore2.field1.method6(var1, var2, var3, var4);
      }
   }

   @Redirect(
      method = {"doRender", "renderLivingAt", "rotateCorpse"},
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glScalef (FFF)V", remap = false),
      require = 0
   )
   private void lunar$onScalef(float var1, float var2, float var3) {
      GL11.glScalef(var1, var2, var3);
      if (MixinCore2.field1 != null) {
         MixinCore2.field1.scale(var1, var2, var3);
      }
   }

   @Redirect(
      method = {"doRender", "renderLivingAt", "rotateCorpse"},
      at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glTranslatef (FFF)V", remap = false),
      require = 0
   )
   private void lunar$onTranslatef(float var1, float var2, float var3) {
      GL11.glTranslatef(var1, var2, var3);
      if (MixinCore2.field1 != null) {
         MixinCore2.field1.method7(var1, var2, var3);
      }
   }

   @Redirect(method = "canRenderName", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isGuiEnabled()Z"))
   private boolean lunar$canRenderNameHook() {
      if (Minecraft.isGuiEnabled()) {
         return true;
      }

      Rewind var1 = ThreadModuleDump63.method4().method40().method85();
      if (var1.method17(var0 -> !var0.method45().method15().isFixedToPlayer())) {
         return true;
      }

      Nametag var2 = ThreadModuleDump63.method4().method40().method51();
      return var2.isEnabled() && !var2.getHideNametagsInF1().get();
   }

   @Inject(
      method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RendererLivingEntity;preRenderCallback(Lnet/minecraft/entity/EntityLivingBase;F)V"
      )
   )
   private void lunar$changeEntityScale(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      EventLivingBase.EventEntityScaling var11 = ClientEventBus.method29().method12(EventLivingBase.EventEntityScaling.class, () -> new EventLivingBase.EventEntityScaling((BridgeExtension2_5)var1, 1.0F));
      if (var11 != null) {
         float var12 = var11.getScale();
         GL11.glScalef(var12, var12, var12);
         if (MixinCore2.field1 != null) {
            MixinCore2.field1.scale(var12, var12, var12);
         }
      }
   }
}
