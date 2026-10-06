package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

final class NumberBase3 extends NumberBase implements MixinHelper_12, Serializable {
   private static final long field8 = 7249069246863182397L;

   @Override
   final long fn(long var1, long var3) {
      return var1 + var3;
   }

   public NumberBase3() {
   }

   @Override
   public void add(long var1) {
      NumberBase.Data2[] var3 = this.field4;
      if (this.field4 == null) {
         long var4 = this.base;
         if (this.method1(this.base, var4 + var1)) {
            return;
         }
      }

      boolean var11 = true;
      int[] var8;
      NumberBase.Data2 var9;
      int var10;
      if ((var8 = (int[])field1.get()) != null
         && var3 != null
         && (var10 = var3.length) >= 1
         && (var9 = var3[var10 - 1 & var8[0]]) != null) {
         long var6 = var9.value;
         if (var11 = var9.method1(var9.value, var6 + var1)) {
            return;
         }
      }

      this.method3(var1, var8, var11);
   }

   @Override
   public void increment() {
      this.add(1L);
   }

   public void decrement() {
      this.add(-1L);
   }

   @Override
   public long sum() {
      long var1 = this.base;
      NumberBase.Data2[] var3 = this.field4;
      if (var3 != null) {
         for (NumberBase.Data2 var6 : var3) {
            if (var6 != null) {
               var1 += var6.value;
            }
         }
      }

      return var1;
   }

   public void reset() {
      this.add(0L);
   }

   public long sumThenReset() {
      long var1 = this.base;
      NumberBase.Data2[] var3 = this.field4;
      this.base = 0L;
      if (var3 != null) {
         for (NumberBase.Data2 var6 : var3) {
            if (var6 != null) {
               var1 += var6.value;
               var6.value = 0L;
            }
         }
      }

      return var1;
   }

   @Override
   public String toString() {
      return Long.toString(this.sum());
   }

   @Override
   public long longValue() {
      return this.sum();
   }

   @Override
   public int intValue() {
      return (int)this.sum();
   }

   @Override
   public float floatValue() {
      return (float)this.sum();
   }

   @Override
   public double doubleValue() {
      return this.sum();
   }

   private void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();
      var1.writeLong(this.sum());
   }

   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      this.busy = 0;
      this.field4 = null;
      this.base = var1.readLong();
   }
}
