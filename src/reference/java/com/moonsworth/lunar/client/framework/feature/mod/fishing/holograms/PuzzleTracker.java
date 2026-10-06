package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles.SkyblockDungeonPuzzles;
import org.jetbrains.annotations.NotNull;

public class PuzzleTracker {
   private final SkyblockDungeonPuzzles field1;

   public PuzzleTracker(SkyblockDungeonPuzzles skyblockdungeonpuzzles1) {
      this.field1 = skyblockdungeonpuzzles1;
   }

   public void method1(@NotNull DungeonRoomTracker holograms4iterator1, PuzzleType hologramstype82) {
      switch (hologramstype82) {
         case BOULDER:
            if ((Boolean)this.field1.method36().get()) {
               this.field1.method27().method3(holograms4iterator1);
            }
            break;
         case QUIZ:
            if ((Boolean)this.field1.method35().get()) {
               this.field1.method25().method4(holograms4iterator1);
            }
            break;
         case ICE_FILL:
            if ((Boolean)this.field1.method39().get()) {
               this.field1.method23().method3(holograms4iterator1);
            }
            break;
         case ICE_PATH:
            if ((Boolean)this.field1.method41().get()) {
               this.field1.method24().method3(holograms4iterator1);
            }
            break;
         case TIC_TAC_TOE:
            if ((Boolean)this.field1.method38().get()) {
               this.field1.method16().method3(holograms4iterator1);
            }
            break;
         case WATER_BOARD:
            if ((Boolean)this.field1.method30().get()) {
               this.field1.method21().method3(holograms4iterator1);
            }
            break;
         case CREEPER_BEAMS:
            if ((Boolean)this.field1.method29().get()) {
               this.field1.method19().method3(holograms4iterator1);
            }
            break;
         case TP_MAZE:
            if ((Boolean)this.field1.method34().get()) {
               this.field1.method22().clear();
            }
      }
   }
}
