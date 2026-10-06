package com.moonsworth.lunar.client.render;

import java.nio.IntBuffer;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

public final class CubeGeometry {
   public static final float[] field1 = new float[]{
      0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F
   };
   public static final int[] field2 = new int[]{0, 3, 2, 2, 1, 0};
   public static int field3 = 0;

   public static void method1() {
      if (field3 == 0) {
         field3 = GL15.glGenBuffers();
         IntBuffer intbuffer0 = BufferUtils.createIntBuffer(field2.length);
         intbuffer0.put(field2);
         intbuffer0.flip();
         GL15.glBindBuffer(34963, field3);
         GL15.glBufferData(34963, intbuffer0, 35044);
      }
   }

   public static float lerp(float value0, float value1, float value2) {
      return value0 + value2 * (value1 - value0);
   }

   public static float method2(float value0, float value1, float value2) {
      return Math.abs(value1 - value0) < Float.MIN_VALUE ? value0 : (value2 - value0) / (value1 - value0);
   }

   public static float[] method3(int value, int value2, int value3, int value4, int value5, int value6, int value13) {
      float value7 = 1.0F / value3;
      float value8 = 1.0F / value4;
      float value9 = method2(0.0F, value7, (float)value / value3);
      float value10 = method2(0.0F, value7, (float)(value + value5) / value3);
      float value11 = method2(0.0F, value8, (float)value2 / value4);
      float value12 = method2(0.0F, value8, (float)(value2 + value6) / value4);
      return new float[]{
         lerp(value9, value10, 0.0F) / value3,
         lerp(value11, value12, 1.0F) / value4,
         lerp(value9, value10, 1.0F) / value3,
         lerp(value11, value12, 1.0F) / value4,
         lerp(value9, value10, 1.0F) / value3,
         lerp(value11, value12, 0.0F) / value4,
         lerp(value9, value10, 0.0F) / value3,
         lerp(value11, value12, 0.0F) / value4
      };
   }

   public static Vector3f[] method4(Vector3f vector3f0, Vector3f vector3f1) {
      return new Vector3f[]{
         new Vector3f(vector3f1.x, vector3f0.y, vector3f1.z),
         new Vector3f(vector3f0.x, vector3f0.y, vector3f1.z),
         new Vector3f(vector3f0.x, vector3f1.y, vector3f1.z),
         new Vector3f(vector3f1.x, vector3f1.y, vector3f1.z),
         new Vector3f(vector3f1.x, vector3f0.y, vector3f0.z),
         new Vector3f(vector3f0.x, vector3f0.y, vector3f0.z),
         new Vector3f(vector3f0.x, vector3f0.y, vector3f1.z),
         new Vector3f(vector3f1.x, vector3f0.y, vector3f1.z),
         new Vector3f(vector3f1.x, vector3f1.y, vector3f1.z),
         new Vector3f(vector3f0.x, vector3f1.y, vector3f1.z),
         new Vector3f(vector3f0.x, vector3f1.y, vector3f0.z),
         new Vector3f(vector3f1.x, vector3f1.y, vector3f0.z),
         new Vector3f(vector3f0.x, vector3f0.y, vector3f1.z),
         new Vector3f(vector3f0.x, vector3f0.y, vector3f0.z),
         new Vector3f(vector3f0.x, vector3f1.y, vector3f0.z),
         new Vector3f(vector3f0.x, vector3f1.y, vector3f1.z),
         new Vector3f(vector3f1.x, vector3f0.y, vector3f0.z),
         new Vector3f(vector3f1.x, vector3f0.y, vector3f1.z),
         new Vector3f(vector3f1.x, vector3f1.y, vector3f1.z),
         new Vector3f(vector3f1.x, vector3f1.y, vector3f0.z),
         new Vector3f(vector3f0.x, vector3f0.y, vector3f0.z),
         new Vector3f(vector3f1.x, vector3f0.y, vector3f0.z),
         new Vector3f(vector3f1.x, vector3f1.y, vector3f0.z),
         new Vector3f(vector3f0.x, vector3f1.y, vector3f0.z)
      };
   }

   private CubeGeometry() {
   }
}
