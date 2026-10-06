package com.moonsworth.lunar.client.framework.feature.mod.debug;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStats;

public class HologramsHandler implements DungeonStats {
   private final DungeonStateTracker field1;

   public HologramsHandler(DungeonStateTracker holograms2_51) {
      this.field1 = holograms2_51;
   }

   @Override
   public int method2() {
      return 287;
   }

   @Override
   public int method3() {
      return 95;
   }

   @Override
   public int method4() {
      return 92;
   }

   @Override
   public int method5() {
      return 80;
   }

   @Override
   public int method6() {
      return 7;
   }

   @Override
   public int method7() {
      return 17;
   }

   @Override
   public int method8() {
      return 24;
   }

   @Override
   public int method9() {
      return 20;
   }

   @Override
   public int method10() {
      return 5;
   }

   @Override
   public int method11() {
      return 1;
   }

   @Override
   public boolean method12() {
      return true;
   }

   @Override
   public boolean method13() {
      return true;
   }

   @Override
   public boolean method14() {
      return false;
   }

   @Override
   public DungeonStateTracker method1() {
      return this.field1;
   }
}
