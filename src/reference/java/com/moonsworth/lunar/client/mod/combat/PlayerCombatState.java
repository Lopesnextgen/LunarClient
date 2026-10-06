package com.moonsworth.lunar.client.mod.combat;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;

public class PlayerCombatState implements LoadableHandler, EventBusAccess {
   private static final Map<UUID, PlayerCombatState.Data> field1 = new ConcurrentHashMap<>();

   public PlayerCombatState() {
      this.handle(EventPlayerRemove.class, arg0 -> field1.remove(arg0.method1().bridge$getUniqueID()));
   }

   public static PlayerCombatState.Data method1(BridgeExtension bridge) {
      return field1.computeIfAbsent(bridge.bridge$getUniqueID(), PlayerCombatState.Data::new);
   }

   public void close() {
   }

   public void init() {
   }

   public static final class Data {
      private final UUID field1;
      private long field2 = 0L;
      private long field3 = 0L;
      private long field4;

      public Data(UUID uuid1) {
         this.field1 = uuid1;
      }

      @Generated
      public void method1(long number1) {
         this.field2 = number1;
      }

      @Generated
      public void method2(long number1) {
         this.field3 = number1;
      }

      @Generated
      public void method3(long number1) {
         this.field4 = number1;
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
