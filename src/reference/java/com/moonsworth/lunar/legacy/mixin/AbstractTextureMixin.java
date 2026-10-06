package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.TextureFormat;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.pipeline.ByteBufferCache;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractTexture.class)
public abstract class AbstractTextureMixin implements Bridge8Extension3 {
   public AbstractTextureMixin() {
   }

   @Shadow
   public int getGlTextureId() {
      throw new AssertionError();
   }

   @Shadow
   public abstract void setBlurMipmap(boolean flag1, boolean flag2);

   @Override
   public void bridge$allocate(boolean flag1, boolean flag2, int number3, int number4, TextureFormat bridgetype_115, boolean flag6, boolean flag7) {
      if (bridgetype_115 != TextureFormat.RGBA8) {
         throw new UnsupportedOperationException("Added support for more diverse texture formats");
      }

      TextureUtil.bindTexture(this.getGlTextureId());
      GL11.glTexImage2D(3553, 0, 32856, number3, number4, 0, 6408, 5121, (ByteBuffer)null);
      this.bridge$setFilter(flag1, flag2);
   }

   @Override
   public void bridge$upload(int number1, int number2, int number3, int number4, int[] items5) {
      ByteBuffer buffer6 = ByteBufferCache.getBuffer(items5.length * 4);
      buffer6.asIntBuffer().put(items5);
      buffer6.limit(items5.length * 4);
      this.lunar$upload(number1, number2, number3, number4, buffer6, 6408, 0, 0, number3 * 4);
   }

   @Override
   public void bridge$uploadBgra(int number1, int number2, int number3, int number4, ByteBuffer buffer5, int number6, int number7, int number8) {
      this.lunar$upload(number1, number2, number3, number4, buffer5.duplicate(), 32993, number6, number7, number8);
   }

   @Unique
   private void lunar$upload(int number1, int number2, int number3, int number4, ByteBuffer buffer5, int number6, int number7, int number8, int number9) {
      int number10 = GL11.glGetInteger(32873);
      int number11 = GL11.glGetInteger(3315);
      int number12 = GL11.glGetInteger(3316);
      int number13 = GL11.glGetInteger(3314);
      int number14 = GL11.glGetInteger(3317);

      try {
         GL11.glPixelStorei(3315, number8);
         GL11.glPixelStorei(3316, number7);
         GL11.glPixelStorei(3314, number9 / 4);
         GL11.glPixelStorei(3317, 4);
         TextureUtil.bindTexture(this.getGlTextureId());
         GL11.glTexSubImage2D(3553, 0, number1, number2, number3, number4, number6, 5121, buffer5);
      } finally {
         GL11.glPixelStorei(3315, number11);
         GL11.glPixelStorei(3316, number12);
         GL11.glPixelStorei(3314, number13);
         GL11.glPixelStorei(3317, number14);
         TextureUtil.bindTexture(number10);
      }
   }

   @Override
   public void bridge$setFilter(boolean flag1, boolean flag2) {
      if (Ref.MC_VERSION >= 1) {
         TextureUtil.bindTexture(this.getGlTextureId());
         this.setBlurMipmap(flag1, flag2);
      } else {
         GL11.glBindTexture(3553, this.getGlTextureId());
         int number3;
         short number4;
         if (flag1) {
            number3 = flag2 ? 9987 : 9729;
            number4 = 9729;
         } else {
            number3 = flag2 ? 9986 : 9728;
            number4 = 9728;
         }

         GL11.glTexParameteri(3553, 10241, number3);
         GL11.glTexParameteri(3553, 10240, number4);
      }
   }
}
