package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import lombok.Generated;

public class SetRoomCoordinatesAction extends RoomEvent {
   private final int field2;
   private final int field3;
   private final int field4;
   private final int field5;

   @Override
   public void method1(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().method17(this.field3);
      holograms4iterator1.method30().method10(this.field5);
   }

   @Override
   public void method2(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().method17(this.field2);
      holograms4iterator1.method30().method10(this.field4);
   }

   @Generated
   public SetRoomCoordinatesAction(int value, int value2, int value3, int value4) {
      this.field2 = value;
      this.field3 = value2;
      this.field4 = value3;
      this.field5 = value4;
   }
}
