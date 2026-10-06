package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.lunarclient.websocket.skyblock.v1.DungeonRoomType;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public enum HologramsType5 {
   SPAWN(false, NamedTextColor.GREEN),
   CLEAR(true, NamedTextColor.GRAY),
   MINIBOSS(false, NamedTextColor.YELLOW),
   PUZZLE(true, NamedTextColor.DARK_PURPLE),
   TRAP(true, NamedTextColor.GOLD),
   FAIRY(false, NamedTextColor.LIGHT_PURPLE),
   BLOOD(false, NamedTextColor.RED),
   BOSS(false, NamedTextColor.GRAY),
   UNKNOWN(true, NamedTextColor.GRAY),
   WITHER_DOOR(false, NamedTextColor.GRAY);

   private final boolean hasSecrets;
   private final NamedTextColor color;

   public static HologramsType5 fromProto(DungeonRoomType dungeonRoomType) {
      return switch (dungeonRoomType) {
         case DUNGEON_ROOM_TYPE_SPAWN -> SPAWN;
         case DUNGEON_ROOM_TYPE_CLEAR -> CLEAR;
         case DUNGEON_ROOM_TYPE_MINIBOSS -> MINIBOSS;
         case DUNGEON_ROOM_TYPE_PUZZLE -> PUZZLE;
         case DUNGEON_ROOM_TYPE_TRAP -> TRAP;
         case DUNGEON_ROOM_TYPE_FAIRY -> FAIRY;
         case DUNGEON_ROOM_TYPE_BLOOD -> BLOOD;
         case DUNGEON_ROOM_TYPE_BOSS -> BOSS;
         case DUNGEON_ROOM_TYPE_UNKNOWN -> UNKNOWN;
         case DUNGEON_ROOM_TYPE_WITHER_DOOR -> WITHER_DOOR;
         default -> null;
      };
   }

   public DungeonRoomType toProto() {
      return switch (this) {
         case SPAWN -> DungeonRoomType.DUNGEON_ROOM_TYPE_SPAWN;
         case CLEAR -> DungeonRoomType.DUNGEON_ROOM_TYPE_CLEAR;
         case MINIBOSS -> DungeonRoomType.DUNGEON_ROOM_TYPE_MINIBOSS;
         case PUZZLE -> DungeonRoomType.DUNGEON_ROOM_TYPE_PUZZLE;
         case TRAP -> DungeonRoomType.DUNGEON_ROOM_TYPE_TRAP;
         case FAIRY -> DungeonRoomType.DUNGEON_ROOM_TYPE_FAIRY;
         case BLOOD -> DungeonRoomType.DUNGEON_ROOM_TYPE_BLOOD;
         case BOSS -> DungeonRoomType.DUNGEON_ROOM_TYPE_BOSS;
         case UNKNOWN -> DungeonRoomType.DUNGEON_ROOM_TYPE_UNKNOWN;
         case WITHER_DOOR -> DungeonRoomType.DUNGEON_ROOM_TYPE_WITHER_DOOR;
      };
   }

   @Generated
   public boolean hasSecrets() {
      return this.hasSecrets;
   }

   @Generated
   public NamedTextColor color() {
      return this.color;
   }

   @Generated
   HologramsType5(boolean flag, NamedTextColor namedTextColor) {
      this.hasSecrets = flag;
      this.color = namedTextColor;
   }
}
