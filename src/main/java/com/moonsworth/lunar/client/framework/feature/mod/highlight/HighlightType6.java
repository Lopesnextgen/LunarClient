package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public enum HighlightType6 {
   GLOWING_MUSHROOM_CAVE("Glowing Mushroom Cave");

   @NotNull
   private final String scoreboardName;

   HighlightType6(@NotNull String var3) {
      this.scoreboardName = var3;
   }

   public static Optional<HighlightType6> fromScoreboard(@NotNull String var0) {
      for (HighlightType6 var4 : values()) {
         if (var0.equals(var4.getScoreboardName())) {
            return Optional.of(var4);
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
