package com.moonsworth.lunar.client.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.OpenGlHelperBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.Bridge8Handler;
import com.moonsworth.lunar.bridge.Bridge8_7;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import java.nio.ByteBuffer;
import java.util.function.Consumer;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Click7 {
   public static void method1(Bridge8_7 var0, int var1, int var2, int var3, int var4, TexturePixelFormat var5, Consumer<ByteBuffer> var6) {
      ByteBuffer var7 = MemoryUtil.memCalloc(var3 * var4 * 4);

      try {
         short var8;
         short var9;
         if (var5 == TexturePixelFormat.RGBA8) {
            var8 = 6408;
            var9 = 5121;
         } else {
            if (var5 != TexturePixelFormat.DEPTH32) {
               throw new RuntimeException("Unsupported readback format " + var5.name());
            }

            var8 = 6402;
            var9 = 5126;
         }

         int var10;
         if (var0 instanceof Bridge8Extension2 var11) {
            var10 = var11.lunar$getHandle();
         } else {
            var10 = 0;
         }

         boolean var20 = var0 instanceof Bridge8Handler;
         OpenGlHelperBridge.Type var12 = Bridge.method22().method27();
         switch (var12) {
            case BASE:
               int var22 = var8 == 6408 ? 36064 : 36096;
               int var24 = GL11.glGetInteger(36010);
               int var26 = var20 ? 0 : GL30.glGenFramebuffers();
               GL30.glBindFramebuffer(36008, var26);
               GL30.glFramebufferTexture2D(36008, var22, 3553, var10, 0);
               GL11.glReadPixels(var1, var2, var3, var4, var8, var9, var7);
               GL30.glBindFramebuffer(36008, var24);
               if (!var20) {
                  GL30.glDeleteFramebuffers(var26);
               }
               break;
            case ARB:
               int var21 = var8 == 6408 ? 36064 : 36096;
               int var23 = GL11.glGetInteger(36010);
               int var25 = GL11.glGetInteger(36006);
               int var16 = var20 ? 0 : ARBFramebufferObject.glGenFramebuffers();
               ARBFramebufferObject.glBindFramebuffer(36160, var16);
               ARBFramebufferObject.glFramebufferTexture2D(36160, var21, 3553, var10, 0);
               if (var8 == 6402 && !var20) {
                  GL11.glDrawBuffer(0);
                  GL11.glReadBuffer(0);
               }

               GL11.glReadPixels(var1, var2, var3, var4, var8, var9, var7);
               ARBFramebufferObject.glBindFramebuffer(36008, var23);
               ARBFramebufferObject.glBindFramebuffer(36009, var25);
               if (!var20) {
                  ARBFramebufferObject.glDeleteFramebuffers(var16);
               }
               break;
            case EXT:
               int var13 = var8 == 6408 ? 36064 : 36096;
               int var14 = GL11.glGetInteger(36006);
               int var15 = var20 ? 0 : EXTFramebufferObject.glGenFramebuffersEXT();
               EXTFramebufferObject.glBindFramebufferEXT(36160, var15);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, var13, 3553, var10, 0);
               if (var8 == 6402 && !var20) {
                  GL11.glDrawBuffer(0);
                  GL11.glReadBuffer(0);
               }

               GL11.glReadPixels(var1, var2, var3, var4, var8, var9, var7);
               EXTFramebufferObject.glBindFramebufferEXT(36160, var14);
               if (!var20) {
                  EXTFramebufferObject.glDeleteFramebuffersEXT(var15);
               }
         }

         var6.accept(var7);
      } finally {
         MemoryUtil.memFree(var7);
      }
   }
}
