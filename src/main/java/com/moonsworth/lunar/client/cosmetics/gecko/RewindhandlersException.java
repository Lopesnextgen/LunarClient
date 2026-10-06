package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class RewindhandlersException extends RuntimeException {
   public RewindhandlersException(ResourceLocationBridge horsestats141, String text2) {
      super(horsestats141 + ": " + text2);
   }

   public RewindhandlersException(ResourceLocationBridge horsestats141, String text2, Throwable exception3) {
      super(horsestats141 + ": " + text2, exception3);
   }
}
