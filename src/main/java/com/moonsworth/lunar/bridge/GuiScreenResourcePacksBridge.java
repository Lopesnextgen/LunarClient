package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiScreenResourcePacksBridge extends GuiBridge {
   default void bridge$handlePackSwapList() {
   }

   default Optional<String> method1() {
      return Optional.of("Resource Packs");
   }
}
