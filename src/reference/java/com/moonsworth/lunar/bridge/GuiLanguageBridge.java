package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiLanguageBridge extends GuiScreenBridge {
   default Optional<String> method1() {
      return Optional.of("Language");
   }
}
