package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiScreenBookBridge extends GuiBridge {
   int bridge$getPageCount();

   int bridge$getCurrentPage();

   String bridge$getPageContents(int number1);

   default Optional<String> method1() {
      return Optional.of("Book");
   }
}
