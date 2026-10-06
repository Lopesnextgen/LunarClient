package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeoutException;

final class TimeoutFuture$TimeoutFutureException extends TimeoutException {
   private TimeoutFuture$TimeoutFutureException(String text1) {
      super(text1);
   }

   @Override
   public synchronized Throwable fillInStackTrace() {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
   }
}
