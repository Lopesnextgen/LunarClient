package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ShaderBridge;
import com.moonsworth.lunar.bridge.ShaderManagerBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.ShaderRenderState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.client.shader.ShaderUniform;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Shader.class)
public abstract class ShaderMixin implements ShaderBridge, ShaderRenderState {
   @Shadow
   public ShaderManager manager;

   public ShaderMixin() {
   }

   @Shadow
   public abstract ShaderManager getShaderManager();

   public ShaderManagerBridge bridge$getShaderUniforms() {
      return (ShaderManagerBridge)this.getShaderManager();
   }

   @VersionGate(max = 0)
   @Redirect(method = "loadShader", at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glColorMask (ZZZZ)V"))
   private void lunar$writeAlpha(boolean flag1, boolean flag2, boolean flag3, boolean flag4) {
      GL11.glColorMask(true, true, true, true);
   }

   public void lunar$setupState() {
      OpenGlHelper.glUseProgram(this.manager.program);
      ShaderManager.currentProgram = this.manager.program;
      if (Ref.MC_VERSION >= 1) {
         for (int index1 = 0; index1 < this.manager.shaderSamplerLocations.size(); index1++) {
            if (this.manager.shaderSamplers.get(this.manager.samplerNames.get(index1)) != null) {
               int number2 = ShaderRenderState.method1(index1);
               GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + number2);
               GlStateManager.enableTexture2D();
               Object obj3 = this.manager.shaderSamplers.get(this.manager.samplerNames.get(index1));
               int number4 = -1;
               if (obj3 instanceof Framebuffer) {
                  number4 = ((Framebuffer)obj3).framebufferTexture;
               } else if (obj3 instanceof ITextureObject) {
                  number4 = ((ITextureObject)obj3).getGlTextureId();
               } else if (obj3 instanceof Integer) {
                  number4 = (Integer)obj3;
               }

               if (number4 != -1) {
                  GlStateManager.bindTexture(number4);
                  OpenGlHelper.glUniform1i((Integer)this.manager.shaderSamplerLocations.get(index1), number2);
               }
            }
         }

         for (ShaderUniform shaderuniform8 : this.manager.shaderUniforms) {
            shaderuniform8.upload();
         }
      } else {
         for (int index6 = 0; index6 < this.manager.shaderSamplerLocations$v1_7.size(); index6++) {
            if (this.manager.shaderSamplers$v1_7.get(this.manager.samplerNames$v1_7.get(index6)) != null) {
               int number9 = ShaderRenderState.method1(index6);
               GL13.glActiveTexture(33984 + number9);
               GL11.glEnable(3553);
               Object obj11 = this.manager.shaderSamplers$v1_7.get(this.manager.samplerNames$v1_7.get(index6));
               int number12 = -1;
               if (obj11 instanceof Framebuffer) {
                  number12 = ((Framebuffer)obj11).framebufferTexture;
               } else if (obj11 instanceof ITextureObject) {
                  number12 = ((ITextureObject)obj11).getGlTextureId();
               } else if (obj11 instanceof Integer) {
                  number12 = (Integer)obj11;
               }

               if (number12 != -1) {
                  GL11.glBindTexture(3553, number12);
                  OpenGlHelper.glUniform1i((Integer)this.manager.shaderSamplerLocations$v1_7.get(index6), number9);
               }
            }
         }

         for (Object obj10 : this.manager.shaderUniforms$v1_7) {
            ((ShaderUniform)obj10).upload();
         }
      }
   }

   public void lunar$clearState() {
      OpenGlHelper.glUseProgram(0);
      ShaderManager.currentProgram = -1;
   }
}
