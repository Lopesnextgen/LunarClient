package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiMainMenuBridge extends GuiScreenBridge {
   default Optional<String> method1() {
      return Optional.of("Main Menu");
   }
}
