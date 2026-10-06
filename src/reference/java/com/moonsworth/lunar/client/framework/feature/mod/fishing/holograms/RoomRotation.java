package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonRoomRotation;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import lombok.Generated;
import org.joml.Vector3i;

public enum RoomRotation {
   EAST(7, 0),
   SOUTH(30, 7),
   WEST(23, 30),
   NORTH(0, 23);

   public static final RoomRotation[] VALUES = values();
   private final int x;
   private final int z;

   public Vec3iBridge getCorner(Vec3iBridge horsestats201) {
      Vector3i vector3i2 = switch (this) {
         case SOUTH -> new Vector3i(0, 0, -7);
         case WEST -> new Vector3i(7, 0, 0);
         case NORTH -> new Vector3i(0, 0, 7);
         default -> new Vector3i(-7, 0, 0);
      };
      return horsestats201.bridge$add(vector3i2);
   }

   public Vec3iBridge inverseGetCorner(Vec3iBridge horsestats201) {
      Vector3i vector3i2 = switch (this) {
         case SOUTH -> new Vector3i(0, 0, -7);
         case WEST -> new Vector3i(7, 0, 0);
         case NORTH -> new Vector3i(0, 0, 7);
         default -> new Vector3i(-7, 0, 0);
      };
      return horsestats201.bridge$add(vector3i2.mul(-1));
   }

   public HorsestatsType_2 asDirectionBridge() {
      return switch (this) {
         case SOUTH -> HorsestatsType_2.SOUTH;
         case WEST -> HorsestatsType_2.WEST;
         case NORTH -> HorsestatsType_2.NORTH;
         case EAST -> HorsestatsType_2.EAST;
      };
   }

   public static RoomRotation fromProto(DungeonRoomRotation dungeonroomrotation0) {
      return switch (dungeonroomrotation0) {
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
   RoomRotation(int value, int value2) {
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
