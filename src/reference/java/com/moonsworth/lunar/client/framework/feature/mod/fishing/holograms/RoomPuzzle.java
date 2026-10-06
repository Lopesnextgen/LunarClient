package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

public class RoomPuzzle {
   private final DungeonStateTracker field1;
   private String field2;
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState field3 = null;

   public RoomPuzzle(DungeonStateTracker holograms2_51, String text, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state) {
      this.field1 = holograms2_51;
      this.method2(text, state);
   }

   private void method1(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state) {
      if (this.field3 != state) {
         if ((this.field3 == null || this.field3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT)
            && (
               state == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.OPENED
                  || state == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED
                  || state == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.FAILED
            )
            && !this.field1.method35(this)) {
            this.field3 = com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT;
         } else {
            this.field3 = state;
         }
      }
   }

   public String getName() {
      return this.field2;
   }

   public void method2(String text, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state) {
      this.field2 = text;
      if (this.field2.equals("???")) {
         state = com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT;
      }

      this.method1(state);
   }
}
