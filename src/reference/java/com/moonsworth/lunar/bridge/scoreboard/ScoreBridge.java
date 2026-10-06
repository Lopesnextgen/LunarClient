package com.moonsworth.lunar.bridge.scoreboard;

import com.moonsworth.lunar.bridge.Bridge2_42;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public interface ScoreBridge {
   String bridge$getPlayerName();

   Bridge2_42 bridge$getPlayerComponentBridge();

   Component bridge$getPlayerComponent();

   default Component bridge$getScoreComponent(ScoreboardObjectiveBridge lighting1) {
      return Component.text(this.bridge$getScorePoints()).color(NamedTextColor.RED);
   }

   Bridge2_42 bridge$getScoreComponentBridge(ScoreboardObjectiveBridge lighting1);

   int bridge$getScorePoints();

   void bridge$setScorePoints(int number1);
}
