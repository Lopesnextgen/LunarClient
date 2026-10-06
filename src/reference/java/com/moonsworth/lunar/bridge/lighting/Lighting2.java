package com.moonsworth.lunar.bridge.lighting;

import com.moonsworth.lunar.bridge.Bridge2_42;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public interface Lighting2 {
   String bridge$getPlayerName();

   Bridge2_42 bridge$getPlayerComponentBridge();

   Component bridge$getPlayerComponent();

   default Component bridge$getScoreComponent(Lighting var1) {
      return Component.text(this.bridge$getScorePoints()).color(NamedTextColor.RED);
   }

   Bridge2_42 bridge$getScoreComponentBridge(Lighting var1);

   int bridge$getScorePoints();

   void bridge$setScorePoints(int var1);
}
