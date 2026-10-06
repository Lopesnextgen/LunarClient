package com.moonsworth.lunar.bridge;

class BridgeGenerationException extends RuntimeException {
   public BridgeGenerationException(BytecodeEmitter bridge_151, Exception exception2) {
      super("Exception occurred during bridge gen stage " + bridge_151, exception2);
   }
}
