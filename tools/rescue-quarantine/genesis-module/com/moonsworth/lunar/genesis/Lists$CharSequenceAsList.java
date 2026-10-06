package com.moonsworth.lunar.genesis;

import java.util.AbstractList;
import com.google.common.base.Preconditions;

final class Lists$CharSequenceAsList extends AbstractList<Character> {
   private final CharSequence field1;

   Lists$CharSequenceAsList(CharSequence text1) {
      this.field1 = text1;
   }

   public Character get(int index1) {
      Preconditions.checkElementIndex(index1, this.size());
      return this.field1.charAt(index1);
   }

   @Override
   public int size() {
      return this.field1.length();
   }
}
