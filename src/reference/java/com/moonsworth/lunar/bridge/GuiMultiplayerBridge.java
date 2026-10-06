package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiMultiplayerBridge extends GuiScreenBridge {
   default Optional<String> method1() {
      return Optional.of("Server Selector");
   }
}
