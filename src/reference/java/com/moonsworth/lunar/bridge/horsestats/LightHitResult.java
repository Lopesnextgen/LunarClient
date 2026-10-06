package com.moonsworth.lunar.bridge.horsestats;

import lombok.Generated;

public class LightHitResult implements Horsestats_3<LightHitResult, LightHitResult.Data> {
   public static LightHitResult method5() {
      return new LightHitResult();
   }

   public static LightHitResult.Data method2(Vector3iBridge var0, LightHitResult.Type var1, int var2) {
      return new LightHitResult.Data(var0, var1, var2);
   }

   public LightHitResult method6() {
      return method5();
   }

   @Generated
   private LightHitResult() {
   }

   public static class Data extends LightHitResult {
      private final Vector3iBridge field1;
      private final LightHitResult.Type field2;
      private final int field3;

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.LIGHT;
      }

      @Override
      public LightHitResult method6() {
         return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1, this.field2, this.field3);
      }

      @Generated
      public Vector3iBridge method7() {
         return this.field1;
      }

      @Generated
      public LightHitResult.Type method8() {
         return this.field2;
      }

      @Generated
      public int method9() {
         return this.field3;
      }

      @Generated
      private Data(Vector3iBridge var1, LightHitResult.Type var2, int var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }

   public enum Type {
      PACKED_LIGHT,
      BLOCK_LIGHT,
      SKY_LIGHT;
   }
}
