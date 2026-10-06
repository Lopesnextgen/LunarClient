package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_13;
import com.moonsworth.lunar.bridge.ShaderManagerBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8Extension2;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.client.shader.ShaderUniform;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShaderManager.class)
public abstract class ShaderManagerMixin implements ShaderManagerBridge {
   @Final
   @Shadow
   public int program;

   public ShaderManagerMixin() {
   }

   @Shadow
   public abstract ShaderUniform getShaderUniform(String text1);

   @Shadow
   public abstract void addSamplerTexture(String text1, Object obj2);

   @Shadow
   public abstract void useShader();

   @Shadow
   public abstract void endShader();

   public Bridge5_13 bridge$getShaderUniform(String text1) {
      return (Bridge5_13)this.getShaderUniform(text1);
   }

   public void bridge$bindSampler(String text1, Bridge8Extension bridge8) {
      Bridge8Extension2 bridge8extension23 = (Bridge8Extension2)bridge8;
      this.addSamplerTexture(text1, bridge8extension23.lunar$getHandle());
   }

   public int bridge$getProgram() {
      return this.program;
   }

   public void bridge$apply() {
      this.useShader();
   }

   public void bridge$clear() {
      this.endShader();
   }
}
