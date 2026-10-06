package com.moonsworth.lunar.genesis;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

enum MixinHelper2$Type8 implements MixinHelper2$Extension {
   UNSAFE_LITTLE_ENDIAN {
      @Override
      public long getLongLittleEndian(byte[] var1, int var2) {
         return MixinHelper2$Type8.theUnsafe.getLong(var1, (long)var2 + MixinHelper2$Type8.BYTE_ARRAY_BASE_OFFSET);
      }

      @Override
      public void putLongLittleEndian(byte[] var1, int var2, long var3) {
         MixinHelper2$Type8.theUnsafe.putLong(var1, (long)var2 + MixinHelper2$Type8.BYTE_ARRAY_BASE_OFFSET, var3);
      }
   },
   UNSAFE_BIG_ENDIAN {
      @Override
      public long getLongLittleEndian(byte[] var1, int var2) {
         long var3 = MixinHelper2$Type8.theUnsafe.getLong(var1, (long)var2 + MixinHelper2$Type8.BYTE_ARRAY_BASE_OFFSET);
         return Long.reverseBytes(var3);
      }

      @Override
      public void putLongLittleEndian(byte[] var1, int var2, long var3) {
         long var5 = Long.reverseBytes(var3);
         MixinHelper2$Type8.theUnsafe.putLong(var1, (long)var2 + MixinHelper2$Type8.BYTE_ARRAY_BASE_OFFSET, var5);
      }
   };

   private static final Unsafe theUnsafe = getUnsafe();
   private static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);

   MixinHelper2$Type8() {
   }

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

   static {
      if (theUnsafe.arrayIndexScale(byte[].class) != 1) {
         throw new AssertionError();
      }
   }
}
