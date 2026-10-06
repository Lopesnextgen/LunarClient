package com.moonsworth.lunar.genesis;

import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;
import com.google.common.primitives.UnsignedLongs;
import com.google.common.primitives.UnsignedBytes;

@Annotation4
class MixinHelper8$Data22 {
   static final String field1 = MixinHelper8$Data22.class.getName() + "$UnsafeComparator";
   static final java.util.Comparator<byte[]> field2 = getBestComparator();

   static java.util.Comparator<byte[]> getBestComparator() {
      try {
         Class var0 = Class.forName(field1);
         return (java.util.Comparator<byte[]>)var0.getEnumConstants()[0];
      } catch (Throwable var2) {
         return UnsignedBytes.lexicographicalComparatorJavaImpl();
      }
   }

   @Annotation4
   enum Type implements java.util.Comparator<byte[]> {
      INSTANCE;

      static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      static final Unsafe theUnsafe = getUnsafe();
      static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);

      private static Unsafe getUnsafe() {
         try {
            return Unsafe.getUnsafe();
         } catch (SecurityException var2) {
            try {
               return AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                  public Unsafe run() {
                     Class<Unsafe> var1 = Unsafe.class;

                     for (Field var5 : var1.getDeclaredFields()) {
                        var5.setAccessible(true);
                        Object var6 = var5.get(null);
                        if (var1.isInstance(var6)) {
                           return var1.cast(var6);
                        }
                     }

                     throw new NoSuchFieldError("the Unsafe");
                  }
               });
            } catch (PrivilegedActionException var1) {
               throw new RuntimeException("Could not initialize intrinsics", var1.getCause());
            }
         }
      }

      public int compare(byte[] var1, byte[] var2) {
         byte var3 = 8;
         int var4 = Math.min(var1.length, var2.length);
         int var5 = var4 & -8;

         int var6;
         for (var6 = 0; var6 < var5; var6 += 8) {
            long var7 = theUnsafe.getLong(var1, (long)BYTE_ARRAY_BASE_OFFSET + var6);
            long var9 = theUnsafe.getLong(var2, (long)BYTE_ARRAY_BASE_OFFSET + var6);
            if (var7 != var9) {
               if (BIG_ENDIAN) {
                  return UnsignedLongs.compare(var7, var9);
               }

               int var11 = Long.numberOfTrailingZeros(var7 ^ var9) & -8;
               return (int)(var7 >>> var11 & 255L) - (int)(var9 >>> var11 & 255L);
            }
         }

         while (var6 < var4) {
            int var12 = UnsignedBytes.compare(var1[var6], var2[var6]);
            if (var12 != 0) {
               return var12;
            }

            var6++;
         }

         return var1.length - var2.length;
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

   enum Type2 implements java.util.Comparator<byte[]> {
      INSTANCE;

      public int compare(byte[] var1, byte[] var2) {
         int var3 = Math.min(var1.length, var2.length);

         for (int var4 = 0; var4 < var3; var4++) {
            int var5 = UnsignedBytes.compare(var1[var4], var2[var4]);
            if (var5 != 0) {
               return var5;
            }
         }

         return var1.length - var2.length;
      }

      @Override
      public String toString() {
         return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
      }
   }
}
