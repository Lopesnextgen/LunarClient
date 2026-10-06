package com.moonsworth.lunar.client.framework.feature.waypoints;

import java.util.BitSet;

public class Waypoints {
   private final BitSet field1;
   private int currentIndex = 0;

   public Waypoints(BadlionWaypointImporter badlionWaypointImporter) {
      int number2 = badlionWaypointImporter.readInt();
      byte[] items3 = badlionWaypointImporter.method14(number2);
      this.field1 = BitSet.valueOf(items3);
   }

   public boolean method1() {
      return this.field1.get(this.currentIndex++);
   }
}
