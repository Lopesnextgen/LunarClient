package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeoutException;

final class MixinHelper32$Data2 extends TimeoutException {
   private MixinHelper32$Data2(String var1) {
      super(var1);
   }

   @Override
   public synchronized Throwable fillInStackTrace() {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
   }
}
