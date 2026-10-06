package com.moonsworth.lunar.genesis;

final class CharMatcher$JavaLetterOrDigit extends PredicateExtension2 {
   static final CharMatcher$JavaLetterOrDigit field2 = new CharMatcher$JavaLetterOrDigit();

   private CharMatcher$JavaLetterOrDigit() {
   }

   public boolean matches(char character1) {
      return Character.isLetterOrDigit(character1);
   }

   public String toString() {
      return "CharMatcher.javaLetterOrDigit()";
   }
}
