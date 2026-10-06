package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.TexturedBoxBridge;
import com.moonsworth.lunar.client.render.texture.NativeImageBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.CubeGeometry;
import java.nio.FloatBuffer;
import java.util.UUID;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class TexturedBoxBridgeImpl implements TexturedBoxBridge {
   private final float[] field1;
   private final Vector3f[] field2;
   private int field3;

   public static TexturedBoxBridgeImpl method1(float value0, float value1, float value2, int number3, int number4, int number5, int number6, int number7) {
      Vector3f vector3f8 = new Vector3f(value0 - number3, value1 - number4, value2 - number5);
      Vector3f vector3f9 = new Vector3f(value0 + number3, value1 + number4, value2 + number5);
      return new TexturedBoxBridgeImpl(vector3f9, vector3f8, number6, number7);
   }

   public static TexturedBoxBridgeImpl method2(int number0, int number1) {
      return method1(0.0F, 16.0F, -1.0F, 10, 16, 1, number0, number1);
   }

   public TexturedBoxBridgeImpl(Vector3f vector3f1, Vector3f vector3f2, int number3, int number4) {
      Vector3f vector3f5 = vector3f1.sub(vector3f2, new Vector3f());
      vector3f5.x /= 2.0F;
      vector3f5.y /= 2.0F;
      vector3f5.z /= 2.0F;
      float value6 = vector3f5.x;
      float value7 = vector3f5.y;
      float value8 = vector3f5.z;
      this.field2 = CubeGeometry.method4(vector3f1, vector3f2);
      this.field3 = -1;
      FloatBuffer floatbuffer9 = FloatBuffer.allocate(48);
      floatbuffer9.put(CubeGeometry.method3((int)value8, (int)value8, number3, number4, (int)value6, (int)value7, 1));
      floatbuffer9.put(CubeGeometry.method3((int)(value8 + value6), 0, number3, number4, (int)value6, (int)value8, 1));
      floatbuffer9.put(CubeGeometry.method3((int)value8, 0, number3, number4, (int)value6, (int)value8, 1));
      floatbuffer9.put(CubeGeometry.method3((int)(value6 + value8), (int)value8, number3, number4, (int)value8, (int)value7, 1));
      floatbuffer9.put(CubeGeometry.method3(0, (int)value8, number3, number4, 1, (int)value7, 1));
      floatbuffer9.put(CubeGeometry.method3((int)(value6 + value8 + value8), (int)value8, number3, number4, (int)value6, (int)value7, 1));
      this.field1 = floatbuffer9.array();
   }

   public void method1(AbstractRenderContext bridgeextension_91, float value2, ResourceLocationBridge horsestats143, UUID uuid4) {
      RenderTypeBridge bridge205 = LunarRenderTypes.field55.get(horsestats143);
      DrawBufferBridge bridge2_326 = bridgeextension_91.method10(bridge205);
      if (!bridge2_326.method22()) {
         if (this.field1 != null) {
            Ref.method4().method102().method15(NativeImageBuilder.method7(), arg2x -> this.method4(arg2x::method1, value2, arg2x::method2, true), uuid4, horsestats143);
            boolean flag7 = bridgeextension_91.method7();
            bridgeextension_91.method20();
            if (this.field3 == -1) {
               this.field3 = GL11.glGenLists(1);
               if (bridge2_326 instanceof com.moonsworth.lunar.bridge.Bridge2Handler) {
                  GL11.glNewList(this.field3, 4864);
               } else {
                  GL11.glNewList(this.field3, 4865);
               }

               bridge2_326.method1();
               this.method4(
                  (arg1x, arg2x, arg3x, arg4x, arg5x, arg6x, arg7x, arg8x) -> bridge2_326.method2(arg3x, arg4x, arg5x)
                     .method10(arg1x, arg2x)
                     .method14(arg6x, arg7x, arg8x)
                     .method16(),
                  value2,
                  null,
                  false
               );
               if (bridge2_326 instanceof com.moonsworth.lunar.bridge.Bridge2Handler bridge2handler8) {
                  bridge2handler8.method13();
                  GL11.glEndList();
                  bridge205.bridge$setupRenderState();
                  GL11.glCallList(this.field3);
                  bridge205.bridge$clearRenderState();
               } else {
                  bridge2_326.method17(BufferMode.BATCHED);
                  GL11.glEndList();
               }
            } else if (bridge2_326 instanceof com.moonsworth.lunar.bridge.Bridge2Handler) {
               bridge205.bridge$setupRenderState();
               GL11.glCallList(this.field3);
               bridge205.bridge$clearRenderState();
            } else {
               GL11.glCallList(this.field3);
            }

            if (!flag7) {
               bridgeextension_91.method21();
            }
         }
      }
   }

   private void method4(TexturedBoxBridgeImpl.VertexSink extension1, float value2, Runnable runnable3, boolean flag4) {
      byte index5 = 0;
      byte index6 = 0;

      for (byte index7 = 0; index7 < this.field2.length; index7 += 4) {
         Vector3f vector3f8 = this.field2[index7];
         Vector3f vector3f9 = this.field2[index7 + 1];
         Vector3f vector3f10 = this.field2[index7 + 2];
         Vector3f vector3f11 = this.field2[index7 + 3];
         float value12 = CubeGeometry.field1[index6];
         float value13 = CubeGeometry.field1[index6 + 1];
         float value14 = CubeGeometry.field1[index6 + 2];
         extension1.vertex(this.field1[index5], this.field1[index5 + 1], vector3f8.x() * value2, vector3f8.y() * value2, vector3f8.z() * value2, value12, value13, value14);
         if (flag4) {
            extension1.vertex(this.field1[index5 + 6], this.field1[index5 + 7], vector3f11.x() * value2, vector3f11.y() * value2, vector3f11.z() * value2, value12, value13, value14);
            extension1.vertex(this.field1[index5 + 2], this.field1[index5 + 3], vector3f9.x() * value2, vector3f9.y() * value2, vector3f9.z() * value2, value12, value13, value14);
            extension1.vertex(this.field1[index5 + 4], this.field1[index5 + 5], vector3f10.x() * value2, vector3f10.y() * value2, vector3f10.z() * value2, value12, value13, value14);
         } else {
            extension1.vertex(this.field1[index5 + 2], this.field1[index5 + 3], vector3f9.x() * value2, vector3f9.y() * value2, vector3f9.z() * value2, value12, value13, value14);
            extension1.vertex(this.field1[index5 + 4], this.field1[index5 + 5], vector3f10.x() * value2, vector3f10.y() * value2, vector3f10.z() * value2, value12, value13, value14);
            extension1.vertex(this.field1[index5 + 6], this.field1[index5 + 7], vector3f11.x() * value2, vector3f11.y() * value2, vector3f11.z() * value2, value12, value13, value14);
         }

         if (runnable3 != null) {
            runnable3.run();
         }

         if (index5 + 2 < this.field1.length) {
            index5 += 8;
         }

         index6 += 3;
      }
   }

   @FunctionalInterface
   public interface VertexSink {
      void vertex(float value1, float value2, float value3, float value4, float value5, float value6, float value7, float value8);
   }
}
