package com.moonsworth.lunar.genesis;

final class CharMatcher$JavaUpperCase extends PredicateExtension2 {
   static final CharMatcher$JavaUpperCase field2 = new CharMatcher$JavaUpperCase();

   private CharMatcher$JavaUpperCase() {
   }

   public boolean matches(char character1) {
      return Character.isUpperCase(character1);
   }

   public String toString() {
      return "CharMatcher.javaUpperCase()";
   }
}
