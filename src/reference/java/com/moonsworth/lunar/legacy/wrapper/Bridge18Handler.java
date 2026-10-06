package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.OpenGlHelperBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;

public class Bridge18Handler implements OpenGlHelperBridge {
   @Override
   public boolean method1() {
      return OpenGlHelper.isFramebufferEnabled();
   }

   @Override
   public boolean method2() {
      return OpenGlHelper.framebufferSupported;
   }

   @Override
   public boolean method3() {
      return OpenGlHelper.framebufferSupported;
   }

   @Override
   public void method4(int var1, float var2, float var3) {
      OpenGlHelper.setLightmapTextureCoords(var1, var2, var3);
   }

   @Override
   public int method5() {
      return OpenGlHelper.lightmapTexUnit;
   }

   @Override
   public int method6() {
      return OpenGlHelper.defaultTexUnit;
   }

   @Override
   public float method7() {
      return 240.0F;
   }

   @Override
   public float method8() {
      return 240.0F;
   }

   @Override
   public String method9() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? OpenGlHelper.getCpu() : "unknown";
   }

   @Override
   public void method10() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Minecraft.getMinecraft().entityRenderer.enableLightmap();
      } else {
         Minecraft.getMinecraft().entityRenderer.enableLightmap(0.0);
      }
   }

   @Override
   public void method11() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Minecraft.getMinecraft().entityRenderer.disableLightmap();
      } else {
         Minecraft.getMinecraft().entityRenderer.disableLightmap(0.0);
      }
   }

   @Override
   public void method13(int var1, ByteBuffer var2) {
      GL11.glGetBoolean(var1, var2);
   }

   @Override
   public void method14(int var1, IntBuffer var2) {
      GL11.glGetInteger(var1, var2);
   }

   @Override
   public void method15(int var1, FloatBuffer var2) {
      GL11.glGetFloat(var1, var2);
   }

   @Override
   public void method16(int var1, DoubleBuffer var2) {
      GL11.glGetDouble(var1, var2);
   }

   @Override
   public boolean method17() {
      return GLContext.getCapabilities().OpenGL30;
   }

   @Override
   public boolean method18() {
      return GLContext.getCapabilities().OpenGL33;
   }

   @Override
   public boolean method19() {
      return GLContext.getCapabilities().OpenGL44;
   }

   @Override
   public boolean method20() {
      return GLContext.getCapabilities().GL_ARB_buffer_storage;
   }

   @Override
   public boolean method21() {
      return GLContext.getCapabilities().GL_ARB_framebuffer_object;
   }

   @Override
   public boolean method22() {
      return GLContext.getCapabilities().GL_ARB_map_buffer_range;
   }

   @Override
   public boolean method23() {
      return GLContext.getCapabilities().GL_ARB_vertex_array_object;
   }

   @Override
   public boolean method24() {
      return GLContext.getCapabilities().GL_EXT_framebuffer_object;
   }

   @Override
   public boolean method25() {
      return GLContext.getCapabilities().OpenGL43;
   }

   @Override
   public List<String> method26() {
      ArrayList var1 = new ArrayList();

      try {
         ContextCapabilities var2 = GLContext.getCapabilities();
         if (var2.OpenGL30) {
            int var3 = GL11.glGetInteger(33309);

            for (int var4 = 0; var4 < var3; var4++) {
               var1.add(GL30.glGetStringi(7939, var4));
            }
         } else {
            String var6 = GL11.glGetString(7939);
            if (var6 != null) {
               var1.addAll(List.of(var6.split(" ")));
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return var1;
   }

   @Override
   public OpenGlHelperBridge.Type method27() {
      int var1;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var1 = OpenGlHelper.framebufferType$v1_12.ordinal();
      } else if (ThreadModuleDump63.MC_VERSION == 1) {
         var1 = OpenGlHelper.framebufferType;
      } else {
         var1 = OpenGlHelper.field_153212_w$v1_7;
      }

      switch (var1) {
         case 0:
            return OpenGlHelperBridge.Type.BASE;
         case 1:
            return OpenGlHelperBridge.Type.ARB;
         case 2:
            return OpenGlHelperBridge.Type.EXT;
         default:
            throw new RuntimeException("Missing framebuffer support unexpected");
      }
   }

   @Override
   public void method28(int var1, String var2) {
      GL20.glShaderSource(var1, var2);
   }
}
