package com.moonsworth.lunar.genesis;

import java.util.BitSet;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

class CharMatcher$Negated extends PredicateExtension2 {
   final PredicateExtension2 field2;

   CharMatcher$Negated(PredicateExtension2 predicateextension21) {
      this.field2 = (PredicateExtension2)Preconditions.checkNotNull(predicateextension21);
   }

   public boolean matches(char character1) {
      return !this.field2.matches(character1);
   }

   public boolean matchesAllOf(CharSequence text1) {
      return this.field2.matchesNoneOf(text1);
   }

   public boolean matchesNoneOf(CharSequence text1) {
      return this.field2.matchesAllOf(text1);
   }

   public int countIn(CharSequence text1) {
      return text1.length() - this.field2.countIn(text1);
   }

   @GwtIncompatible
   void setBits(BitSet bitset1) {
      BitSet bitset2 = new BitSet();
      this.field2.setBits(bitset2);
      bitset2.flip(0, 65536);
      bitset1.or(bitset2);
   }

   public PredicateExtension2 method21() {
      return this.field2;
   }

   public String toString() {
      return this.field2 + ".negate()";
   }
}
