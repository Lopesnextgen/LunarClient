package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface GuiBridge {
   default Optional<String> method1() {
      return Optional.empty();
   }
}
