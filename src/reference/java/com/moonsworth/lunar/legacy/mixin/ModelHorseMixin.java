package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelHorse.class)
public class ModelHorseMixin {
   @Final
   @Shadow
   public ModelRenderer head;
   @Final
   @Shadow
   public ModelRenderer upperMouth;
   @Final
   @Shadow
   public ModelRenderer lowerMouth;
   @Final
   @Shadow
   public ModelRenderer horseLeftEar;
   @Final
   @Shadow
   public ModelRenderer horseRightEar;
   @Final
   @Shadow
   public ModelRenderer muleLeftEar;
   @Final
   @Shadow
   public ModelRenderer muleRightEar;
   @Final
   @Shadow
   public ModelRenderer neck;
   @Final
   @Shadow
   public ModelRenderer horseFaceRopes;
   @Final
   @Shadow
   public ModelRenderer mane;
   @Final
   @Shadow
   public ModelRenderer horseLeftRein;
   @Final
   @Shadow
   public ModelRenderer horseRightRein;
   @Final
   @Shadow
   public ModelRenderer horseLeftFaceMetal;
   @Final
   @Shadow
   public ModelRenderer horseRightFaceMetal;

   @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelRenderer;render(F)V"))
   private void lunar$onRender(ModelRenderer var1, float var2, Operation<Void> var3, Entity var4) {
      if (var4 != ((EntityPlayerSP)ThreadModuleDump63.method3().bridge$getPlayer()).ridingEntity
         || (ThreadModuleDump63.MC_VERSION != 5 || !(var4 instanceof AbstractHorse)) && !(var4 instanceof EntityHorse)
         || !ThreadModuleDump63.method4().method40().method84().method26()
         || var1 != this.head
            && var1 != this.upperMouth
            && var1 != this.lowerMouth
            && var1 != this.horseLeftEar
            && var1 != this.horseRightEar
            && var1 != this.muleLeftEar
            && var1 != this.muleRightEar
            && var1 != this.neck
            && var1 != this.horseFaceRopes
            && var1 != this.mane
            && var1 != this.horseLeftRein
            && var1 != this.horseRightRein
            && var1 != this.horseLeftFaceMetal
            && var1 != this.horseRightFaceMetal) {
         var3.call(new Object[]{var1, var2});
      }
   }

   @Annotation2(0)
   @Inject(method = "render", at = @At("HEAD"), cancellable = true)
   private void lunar$skipHorseRendering(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7, CallbackInfo var8) {
      if (var1 instanceof EntityHorse && ((EntityHorse)var1).getGrowingAge() < 0) {
         var8.cancel();
      }
   }
}
