package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiControlsBridge extends GuiBridge {
   default Optional<String> method1() {
      return Optional.of("Controls");
   }
}
