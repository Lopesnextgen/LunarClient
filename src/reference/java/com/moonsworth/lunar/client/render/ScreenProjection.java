package com.moonsworth.lunar.client.render;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.nio.FloatBuffer;
import java.util.function.BiConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector4f;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;

public final class ScreenProjection {
   public static final Matrix4f projectionMatrix = new Matrix4f();
   private static final Matrix4f modelViewMatrix = new Matrix4f();
   public static final Matrix4f worldProjectionMatrix = new Matrix4f();
   private static final Matrix4f inverseModelViewMatrix = new Matrix4f();
   private static final Matrix4f inverseWorldProjectionMatrix = new Matrix4f();
   private static final float[] modelViewArray = new float[16];
   private static final float[] projectionArray = new float[16];
   private static final FloatBuffer modelViewBuffer = FloatBuffer.wrap(field6);
   private static final FloatBuffer projectionBuffer = FloatBuffer.wrap(field7);
   private static int field10;
   private static int field11;
   private static double cameraX;
   private static double cameraY;
   private static double cameraZ;

   public static void update(double value0, double value2, double value4, BiConsumer<FloatBuffer, FloatBuffer> biconsumer6) {
      GuiResolution threadmoduledump717 = LcuiScreen.method151();
      field12 = value0;
      field13 = value2;
      field14 = value4;
      field10 = threadmoduledump717.getScaledWidth();
      field11 = threadmoduledump717.getScaledHeight();
      biconsumer6.accept(field8, field9);
      field2.set(field6);
      field3.set(field7);
      field3.invert(field5);
      field2.invert(field4);
   }

   @Nullable
   private static Vector2d project(float value0, float value1, float value2, float value3) {
      Vector4f vector4f4 = new Vector4f(value0, value1, value2, value3);
      field2.transform(vector4f4);
      field3.transform(vector4f4);
      if (Ref.MC_VERSION >= 39 ? !(vector4f4.w >= 0.0F) : !(vector4f4.w <= 0.0F)) {
         vector4f4.x = vector4f4.x / vector4f4.w;
         vector4f4.y = vector4f4.y / vector4f4.w;
         double value5 = (vector4f4.x + 1.0) / 2.0 * field10;
         double value7 = (1.0 - (vector4f4.y + 1.0) / 2.0) * field11;
         return new Vector2d(value5, value7);
      } else {
         return null;
      }
   }

   @Nullable
   public static Vector2d worldToScreen(double value0, double value2, double value4) {
      return project((float)(value0 - field12), (float)(value2 - field13), (float)(value4 - field14), 1.0F);
   }

   public static Vector3d screenToRay(double value0, double value2) {
      float value4 = (float)(value0 / field10 * 2.0 - 1.0);
      float value5 = (float)((1.0 - value2 / field11) * 2.0 - 1.0);
      Vector4f vector4f6 = new Vector4f(value4, value5, -1.0F, 1.0F);
      Vector4f vector4f7 = new Vector4f(value4, value5, 1.0F, 1.0F);
      field5.transform(vector4f6);
      field5.transform(vector4f7);
      if (vector4f6.w != 0.0F && vector4f7.w != 0.0F) {
         vector4f6.div(vector4f6.w);
         vector4f7.div(vector4f7.w);
         field4.transform(vector4f6);
         field4.transform(vector4f7);
         if (vector4f6.w != 0.0F && vector4f7.w != 0.0F) {
            vector4f6.div(vector4f6.w);
            vector4f7.div(vector4f7.w);
            Vector3d vector3d8 = new Vector3d(vector4f7.x - vector4f6.x, vector4f7.y - vector4f6.y, vector4f7.z - vector4f6.z).normalize();
            if (Ref.MC_VERSION >= 39) {
               vector3d8.negate();
            }

            return vector3d8;
         } else {
            return new Vector3d(0.0, 0.0, -1.0);
         }
      } else {
         return new Vector3d(0.0, 0.0, -1.0);
      }
   }

   public static Vector3d getCameraPosition() {
      return new Vector3d(field12, field13, field14);
   }

   @Generated
   private ScreenProjection() {
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
