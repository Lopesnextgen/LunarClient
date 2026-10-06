package com.moonsworth.lunar.genesis;

final class CharMatcher$JavaDigit extends PredicateExtension2 {
   static final CharMatcher$JavaDigit field2 = new CharMatcher$JavaDigit();

   private CharMatcher$JavaDigit() {
   }

   public boolean matches(char character1) {
      return Character.isDigit(character1);
   }

   public String toString() {
      return "CharMatcher.javaDigit()";
   }
}
