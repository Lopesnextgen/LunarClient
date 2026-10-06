package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import com.google.common.base.Preconditions;

class CharMatcher$RangesMatcher extends PredicateExtension2 {
   private final String field2;
   private final char[] field3;
   private final char[] field4;

   CharMatcher$RangesMatcher(String text1, char[] items2, char[] items3) {
      this.field2 = text1;
      this.field3 = items2;
      this.field4 = items3;
      Preconditions.checkArgument(items2.length == items3.length);

      for (int index4 = 0; index4 < items2.length; index4++) {
         Preconditions.checkArgument(items2[index4] <= items3[index4]);
         if (index4 + 1 < items2.length) {
            Preconditions.checkArgument(items3[index4] < items2[index4 + 1]);
         }
      }
   }

   public boolean matches(char character1) {
      int index2 = Arrays.binarySearch(this.field3, character1);
      if (index2 >= 0) {
         return true;
      }

      index2 = ~index2 - 1;
      return index2 >= 0 && character1 <= this.field4[index2];
   }

   public String toString() {
      return this.field2;
   }
}
