package com.moonsworth.lunar.genesis;

import java.util.BitSet;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

final class CharMatcher$And extends PredicateExtension2 {
   final PredicateExtension2 field2;
   final PredicateExtension2 field3;

   CharMatcher$And(PredicateExtension2 predicateextension21, PredicateExtension2 predicateextension22) {
      this.field2 = (PredicateExtension2)Preconditions.checkNotNull(predicateextension21);
      this.field3 = (PredicateExtension2)Preconditions.checkNotNull(predicateextension22);
   }

   public boolean matches(char character1) {
      return this.field2.matches(character1) && this.field3.matches(character1);
   }

   @GwtIncompatible
   void setBits(BitSet bitset1) {
      BitSet bitset2 = new BitSet();
      this.field2.setBits(bitset2);
      BitSet bitset3 = new BitSet();
      this.field3.setBits(bitset3);
      bitset2.and(bitset3);
      bitset1.or(bitset2);
   }

   public String toString() {
      return "CharMatcher.and(" + this.field2 + ", " + this.field3 + ")";
   }
}
