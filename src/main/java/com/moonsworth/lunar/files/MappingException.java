package com.moonsworth.lunar.files;

import javax.annotation.Nullable;

public class MappingException extends Exception {
   public MappingException(String text, @Nullable Throwable exception2) {
      super(text, exception2);
   }
}
