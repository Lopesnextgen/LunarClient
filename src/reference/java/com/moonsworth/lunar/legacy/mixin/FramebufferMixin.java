package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.Bridge8Handler;
import com.moonsworth.lunar.bridge.Bridge8_7;
import com.moonsworth.lunar.bridge.Bridge8Handler.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.legacy.wrapper.FramebufferImpl;
import com.moonsworth.lunar.legacy.wrapper.ExternalTextureFramebufferAdapter;
import com.moonsworth.lunar.legacy.wrapper.util.OpenALNative2;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Framebuffer.class)
public abstract class FramebufferMixin implements Bridge3_24 {
   @Shadow
   public int framebufferTextureWidth;
   @Shadow
   public int framebufferTextureHeight;
   @Shadow
   public int framebufferWidth;
   @Shadow
   public int framebufferHeight;
   @Shadow
   public int framebufferTexture;
   @Shadow
   public float[] framebufferColor;
   @Shadow
   public boolean useDepth;
   @Shadow
   public int depthBuffer;
   @Shadow
   public int framebufferObject;

   @Shadow
   public abstract void unbindFramebuffer();

   @Shadow
   public abstract void bindFramebuffer(boolean var1);

   @Shadow
   public abstract void framebufferRender(int var1, int var2);

   @Shadow
   public abstract void deleteFramebuffer();

   @Shadow
   public abstract void createBindFramebuffer(int var1, int var2);

   @Shadow
   public abstract void framebufferClear();

   @Shadow
   public abstract void framebufferRenderExt(int var1, int var2, boolean var3);

   @Shadow
   public abstract void bindFramebufferTexture();

   @Shadow
   public abstract void unbindFramebufferTexture();

   public int bridge$framebufferWidth() {
      return this.framebufferWidth;
   }

   public int bridge$framebufferHeight() {
      return this.framebufferHeight;
   }

   public int bridge$framebufferTextureWidth() {
      return this.framebufferTextureWidth;
   }

   public int bridge$framebufferTextureHeight() {
      return this.framebufferTextureHeight;
   }

   public int bridge$getFramebufferTexture() {
      return this.framebufferTexture;
   }

   public void bridge$unbindFrameBuffer() {
      this.unbindFramebuffer();
   }

   public void bridge$framebufferClear(boolean var1) {
      if (Bridge.method5().map(var0 -> var0.getConfig().hasFastRender()).orElse(false) && var1) {
         int var2 = this == ThreadModuleDump63.method3().bridge$getMainRenderTarget() ? 0 : this.framebufferObject;
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            OpenGlHelper.glBindFramebuffer(36009, var2);
            GlStateManager.clearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
            short var3 = 16384;
            if (this.useDepth) {
               GlStateManager.clearDepth(1.0);
               var3 |= 256;
            }

            GlStateManager.clear(var3);
            OpenGlHelper.glBindFramebuffer(36009, 0);
         } else {
            OpenGlHelper.func_153171_g$v1_7(36009, var2);
            GL11.glClearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
            short var4 = 16384;
            if (this.useDepth) {
               GL11.glClearDepth(1.0);
               var4 |= 256;
            }

            GL11.glClear(var4);
            OpenGlHelper.func_153171_g$v1_7(36009, 0);
         }
      } else {
         this.bridge$framebufferClear();
      }
   }

   public void bridge$delete() {
      Framebuffer var1 = (Framebuffer)this;
      if (var1 instanceof ExternalTextureFramebufferAdapter var2) {
         var2.method2();
      }

      this.deleteFramebuffer();
   }

   public void bridge$setClearColor(float var1, float var2, float var3, float var4) {
      this.framebufferColor[0] = var1;
      this.framebufferColor[1] = var2;
      this.framebufferColor[2] = var3;
      this.framebufferColor[3] = var4;
   }

   public void bridge$blitToScreen(int var1, int var2, int var3, int var4, boolean var5) {
      GL30.glBindFramebuffer(36008, this.framebufferObject);
      GL30.glBlitFramebuffer(0, 0, this.framebufferWidth, this.framebufferHeight, var1, var2, var3, var4, 16384, var5 ? 9729 : 9728);
      GL30.glBindFramebuffer(36008, 0);
   }

   public void bridge$blitToRenderTarget(Bridge3_24 var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10) {
      GL30.glBindFramebuffer(36008, this.framebufferObject);
      GL30.glBindFramebuffer(36009, ((Framebuffer)var1).framebufferObject);
      GL30.glBlitFramebuffer(var2, var3, var4, var5, var6, var7, var8, var9, 16384, var10 ? 9729 : 9728);
      GL30.glBindFramebuffer(36160, 0);
   }

   public Bridge8_7 bridge$getColorTexture(boolean var1) {
      return (Bridge8_7)(!var1 && !OpenGlHelper.isFramebufferEnabled() ? new Bridge8Handler(Type.COLOR) : Bridge8Extension2.create(this.framebufferTexture));
   }

   public Bridge8Extension bridge$getDepthTexture() {
      if (!(this instanceof FramebufferImpl)) {
         throw new IllegalStateException("Framebuffer does not have a depth texture");
      } else {
         return Bridge8Extension2.create(((FramebufferImpl)this).method1());
      }
   }

   public void bridge$frameBufferRender(int var1, int var2) {
      this.framebufferRender(var1, var2);
   }

   public void bridge$frameBufferRender(int var1, int var2, boolean var3) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.framebufferRenderExt(var1, var2, var3);
      } else if (OpenGlHelper.isFramebufferEnabled()) {
         GL11.glColorMask(true, true, true, false);
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glMatrixMode(5889);
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0, var1, var2, 0.0, 1000.0, 3000.0);
         GL11.glMatrixMode(5888);
         GL11.glLoadIdentity();
         GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
         GL11.glViewport(0, 0, var1, var2);
         GL11.glEnable(3553);
         GL11.glDisable(2896);
         GL11.glDisable(3008);
         if (var3) {
            GL11.glDisable(3042);
            GL11.glEnable(2903);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindFramebufferTexture();
         float var4 = (float)this.framebufferWidth / this.framebufferTextureWidth;
         float var5 = (float)this.framebufferHeight / this.framebufferTextureHeight;
         Tessellator var6 = Tessellator.theMinecraft;
         var6.startDrawingQuads$v1_7();
         var6.setColorOpaque_I$v1_7(-1);
         var6.addVertexWithUV$v1_7(0.0, var2, 0.0, 0.0, 0.0);
         var6.addVertexWithUV$v1_7(var1, var2, 0.0, var4, 0.0);
         var6.addVertexWithUV$v1_7(var1, 0.0, 0.0, var4, var5);
         var6.addVertexWithUV$v1_7(0.0, 0.0, 0.0, 0.0, var5);
         var6.draw();
         this.unbindFramebufferTexture();
         GL11.glDepthMask(true);
         GL11.glColorMask(true, true, true, true);
      }
   }

   public void bridge$createBindFramebuffer(int var1, int var2) {
      this.createBindFramebuffer(var1, var2);
   }

   public void bridge$framebufferClear() {
      this.framebufferClear();
   }

   public void bridge$present() {
      this.unbindFramebuffer();
      this.bridge$frameBufferRender(this.framebufferWidth, this.framebufferHeight, true);
      this.bindFramebuffer(false);
   }

   @Redirect(
      method = {
            "createBindFramebuffer",
            "deleteFramebuffer",
            "createFramebuffer",
            "setFramebufferFilter",
            "bindFramebufferTexture",
            "unbindFramebufferTexture",
            "bindFramebuffer",
            "unbindFramebuffer",
            "framebufferRenderExt"
      },
      at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/OpenGlHelper.isFramebufferEnabled ()Z")
   )
   private boolean impl$offscreenFramebuffer() {
      return !(this instanceof ExternalTextureFramebufferAdapter) && !OpenALNative2.field1 ? OpenGlHelper.isFramebufferEnabled() : OpenGlHelper.framebufferSupported;
   }

   @Redirect(method = "deleteFramebuffer", at = @At(value = "FIELD", target = "net/minecraft/client/shader/Framebuffer.framebufferTexture : I", opcode = 180))
   private int impl$doNotDeleteTexture(Framebuffer var1) {
      Framebuffer var2 = (Framebuffer)this;
      return var2 instanceof ExternalTextureFramebufferAdapter var3 && !var3.method1() ? -1 : this.framebufferTexture;
   }

   @Redirect(method = "createFramebuffer", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/texture/TextureUtil.glGenTextures ()I"))
   private int impl$doNotRecreateTexture() {
      return this.framebufferTexture > -1 && this instanceof ExternalTextureFramebufferAdapter ? this.framebufferTexture : TextureUtil.glGenTextures();
   }
}
