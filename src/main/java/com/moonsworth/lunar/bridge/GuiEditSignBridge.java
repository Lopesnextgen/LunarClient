package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiEditSignBridge extends GuiBridge {
   int bridge$getEditLine();

   String bridge$getLine(int number1);

   default Optional<String> method1() {
      return Optional.of("Edit Sign");
   }
}
