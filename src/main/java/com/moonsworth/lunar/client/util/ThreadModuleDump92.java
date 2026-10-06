package com.moonsworth.lunar.client.util;

import java.nio.IntBuffer;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

public final class ThreadModuleDump92 {
   public static final float[] field1 = new float[]{
      0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F
   };
   public static final int[] field2 = new int[]{0, 3, 2, 2, 1, 0};
   public static int field3 = 0;

   public static void method1() {
      if (field3 == 0) {
         field3 = GL15.glGenBuffers();
         IntBuffer var0 = BufferUtils.createIntBuffer(field2.length);
         var0.put(field2);
         var0.flip();
         GL15.glBindBuffer(34963, field3);
         GL15.glBufferData(34963, var0, 35044);
      }
   }

   public static float lerp(float var0, float var1, float var2) {
      return var0 + var2 * (var1 - var0);
   }

   public static float method2(float var0, float var1, float var2) {
      return Math.abs(var1 - var0) < Float.MIN_VALUE ? var0 : (var2 - var0) / (var1 - var0);
   }

   public static float[] method3(int var0, int var1, int var2, int value, int value2, int value3, int value4) {
      float var7 = 1.0F / var2;
      float var8 = 1.0F / value;
      float var9 = method2(0.0F, var7, (float)var0 / var2);
      float var10 = method2(0.0F, var7, (float)(var0 + value2) / var2);
      float var11 = method2(0.0F, var8, (float)var1 / value);
      float var12 = method2(0.0F, var8, (float)(var1 + value3) / value);
      return new float[]{
         lerp(var9, var10, 0.0F) / var2,
         lerp(var11, var12, 1.0F) / value,
         lerp(var9, var10, 1.0F) / var2,
         lerp(var11, var12, 1.0F) / value,
         lerp(var9, var10, 1.0F) / var2,
         lerp(var11, var12, 0.0F) / value,
         lerp(var9, var10, 0.0F) / var2,
         lerp(var11, var12, 0.0F) / value
      };
   }

   public static Vector3f[] method4(Vector3f var0, Vector3f var1) {
      return new Vector3f[]{
         new Vector3f(var1.x, var0.y, var1.z),
         new Vector3f(var0.x, var0.y, var1.z),
         new Vector3f(var0.x, var1.y, var1.z),
         new Vector3f(var1.x, var1.y, var1.z),
         new Vector3f(var1.x, var0.y, var0.z),
         new Vector3f(var0.x, var0.y, var0.z),
         new Vector3f(var0.x, var0.y, var1.z),
         new Vector3f(var1.x, var0.y, var1.z),
         new Vector3f(var1.x, var1.y, var1.z),
         new Vector3f(var0.x, var1.y, var1.z),
         new Vector3f(var0.x, var1.y, var0.z),
         new Vector3f(var1.x, var1.y, var0.z),
         new Vector3f(var0.x, var0.y, var1.z),
         new Vector3f(var0.x, var0.y, var0.z),
         new Vector3f(var0.x, var1.y, var0.z),
         new Vector3f(var0.x, var1.y, var1.z),
         new Vector3f(var1.x, var0.y, var0.z),
         new Vector3f(var1.x, var0.y, var1.z),
         new Vector3f(var1.x, var1.y, var1.z),
         new Vector3f(var1.x, var1.y, var0.z),
         new Vector3f(var0.x, var0.y, var0.z),
         new Vector3f(var1.x, var0.y, var0.z),
         new Vector3f(var1.x, var1.y, var0.z),
         new Vector3f(var0.x, var1.y, var0.z)
      };
   }

   private ThreadModuleDump92() {
   }
}
