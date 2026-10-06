package com.moonsworth.lunar.client.hitcolor;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;

public class FogHandler implements LoadableHandler, EventRegistrar {
   private static final Map<UUID, FogHandler.Data> field1 = new ConcurrentHashMap<>();

   public FogHandler() {
      this.handle(EventPlayerRemoval.class, var0 -> field1.remove(var0.method1().bridge$getUniqueID()));
   }

   public static FogHandler.Data method1(BridgeExtension var0) {
      return field1.computeIfAbsent(var0.bridge$getUniqueID(), FogHandler.Data::new);
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
   }

   public static final class Data {
      private final UUID field1;
      private long field2 = 0L;
      private long field3 = 0L;
      private long field4;

      public Data(UUID var1) {
         this.field1 = var1;
      }

      @Generated
      public void method1(long var1) {
         this.field2 = var1;
      }

      @Generated
      public void method2(long var1) {
         this.field3 = var1;
      }

      @Generated
      public void method3(long var1) {
         this.field4 = var1;
      }

      @Generated
      public UUID method4() {
         return this.field1;
      }

      @Generated
      public long method5() {
         return this.field2;
      }

      @Generated
      public long method6() {
         return this.field3;
      }

      @Generated
      public long method7() {
         return this.field4;
      }
   }
}
