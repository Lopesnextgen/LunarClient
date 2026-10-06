package com.moonsworth.lunar.bridge;

class Bridge$Data extends RuntimeException {
   public Bridge$Data(BytecodeEmitter bytecodeEmitter, Exception exception) {
      super("Exception occurred during bridge gen stage " + bytecodeEmitter, exception);
   }
}
