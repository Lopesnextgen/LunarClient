package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.framework.Ref;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import lombok.Generated;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;

public class FramebufferImpl extends Framebuffer {
   private int field1;

   public FramebufferImpl(int number1, int number2, int number3) {
      super(number1, number2, true);
      this.field1 = number3;
      this.createBindFramebuffer(number1, number2);
   }

   public void createFramebuffer(int number1, int number2) {
      if (this.field1 <= 0) {
         this.field1 = TextureUtil.glGenTextures();
      }

      this.framebufferWidth = number1;
      this.framebufferHeight = number2;
      this.framebufferTextureWidth = number1;
      this.framebufferTextureHeight = number2;
      if (!OpenGlHelper.isFramebufferEnabled()) {
         this.framebufferClear();
      } else {
         this.framebufferTexture = TextureUtil.glGenTextures();
         if (Ref.MC_VERSION == 0) {
            this.framebufferObject = OpenGlHelper.func_153165_e$v1_7();
            OpenGlHelper.func_153171_g$v1_7(OpenGlHelper.GL_FRAMEBUFFER, this.framebufferObject);
            GL11.glBindTexture(3553, this.framebufferTexture);
         } else {
            this.framebufferObject = OpenGlHelper.glGenFramebuffers();
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, this.framebufferObject);
            GlStateManager.bindTexture(this.framebufferTexture);
         }

         GL11.glTexImage2D(3553, 0, 32856, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, 6408, 5121, (IntBuffer)null);
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         if (Ref.MC_VERSION == 0) {
            OpenGlHelper.func_153188_a$v1_7(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, 3553, this.framebufferTexture, 0);
         } else {
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, 3553, this.framebufferTexture, 0);
         }

         if (Ref.MC_VERSION == 0) {
            GL11.glBindTexture(3553, this.field1);
         } else {
            GlStateManager.bindTexture(this.field1);
         }

         GL11.glTexImage2D(3553, 0, 33190, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, 6402, 5125, (ByteBuffer)null);
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 34892, 0);
         GL11.glTexParameteri(3553, 34891, 32841);
         GL11.glTexParameterf(3553, 34893, 515.0F);
         if (Ref.MC_VERSION == 0) {
            OpenGlHelper.func_153188_a$v1_7(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, 3553, this.field1, 0);
         } else {
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, 3553, this.field1, 0);
         }

         GL11.glDrawBuffer(OpenGlHelper.GL_COLOR_ATTACHMENT0);
         GL11.glReadBuffer(OpenGlHelper.GL_COLOR_ATTACHMENT0);
         this.framebufferClear();
         if (Ref.MC_VERSION == 0) {
            OpenGlHelper.func_153171_g$v1_7(OpenGlHelper.GL_FRAMEBUFFER, 0);
            GL11.glBindTexture(3553, 0);
         } else {
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, 0);
            GlStateManager.bindTexture(0);
         }
      }
   }

   public void deleteFramebuffer() {
      super.deleteFramebuffer();
      if (this.field1 > 0) {
         TextureUtil.deleteTexture(this.field1);
         this.field1 = 0;
      }
   }

   public void framebufferClear() {
      this.bindFramebuffer(true);
      if (Ref.MC_VERSION == 0) {
         GL11.glClearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
      } else {
         GlStateManager.clearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
      }

      short number1 = 16384;
      if (this.field1 > 0) {
         GL11.glClearDepth(1.0);
         number1 |= 256;
      }

      GL11.glClear(number1);
      this.unbindFramebuffer();
   }

   public void createBindFramebuffer(int number1, int number2) {
      if (this.field1 > 0) {
         super.createBindFramebuffer(number1, number2);
      }
   }

   @Generated
   public int method1() {
      return this.field1;
   }
}
