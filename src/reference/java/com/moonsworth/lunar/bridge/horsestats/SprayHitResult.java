package com.moonsworth.lunar.bridge.horsestats;

import lombok.Generated;
import org.joml.Vector3d;

public class SprayHitResult implements Horsestats_3<SprayHitResult, SprayHitResult.Data> {
   public static SprayHitResult method5() {
      return new SprayHitResult();
   }

   public static SprayHitResult.Data method2(Object var0, Vector3d var1) {
      return new SprayHitResult.Data(var0, var1);
   }

   public SprayHitResult method6() {
      return method5();
   }

   @Generated
   private SprayHitResult() {
   }

   public static class Data extends SprayHitResult {
      private final Object field1;
      private final Vector3d field2;

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.SPRAY;
      }

      @Override
      public SprayHitResult method6() {
         return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1, new Vector3d(this.field2));
      }

      @Generated
      public Object method7() {
         return this.field1;
      }

      @Generated
      public Vector3d method8() {
         return this.field2;
      }

      @Generated
      private Data(Object var1, Vector3d var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
