package com.moonsworth.lunar.ichor.util;

public class FatalIchorError extends Error {
   public FatalIchorError(String text1) {
      this(text1, null);
   }

   public FatalIchorError(String text1, Throwable exception2) {
      super(
         "["
            + (Thread.currentThread().getContextClassLoader() == null ? "UnknownClassLoader" : Thread.currentThread().getContextClassLoader().getName())
            + "] "
            + text1,
         exception2
      );
   }
}
