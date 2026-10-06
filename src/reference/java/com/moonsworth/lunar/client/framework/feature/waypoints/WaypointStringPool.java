package com.moonsworth.lunar.client.framework.feature.waypoints;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.Inflater;

public class WaypointStringPool {
   private static final String[] field1 = new String[]{
      "Waypoint",
      "Unnamed group",
      "mc.hypixel.net",
      "SKYBLOCK",
      "Singleplayer",
      "Multiplayer",
      "Supplies",
      "Build Ballista",
      "Fuel Ballista",
      "Killed",
      "P5",
      "BLOOD_OPEN",
      "BLOOD_CLEAR",
      "F1_BONZO_1_KILLED",
      "F1_BONZO_2_KILLED",
      "F2_SCARF_FREED",
      "F2_SCARF_KILLED",
      "F3_BOSS_ENTERED",
      "F3_PROFESSOR_FREED",
      "F3_PROFESSOR_1_KILLED",
      "F3_PROFESSOR_2_KILLED",
      "F4_THORN_ENTERED",
      "F4_THORN_KILLED",
      "F5_LIVID_ENTERED",
      "F5_LIVID_KILLED",
      "F6_ENTRY",
      "F6_TERRACOTTA",
      "F6_GIANTS",
      "F6_SADAN",
      "F7_ENTRY",
      "F7_PHASE1",
      "F7_PHASE2",
      "F7_PHASE3",
      "F7_PHASE4",
      "F7_PHASE5"
   };
   private static final String field2 = String.join("", field1);
   private final BadlionWaypointImporter field3;
   private final String string;

   public WaypointStringPool(BadlionWaypointImporter gui2loader1) {
      this.field3 = gui2loader1;
      boolean flag2 = gui2loader1.readBool();
      int number3 = gui2loader1.readInt();
      byte[] items4 = gui2loader1.method14(number3);
      if (flag2) {
         this.string = this.method1(items4);
      } else {
         this.string = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(items4)).toString();
      }
   }

   private String method1(byte[] items1) {
      Inflater inflater2 = new Inflater();
      inflater2.setInput(items1);
      byte[] items3 = new byte[1024];
      byte[] items4 = new byte[0];

      int number5;
      do {
         number5 = inflater2.inflate(items3, 0, items3.length);
         items4 = Arrays.copyOf(items4, items4.length + number5);
         System.arraycopy(items3, 0, items4, items4.length - number5, number5);
      } while (number5 >= items3.length);

      return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(items4)).toString();
   }

   public String readString() {
      boolean flag1 = this.field3.readBool();
      long index2 = this.field3.method13();
      long number4 = this.field3.method13();
      return flag1 ? field2.substring((int)index2, (int)(index2 + number4)) : this.string.substring((int)index2, (int)(index2 + number4));
   }
}
