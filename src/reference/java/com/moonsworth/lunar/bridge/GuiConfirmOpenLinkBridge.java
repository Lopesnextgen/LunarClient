package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiConfirmOpenLinkBridge extends GuiYesNoBridge {
   default Optional<String> method1() {
      return Optional.of("Confirm Open Link");
   }
}
