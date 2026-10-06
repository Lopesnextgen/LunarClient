package com.moonsworth.lunar.genesis;

final class CharMatcher$BreakingWhitespace extends PredicateExtension2 {
   static final PredicateExtension2 field2 = new CharMatcher$BreakingWhitespace();

   private CharMatcher$BreakingWhitespace() {
   }

   public boolean matches(char character1) {
      switch (character1) {
         case '\t':
         case '\n':
         case '\u000b':
         case '\f':
         case '\r':
         case ' ':
         case '\u0085':
         case ' ':
         case '\u2028':
         case '\u2029':
         case ' ':
         case '　':
            return true;
         case ' ':
            return false;
         default:
            return character1 >= 8192 && character1 <= 8202;
      }
   }

   public String toString() {
      return "CharMatcher.breakingWhitespace()";
   }
}
