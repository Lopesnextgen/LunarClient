package com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin;

import com.moonsworth.lunar.client.mod.skyblock.burrowlocating.SkyblockBurrowLocating;
import org.jetbrains.annotations.Nullable;

public enum BurrowType2 {
   MOB,
   TREASURE,
   START;

   public static int getColor(SkyblockBurrowLocating skyblockBurrowLocating, @Nullable BurrowType2 var1) {
      if (var1 == null) {
         return skyblockBurrowLocating.method23().method14(0.0F);
      }

      return switch (var1) {
         case START -> skyblockBurrowLocating.method22().method14(0.0F);
         case MOB -> skyblockBurrowLocating.method19().method14(0.0F);
         case TREASURE -> skyblockBurrowLocating.method21().method14(0.0F);
      };
   }
}
