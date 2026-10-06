package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;

final class Lists$StringAsImmutableList extends ImmutableList<Character> {
   private final String field3;

   Lists$StringAsImmutableList(String text1) {
      this.field3 = text1;
   }

   public int indexOf(@Nullable Object obj1) {
      return obj1 instanceof Character ? this.field3.indexOf((Character)obj1) : -1;
   }

   public int lastIndexOf(@Nullable Object obj1) {
      return obj1 instanceof Character ? this.field3.lastIndexOf((Character)obj1) : -1;
   }

   public ImmutableList<Character> method26(int index1, int index2) {
      Preconditions.checkPositionIndexes(index1, index2, this.size());
      return MixinHelper22.method2(this.field3.substring(index1, index2));
   }

   boolean isPartialView() {
      return false;
   }

   public Character get(int index1) {
      Preconditions.checkElementIndex(index1, this.size());
      return this.field3.charAt(index1);
   }

   public int size() {
      return this.field3.length();
   }
}
