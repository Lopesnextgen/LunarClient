package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiIngameMenuBridge extends GuiScreenBridge {
   default Optional<String> method1() {
      return Optional.of("Pause Screen");
   }
}
