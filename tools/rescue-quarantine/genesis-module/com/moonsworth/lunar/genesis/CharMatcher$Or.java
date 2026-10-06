package com.moonsworth.lunar.genesis;

import java.util.BitSet;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

final class CharMatcher$Or extends PredicateExtension2 {
   final PredicateExtension2 field2;
   final PredicateExtension2 field3;

   CharMatcher$Or(PredicateExtension2 predicateextension21, PredicateExtension2 predicateextension22) {
      this.field2 = (PredicateExtension2)Preconditions.checkNotNull(predicateextension21);
      this.field3 = (PredicateExtension2)Preconditions.checkNotNull(predicateextension22);
   }

   @GwtIncompatible
   void setBits(BitSet bitset1) {
      this.field2.setBits(bitset1);
      this.field3.setBits(bitset1);
   }

   public boolean matches(char character1) {
      return this.field2.matches(character1) || this.field3.matches(character1);
   }

   public String toString() {
      return "CharMatcher.or(" + this.field2 + ", " + this.field3 + ")";
   }
}
