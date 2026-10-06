package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Shader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Shader.class)
public class ShaderCustomResourceMixin {
   public ShaderCustomResourceMixin() {
   }

   @ModifyVariable(
      method = "<init>(Lnet/minecraft/client/resources/IResourceManager;Ljava/lang/String;Lnet/minecraft/client/shader/Framebuffer;Lnet/minecraft/client/shader/Framebuffer;)V",
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 0
   )
   private static IResourceManager lunar$injectCustomResourceProvider(IResourceManager iresourcemanager0, @Local(argsOnly = true) String text1) {
      String text2 = "lunar-shaders/";
      if (text1.startsWith(text2)) {
         text1 = text1.substring(text2.length());
      }

      return (IResourceManager)(!ShaderInjectRegistry.method13(text1) ? iresourcemanager0 : com.moonsworth.lunar.legacy.wrapper.IResourceManager.field1);
   }
}
