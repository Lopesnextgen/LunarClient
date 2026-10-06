package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface Bridge5Extension64 extends Bridge5Extension6 {
   @Override
   default Optional<String> method1() {
      return Optional.of("Options");
   }
}
