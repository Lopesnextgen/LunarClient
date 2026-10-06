package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.nio.FloatBuffer;
import java.util.function.BiConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector4f;

public final class ThreadModuleDump64 {
   public static final Matrix4f field1 = new Matrix4f();
   private static final Matrix4f field2 = new Matrix4f();
   public static final Matrix4f field3 = new Matrix4f();
   private static final Matrix4f field4 = new Matrix4f();
   private static final Matrix4f field5 = new Matrix4f();
   private static final float[] field6 = new float[16];
   private static final float[] field7 = new float[16];
   private static final FloatBuffer field8 = FloatBuffer.wrap(field6);
   private static final FloatBuffer field9 = FloatBuffer.wrap(field7);
   private static int field10;
   private static int field11;
   private static double field12;
   private static double field13;
   private static double field14;

   public static void method1(double var0, double var2, double var4, BiConsumer<FloatBuffer, FloatBuffer> var6) {
      ThreadModuleDump71 var7 = LcuiScreen.method151();
      field12 = var0;
      field13 = var2;
      field14 = var4;
      field10 = var7.getScaledWidth();
      field11 = var7.getScaledHeight();
      var6.accept(field8, field9);
      field2.set(field6);
      field3.set(field7);
      field3.invert(field5);
      field2.invert(field4);
   }

   @Nullable
   private static Vector2d method2(float var0, float value, float var2, float value2) {
      Vector4f var4 = new Vector4f(var0, value, var2, value2);
      field2.transform(var4);
      field3.transform(var4);
      if (ThreadModuleDump63.MC_VERSION >= 39 ? !(var4.w >= 0.0F) : !(var4.w <= 0.0F)) {
         var4.x = var4.x / var4.w;
         var4.y = var4.y / var4.w;
         double var5 = (var4.x + 1.0) / 2.0 * field10;
         double var7 = (1.0 - (var4.y + 1.0) / 2.0) * field11;
         return new Vector2d(var5, var7);
      } else {
         return null;
      }
   }

   @Nullable
   public static Vector2d method3(double var0, double var2, double var4) {
      return method2((float)(var0 - field12), (float)(var2 - field13), (float)(var4 - field14), 1.0F);
   }

   public static Vector3d method4(double var0, double var2) {
      float var4 = (float)(var0 / field10 * 2.0 - 1.0);
      float var5 = (float)((1.0 - var2 / field11) * 2.0 - 1.0);
      Vector4f var6 = new Vector4f(var4, var5, -1.0F, 1.0F);
      Vector4f var7 = new Vector4f(var4, var5, 1.0F, 1.0F);
      field5.transform(var6);
      field5.transform(var7);
      if (var6.w != 0.0F && var7.w != 0.0F) {
         var6.div(var6.w);
         var7.div(var7.w);
         field4.transform(var6);
         field4.transform(var7);
         if (var6.w != 0.0F && var7.w != 0.0F) {
            var6.div(var6.w);
            var7.div(var7.w);
            Vector3d var8 = new Vector3d(var7.x - var6.x, var7.y - var6.y, var7.z - var6.z).normalize();
            if (ThreadModuleDump63.MC_VERSION >= 39) {
               var8.negate();
            }

            return var8;
         } else {
            return new Vector3d(0.0, 0.0, -1.0);
         }
      } else {
         return new Vector3d(0.0, 0.0, -1.0);
      }
   }

   public static Vector3d method5() {
      return new Vector3d(field12, field13, field14);
   }

   @Generated
   private ThreadModuleDump64() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static int getScaledWidth() {
      return field10;
   }

   @Generated
   public static int getScaledHeight() {
      return field11;
   }
}
