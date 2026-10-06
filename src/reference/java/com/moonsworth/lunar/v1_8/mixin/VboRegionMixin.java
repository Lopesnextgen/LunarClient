package com.moonsworth.lunar.v1_8.mixin;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.VboRenderList;
import net.optifine.Config;
import net.optifine.render.VboRegion;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import org.lwjgl.opengl.APPLEVertexArrayObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VboRegion.class)
public class VboRegionMixin {
   @Unique
   private int lunar$vao;
   @Unique
   private boolean lunar$deleted;

   @Inject(method = "bufferData", at = @At("HEAD"), cancellable = true)
   private void lunar$bufferData(CallbackInfo var1) {
      if (this.lunar$deleted) {
         Slayer.method3("Stopped upload to deleted buffer");
         var1.cancel();
      }
   }

   @Inject(method = "<init>(Lnet/minecraft/util/EnumWorldBlockLayer;)V", at = @At(value = "INVOKE", target = "Lnet/optifine/render/VboRegion;unbindBuffer()V"))
   private void lunar$createdBuffer(CallbackInfo var1) {
      this.lunar$setupVAO();
   }

   private void lunar$setupVAO() {
      if (lunar$hasVertexArray()) {
         if (this.lunar$vao != 0) {
            lunar$deleteVertexArray(this.lunar$vao);
         }

         this.lunar$vao = lunar$createVertexArray();
         lunar$bindVertexArray(this.lunar$vao);
         if (Config.isShaders()) {
            GL11.glEnableClientState(32884);
            GL11.glEnableClientState(32886);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glEnableClientState(32888);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            GL11.glEnableClientState(32888);
            GL11.glEnableClientState(32885);
            GL20.glEnableVertexAttribArray(Shaders.midTexCoordAttrib);
            GL20.glEnableVertexAttribArray(Shaders.tangentAttrib);
            GL20.glEnableVertexAttribArray(Shaders.entityAttrib);
            ShadersRender.setupArrayPointersVbo();
         } else {
            GL11.glEnableClientState(32884);
            GlStateManager.glVertexPointer(3, 5126, 28, 0);
            GL11.glEnableClientState(32886);
            GlStateManager.glColorPointer(4, 5121, 28, 12);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.glTexCoordPointer(2, 5122, 28, 24);
            GL11.glEnableClientState(32888);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.glTexCoordPointer(2, 5126, 28, 16);
            GL11.glEnableClientState(32888);
         }

         lunar$bindVertexArray(0);
      }
   }

   @Inject(method = "copyVboData", at = @At("HEAD"), cancellable = true)
   private void lunar$copyVboData(CallbackInfo var1) {
      if (this.lunar$deleted) {
         Slayer.method3("Tried to copy deleted buffer");
         var1.cancel();
      }
   }

   @Inject(method = "expandVbo", at = @At("HEAD"), cancellable = true)
   private void lunar$expandVboBufferData(CallbackInfo var1) {
      if (this.lunar$deleted) {
         Slayer.method3("Tried to expand deleted buffer");
         var1.cancel();
      }
   }

   @Inject(method = "expandVbo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OpenGlHelper;glBufferData(IJI)V"))
   private void lunar$newBuffer(CallbackInfo var1) {
      this.lunar$setupVAO();
   }

   @Redirect(method = "finishDraw", at = @At(value = "INVOKE", target = "Lnet/optifine/render/VboRegion;bindBuffer()V"))
   private void lunar$bindBuffer(VboRegion var1) {
      if (this.lunar$vao != 0) {
         lunar$bindVertexArray(this.lunar$vao);
      } else {
         var1.bindBuffer();
      }
   }

   @Redirect(method = "finishDraw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/VboRenderList;setupArrayPointers()V"))
   private void lunar$ignoreArrayPointers(VboRenderList var1) {
      if (this.lunar$vao == 0) {
         var1.setupArrayPointers();
      }
   }

   @Inject(method = "finishDraw", at = @At("TAIL"))
   private void lunar$unbind(VboRenderList var1, CallbackInfo var2) {
      if (this.lunar$vao != 0) {
         lunar$bindVertexArray(0);
      }
   }

   @Inject(method = "deleteGlBuffers", at = @At("HEAD"))
   private void lunar$free(CallbackInfo var1) {
      if (this.lunar$vao != 0) {
         lunar$deleteVertexArray(this.lunar$vao);
         this.lunar$vao = 0;
      }

      this.lunar$deleted = true;
   }

   @Unique
   private static boolean lunar$hasVertexArray() {
      return ThreadModuleDumpType2.isMacos() ? GLContext.getCapabilities().GL_APPLE_vertex_array_object : GLContext.getCapabilities().OpenGL30;
   }

   @Unique
   private static int lunar$createVertexArray() {
      return ThreadModuleDumpType2.isMacos() ? APPLEVertexArrayObject.glGenVertexArraysAPPLE() : GL30.glGenVertexArrays();
   }

   @Unique
   private static void lunar$bindVertexArray(int var0) {
      if (ThreadModuleDumpType2.isMacos()) {
         APPLEVertexArrayObject.glBindVertexArrayAPPLE(var0);
      } else {
         GL30.glBindVertexArray(var0);
      }
   }

   @Unique
   private static void lunar$deleteVertexArray(int var0) {
      if (ThreadModuleDumpType2.isMacos()) {
         APPLEVertexArrayObject.glDeleteVertexArraysAPPLE(var0);
      } else {
         GL30.glDeleteVertexArrays(var0);
      }
   }
}
