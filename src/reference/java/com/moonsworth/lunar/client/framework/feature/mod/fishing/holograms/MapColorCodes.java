package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import java.util.HashMap;
import java.util.Map;

public class MapColorCodes {
   public static final int field1 = 30;
   public static final int field2 = 66;
   public static final int field3 = 82;
   public static final int field4 = 18;
   public static final int field5 = 62;
   public static final int field6 = 74;
   public static final int field7 = 85;
   public static final int field8 = 63;
   public static final int field9 = 119;
   private static final Integer field10 = 34;
   public static final int field11 = 30;
   public static final int field12 = 66;
   public static final int field13 = 82;
   public static final int field14 = 18;
   public static final int field15 = 62;
   public static final int field16 = 74;
   public static final int field17 = 85;
   private static final int field18 = 63;
   private static final int field19 = 119;
   public static final Map<Integer, MapRoomType> field20 = new HashMap<>();
   public static final Map<Integer, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState> field21 = new HashMap<>();

   public MapColorCodes() {
   }

   static {
      field20.put(30, MapRoomType.SPAWN);
      field20.put(66, MapRoomType.PUZZLE);
      field20.put(82, MapRoomType.FAIRY);
      field20.put(18, MapRoomType.BLOOD);
      field20.put(62, MapRoomType.TRAP);
      field20.put(74, MapRoomType.MINIBOSS);
      field20.put(85, MapRoomType.UNKNOWN);
      field20.put(63, MapRoomType.CLEAR);
      field20.put(119, MapRoomType.WITHER_DOOR);
      field21.put(field10, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED);
      field21.put(30, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED);
      field21.put(18, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.FAILED);
      field21.put(119, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT);
   }
}
