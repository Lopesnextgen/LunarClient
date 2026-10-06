package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonRoomRotation;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import lombok.Generated;
import org.joml.Vector3i;

public enum HologramsType_3 {
   EAST(7, 0),
   SOUTH(30, 7),
   WEST(23, 30),
   NORTH(0, 23);

   public static final HologramsType_3[] VALUES = values();
   private final int x;
   private final int z;

   public Vector3iBridge getCorner(Vector3iBridge var1) {
      Vector3i var2 = switch (this) {
         case SOUTH -> new Vector3i(0, 0, -7);
         case WEST -> new Vector3i(7, 0, 0);
         case NORTH -> new Vector3i(0, 0, 7);
         default -> new Vector3i(-7, 0, 0);
      };
      return var1.bridge$add(var2);
   }

   public Vector3iBridge inverseGetCorner(Vector3iBridge var1) {
      Vector3i var2 = switch (this) {
         case SOUTH -> new Vector3i(0, 0, -7);
         case WEST -> new Vector3i(7, 0, 0);
         case NORTH -> new Vector3i(0, 0, 7);
         default -> new Vector3i(-7, 0, 0);
      };
      return var1.bridge$add(var2.mul(-1));
   }

   public HorsestatsType_2 asDirectionBridge() {
      return switch (this) {
         case SOUTH -> HorsestatsType_2.SOUTH;
         case WEST -> HorsestatsType_2.WEST;
         case NORTH -> HorsestatsType_2.NORTH;
         case EAST -> HorsestatsType_2.EAST;
      };
   }

   public static HologramsType_3 fromProto(DungeonRoomRotation dungeonRoomRotation) {
      return switch (dungeonRoomRotation) {
         case DUNGEON_ROOM_ROTATION_EAST -> EAST;
         case DUNGEON_ROOM_ROTATION_SOUTH -> SOUTH;
         case DUNGEON_ROOM_ROTATION_WEST -> WEST;
         case DUNGEON_ROOM_ROTATION_NORTH -> NORTH;
         default -> null;
      };
   }

   public DungeonRoomRotation toProto() {
      return switch (this) {
         case SOUTH -> DungeonRoomRotation.DUNGEON_ROOM_ROTATION_SOUTH;
         case WEST -> DungeonRoomRotation.DUNGEON_ROOM_ROTATION_WEST;
         case NORTH -> DungeonRoomRotation.DUNGEON_ROOM_ROTATION_NORTH;
         case EAST -> DungeonRoomRotation.DUNGEON_ROOM_ROTATION_EAST;
      };
   }

   @Generated
   HologramsType_3(int value, int value2) {
      this.x = value;
      this.z = value2;
   }

   @Generated
   public int getX() {
      return this.x;
   }

   @Generated
   public int getZ() {
      return this.z;
   }
}
