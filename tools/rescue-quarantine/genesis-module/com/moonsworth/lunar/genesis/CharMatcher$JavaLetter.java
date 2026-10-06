package com.moonsworth.lunar.genesis;

final class CharMatcher$JavaLetter extends PredicateExtension2 {
   static final CharMatcher$JavaLetter field2 = new CharMatcher$JavaLetter();

   private CharMatcher$JavaLetter() {
   }

   public boolean matches(char character1) {
      return Character.isLetter(character1);
   }

   public String toString() {
      return "CharMatcher.javaLetter()";
   }
}
