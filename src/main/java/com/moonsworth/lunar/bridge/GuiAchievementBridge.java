package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiAchievementBridge extends GuiBridge {
   @Override
   default Optional<String> method1() {
      return Optional.of("Achievement");
   }
}
