package com.moonsworth.lunar.client.account.skin;

public class SkinLoadException extends Exception {
   public SkinLoadException(String text1) {
      super(text1);
   }

   public SkinLoadException(String text1, Throwable exception2) {
      super(text1, exception2);
   }
}
