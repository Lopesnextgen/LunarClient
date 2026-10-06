package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public enum SkyblockCave {
   GLOWING_MUSHROOM_CAVE("Glowing Mushroom Cave");

   @NotNull
   private final String scoreboardName;

   SkyblockCave(@NotNull String text3) {
      this.scoreboardName = text3;
   }

   public static Optional<SkyblockCave> fromScoreboard(@NotNull String text0) {
      for (SkyblockCave highlighttype64 : values()) {
         if (text0.equals(highlighttype64.getScoreboardName())) {
            return Optional.of(highlighttype64);
         }
      }

      return Optional.empty();
   }

   @NotNull
   @Generated
   public String getScoreboardName() {
      return this.scoreboardName;
   }
}
