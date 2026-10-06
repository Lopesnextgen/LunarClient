package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class CollectPreconditions {
   CollectPreconditions() {
   }

   static void checkEntryNotNull(Object obj0, Object obj1) {
      if (obj0 == null) {
         throw new NullPointerException("null key in entry: null=" + obj1);
      }

      if (obj1 == null) {
         throw new NullPointerException("null value in entry: " + obj0 + "=null");
      }
   }

   @CanIgnoreReturnValue
   static int checkNonnegative(int number0, String text1) {
      if (number0 < 0) {
         throw new IllegalArgumentException(text1 + " cannot be negative but was: " + number0);
      } else {
         return number0;
      }
   }

   @CanIgnoreReturnValue
   static long checkNonnegative(long number0, String text2) {
      if (number0 < 0L) {
         throw new IllegalArgumentException(text2 + " cannot be negative but was: " + number0);
      } else {
         return number0;
      }
   }

   static void checkPositive(int number0, String text1) {
      if (number0 <= 0) {
         throw new IllegalArgumentException(text1 + " must be positive but was: " + number0);
      }
   }

   static void checkRemove(boolean flag0) {
      Preconditions.checkState(flag0, "no calls to next() since the last call to remove()");
   }
}
