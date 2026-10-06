package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiSelectWorldBridge extends GuiScreenBridge {
   default Optional<String> method1() {
      return Optional.of("Select World");
   }
}
