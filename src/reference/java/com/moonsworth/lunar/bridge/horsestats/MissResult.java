package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Bridge;
import lombok.Generated;

public class MissResult implements Horsestats_3<MissResult, MissResult.BlockPositionHitResult> {
   private static final Vec3Bridge field1 = Vec3Bridge.method2(0.0, 0.0, 0.0);
   private static final Vector3iBridge field2 = Bridge.method8().method4(0, 0, 0);

   public Vec3Bridge method5() {
      return field1;
   }

   public Vector3iBridge method6() {
      return field2;
   }

   public HorsestatsType_2 method7() {
      return HorsestatsType_2.UP;
   }

   public boolean method8() {
      return false;
   }

   public double method5(com.moonsworth.lunar.bridge.BridgeExtension var1) {
      return 0.0;
   }

   public static MissResult method9() {
      return new MissResult();
   }

   public static MissResult method7(Vec3Bridge var0, Vector3iBridge var1) {
      return new MissResult.BlockHitResult(var0, var1);
   }

   public static MissResult method8(Vector3iBridge var0) {
      return new MissResult.BlockPositionHitResult(var0);
   }

   public static MissResult method9(Vec3Bridge var0, Vector3iBridge var1, HorsestatsType_2 var2) {
      return new MissResult.DirectedBlockHitResult(var0, var1, var2);
   }

   public MissResult method10() {
      return method9();
   }

   @Generated
   private MissResult() {
   }

   public static class BlockPositionHitResult extends MissResult {
      private final Vector3iBridge field3;

      @Override
      public double method5(com.moonsworth.lunar.bridge.BridgeExtension var1) {
         double var2 = this.field3.bridge$getX() - var1.bridge$getPosX();
         double var4 = this.field3.bridge$getY() - var1.bridge$getPosY();
         double var6 = this.field3.bridge$getZ() - var1.bridge$getPosZ();
         return var2 * var2 + var4 * var4 + var6 * var6;
      }

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.BLOCK;
      }

      @Override
      public MissResult method10() {
         return OHOOORICRHIIIIRHCICICOCHROICRC(this.field3);
      }

      @Generated
      @Override
      public Vector3iBridge method6() {
         return this.field3;
      }

      @Generated
      protected BlockPositionHitResult(Vector3iBridge var1) {
         this.field3 = var1;
      }
   }

   public static class DirectedBlockHitResult extends MissResult.BlockHitResult {
      private final HorsestatsType_2 field5;

      protected DirectedBlockHitResult(Vec3Bridge var1, Vector3iBridge var2, HorsestatsType_2 var3) {
         super(var1, var2);
         this.field5 = var3;
      }

      @Override
      public boolean method8() {
         return false;
      }

      @Override
      public MissResult method10() {
         return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.method5(), this.method6(), this.field5);
      }

      @Generated
      @Override
      public HorsestatsType_2 method7() {
         return this.field5;
      }
   }

   public static class BlockHitResult extends MissResult.BlockPositionHitResult {
      private final Vec3Bridge field4;

      protected BlockHitResult(Vec3Bridge var1, Vector3iBridge var2) {
         super(var2);
         this.field4 = var1;
      }

      @Override
      public boolean method8() {
         return true;
      }

      @Override
      public MissResult method10() {
         return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field4, super.field3);
      }

      @Generated
      @Override
      public Vec3Bridge method5() {
         return this.field4;
      }
   }
}
