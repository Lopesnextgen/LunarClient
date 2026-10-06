package com.moonsworth.lunar.genesis;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import org.checkerframework.checker.nullness.qual.Nullable;
import sun.misc.Unsafe;

@Annotation3
abstract class NumberBase extends Number {
   static final ThreadLocal<int[]> field1 = new ThreadLocal<>();
   static final Random field2 = new Random();
   static final int field3 = Runtime.getRuntime().availableProcessors();
   transient volatile NumberBase.Data2 @Nullable [] field4;
   transient volatile long base;
   transient volatile int busy;
   private static final Unsafe field5;
   private static final long field6;
   private static final long field7;

   final boolean method1(long var1, long var3) {
      return field5.compareAndSwapLong(this, field6, var1, var3);
   }

   final boolean method2() {
      return field5.compareAndSwapInt(this, field7, 0, 1);
   }

   abstract long fn(long var1, long var3);

   final void method3(long var1, int @Nullable [] var3, boolean var4) {
      int var5;
      if (var3 == null) {
         field1.set(var3 = new int[1]);
         int var6 = field2.nextInt();
         var5 = var3[0] = var6 == 0 ? 1 : var6;
      } else {
         var5 = var3[0];
      }

      boolean var34 = false;

      while (true) {
         NumberBase.Data2[] var7 = this.field4;
         int var9;
         if (this.field4 != null && (var9 = var7.length) > 0) {
            NumberBase.Data2 var8;
            if ((var8 = var7[var9 - 1 & var5]) == null) {
               if (this.busy == 0) {
                  NumberBase.Data2 var36 = new NumberBase.Data2(var1);
                  if (this.busy == 0 && this.method2()) {
                     boolean var38 = false;

                     try {
                        NumberBase.Data2[] var14 = this.field4;
                        int var15;
                        int var16;
                        if (this.field4 != null && (var15 = var14.length) > 0 && var14[var16 = var15 - 1 & var5] == null) {
                           var14[var16] = var36;
                           var38 = true;
                        }
                     } finally {
                        this.busy = 0;
                     }

                     if (var38) {
                        break;
                     }
                     continue;
                  }
               }

               var34 = false;
            } else if (!var4) {
               var4 = true;
            } else {
               long var35;
               if (var8.method1(var35 = var8.value, this.fn(var35, var1))) {
                  break;
               }

               if (var9 >= field3 || this.field4 != var7) {
                  var34 = false;
               } else if (!var34) {
                  var34 = true;
               } else if (this.busy == 0 && this.method2()) {
                  try {
                     if (this.field4 == var7) {
                        NumberBase.Data2[] var37 = new NumberBase.Data2[var9 << 1];

                        for (int var39 = 0; var39 < var9; var39++) {
                           var37[var39] = var7[var39];
                        }

                        this.field4 = var37;
                     }
                  } finally {
                     this.busy = 0;
                  }

                  var34 = false;
                  continue;
               }
            }

            int var32 = var5 ^ var5 << 13;
            int var33 = var32 ^ var32 >>> 17;
            var5 = var33 ^ var33 << 5;
            var3[0] = var5;
         } else if (this.busy == 0 && this.field4 == var7 && this.method2()) {
            boolean var12 = false;

            try {
               if (this.field4 == var7) {
                  NumberBase.Data2[] var13 = new NumberBase.Data2[2];
                  var13[var5 & 1] = new NumberBase.Data2(var1);
                  this.field4 = var13;
                  var12 = true;
               }
            } finally {
               this.busy = 0;
            }

            if (var12) {
               break;
            }
         } else {
            long var10;
            if (this.method1(var10 = this.base, this.fn(var10, var1))) {
               break;
            }
         }
      }
   }

   final void method4(long var1) {
      NumberBase.Data2[] var3 = this.field4;
      this.base = var1;
      if (var3 != null) {
         for (NumberBase.Data2 var6 : var3) {
            if (var6 != null) {
               var6.value = var1;
            }
         }
      }
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
      try {
         field5 = getUnsafe();
         Class<NumberBase> var0 = NumberBase.class;
         field6 = field5.objectFieldOffset(var0.getDeclaredField("base"));
         field7 = field5.objectFieldOffset(var0.getDeclaredField("busy"));
      } catch (Exception var1) {
         throw new Error(var1);
      }
   }

   static final class Data2 {
      volatile long p0;
      volatile long p1;
      volatile long p2;
      volatile long p3;
      volatile long p4;
      volatile long p5;
      volatile long p6;
      volatile long value;
      volatile long q0;
      volatile long q1;
      volatile long q2;
      volatile long q3;
      volatile long q4;
      volatile long q5;
      volatile long q6;
      private static final Unsafe field1;
      private static final long field2;

      Data2(long var1) {
         this.value = var1;
      }

      final boolean method1(long var1, long var3) {
         return field1.compareAndSwapLong(this, field2, var1, var3);
      }

      static {
         try {
            field1 = NumberBase.getUnsafe();
            Class<NumberBase.Data2> var0 = NumberBase.Data2.class;
            field2 = field1.objectFieldOffset(var0.getDeclaredField("value"));
         } catch (Exception var1) {
            throw new Error(var1);
         }
      }
   }
}
