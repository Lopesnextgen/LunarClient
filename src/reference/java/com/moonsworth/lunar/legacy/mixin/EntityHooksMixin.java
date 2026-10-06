package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.glow.GlowModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Highlight3Iterator20;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl6;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.render.lighting.Lighting;
import com.moonsworth.lunar.client.mod.render.staffxray.StaffXray;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump88;
import com.moonsworth.lunar.client.util.click.Click11;
import com.moonsworth.lunar.client.util.click.Click4;
import com.moonsworth.lunar.client.util.click.Click4Impl;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityHooksMixin implements Click11 {
   @Shadow
   public World world;
   @Unique
   public Click4 lunar$cache;

   @Inject(method = "setSneaking(Z)V", at = @At("HEAD"))
   private void lunar$stopAnimatingWhenSneaking(boolean var1, CallbackInfo var2) {
      if (var1 && this instanceof EntityPlayer) {
         ThreadModuleDump63.method4().method45().method10((Bridge6_10)this, false);
      }
   }

   @Inject(method = "isInRangeToRender3d", at = @At("HEAD"), cancellable = true)
   private void lunar$shouldRender(double var1, double var3, double var5, CallbackInfoReturnable<Boolean> var7) {
      if (!Client.method109().method41().method7().method2((BridgeExtension)this)) {
         var7.setReturnValue(false);
      }

      if (this instanceof EntityArrow && ((EntityArrow)this).inGround && !ThreadModuleDump63.method4().method40().method84().method46()) {
         var7.setReturnValue(false);
      }
   }

   @Inject(method = "isInRangeToRender3d", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindRenderEntities(double var1, double var3, double var5, CallbackInfoReturnable<Boolean> var7) {
      Rewind var8 = ThreadModuleDump63.method4().method40().method85();
      if (var8.method19()) {
         RewindHandlers3Impl6 var9 = var8.method35().method52();
         if (var9.method14().get()) {
            if (this instanceof EntityPlayer) {
               var7.setReturnValue(var9.method21().get());
            } else {
               var7.setReturnValue(var9.method19().get());
            }
         }
      }
   }

   @Annotation2(min = 5)
   @ModifyReturnValue(method = "isGlowing$v1_12", at = @At("RETURN"))
   private boolean apollo$modifyGlowValue(boolean var1) {
      return ThreadModuleDump63.method4()
            .method84()
            .<ApolloModuleHandler>method3(GlowModule.class)
            .filter(var1x -> ((Highlight3Iterator20)var1x).method3((BridgeExtension)this))
            .isPresent()
         ? true
         : var1;
   }

   @WrapOperation(method = "onEntityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;handleWaterMovement()Z"))
   private boolean lunar$rewindSkipUpdate$water(Entity var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)
         ? false
         : (Boolean)var2.call(new Object[]{var1});
   }

   @Annotation2(0)
   @Inject(method = "func_145781_i$v1_7", at = @At("HEAD"))
   private void lunar$dataWatcherUpdated$v1_7(int var1, CallbackInfo var2) {
      if (var1 == 10 && this instanceof ThreadModuleDump88 var3) {
         var3.lunar$onNameTagUpdate();
      }
   }

   @Annotation2(1)
   @Inject(method = "onDataWatcherUpdate$v1_8", at = @At("HEAD"))
   private void lunar$dataWatcherUpdated$v1_8(int var1, CallbackInfo var2) {
      if (var1 == 2 && this instanceof ThreadModuleDump88 var3) {
         var3.lunar$onNameTagUpdate();
      }
   }

   @Annotation2(5)
   @Inject(method = "notifyDataManagerChange$v1_12", at = @At("HEAD"))
   private void lunar$dataWatcherUpdated$v1_12(DataParameter<?> var1, CallbackInfo var2) {
      if (Entity.CUSTOM_NAME$v1_12.equals(var1) && this instanceof ThreadModuleDump88 var3) {
         var3.lunar$onNameTagUpdate();
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "getBrightnessForRender$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$getBrightnessForRender$v1_7(float var1, CallbackInfoReturnable<Integer> var2) {
      Lighting var3 = ThreadModuleDump63.method4().method40().method56();
      if (var3.isFullBrightActive() || this.lunar$isXrayActive()) {
         var2.setReturnValue(Bridge.method8().method92());
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "getBrightnessForRender$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$getBrightnessForRender$v1_12(CallbackInfoReturnable<Integer> var1) {
      Lighting var2 = ThreadModuleDump63.method4().method40().method56();
      if (var2.isFullBrightActive() || this.lunar$isXrayActive()) {
         var1.setReturnValue(Bridge.method8().method92());
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "getBrightness$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$getBrightness$v1_7(float var1, CallbackInfoReturnable<Float> var2) {
      Lighting var3 = ThreadModuleDump63.method4().method40().method56();
      if (var3.isFullBrightActive() || this.lunar$isXrayActive()) {
         var2.setReturnValue(this.world.provider.lightBrightnessTable[15]);
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "getBrightness$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$getBrightness$v1_12(CallbackInfoReturnable<Float> var1) {
      Lighting var2 = ThreadModuleDump63.method4().method40().method56();
      if (var2.isFullBrightActive() || this.lunar$isXrayActive()) {
         var1.setReturnValue(this.world.provider.lightBrightnessTable[15]);
      }
   }

   @Unique
   private boolean lunar$isXrayActive() {
      StaffXray var1 = ThreadModuleDump63.method4().method44().method10();
      return var1.isEnabled() && var1.method16();
   }

   @Override
   public Click4 lunar$getCache() {
      if (this.lunar$cache == null) {
         if (this instanceof EntityPlayer) {
            this.lunar$cache = new Click4Impl();
         } else {
            this.lunar$cache = new Click4();
         }
      }

      return this.lunar$cache;
   }

   @Annotation2(min = 5)
   @Inject(method = "isGlowing$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$rewind$highlightEntity(CallbackInfoReturnable<Boolean> var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var1x -> var1x.method50().method14() == this)) {
         var1.setReturnValue(true);
      }
   }
}
