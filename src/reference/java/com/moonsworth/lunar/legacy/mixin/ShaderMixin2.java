package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Shader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Shader.class)
public class ShaderMixin2 {
   @ModifyVariable(
      method = "<init>(Lnet/minecraft/client/resources/IResourceManager;Ljava/lang/String;Lnet/minecraft/client/shader/Framebuffer;Lnet/minecraft/client/shader/Framebuffer;)V",
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 0
   )
   private static IResourceManager lunar$injectCustomResourceProvider(IResourceManager var0, @Local(argsOnly = true) String var1) {
      String var2 = "lunar-shaders/";
      if (var1.startsWith(var2)) {
         var1 = var1.substring(var2.length());
      }

      return !ShaderInjectRegistry.method13(var1) ? var0 : com.moonsworth.lunar.legacy.wrapper.IResourceManager.field1;
   }
}
