package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import javax.annotation.Nullable;
import lombok.Generated;

public class HologramsHandler implements DungeonStats {
   private final DungeonMapListener field1;
   private final DungeonScoreListener field2;

   @Nullable
   @Override
   public DungeonStateTracker method1() {
      return this.field1.method5().orElse(null);
   }

   @Override
   public int method2() {
      return this.field2.method27();
   }

   @Override
   public int method3() {
      return this.field2.method28();
   }

   @Override
   public int method4() {
      return this.field2.method30();
   }

   @Override
   public int method5() {
      return this.field2.method29();
   }

   @Override
   public int method6() {
      return this.field2.method31();
   }

   @Override
   public int method7() {
      return this.field2.method23();
   }

   @Override
   public int method8() {
      return this.field2.method24();
   }

   @Override
   public int method9() {
      return this.field2.method25();
   }

   @Override
   public int method10() {
      return this.field2.method26();
   }

   @Override
   public int method11() {
      return this.field2.getDeaths();
   }

   @Override
   public boolean method12() {
      return this.field2.method17();
   }

   @Override
   public boolean method13() {
      return this.field2.method18();
   }

   @Override
   public boolean method14() {
      return this.field2.method15();
   }

   @Generated
   public HologramsHandler(DungeonMapListener guirewindhandlershandler2_21, DungeonScoreListener guirewindhandlershandler23_22) {
      this.field1 = guirewindhandlershandler2_21;
      this.field2 = guirewindhandlershandler23_22;
   }
}
