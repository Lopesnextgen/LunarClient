package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelLlama;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Annotation2(5)
@Mixin(ModelLlama.class)
public class ModelLlamaMixin {
   @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelRenderer;render(F)V"))
   private void lunar$onRender(ModelRenderer var1, float var2, Operation<Void> var3, Entity var4) {
      Bridge5Extension_5 var5 = ThreadModuleDump63.method3().bridge$getPlayer();
      if (var1 != ((ModelQuadruped)this).head
         || var5 == null
         || var4 != ((EntityPlayerSP)var5).ridingEntity
         || !ThreadModuleDump63.method4().method40().method84().method27()) {
         var3.call(new Object[]{var1, var2});
      }
   }
}
