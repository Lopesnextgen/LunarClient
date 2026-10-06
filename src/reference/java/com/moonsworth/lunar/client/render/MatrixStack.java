package com.moonsworth.lunar.client.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelQuad;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertex;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import com.moonsworth.lunar.client.util.click.Click2;
import com.moonsworth.lunar.ichor.Annotation2;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.IntConsumer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.render.texture.GlintTexture;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;

public class MatrixStack {
   private static final MatrixStack.Data field1 = new MatrixStack.Data();
   private static ByteBuffer field2 = null;
   private static final Map<BoneList, MatrixStack.Data2> field3 = new WeakHashMap<>();

   @Annotation2(max = 5)
   public static void method1(ModelRenderConfig var0) {
      RenderLayerBridge var1 = var0.getRenderType().get(var0.getTexture());
      GL11.glShadeModel(7425);
      BoneList var2 = var0.method10();
      MatrixStack.Data2 var3 = field3.computeIfAbsent(var2, var0x -> new MatrixStack.Data2());
      float[] var4 = var0.method12() ? var3.field2 : var3.field1;
      int var5;
      if (var4 == null) {
         for (IBoneSerializer var7 : var2.field1) {
            method4(var7, var0.method14() == RenderPass.EMISSIVE, false, var0.method12());
         }

         Click2 var9 = Click2.field1;
         if (var9.size == 0) {
            return;
         }

         var4 = var9.field2;
         var5 = var9.size;
         var9.size = 0;
         float[] var12 = new float[var5];
         System.arraycopy(var4, 0, var12, 0, var5);
         if (var0.method12()) {
            var3.field2 = var12;
         } else {
            var3.field1 = var12;
         }
      } else {
         var5 = var4.length;
         int var10 = 0;

         for (IBoneSerializer var8 : var2.field1) {
            var10 = method3(var8, var0.method14() == RenderPass.EMISSIVE, var4, var10, false, var0.method12());
         }
      }

      ByteBuffer var11 = field2;
      if (var11 == null || var11.capacity() < var5 * 4) {
         field2 = var11 = BufferUtils.createByteBuffer(var5 * 4);
      }

      Bridge.method42()
         .method84()
         .CCROIHHHCOCHHOHORCIRHOCRROIOCI(
            var0.getColor().getRed() / 255.0F, var0.getColor().getGreen() / 255.0F, var0.getColor().getBlue() / 255.0F, var0.getColor().getAlpha() / 255.0F
         );
      var11.asFloatBuffer().put(var4, 0, var5);
      var1.bridge$setupRenderState();
      GL11.glEnableClientState(32884);
      GL11.glVertexPointer(3, 5126, 24, var11);
      GL11.glEnableClientState(32888);
      var11.position(12);
      GL11.glTexCoordPointer(2, 5126, 24, var11);
      GL11.glEnableClientState(32885);
      var11.position(20);
      GL11.glNormalPointer(5120, 24, var11);
      GL11.glDrawArrays(7, 0, var5 / 6);
      if (var0.method14() == RenderPass.NORMAL_GLINT) {
         method2(var5);
      }

      GL11.glDisableClientState(32885);
      GL11.glDisableClientState(32888);
      GL11.glDisableClientState(32884);
      var11.position(0);
      var1.bridge$clearRenderState();
   }

   @Annotation2(max = 5)
   private static void method2(int var0) {
      if (GlintTexture.method5()) {
         IntConsumer var2 = var1x -> {
            Color var2x = new Color(var1x);
            Bridge.method42()
               .method84()
               .CCROIHHHCOCHHOHORCIRHOCRROIOCI(var2x.getRed() / 255.0F, var2x.getGreen() / 255.0F, var2x.getBlue() / 255.0F, var2x.getAlpha() / 255.0F);
            GL11.glDrawArrays(7, 0, var0 / 6);
         };
         GlintTexture.method6(var2);
      } else {
         Color var1 = GlintTexture.method4();
         Bridge.method42()
            .method84()
            .CCROIHHHCOCHHOHORCIRHOCRROIOCI(var1.getRed() / 255.0F, var1.getGreen() / 255.0F, var1.getBlue() / 255.0F, var1.getAlpha() / 255.0F);
         GlintTexture.method9();
         GL11.glDrawArrays(7, 0, var0 / 6);
         GlintTexture.method10();
         GL11.glDrawArrays(7, 0, var0 / 6);
         GlintTexture.method11();
      }
   }

   private static int method3(IBoneSerializer var0, boolean var1, float[] var2, int var3, boolean var4, boolean var5) {
      field1.push();
      PlayerModelPartMap.method17(var0, field1.method1());
      List var6 = var0.field2;
      int var7 = 0;

      for (int var8 = var6.size(); var7 < var8; var7++) {
         CubeMesh var9 = (CubeMesh)var6.get(var7);
         field1.push();
         var3 = method5(var9, var1, var2, var3, var0.isHidden | var4, var5);
         field1.pop();
      }

      List var10 = var0.field1;
      int var11 = 0;

      for (int var12 = var10.size(); var11 < var12; var11++) {
         var3 = method3((IBoneSerializer)var10.get(var11), var1, var2, var3, var0.isHidden | var4, var5);
      }

      field1.pop();
      return var3;
   }

   private static void method4(IBoneSerializer var0, boolean var1, boolean var2, boolean var3) {
      field1.push();
      PlayerModelPartMap.method17(var0, field1.method1());
      List var4 = var0.field2;
      int var5 = 0;

      for (int var6 = var4.size(); var5 < var6; var5++) {
         CubeMesh var7 = (CubeMesh)var4.get(var5);
         field1.push();
         method6(var7, var1, var0.isHidden | var2, var3);
         field1.pop();
      }

      List var8 = var0.field1;
      int var9 = 0;

      for (int var10 = var8.size(); var9 < var10; var9++) {
         method4((IBoneSerializer)var8.get(var9), var1, var0.isHidden | var2, var3);
      }

      field1.pop();
   }

   private static int method5(CubeMesh var0, boolean var1, float[] var2, int var3, boolean var4, boolean var5) {
      PlayerModelPartMap.method21(var0, field1.method1());
      Vector3f var6 = new Vector3f();
      Matrix4f var7 = field1.method1();

      for (ModelQuad var11 : var0.field1) {
         if (var11 != null) {
            if (var4) {
               var2[var3] = var2[var3 + 2] = 0.0F;
               var2[var3 + 6] = var2[var3 + 8] = 0.0F;
               var2[var3 + 12] = var2[var3 + 14] = 0.0F;
               var2[var3 + 18] = var2[var3 + 20] = 0.0F;
               var3 += 24;
            } else {
               float var12;
               if (var1) {
                  var12 = Float.intBitsToFloat(8355711);
               } else {
                  var7.transformDirection(var11.field4, var6);
                  if ((var0.field4.y == 0.0F || var0.field4.z == 0.0F) && var6.x < 0.0F) {
                     var6.x *= -1.0F;
                  }

                  if ((var0.field4.x == 0.0F || var0.field4.z == 0.0F) && var6.y < 0.0F) {
                     var6.y *= -1.0F;
                  }

                  if ((var0.field4.x == 0.0F || var0.field4.y == 0.0F) && var6.z < 0.0F) {
                     var6.z *= -1.0F;
                  }

                  int var13 = ((int)(var6.x * 127.0F) & 0xFF) << 24;
                  var13 |= ((int)(var6.y * 127.0F) & 0xFF) << 16;
                  var13 |= ((int)(var6.z * 127.0F) & 0xFF) << 8;
                  var12 = Float.intBitsToFloat(Integer.reverseBytes(var13));
               }

               int[] var24 = var11.method2(var5);
               Vector3f var14 = var11.field3[var24[0]].field1;
               Vector3f var15 = var11.field3[var24[1]].field1;
               Vector3f var16 = var11.field3[var24[2]].field1;
               Vector3f var17 = var11.field3[var24[3]].field1;
               float var18 = var7.m00();
               float var19 = var7.m10();
               float var20 = var7.m20();
               float var21 = var7.m30();
               var2[var3] = var18 * var14.x + var19 * var14.y + var20 * var14.z + var21;
               var2[var3 + 6] = var18 * var15.x + var19 * var15.y + var20 * var15.z + var21;
               var2[var3 + 12] = var18 * var16.x + var19 * var16.y + var20 * var16.z + var21;
               var2[var3 + 18] = var18 * var17.x + var19 * var17.y + var20 * var17.z + var21;
               var18 = var7.m01();
               var19 = var7.m11();
               var20 = var7.m21();
               var21 = var7.m31();
               var2[var3 + 1] = var18 * var14.x + var19 * var14.y + var20 * var14.z + var21;
               var2[var3 + 7] = var18 * var15.x + var19 * var15.y + var20 * var15.z + var21;
               var2[var3 + 13] = var18 * var16.x + var19 * var16.y + var20 * var16.z + var21;
               var2[var3 + 19] = var18 * var17.x + var19 * var17.y + var20 * var17.z + var21;
               var18 = var7.m02();
               var19 = var7.m12();
               var20 = var7.m22();
               var21 = var7.m32();
               var2[var3 + 2] = var18 * var14.x + var19 * var14.y + var20 * var14.z + var21;
               var2[var3 + 8] = var18 * var15.x + var19 * var15.y + var20 * var15.z + var21;
               var2[var3 + 14] = var18 * var16.x + var19 * var16.y + var20 * var16.z + var21;
               var2[var3 + 20] = var18 * var17.x + var19 * var17.y + var20 * var17.z + var21;
               var2[var3 + 5] = var2[var3 + 11] = var2[var3 + 17] = var2[var3 + 23] = var12;
               var3 += 24;
            }
         }
      }

      return var3;
   }

   private static void method6(CubeMesh var0, boolean var1, boolean var2, boolean var3) {
      float var4 = var2 ? 0.0F : 1.0F;
      PlayerModelPartMap.method21(var0, field1.method1());
      Click2 var5 = Click2.field1;
      Vector3f var6 = new Vector3f();
      Vector3f var7 = new Vector3f();
      Matrix4f var8 = field1.method1();

      for (ModelQuad var12 : var0.field1) {
         if (var12 != null) {
            float var13;
            if (!var1) {
               var8.transformDirection(var12.field4, var6);
               if ((var0.field4.y == 0.0F || var0.field4.z == 0.0F) && var6.x < 0.0F) {
                  var6.x *= -1.0F;
               }

               if ((var0.field4.x == 0.0F || var0.field4.z == 0.0F) && var6.y < 0.0F) {
                  var6.y *= -1.0F;
               }

               if ((var0.field4.x == 0.0F || var0.field4.y == 0.0F) && var6.z < 0.0F) {
                  var6.z *= -1.0F;
               }

               int var14 = ((int)(var6.x * 127.0F) & 0xFF) << 24;
               var14 |= ((int)(var6.y * 127.0F) & 0xFF) << 16;
               var14 |= ((int)(var6.z * 127.0F) & 0xFF) << 8;
               var13 = Float.intBitsToFloat(Integer.reverseBytes(var14));
            } else {
               var13 = Float.intBitsToFloat(8355711);
            }

            var5.method1(24);
            float[] var24 = var5.field2;
            int var15 = var5.size;

            for (int var19 : var12.method2(var3)) {
               ModelVertex var20 = var12.field3[var19];
               Vector3f var21 = var20.field1;
               var8.transformPosition(var21.x, var21.y, var21.z, var7);
               var24[var15] = var7.x * var4;
               var24[var15 + 1] = var7.y * var4;
               var24[var15 + 2] = var7.z * var4;
               var24[var15 + 3] = var20.field2;
               var24[var15 + 4] = var20.field3;
               var24[var15 + 5] = var13;
               var15 += 6;
            }

            var5.size += 24;
         }
      }
   }

   public static class Data {
      public Matrix4f[] field1 = new Matrix4f[32];
      public int index = 0;

      public Data() {
         this.field1[0] = new Matrix4f();
      }

      public void push() {
         Matrix4f var1 = this.field1[this.index++];
         Matrix4f var2 = this.field1[this.index];
         if (var2 == null) {
            this.field1[this.index] = var2 = new Matrix4f();
         }

         var2.set(var1);
      }

      public void pop() {
         this.index--;
      }

      public Matrix4f method1() {
         return this.field1[this.index];
      }
   }

   private static final class Data2 {
      private float[] field1 = null;
      private float[] field2 = null;
   }
}
