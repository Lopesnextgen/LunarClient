package com.moonsworth.lunar.bridge;

import java.util.Optional;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/client/gui/GuiChat")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/client/gui/screens/ChatScreen"))
   }
)
public interface Bridge5Extension612 extends Bridge5Extension6 {
   @Override
   default Optional<String> method1() {
      return Optional.of("GUI");
   }

   String bridge$getInitialText();

   boolean bridge$isSuggestionOverlayVisible();

   boolean bridge$isBedChat();
}
