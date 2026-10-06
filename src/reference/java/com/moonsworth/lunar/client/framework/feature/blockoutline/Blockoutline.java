package com.moonsworth.lunar.client.framework.feature.blockoutline;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge3_10;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectHitType;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter$Type2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import javax.annotation.Nullable;
import lombok.Generated;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.joml.Vector4f;

public final class Blockoutline {
   public static void method1(
      Bridge_28 var0,
      @Nullable HorsestatsType_2 var1,
      AxisAlignedBBBridge var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10
   ) {
      double var11 = var2.bridge$getMinX();
      double var13 = var2.bridge$getMinY();
      double var15 = var2.bridge$getMinZ();
      double var17 = var2.bridge$getMaxX();
      double var19 = var2.bridge$getMaxY();
      double var21 = var2.bridge$getMaxZ();
      if (var1 != null) {
         MovingObjectPositionHitResult var23 = ThreadModuleDump63.method3().bridge$getObjectMouseOver();
         if (var23.bridge$isTypeOfHit(MovingObjectHitType.BLOCK)) {
            method3(var0, var23.bridge$getSideHit(), var11, var17, var13, var19, var15, var21, var3, var4, var5, var6, var7, var8, var9, var10);
            return;
         }
      }

      method2(var0, var11, var17, var13, var15, var21, var3, var4, var5, var6, var7, var8, var9, var10);
      method2(var0, var11, var17, var19, var15, var21, var7, var8, var9, var10, var3, var4, var5, var6);
      var0.method6(var11, var13, var15, var11, var19, var15, var3, var4, var5, var6, var7, var8, var9, var10);
      var0.method6(var17, var13, var15, var17, var19, var15, var7, var8, var9, var10, var3, var4, var5, var6);
      var0.method6(var17, var13, var21, var17, var19, var21, var3, var4, var5, var6, var7, var8, var9, var10);
      var0.method6(var11, var13, var21, var11, var19, var21, var7, var8, var9, var10, var3, var4, var5, var6);
   }

   private static void method2(
      Bridge_28 var0,
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16,
      float var17,
      float var18
   ) {
      var0.method6(var1, var5, var7, var3, var5, var7, var11, var12, var13, var14, var15, var16, var17, var18);
      var0.method6(var3, var5, var7, var3, var5, var9, var15, var16, var17, var18, var11, var12, var13, var14);
      var0.method6(var3, var5, var9, var1, var5, var9, var11, var12, var13, var14, var15, var16, var17, var18);
      var0.method6(var1, var5, var9, var1, var5, var7, var15, var16, var17, var18, var11, var12, var13, var14);
   }

   private static void method3(
      Bridge_28 var0,
      HorsestatsType_2 var1,
      double var2,
      double var4,
      double var6,
      double var8,
      double var10,
      double var12,
      float var14,
      float var15,
      float var16,
      float var17,
      float var18,
      float var19,
      float var20,
      float var21
   ) {
      switch (var1) {
         case DOWN:
            method2(var0, var2, var4, var6, var10, var12, var14, var15, var16, var17, var18, var19, var20, var21);
            break;
         case UP:
            method2(var0, var2, var4, var8, var10, var12, var14, var15, var16, var17, var18, var19, var20, var21);
            break;
         case NORTH:
            var0.method6(var4, var8, var10, var4, var6, var10, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var4, var6, var10, var2, var6, var10, var18, var19, var20, var21, var14, var15, var16, var17);
            var0.method6(var2, var6, var10, var2, var8, var10, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var2, var8, var10, var4, var8, var10, var18, var19, var20, var21, var14, var15, var16, var17);
            break;
         case SOUTH:
            var0.method6(var4, var8, var12, var4, var6, var12, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var4, var6, var12, var2, var6, var12, var18, var19, var20, var21, var14, var15, var16, var17);
            var0.method6(var2, var6, var12, var2, var8, var12, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var2, var8, var12, var4, var8, var12, var18, var19, var20, var21, var14, var15, var16, var17);
            break;
         case WEST:
            var0.method6(var2, var6, var12, var2, var6, var10, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var2, var6, var10, var2, var8, var10, var18, var19, var20, var21, var14, var15, var16, var17);
            var0.method6(var2, var8, var10, var2, var8, var12, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var2, var8, var12, var2, var6, var12, var18, var19, var20, var21, var14, var15, var16, var17);
            break;
         case EAST:
            var0.method6(var4, var6, var12, var4, var6, var10, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var4, var6, var10, var4, var8, var10, var18, var19, var20, var21, var14, var15, var16, var17);
            var0.method6(var4, var8, var10, var4, var8, var12, var14, var15, var16, var17, var18, var19, var20, var21);
            var0.method6(var4, var8, var12, var4, var6, var12, var18, var19, var20, var21, var14, var15, var16, var17);
      }
   }

   @Annotation2(min = 6)
   public static void method4(
      Bridge5_16 var0,
      Bridge_28 var1,
      @Nullable HorsestatsType_2 var2,
      Itemcounter_4 var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11
   ) {
      if (!var3.bridge$isEmpty()) {
         AxisAlignedBBBridge var12 = var3.bridge$bounds();
         Vector3d var13 = new Vector3d(var12.bridge$getMinX(), var12.bridge$getMinY(), var12.bridge$getMinZ());
         Vector3d var14 = new Vector3d(var12.bridge$getMaxX(), var12.bridge$getMaxY(), var12.bridge$getMaxZ());
         Vector4f var15 = new Vector4f(var4, var5, var6, var7);
         Vector4f var16 = new Vector4f(var8, var9, var10, var11);
         Vector3d var17 = new Vector3d();
         Vector4f var18 = new Vector4f();
         Vector3d var19 = new Vector3d();
         Bridge3_10 var20 = var0.bridge$last();
         MixinHelper_21 var21 = var20.bridge$pose();
         Matrix3fBridge var22 = var20.bridge$normal();
         if (var2 != null) {
            double var23 = var12.bridge$getMinX();
            double var25 = var12.bridge$getMinY();
            double var27 = var12.bridge$getMinZ();
            double var29 = var12.bridge$getMaxX();
            double var31 = var12.bridge$getMaxY();
            double var33 = var12.bridge$getMaxZ();

            Itemcounter$Type2 var35 = switch (var2) {
               case DOWN, UP -> Itemcounter$Type2.FORWARD;
               case NORTH, SOUTH -> Itemcounter$Type2.NONE;
               case WEST, EAST -> Itemcounter$Type2.BACKWARD;
            };
            var3.bridge$forAllAxisEdgesExclude(
               var35,
               (var23x, var25x, var27x, var29x, var31x, var33x) -> {
                  switch (var2) {
                     case DOWN:
                        if (var25x != var25 || var31x != var25) {
                           return;
                        }
                        break;
                     case UP:
                        if (var25x != var31 || var31x != var31) {
                           return;
                        }
                        break;
                     case NORTH:
                        if (var27x != var27 || var33x != var27) {
                           return;
                        }
                        break;
                     case SOUTH:
                        if (var27x != var33 || var33x != var33) {
                           return;
                        }
                        break;
                     case WEST:
                        if (var23x != var23 || var29x != var23) {
                           return;
                        }
                        break;
                     case EAST:
                        if (var23x != var29 || var29x != var29) {
                           return;
                        }
                        break;
                     default:
                        throw new IncompatibleClassChangeError();
                  }

                  float var35x = (float)(var29x - var23x);
                  float var36 = (float)(var31x - var25x);
                  float var37 = (float)(var33x - var27x);
                  float var38 = (float)Math.sqrt(var35x * var35x + var36 * var36 + var37 * var37);
                  var35x /= var38;
                  var36 /= var38;
                  var37 /= var38;
                  method8(var15, var16, var17.set(var23x, var25x, var27x), var13, var14, var18, var19);
                  var1.method8(var21, (float)var23x, (float)var25x, (float)var27x)
                     .method9(var18.x, var18.y, var18.z, var18.w)
                     .method12(var22, var35x, var36, var37)
                     .endVertex();
                  method8(var15, var16, var17.set(var29x, var31x, var33x), var13, var14, var18, var19);
                  var1.method8(var21, (float)var29x, (float)var31x, (float)var33x)
                     .method9(var18.x, var18.y, var18.z, var18.w)
                     .method12(var22, var35x, var36, var37)
                     .endVertex();
               }
            );
         } else {
            var3.bridge$forAllEdges(
               (var10x, var12x, var14x, var16x, var18x, var20x) -> {
                  float var22x = (float)(var16x - var10x);
                  float var23x = (float)(var18x - var12x);
                  float var24 = (float)(var20x - var14x);
                  float var25x = (float)Math.sqrt(var22x * var22x + var23x * var23x + var24 * var24);
                  var22x /= var25x;
                  var23x /= var25x;
                  var24 /= var25x;
                  method8(var15, var16, var17.set(var10x, var12x, var14x), var13, var14, var18, var19);
                  var1.method8(var21, (float)var10x, (float)var12x, (float)var14x)
                     .method9(var18.x, var18.y, var18.z, var18.w)
                     .method12(var22, var22x, var23x, var24)
                     .endVertex();
                  method8(var15, var16, var17.set(var16x, var18x, var20x), var13, var14, var18, var19);
                  var1.method8(var21, (float)var16x, (float)var18x, (float)var20x)
                     .method9(var18.x, var18.y, var18.z, var18.w)
                     .method12(var22, var22x, var23x, var24)
                     .endVertex();
               }
            );
         }
      }
   }

   @Annotation2(min = 6)
   public static void method5(
      Bridge5_16 var0,
      Bridge_28 var1,
      @Nullable HorsestatsType_2 var2,
      Itemcounter_4 var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11
   ) {
      if (var2 != null) {
         method6(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      } else {
         Bridge3_10 var12 = var0.bridge$last();
         MixinHelper_21 var13 = var12.bridge$pose();
         Matrix3fBridge var14 = var12.bridge$normal();
         var3.bridge$forAllEdges((var11x, var13x, var15, var17, var19, var21) -> {
            float var23 = (float)(var17 - var11x);
            float var24 = (float)(var19 - var13x);
            float var25 = (float)(var21 - var15);
            float var26 = (float)Math.sqrt(var23 * var23 + var24 * var24 + var25 * var25);
            var23 /= var26;
            var24 /= var26;
            var25 /= var26;
            var1.method8(var13, (float)var11x, (float)var13x, (float)var15).method9(var4, var5, var6, var7).method12(var14, var23, var24, var25).endVertex();
            var1.method8(var13, (float)var17, (float)var19, (float)var21).method9(var8, var9, var10, var11).method12(var14, var23, var24, var25).endVertex();
         });
      }
   }

   @Annotation2(min = 6)
   public static void method6(
      Bridge5_16 var0,
      Bridge_28 var1,
      HorsestatsType_2 var2,
      Itemcounter_4 var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11
   ) {
      AxisAlignedBBBridge var12 = var3.bridge$bounds();
      double var13 = var12.bridge$getMinX();
      double var15 = var12.bridge$getMinY();
      double var17 = var12.bridge$getMinZ();
      double var19 = var12.bridge$getMaxX();
      double var21 = var12.bridge$getMaxY();
      double var23 = var12.bridge$getMaxZ();
      Bridge3_10 var25 = var0.bridge$last();
      MixinHelper_21 var26 = var25.bridge$pose();
      Matrix3fBridge var27 = var25.bridge$normal();

      Itemcounter$Type2 var28 = switch (var2) {
         case DOWN, UP -> Itemcounter$Type2.FORWARD;
         case NORTH, SOUTH -> Itemcounter$Type2.NONE;
         case WEST, EAST -> Itemcounter$Type2.BACKWARD;
      };
      var3.bridge$forAllAxisEdgesExclude(var28, (var24, var26x, var28x, var30, var32, var34) -> {
         switch (var2) {
            case DOWN:
               if (var26x != var15 || var32 != var15) {
                  return;
               }
               break;
            case UP:
               if (var26x != var21 || var32 != var21) {
                  return;
               }
               break;
            case NORTH:
               if (var28x != var17 || var34 != var17) {
                  return;
               }
               break;
            case SOUTH:
               if (var28x != var23 || var34 != var23) {
                  return;
               }
               break;
            case WEST:
               if (var24 != var13 || var30 != var13) {
                  return;
               }
               break;
            case EAST:
               if (var24 != var19 || var30 != var19) {
                  return;
               }
               break;
            default:
               throw new IncompatibleClassChangeError();
         }

         float var36 = (float)(var30 - var24);
         float var37 = (float)(var32 - var26x);
         float var38 = (float)(var34 - var28x);
         float var39 = (float)Math.sqrt(var36 * var36 + var37 * var37 + var38 * var38);
         var36 /= var39;
         var37 /= var39;
         var38 /= var39;
         var1.method8(var26, (float)var24, (float)var26x, (float)var28x).method9(var4, var5, var6, var7).method12(var27, var36, var37, var38).endVertex();
         var1.method8(var26, (float)var30, (float)var32, (float)var34).method9(var8, var9, var10, var11).method12(var27, var36, var37, var38).endVertex();
      });
   }

   @Annotation2(min = 6)
   public static void method7(
      AbstractRenderContext var0,
      @Nullable HorsestatsType_2 var1,
      Itemcounter_4 var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      RenderLayerBridge var11
   ) {
      AxisAlignedBBBridge var12 = var2.bridge$bounds();
      Vector3d var13 = new Vector3d(var12.bridge$getMinX(), var12.bridge$getMinY(), var12.bridge$getMinZ());
      Vector3d var14 = new Vector3d(var12.bridge$getMaxX(), var12.bridge$getMaxY(), var12.bridge$getMaxZ());
      Vector4f var15 = new Vector4f(var3, var4, var5, var6);
      Vector4f var16 = new Vector4f(var7, var8, var9, var10);
      Vector3d var17 = new Vector3d();
      Vector4f var18 = new Vector4f();
      Vector3d var19 = new Vector3d();
      if (var1 != null) {
         for (AxisAlignedBBBridge var31 : var2.bridge$toAabbs()) {
            float var32 = (float)var31.bridge$getMinX();
            float var34 = (float)var31.bridge$getMinY();
            float var36 = (float)var31.bridge$getMinZ();
            float var38 = (float)var31.bridge$getMaxX();
            float var40 = (float)var31.bridge$getMaxY();
            float var42 = (float)var31.bridge$getMaxZ();
            Bridge2_32 var44 = var0.method10(var11);
            var44.method1();
            switch (var1) {
               case DOWN:
                  var34 -= 5.0E-4F;
                  method8(var15, var16, var17.set(var38, var34, var36), var13, var14, var18, var19);
                  var44.method2(var38, var34, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var34, var42), var13, var14, var18, var19);
                  var44.method2(var38, var34, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var34, var42), var13, var14, var18, var19);
                  var44.method2(var32, var34, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var34, var36), var13, var14, var18, var19);
                  var44.method2(var32, var34, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  break;
               case UP:
                  var40 += 5.0E-4F;
                  method8(var15, var16, var17.set(var32, var40, var36), var13, var14, var18, var19);
                  var44.method2(var32, var40, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var40, var42), var13, var14, var18, var19);
                  var44.method2(var32, var40, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var40, var42), var13, var14, var18, var19);
                  var44.method2(var38, var40, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var40, var36), var13, var14, var18, var19);
                  var44.method2(var38, var40, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  break;
               case NORTH:
                  var36 -= 5.0E-4F;
                  method8(var15, var16, var17.set(var38, var40, var36), var13, var14, var18, var19);
                  var44.method2(var38, var40, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var34, var36), var13, var14, var18, var19);
                  var44.method2(var38, var34, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var34, var36), var13, var14, var18, var19);
                  var44.method2(var32, var34, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var40, var36), var13, var14, var18, var19);
                  var44.method2(var32, var40, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  break;
               case SOUTH:
                  var42 += 5.0E-4F;
                  method8(var15, var16, var17.set(var32, var40, var42), var13, var14, var18, var19);
                  var44.method2(var32, var40, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var34, var42), var13, var14, var18, var19);
                  var44.method2(var32, var34, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var34, var42), var13, var14, var18, var19);
                  var44.method2(var38, var34, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var40, var42), var13, var14, var18, var19);
                  var44.method2(var38, var40, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  break;
               case WEST:
                  var32 -= 5.0E-4F;
                  method8(var15, var16, var17.set(var32, var40, var42), var13, var14, var18, var19);
                  var44.method2(var32, var40, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var40, var36), var13, var14, var18, var19);
                  var44.method2(var32, var40, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var34, var36), var13, var14, var18, var19);
                  var44.method2(var32, var34, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var32, var34, var42), var13, var14, var18, var19);
                  var44.method2(var32, var34, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  break;
               case EAST:
                  var38 += 5.0E-4F;
                  method8(var15, var16, var17.set(var38, var34, var42), var13, var14, var18, var19);
                  var44.method2(var38, var34, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var34, var36), var13, var14, var18, var19);
                  var44.method2(var38, var34, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var40, var36), var13, var14, var18, var19);
                  var44.method2(var38, var40, var36).method8(var18.x, var18.y, var18.z, var18.w).method16();
                  method8(var15, var16, var17.set(var38, var40, var42), var13, var14, var18, var19);
                  var44.method2(var38, var40, var42).method8(var18.x, var18.y, var18.z, var18.w).method16();
            }

            var44.method17(BufferBuildMode.BATCHED);
         }
      } else {
         for (AxisAlignedBBBridge var21 : var2.bridge$toAabbs()) {
            var21 = var21.method11(5.0E-4);
            float var22 = (float)var21.bridge$getMinX();
            float var23 = (float)var21.bridge$getMinY();
            float var24 = (float)var21.bridge$getMinZ();
            float var25 = (float)var21.bridge$getMaxX();
            float var26 = (float)var21.bridge$getMaxY();
            float var27 = (float)var21.bridge$getMaxZ();
            Bridge2_32 var28 = var0.method10(var11);
            var28.method1();
            method8(var15, var16, var17.set(var22, var23, var24), var13, var14, var18, var19);
            var28.method2(var22, var23, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var23, var27), var13, var14, var18, var19);
            var28.method2(var22, var23, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var26, var27), var13, var14, var18, var19);
            var28.method2(var22, var26, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var26, var24), var13, var14, var18, var19);
            var28.method2(var22, var26, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var26, var27), var13, var14, var18, var19);
            var28.method2(var22, var26, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var23, var27), var13, var14, var18, var19);
            var28.method2(var22, var23, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var23, var27), var13, var14, var18, var19);
            var28.method2(var25, var23, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var26, var27), var13, var14, var18, var19);
            var28.method2(var25, var26, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var23, var27), var13, var14, var18, var19);
            var28.method2(var25, var23, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var23, var24), var13, var14, var18, var19);
            var28.method2(var25, var23, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var26, var24), var13, var14, var18, var19);
            var28.method2(var25, var26, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var26, var27), var13, var14, var18, var19);
            var28.method2(var25, var26, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var26, var24), var13, var14, var18, var19);
            var28.method2(var25, var26, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var23, var24), var13, var14, var18, var19);
            var28.method2(var25, var23, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var23, var24), var13, var14, var18, var19);
            var28.method2(var22, var23, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var26, var24), var13, var14, var18, var19);
            var28.method2(var22, var26, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var23, var24), var13, var14, var18, var19);
            var28.method2(var22, var23, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var23, var24), var13, var14, var18, var19);
            var28.method2(var25, var23, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var23, var27), var13, var14, var18, var19);
            var28.method2(var25, var23, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var23, var27), var13, var14, var18, var19);
            var28.method2(var22, var23, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var26, var24), var13, var14, var18, var19);
            var28.method2(var22, var26, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var22, var26, var27), var13, var14, var18, var19);
            var28.method2(var22, var26, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var26, var27), var13, var14, var18, var19);
            var28.method2(var25, var26, var27).method8(var18.x, var18.y, var18.z, var18.w).method16();
            method8(var15, var16, var17.set(var25, var26, var24), var13, var14, var18, var19);
            var28.method2(var25, var26, var24).method8(var18.x, var18.y, var18.z, var18.w).method16();
            var28.method17(BufferBuildMode.BATCHED);
         }
      }
   }

   public static void method8(Vector4f var0, Vector4f var1, Vector3d var2, Vector3d var3, Vector3d var4, Vector4f var5, Vector3d var6) {
      double var7 = var2.sub(var3, var6).length() / var4.sub(var3, var6).length();
      var5.x = (float)org.joml.Math.fma(var1.x() - var0.x(), var7, var0.x());
      var5.y = (float)org.joml.Math.fma(var1.y() - var0.y(), var7, var0.y());
      var5.z = (float)org.joml.Math.fma(var1.z() - var0.z(), var7, var0.z());
      var5.w = (float)org.joml.Math.fma(var1.w() - var0.w(), var7, var0.w());
   }

   public static void method9(
      AbstractRenderContext var0,
      @Nullable HorsestatsType_2 var1,
      AxisAlignedBBBridge var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      RenderLayerBridge var11
   ) {
      if (var1 != null) {
         method10(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      } else {
         var2 = var2.method11(5.0E-4);
         float var12 = (float)var2.bridge$getMinX();
         float var13 = (float)var2.bridge$getMinY();
         float var14 = (float)var2.bridge$getMinZ();
         float var15 = (float)var2.bridge$getMaxX();
         float var16 = (float)var2.bridge$getMaxY();
         float var17 = (float)var2.bridge$getMaxZ();
         Bridge2_32 var18 = var0.method10(var11);
         var18.method1();
         var18.method2(var12, var13, var14).method8(var3, var4, var5, var6).method16();
         var18.method2(var12, var13, var17).method8(var3, var4, var9, var6).method16();
         var18.method2(var12, var16, var17).method8(var3, var8, var9, var6).method16();
         var18.method2(var12, var16, var14).method8(var3, var8, var5, var6).method16();
         var18.method2(var12, var16, var17).method8(var3, var8, var9, var6).method16();
         var18.method2(var12, var13, var17).method8(var3, var4, var9, var6).method16();
         var18.method2(var15, var13, var17).method8(var7, var4, var9, var10).method16();
         var18.method2(var15, var16, var17).method8(var7, var8, var9, var10).method16();
         var18.method2(var15, var13, var17).method8(var7, var4, var9, var10).method16();
         var18.method2(var15, var13, var14).method8(var7, var4, var5, var10).method16();
         var18.method2(var15, var16, var14).method8(var7, var8, var5, var10).method16();
         var18.method2(var15, var16, var17).method8(var7, var8, var9, var10).method16();
         var18.method2(var15, var16, var14).method8(var7, var8, var5, var10).method16();
         var18.method2(var15, var13, var14).method8(var7, var4, var5, var10).method16();
         var18.method2(var12, var13, var14).method8(var3, var4, var5, var6).method16();
         var18.method2(var12, var16, var14).method8(var3, var8, var5, var6).method16();
         var18.method2(var12, var13, var14).method8(var3, var4, var5, var6).method16();
         var18.method2(var15, var13, var14).method8(var7, var4, var5, var10).method16();
         var18.method2(var15, var13, var17).method8(var7, var4, var9, var10).method16();
         var18.method2(var12, var13, var17).method8(var3, var4, var9, var6).method16();
         var18.method2(var12, var16, var14).method8(var3, var8, var5, var6).method16();
         var18.method2(var12, var16, var17).method8(var3, var8, var9, var6).method16();
         var18.method2(var15, var16, var17).method8(var7, var8, var9, var10).method16();
         var18.method2(var15, var16, var14).method8(var7, var8, var5, var10).method16();
         var18.method17(BufferBuildMode.BATCHED);
      }
   }

   public static void method10(
      AbstractRenderContext var0,
      HorsestatsType_2 var1,
      AxisAlignedBBBridge var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      RenderLayerBridge var11
   ) {
      float var12 = (float)var2.bridge$getMinX();
      float var13 = (float)var2.bridge$getMinY();
      float var14 = (float)var2.bridge$getMinZ();
      float var15 = (float)var2.bridge$getMaxX();
      float var16 = (float)var2.bridge$getMaxY();
      float var17 = (float)var2.bridge$getMaxZ();
      Bridge2_32 var18 = var0.method10(var11);
      var18.method1();
      switch (var1) {
         case DOWN:
            var13 -= 5.0E-4F;
            var18.method2(var15, var13, var14).method8(var7, var8, var9, var10).method16();
            var18.method2(var15, var13, var17).method8(var7, var8, var9, var10).method16();
            var18.method2(var12, var13, var17).method8(var3, var4, var5, var6).method16();
            var18.method2(var12, var13, var14).method8(var3, var4, var5, var6).method16();
            break;
         case UP:
            var16 += 5.0E-4F;
            var18.method2(var12, var16, var14).method8(var7, var8, var9, var10).method16();
            var18.method2(var12, var16, var17).method8(var7, var8, var9, var10).method16();
            var18.method2(var15, var16, var17).method8(var3, var4, var5, var6).method16();
            var18.method2(var15, var16, var14).method8(var3, var4, var5, var6).method16();
            break;
         case NORTH:
            var14 -= 5.0E-4F;
            var18.method2(var15, var16, var14).method8(var7, var8, var9, var10).method16();
            var18.method2(var15, var13, var14).method8(var7, var8, var9, var10).method16();
            var18.method2(var12, var13, var14).method8(var3, var4, var5, var6).method16();
            var18.method2(var12, var16, var14).method8(var3, var4, var5, var6).method16();
            break;
         case SOUTH:
            var17 += 5.0E-4F;
            var18.method2(var12, var16, var17).method8(var7, var8, var9, var10).method16();
            var18.method2(var12, var13, var17).method8(var7, var8, var9, var10).method16();
            var18.method2(var15, var13, var17).method8(var3, var4, var5, var6).method16();
            var18.method2(var15, var16, var17).method8(var3, var4, var5, var6).method16();
            break;
         case WEST:
            var12 -= 5.0E-4F;
            var18.method2(var12, var16, var17).method8(var7, var8, var9, var10).method16();
            var18.method2(var12, var16, var14).method8(var7, var8, var9, var10).method16();
            var18.method2(var12, var13, var14).method8(var3, var4, var5, var6).method16();
            var18.method2(var12, var13, var17).method8(var3, var4, var5, var6).method16();
            break;
         case EAST:
            var15 += 5.0E-4F;
            var18.method2(var15, var13, var17).method8(var7, var8, var9, var10).method16();
            var18.method2(var15, var13, var14).method8(var7, var8, var9, var10).method16();
            var18.method2(var15, var16, var14).method8(var3, var4, var5, var6).method16();
            var18.method2(var15, var16, var17).method8(var3, var4, var5, var6).method16();
      }

      var18.method17(BufferBuildMode.BATCHED);
   }

   @Annotation2(min = 6)
   public static Itemcounter_4 method11(Itemcounter_4 var0) {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 == null) {
         return var0;
      }

      MovingObjectPositionHitResult var2 = ThreadModuleDump63.method3().bridge$getObjectMouseOver();
      if (var2.bridge$isTypeOfHit(MovingObjectHitType.BLOCK)) {
         Horsestats20Extension2 var3 = var2.bridge$getBlockPosition();
         Bridge2_17 var4 = var1.method2(var3);
         HorsestatsType_2 var5 = var4.bridge$getBlock().bridge$isMultiBlock(var1, var3);
         if (var5 != null) {
            Vector3i var6 = var5.getVector();
            Vector3iBridge var7 = var3.bridge$add(var6);
            Bridge2_17 var8 = var1.method2(var7);
            Bridge2_19 var9 = ThreadModuleDump63.method13().bridge$getCamera().get();
            Itemcounter_4 var10 = var8.bridge$getBlockShape(var1, var7);
            if (var10 != null) {
               if (ThreadModuleDump63.MC_VERSION >= 39) {
                  var10 = var10.bridge$move(var6.x(), var6.y(), var6.z());
               } else {
                  var10 = var10.bridge$move(
                     var7.bridge$getX() - var9.bridge$getPosX(), var7.bridge$getY() - var9.bridge$getPosY(), var7.bridge$getZ() - var9.bridge$getPosZ()
                  );
               }

               return var0.bridge$join(var10);
            }
         }
      }

      return var0;
   }

   @Generated
   private Blockoutline() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
