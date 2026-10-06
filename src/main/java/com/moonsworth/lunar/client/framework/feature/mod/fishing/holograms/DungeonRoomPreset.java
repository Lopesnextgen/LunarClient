package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

class DungeonRoomPreset {
   private final String field1;
   private final int field2;
   private final int field3;
   private final com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState field4;

   private DungeonRoomPreset(String text, int value, int value2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state) {
      this.field1 = text;
      this.field2 = value;
      this.field3 = value2;
      this.field4 = state;
   }

   public String name() {
      return this.field1;
   }

   public int method1() {
      return this.field2;
   }

   public int method2() {
      return this.field3;
   }

   public com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState method3() {
      return this.field4;
   }
}
