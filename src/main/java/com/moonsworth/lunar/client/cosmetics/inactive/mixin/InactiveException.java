package com.moonsworth.lunar.client.cosmetics.inactive.mixin;

public class InactiveException extends Exception {
   public InactiveException(String text1) {
      super(text1);
   }

   public InactiveException(String text1, Throwable exception2) {
      super(text1, exception2);
   }
}
