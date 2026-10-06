package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomSecret;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonSecretListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class RoomSecretEvent extends LunarEvent implements DynamicListenerEvent {
   private final RoomSecret field1;
   private final Vec3iBridge field2;

   @Generated
   public RoomSecretEvent(RoomSecret holograms_71, Vec3iBridge horsestats202) {
      this.field1 = holograms_71;
      this.field2 = horsestats202;
   }

   @Generated
   public RoomSecret method1() {
      return this.field1;
   }

   @Generated
   public Vec3iBridge method2() {
      return this.field2;
   }

   @TriggeredBy(DungeonSecretListener.class)
   public static class Data extends RoomSecretEvent {
      private final RoomSecretEvent.Data.Type field3;

      public Data(RoomSecret holograms_71, Vec3iBridge horsestats202, RoomSecretEvent.Data.Type type) {
         super(holograms_71, horsestats202);
         this.field3 = type;
      }

      @Generated
      public RoomSecretEvent.Data.Type method3() {
         return this.field3;
      }

      public enum Type {
         SUPERBOOM,
         LEVER,
         REDSTONE_KEY_PICKUP,
         REDSTONE_KEY_PLACED,
         LOCKED_CHEST,
         ROOM_COMPLETED;

         Type() {
         }
      }
   }

   @TriggeredBy(DungeonSecretListener.class)
   public static class RoomSecretCollectedEvent extends RoomSecretEvent {
      public RoomSecretCollectedEvent(RoomSecret holograms_71, Vec3iBridge horsestats202) {
         super(holograms_71, horsestats202);
      }
   }
}
