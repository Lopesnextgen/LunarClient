package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import lombok.Generated;

public class RenameRoomAction extends RoomEvent {
   private final String field2;
   private final String newName;

   @Override
   public void method1(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().setDisplayName(this.newName);
   }

   @Override
   public void method2(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().setDisplayName(this.field2);
   }

   @Generated
   public RenameRoomAction(String text, String text2) {
      this.field2 = text;
      this.newName = text2;
   }
}
