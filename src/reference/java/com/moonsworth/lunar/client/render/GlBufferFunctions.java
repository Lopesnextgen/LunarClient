package com.moonsworth.lunar.client.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import com.moonsworth.lunar.ichor.Annotation2;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.ARBDirectStateAccess;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL15C;
import org.lwjgl.opengl.GL45C;
import org.lwjgl.opengl.GLCapabilities;

@Annotation2(min = 8)
public class GlBufferFunctions {
   private static final boolean field1;
   private static final boolean field2;
   private static final boolean IS_LINUX = ThreadModuleDumpType2.isLinux();

   public static boolean method1() {
      return field1 || field2;
   }

   public static int method2() {
      return field1 ? GL45C.glCreateBuffers() : (field2 ? ARBDirectStateAccess.glCreateBuffers() : Bridge.method42().method83().method18());
   }

   public static void method3(int var0, int var1) {
      if (IS_LINUX) {
         method5(var0, var1, 35048);
      }

      GL15C.glDeleteBuffers(var1);
   }

   public static void method4(int var0, int var1, ByteBuffer var2, int var3) {
      if (field1) {
         GL45C.glNamedBufferData(var1, var2, var3);
      } else if (field2) {
         ARBDirectStateAccess.glNamedBufferData(var1, var2, var3);
      } else {
         RenderSystemBridge.Extension2 var4 = Bridge.method42().method83();
         var4.method20();
         var4.method16(var0, var1);
         var4.method17(var0, var2, var3);
         var4.method16(var0, 0);
      }
   }

   public static void method5(int var0, int var1, int var2) {
      if (field1) {
         GL45C.glNamedBufferData(var1, 0L, var2);
      } else if (field2) {
         ARBDirectStateAccess.glNamedBufferData(var1, 0L, var2);
      } else {
         RenderSystemBridge.Extension2 var3 = Bridge.method42().method83();
         var3.method20();
         var3.method16(var0, var1);
         var3.method16(var0, 0, var2);
         var3.method16(var0, 0);
      }
   }

   static {
      GLCapabilities var0 = GL.getCapabilities();
      field1 = var0.OpenGL45;
      field2 = var0.GL_ARB_direct_state_access;
   }
}
