package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import java.util.BitSet;
import com.google.common.annotations.GwtIncompatible;

final class CharMatcher$AnyOf extends PredicateExtension2 {
   private final char[] field2;

   public CharMatcher$AnyOf(CharSequence text1) {
      this.field2 = text1.toString().toCharArray();
      Arrays.sort(this.field2);
   }

   public boolean matches(char character1) {
      return Arrays.binarySearch(this.field2, character1) >= 0;
   }

   @GwtIncompatible
   void setBits(BitSet bitset1) {
      for (char character5 : this.field2) {
         bitset1.set(character5);
      }
   }

   public String toString() {
      StringBuilder builder1 = new StringBuilder("CharMatcher.anyOf(\"");

      for (char character5 : this.field2) {
         builder1.append(PredicateExtension2.access$100(character5));
      }

      builder1.append("\")");
      return builder1.toString();
   }
}
