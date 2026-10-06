package com.moonsworth.lunar.client.framework.feature.pkg.mixin;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge3_10;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import java.util.HashMap;
import org.joml.Vector3f;

public class Pkg2 {
   private final PkgType[] field1;
   private final Pkg2.Data2[] field2;

   public Pkg2(
      int var1,
      int var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      boolean var12,
      float var13,
      float var14,
      PkgType[] items,
      PkgType[][] items2
   ) {
      this.field1 = items;
      float var17 = var3 + var6;
      float var18 = var4 + var7;
      float var19 = var5 + var8;
      var3 -= var9;
      var4 -= var10;
      var5 -= var11;
      var17 += var9;
      var18 += var10;
      var19 += var11;
      if (var12) {
         float var20 = var17;
         var17 = var3;
         var3 = var20;
      }

      Pkg2.Data var49 = new Pkg2.Data(var3, var4, var5, 0.0F, 0.0F);
      Pkg2.Data var21 = new Pkg2.Data(var17, var4, var5, 0.0F, 8.0F);
      Pkg2.Data var22 = new Pkg2.Data(var17, var18, var5, 8.0F, 8.0F);
      Pkg2.Data var23 = new Pkg2.Data(var3, var18, var5, 8.0F, 0.0F);
      Pkg2.Data var24 = new Pkg2.Data(var3, var4, var19, 0.0F, 0.0F);
      Pkg2.Data var25 = new Pkg2.Data(var17, var4, var19, 0.0F, 8.0F);
      Pkg2.Data var26 = new Pkg2.Data(var17, var18, var19, 8.0F, 8.0F);
      Pkg2.Data var27 = new Pkg2.Data(var3, var18, var19, 8.0F, 0.0F);
      float var28 = var1 + 1.0F;
      float var29 = var2 + 1.0F;
      HashMap var30 = new HashMap();

      for (PkgType[] var34 : items2) {
         label58:
         for (PkgType.Type var38 : PkgType.Type.VALUES) {
            for (PkgType var42 : var34) {
               if (var42.getAxis() == var38) {
                  continue label58;
               }
            }

            var30.put(var38, var34);
            break;
         }
      }

      this.field2 = new Pkg2.Data2[this.method1()];
      int var50 = 0;
      if (this.method2(PkgType.DOWN)) {
         this.field2[var50++] = new Pkg2.Data2(
            method3(new Pkg2.Data[]{var25, var24, var49, var21}, (PkgType[])var30.get(PkgType.Type.Y)),
            var1,
            var2,
            var28,
            var29,
            var13,
            var14,
            var12,
            PkgType.DOWN
         );
      }

      if (this.method2(PkgType.UP)) {
         this.field2[var50++] = new Pkg2.Data2(
            method3(new Pkg2.Data[]{var22, var23, var27, var26}, (PkgType[])var30.get(PkgType.Type.Y)),
            var1,
            var2,
            var28,
            var29,
            var13,
            var14,
            var12,
            PkgType.UP
         );
      }

      if (this.method2(PkgType.NORTH)) {
         this.field2[var50++] = new Pkg2.Data2(
            method3(new Pkg2.Data[]{var21, var49, var23, var22}, (PkgType[])var30.get(PkgType.Type.Z)),
            var1,
            var2,
            var28,
            var29,
            var13,
            var14,
            var12,
            PkgType.NORTH
         );
      }

      if (this.method2(PkgType.SOUTH)) {
         this.field2[var50++] = new Pkg2.Data2(
            method3(new Pkg2.Data[]{var24, var25, var26, var27}, (PkgType[])var30.get(PkgType.Type.Z)),
            var1,
            var2,
            var28,
            var29,
            var13,
            var14,
            var12,
            PkgType.SOUTH
         );
      }

      if (this.method2(PkgType.WEST)) {
         this.field2[var50++] = new Pkg2.Data2(
            method3(new Pkg2.Data[]{var49, var24, var27, var23}, (PkgType[])var30.get(PkgType.Type.X)),
            var1,
            var2,
            var28,
            var29,
            var13,
            var14,
            var12,
            PkgType.WEST
         );
      }

      if (this.method2(PkgType.EAST)) {
         this.field2[var50] = new Pkg2.Data2(
            method3(new Pkg2.Data[]{var25, var21, var22, var26}, (PkgType[])var30.get(PkgType.Type.X)),
            var1,
            var2,
            var28,
            var29,
            var13,
            var14,
            var12,
            PkgType.EAST
         );
      }
   }

   private int method1() {
      int var1 = 0;

      for (PkgType var5 : PkgType.VALUES) {
         if (this.method2(var5)) {
            var1++;
         }
      }

      return var1;
   }

   private boolean method2(PkgType var1) {
      for (PkgType var5 : this.field1) {
         if (var5 == var1) {
            return false;
         }
      }

      return true;
   }

   private static Pkg2.Data[] method3(Pkg2.Data[] var0, PkgType[] var1) {
      if (var1 == null) {
         return var0;
      }

      Pkg2.Data var2 = var0[0];

      for (int var3 = 1; var3 < 4; var3++) {
         var2 = method4(var2, var0[var3], var1);
      }

      int var5 = 0;

      for (int var4 = 0; var4 < 4; var4++) {
         if (var0[var4] != var2) {
            var0[var5++] = var0[var4];
         }
      }

      var0[3] = var0[2];
      return var0;
   }

   private static Pkg2.Data method4(Pkg2.Data var0, Pkg2.Data var1, PkgType[] var2) {
      for (PkgType var6 : var2) {
         double var7 = var6.getAxis().choose(var0.field1.x() - var1.field1.x(), var0.field1.y() - var1.field1.y(), var0.field1.z() - var1.field1.z())
            * var6.getAxisDirection();
         if (var7 > 0.0) {
            return var0;
         }

         if (var7 < 0.0) {
            return var1;
         }
      }

      return var0;
   }

   public void method5(Bridge2_32 var1, float var2) {
      for (Pkg2.Data2 var8 : this.field2) {
         Vector3f var3 = var8.field2;

         for (int var9 = 0; var9 < 4; var9++) {
            Pkg2.Data var4 = var8.field1[var9];
            float var10 = var4.field1.x() * var2;
            float var11 = var4.field1.y() * var2;
            float var12 = var4.field1.z() * var2;
            var1.method2(var10, var11, var12);
            var1.method10(var4.field2, var4.field3);
            var1.method14(var3.x, var3.y, var3.z);
            var1.method16();
         }
      }
   }

   public void method6(Bridge3_10 var1, Bridge4_6 var2, int var3, int var4, float var5, float var6, float var7, float var8, float var9) {
      MixinHelper_21 var10 = var1.bridge$pose();
      Matrix3fBridge var11 = var1.bridge$normal();

      for (Pkg2.Data2 var17 : this.field2) {
         Vector3f var12 = var17.field2;
         float var18 = var11.bridge$getTransformX(var12.x, var12.y, var12.z);
         float var19 = var11.bridge$getTransformY(var12.x, var12.y, var12.z);
         float var20 = var11.bridge$getTransformZ(var12.x, var12.y, var12.z);

         for (int var21 = 0; var21 < 4; var21++) {
            Pkg2.Data var13 = var17.field1[var21];
            float var22 = var13.field1.x() * var9;
            float var23 = var13.field1.y() * var9;
            float var24 = var13.field1.z() * var9;
            float var25 = var10.bridge$getTransformX(var22, var23, var24, 1.0F);
            float var26 = var10.bridge$getTransformY(var22, var23, var24, 1.0F);
            float var27 = var10.bridge$getTransformZ(var22, var23, var24, 1.0F);
            var2.bridge$vertex(var25, var26, var27, var5, var6, var7, var8, var13.field2, var13.field3, var4, var3, var18, var19, var20);
         }
      }
   }

   private static class Data {
      public final Vector3f field1;
      public final float field2;
      public final float field3;

      public Data(float var1, float var2, float var3, float var4, float var5) {
         this(new Vector3f(var1, var2, var3), var4, var5);
      }

      public Pkg2.Data method1(float var1, float var2) {
         return new Pkg2.Data(this.field1, var1, var2);
      }

      public Data(Vector3f var1, float var2, float var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }

   private static class Data2 {
      public final Pkg2.Data[] field1;
      public final Vector3f field2;

      public Data2(Pkg2.Data[] var1, float var2, float var3, float var4, float var5, float var6, float var7, boolean var8, PkgType var9) {
         this.field1 = var1;
         float var10 = 0.0F / var6;
         float var11 = 0.0F / var7;
         var1[0] = var1[0].method1(var4 / var6 - var10, var3 / var7 + var11);
         var1[1] = var1[1].method1(var2 / var6 + var10, var3 / var7 + var11);
         var1[2] = var1[2].method1(var2 / var6 + var10, var5 / var7 - var11);
         var1[3] = var1[3].method1(var4 / var6 - var10, var5 / var7 - var11);
         if (var8) {
            int var12 = var1.length;

            for (int var13 = 0; var13 < var12 / 2; var13++) {
               Pkg2.Data var14 = var1[var13];
               var1[var13] = var1[var12 - 1 - var13];
               var1[var12 - 1 - var13] = var14;
            }
         }

         this.field2 = new Vector3f(var9.getStepX(), var9.getStepY(), var9.getStepZ());
         if (var8) {
            this.field2.mul(-1.0F, 1.0F, 1.0F);
         }
      }
   }
}
