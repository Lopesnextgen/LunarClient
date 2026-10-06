package com.moonsworth.lunar.genesis;

import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Comparator;
import sun.misc.Unsafe;
import com.google.common.primitives.UnsignedLongs;
import com.google.common.eventbus.Subscribe;

@Subscribe
enum UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator implements Comparator<byte[]> {
   INSTANCE;

   static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
   static final Unsafe theUnsafe = getUnsafe();
   static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);

   UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator() {
   }

   private static Unsafe getUnsafe() {
      try {
         return Unsafe.getUnsafe();
      } catch (SecurityException securityexception2) {
         try {
            return AccessController.doPrivileged(new Type$1());
         } catch (PrivilegedActionException privilegedactionexception1) {
            throw new RuntimeException("Could not initialize intrinsics", privilegedactionexception1.getCause());
         }
      }
   }

   public int compare(byte[] items1, byte[] items2) {
      byte number3 = 8;
      int number4 = Math.min(items1.length, items2.length);
      int number5 = number4 & -8;

      int index6;
      for (index6 = 0; index6 < number5; index6 += 8) {
         long number7 = theUnsafe.getLong(items1, (long)BYTE_ARRAY_BASE_OFFSET + index6);
         long number9 = theUnsafe.getLong(items2, (long)BYTE_ARRAY_BASE_OFFSET + index6);
         if (number7 != number9) {
            if (BIG_ENDIAN) {
               return UnsignedLongs.compare(number7, number9);
            }

            int number11 = Long.numberOfTrailingZeros(number7 ^ number9) & -8;
            return (int)(number7 >>> number11 & 255L) - (int)(number9 >>> number11 & 255L);
         }
      }

      while (index6 < number4) {
         int number12 = MixinHelper8_7.compare(items1[index6], items2[index6]);
         if (number12 != 0) {
            return number12;
         }

         index6++;
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
   }

   static {
      if (!"64".equals(System.getProperty("sun.arch.data.model")) || BYTE_ARRAY_BASE_OFFSET % 8 != 0 || theUnsafe.arrayIndexScale(byte[].class) != 1) {
         throw new Error();
      }
   }
}
