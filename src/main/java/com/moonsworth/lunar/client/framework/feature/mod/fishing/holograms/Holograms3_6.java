package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import java.util.HashMap;
import java.util.Map;

public class Holograms3_6 {
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
   public static final Map<Integer, HologramsType5> field20 = new HashMap<>();
   public static final Map<Integer, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2> field21 = new HashMap<>();

   static {
      field20.put(30, HologramsType5.SPAWN);
      field20.put(66, HologramsType5.PUZZLE);
      field20.put(82, HologramsType5.FAIRY);
      field20.put(18, HologramsType5.BLOOD);
      field20.put(62, HologramsType5.TRAP);
      field20.put(74, HologramsType5.MINIBOSS);
      field20.put(85, HologramsType5.UNKNOWN);
      field20.put(63, HologramsType5.CLEAR);
      field20.put(119, HologramsType5.WITHER_DOOR);
      field21.put(field10, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED);
      field21.put(30, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED);
      field21.put(18, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.FAILED);
      field21.put(119, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT);
   }
}
