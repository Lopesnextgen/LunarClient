package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public abstract class RoomEvent {
   private final long field1 = Ref.method3().bridge$getSystemTime();

   public RoomEvent() {
   }

   public abstract void method1(DungeonRoomTracker holograms4iterator1);

   public abstract void method2(DungeonRoomTracker holograms4iterator1);

   @Generated
   public long getTimestamp() {
      return this.field1;
   }
}
