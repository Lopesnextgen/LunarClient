package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Bridge_61;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ClientEntityHitResult implements Horsestats_3<ClientEntityHitResult, ClientEntityHitResult.Data> {
   @Nullable
   public Bridge_61 method5() {
      return null;
   }

   public static ClientEntityHitResult method6() {
      return new ClientEntityHitResult();
   }

   public static ClientEntityHitResult.Data method3(Bridge_61 var0) {
      return new ClientEntityHitResult.Data(var0);
   }

   public ClientEntityHitResult method7() {
      return method6();
   }

   @Generated
   private ClientEntityHitResult() {
   }

   public static class Data extends ClientEntityHitResult {
      private final Bridge_61 field1;

      @Override
      public Horsestats$Type method1() {
         return Horsestats$Type.CLIENT_ENTITY;
      }

      @Override
      public ClientEntityHitResult method7() {
         return HHRROIIHRRICIIHIIHICRHHRHOHHOO(this.field1);
      }

      @Generated
      @Override
      public Bridge_61 method5() {
         return this.field1;
      }

      @Generated
      private Data(Bridge_61 var1) {
         this.field1 = var1;
      }
   }
}
