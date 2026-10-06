package com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin;

import com.moonsworth.lunar.client.mod.skyblock.burrowlocating.SkyblockBurrowLocating;
import org.jetbrains.annotations.Nullable;

public enum BurrowKind {
   MOB,
   TREASURE,
   START;

   BurrowKind() {
   }

   public static int getColor(SkyblockBurrowLocating skyblockburrowlocating0, @Nullable BurrowKind burrowtype21) {
      if (burrowtype21 == null) {
         return skyblockburrowlocating0.method23().method14(0.0F);
      }

      return switch (burrowtype21) {
         case START -> skyblockburrowlocating0.method22().method14(0.0F);
         case MOB -> skyblockburrowlocating0.method19().method14(0.0F);
         case TREASURE -> skyblockburrowlocating0.method21().method14(0.0F);
      };
   }
}
