package com.moonsworth.lunar.genesis;

final class CharMatcher$JavaLowerCase extends PredicateExtension2 {
   static final CharMatcher$JavaLowerCase field2 = new CharMatcher$JavaLowerCase();

   private CharMatcher$JavaLowerCase() {
   }

   public boolean matches(char character1) {
      return Character.isLowerCase(character1);
   }

   public String toString() {
      return "CharMatcher.javaLowerCase()";
   }
}
