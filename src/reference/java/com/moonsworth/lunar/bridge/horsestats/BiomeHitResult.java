package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import lombok.Generated;

public class BiomeHitResult implements Horsestats_3<BiomeHitResult, BiomeHitResult.Data> {
   public static BiomeHitResult method5() {
      return new BiomeHitResult();
   }

   public static BiomeHitResult.Data method2(Vector3iBridge var0, Itemcounter_3 var1) {
      return new BiomeHitResult.Data(var0, var1);
   }

   public BiomeHitResult method6() {
      return method5();
   }

   @Generated
   private BiomeHitResult() {
   }

   public static class Data extends BiomeHitResult {
      private final Vector3iBridge field1;
      private final Itemcounter_3 field2;

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.BIOME;
      }

      @Override
      public BiomeHitResult method6() {
         return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1, this.field2);
      }

      @Generated
      public Vector3iBridge method7() {
         return this.field1;
      }

      @Generated
      public Itemcounter_3 method8() {
         return this.field2;
      }

      @Generated
      private Data(Vector3iBridge var1, Itemcounter_3 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
