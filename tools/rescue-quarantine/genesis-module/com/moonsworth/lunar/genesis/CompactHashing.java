package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
final class CompactHashing {
   static final byte field1 = 0;
   private static final int field2 = 5;
   static final int field3 = 32;
   static final int field4 = 31;
   static final int field5 = 1073741823;
   static final int field6 = 3;
   private static final int field7 = 4;
   private static final int field8 = 256;
   private static final int field9 = 255;
   private static final int field10 = 65536;
   private static final int field11 = 65535;

   private CompactHashing() {
   }

   static int tableSize(int number0) {
      return Math.max(4, Hashing.closedTableSize(number0 + 1, 1.0));
   }

   static Object createTable(int index0) {
      if (index0 < 2 || index0 > 1073741824 || Integer.highestOneBit(index0) != index0) {
         throw new IllegalArgumentException("must be power of 2 between 2^1 and 2^30: " + index0);
      } else if (index0 <= 256) {
         return new byte[index0];
      } else {
         return index0 <= 65536 ? new short[index0] : new int[index0];
      }
   }

   static void tableClear(Object obj0) {
      if (obj0 instanceof byte[]) {
         Arrays.fill((byte[])obj0, (byte)0);
      } else if (obj0 instanceof short[]) {
         Arrays.fill((short[])obj0, (short)0);
      } else {
         Arrays.fill((int[])obj0, 0);
      }
   }

   static int tableGet(Object obj0, int index1) {
      if (obj0 instanceof byte[]) {
         return ((byte[])obj0)[index1] & 0xFF;
      } else {
         return obj0 instanceof short[] ? ((short[])obj0)[index1] & 65535 : ((int[])obj0)[index1];
      }
   }

   static void tableSet(Object obj0, int index1, int number2) {
      if (obj0 instanceof byte[]) {
         ((byte[])obj0)[index1] = (byte)number2;
      } else if (obj0 instanceof short[]) {
         ((short[])obj0)[index1] = (short)number2;
      } else {
         ((int[])obj0)[index1] = number2;
      }
   }

   static int newCapacity(int number0) {
      return (number0 < 32 ? 4 : 2) * (number0 + 1);
   }

   static int getHashPrefix(int number0, int number1) {
      return number0 & ~number1;
   }

   static int getNext(int number0, int number1) {
      return number0 & number1;
   }

   static int maskCombine(int number0, int number1, int number2) {
      return number0 & ~number2 | number1 & number2;
   }

   static int remove(@Nullable Object obj0, @Nullable Object obj1, int number2, Object obj3, int[] items4, Object[] items5, Object @Nullable [] items6) {
      int number7 = Hashing.smearedHash(obj0);
      int number8 = number7 & number2;
      int number9 = tableGet(obj3, number8);
      if (number9 == 0) {
         return -1;
      }

      int number10 = getHashPrefix(number7, number2);
      int index11 = -1;

      do {
         int index12 = number9 - 1;
         int number13 = items4[index12];
         if (getHashPrefix(number13, number2) == number10 && Objects.equal(obj0, items5[index12]) && (items6 == null || Objects.equal(obj1, items6[index12]))) {
            int number14 = getNext(number13, number2);
            if (index11 == -1) {
               tableSet(obj3, number8, number14);
            } else {
               items4[index11] = maskCombine(items4[index11], number14, number2);
            }

            return index12;
         }

         index11 = index12;
         number9 = getNext(number13, number2);
      } while (number9 != 0);

      return -1;
   }
}
