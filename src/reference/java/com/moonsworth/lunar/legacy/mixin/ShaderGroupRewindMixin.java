package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShaderGroup.class)
public class ShaderGroupRewindMixin {
   public ShaderGroupRewindMixin() {
   }

   @WrapOperation(
      method = {"createBindFramebuffers", "resetProjectionMatrix"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/shader/ShaderGroup;mainFramebuffer:Lnet/minecraft/client/shader/Framebuffer;")
   )
   private Framebuffer lunar$rewindDimensions(ShaderGroup shadergroup1, Operation<Framebuffer> operation2) {
      Framebuffer framebuffer3 = (Framebuffer)operation2.call(new Object[]{shadergroup1});
      return framebuffer3 == Minecraft.getMinecraft().framebufferMc ? Minecraft.getMinecraft().getFramebuffer() : framebuffer3;
   }
}
