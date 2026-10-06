package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import lombok.Generated;

public class ChunkHitResult implements Horsestats_3<ChunkHitResult, ChunkHitResult.Data> {
   public static ChunkHitResult method5() {
      return new ChunkHitResult();
   }

   public static ChunkHitResult.Data method2(Itemcounter2 var0) {
      return new ChunkHitResult.Data(var0);
   }

   public ChunkHitResult method6() {
      return method5();
   }

   @Generated
   private ChunkHitResult() {
   }

   public static class Data extends ChunkHitResult {
      private final Itemcounter2 field1;

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.CHUNK;
      }

      @Override
      public ChunkHitResult method6() {
         return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1);
      }

      @Generated
      public Itemcounter2 method7() {
         return this.field1;
      }

      @Generated
      private Data(Itemcounter2 var1) {
         this.field1 = var1;
      }
   }
}
