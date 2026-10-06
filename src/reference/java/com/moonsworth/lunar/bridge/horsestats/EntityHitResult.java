package com.moonsworth.lunar.bridge.horsestats;

import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class EntityHitResult implements Horsestats_3<EntityHitResult, EntityHitResult.Data> {
   @Nullable
   public BridgeExtension method5() {
      return null;
   }

   public static EntityHitResult method6() {
      return new EntityHitResult();
   }

   public static EntityHitResult.Data method3(com.moonsworth.lunar.bridge.BridgeExtension var0) {
      return new EntityHitResult.Data(var0);
   }

   public EntityHitResult method7() {
      return method6();
   }

   @Generated
   private EntityHitResult() {
   }

   public static class Data extends EntityHitResult {
      private final com.moonsworth.lunar.bridge.BridgeExtension field1;

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.ENTITY;
      }

      @Override
      public EntityHitResult method7() {
         return ICRHORIIHOHROHOHOCOOHOOCOORRHO(this.field1);
      }

      @Generated
      @Override
      public com.moonsworth.lunar.bridge.BridgeExtension method5() {
         return this.field1;
      }

      @Generated
      private Data(com.moonsworth.lunar.bridge.BridgeExtension var1) {
         this.field1 = var1;
      }
   }
}
