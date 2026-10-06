package com.moonsworth.lunar.ichor;

public class MixinInternalError extends Error {
   public MixinInternalError(String text1) {
      super(text1);
   }

   public MixinInternalError(String text1, Throwable exception2) {
      super(text1, exception2);
   }
}
