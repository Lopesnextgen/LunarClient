package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;
import com.google.common.escape.CharEscaper;

final class Escapers$1 extends CharEscaper {
   Escapers$1() {
   }

   public String escape(String text1) {
      return (String)Preconditions.checkNotNull(text1);
   }

   protected char[] escape(char character1) {
      return null;
   }
}
