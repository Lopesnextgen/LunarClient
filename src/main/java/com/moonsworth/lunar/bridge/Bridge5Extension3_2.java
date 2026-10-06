package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface Bridge5Extension3_2 extends Bridge5_15 {
   int bridge$getEditLine();

   String bridge$getLine(int var1);

   @Override
   default Optional<String> method1() {
      return Optional.of("Edit Sign");
   }
}
