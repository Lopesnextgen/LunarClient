package com.moonsworth.lunar.bridge;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface GuiYesNoBridge extends GuiScreenBridge {
   default Optional<String> method1() {
      return Optional.of("Yes No");
   }

   default BiConsumer<Boolean, Integer> bridge$getYesNoCallback() {
      return (arg0, arg1) -> {};
   }

   default int bridge$getParentButtonClickedId() {
      return 0;
   }
}
