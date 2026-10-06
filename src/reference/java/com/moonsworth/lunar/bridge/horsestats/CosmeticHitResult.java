package com.moonsworth.lunar.bridge.horsestats;

import lombok.Generated;
import org.joml.Matrix4f;
import org.joml.Vector3d;

public class CosmeticHitResult implements Horsestats_3<CosmeticHitResult, CosmeticHitResult.Data> {
   public static CosmeticHitResult method5() {
      return new CosmeticHitResult();
   }

   public static CosmeticHitResult.Data method2(Object var0, AxisAlignedBBBridge var1, Vector3d var2, Matrix4f var3) {
      return new CosmeticHitResult.Data(var0, var1, var2, var3);
   }

   public CosmeticHitResult method6() {
      return method5();
   }

   @Generated
   private CosmeticHitResult() {
   }

   public static class Data extends CosmeticHitResult {
      private final Object field1;
      private final AxisAlignedBBBridge field2;
      private final Vector3d field3;
      private final Matrix4f field4;

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.COSMETIC;
      }

      @Override
      public CosmeticHitResult method6() {
         return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1, this.field2, new Vector3d(this.field3), new Matrix4f(this.field4));
      }

      @Generated
      public Object method7() {
         return this.field1;
      }

      @Generated
      public AxisAlignedBBBridge method8() {
         return this.field2;
      }

      @Generated
      public Vector3d method9() {
         return this.field3;
      }

      @Generated
      public Matrix4f method10() {
         return this.field4;
      }

      @Generated
      private Data(Object var1, AxisAlignedBBBridge var2, Vector3d var3, Matrix4f var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }
   }
}
