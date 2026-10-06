package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface Bridge5Extension8 extends Bridge5_15 {
   int bridge$getPageCount();

   int bridge$getCurrentPage();

   String bridge$getPageContents(int var1);

   @Override
   default Optional<String> method1() {
      return Optional.of("Book");
   }
}
