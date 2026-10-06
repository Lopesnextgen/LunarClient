package com.moonsworth.lunar.client.render.turbo;

import com.google.common.primitives.Floats;
import com.moonsworth.lunar.bridge.Bridge$Type2;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Bridge_35;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.ints.IntArrays;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import lombok.Generated;
import org.apache.commons.lang3.mutable.MutableInt;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

@Annotation2(min = 8)
public class MatrixBuffer implements Bridge2_5 {
   private final Bridge2_5 field1;
   private final ByteBuffer field2;
   private final ByteBuffer field3;
   private final int vertices;

   public MatrixBuffer(Bridge2_5 var1, ByteBuffer var2, ByteBuffer var3, int var4) {
      this.field1 = var1;
      this.field2 = var2.order(ByteOrder.nativeOrder());
      this.field3 = var3.order(ByteOrder.nativeOrder());
      this.vertices = var4;
   }

   public void method1(RenderLayerBridge var1, Vector3f var2) {
      int[] var3 = method5(var2, this.method2());
      int var4 = var1.bridge$getVertexFormatMode().getPrimitiveStride();
      Bridge$Type2 var5 = this.field1.bridge$getIndexType();
      if (ThreadModuleDump63.MC_VERSION <= 28) {
         this.field3.clear();
         MutableInt var6 = new MutableInt(0);

         for (int var10 : var3) {
            this.method3(var6, var5, var10 * var4 + 0);
            this.method3(var6, var5, var10 * var4 + 1);
            this.method3(var6, var5, var10 * var4 + 2);
            this.method3(var6, var5, var10 * var4 + 2);
            this.method3(var6, var5, var10 * var4 + 3);
            this.method3(var6, var5, var10 * var4 + 0);
         }

         this.bridge$updateIndexBuffer(this.field3);
      } else {
         this.field3.clear();

         for (int var14 : var3) {
            this.method4(this.field3, var5, var14 * var4 + 0);
            this.method4(this.field3, var5, var14 * var4 + 1);
            this.method4(this.field3, var5, var14 * var4 + 2);
            this.method4(this.field3, var5, var14 * var4 + 2);
            this.method4(this.field3, var5, var14 * var4 + 3);
            this.method4(this.field3, var5, var14 * var4 + 0);
         }

         this.field3.flip();
         this.field1.bridge$updateIndexBuffer(this.field3);
      }
   }

   private Vector3f[] method2() {
      int var5 = this.field1.bridge$getVertexFormatMode().getPrimitiveStride();
      FloatBuffer var1 = this.field2.asFloatBuffer();
      int var2 = this.field1.bridge$getVertexFormat().method1();
      int var3 = var2 * var5;
      int var4 = this.vertices / var5;
      Vector3f[] var6 = new Vector3f[var4];

      for (int var7 = 0; var7 < var4; var7++) {
         float var8 = var1.get(var7 * var3);
         float var9 = var1.get(var7 * var3 + 1);
         float var10 = var1.get(var7 * var3 + 2);
         float var11 = var1.get(var7 * var3 + var2 * 2);
         float var12 = var1.get(var7 * var3 + var2 * 2 + 1);
         float var13 = var1.get(var7 * var3 + var2 * 2 + 2);
         var6[var7] = new Vector3f((var8 + var11) / 2.0F, (var9 + var12) / 2.0F, (var10 + var13) / 2.0F);
      }

      return var6;
   }

   private void method3(MutableInt var1, Bridge$Type2 var2, int var3) {
      switch (var2) {
         case BYTE:
            this.field3.put(var1.getAndAdd(1), (byte)var3);
            break;
         case SHORT:
            this.field3.putShort(var1.getAndAdd(2), (short)var3);
            break;
         case INT:
            this.field3.putInt(var1.getAndAdd(4), var3);
      }
   }

   @Annotation2(min = 29)
   private void method4(ByteBuffer var1, Bridge$Type2 var2, int var3) {
      switch (var2) {
         case BYTE:
            var1.put((byte)var3);
            break;
         case SHORT:
            var1.putShort((short)var3);
            break;
         case INT:
            var1.putInt(var3);
      }
   }

   private static int[] method5(Vector3f var0, Vector3f[] var1) {
      float[] var2 = new float[var1.length];
      int[] var3 = new int[var1.length];

      for (int var4 = 0; var4 < var1.length; var3[var4] = var4++) {
         var2[var4] = var0.distanceSquared(var1[var4]);
      }

      IntArrays.mergeSort(var3, (var1x, var2x) -> Floats.compare(var2[var2x], var2[var1x]));
      return var3;
   }

   @Override
   public void bridge$close() {
      this.field1.bridge$close();
      MemoryUtil.memFree(this.field2);
      MemoryUtil.memFree(this.field3);
   }

   @Generated
   @Override
   public void bridge$draw(MixinHelper_21 var1, RenderLayerBridge var2) {
      this.field1.bridge$draw(var1, var2);
   }

   @Generated
   @Override
   public void bridge$drawWithoutFog(MixinHelper_21 var1, RenderLayerBridge var2) {
      this.field1.bridge$drawWithoutFog(var1, var2);
   }

   @Generated
   @Override
   public int method1() {
      return this.field1.method1();
   }

   @Generated
   @Override
   public Bridge_35 bridge$getVertexBuffer() {
      return this.field1.bridge$getVertexBuffer();
   }

   @Generated
   @Override
   public Bridge_63 bridge$getVertexFormat() {
      return this.field1.bridge$getVertexFormat();
   }

   @Generated
   @Override
   public DrawMode bridge$getVertexFormatMode() {
      return this.field1.bridge$getVertexFormatMode();
   }

   @Generated
   @Override
   public Bridge$Type2 bridge$getIndexType() {
      return this.field1.bridge$getIndexType();
   }

   @Generated
   @Override
   public void bridge$bind() {
      this.field1.bridge$bind();
   }

   @Generated
   @Override
   public void bridge$unbind() {
      this.field1.bridge$unbind();
   }

   @Generated
   @Override
   public void bridge$uploadWithBuffers(Object var1, ByteBuffer var2, ByteBuffer var3, RenderLayerBridge var4) {
      this.field1.bridge$uploadWithBuffers(var1, var2, var3, var4);
   }

   @Generated
   @Override
   public void bridge$updateIndexBuffer(ByteBuffer var1) {
      this.field1.bridge$updateIndexBuffer(var1);
   }
}
