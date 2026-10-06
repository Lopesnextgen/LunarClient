package com.moonsworth.lunar.bridge;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface Bridge5Extension66 extends Bridge5Extension6 {
   @Override
   default Optional<String> method1() {
      return Optional.of("Yes No");
   }

   default BiConsumer<Boolean, Integer> bridge$getYesNoCallback() {
      return (var0, var1) -> {};
   }

   default int bridge$getParentButtonClickedId() {
      return 0;
   }
}
