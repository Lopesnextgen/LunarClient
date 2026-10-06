package com.moonsworth.lunar.client.util;
import com.moonsworth.lunar.client.util.collection.RingBuffer;

public class ThreadModuleDump41 {
   private final byte[] field1;
   private final Object field2 = new Object();
   private int field3;
   private int field4;
   private int field5;

   public ThreadModuleDump41(int var1) {
      this.field1 = new byte[var1];
   }

   public int method1() {
      synchronized (this.field2) {
         return this.field5;
      }
   }

   public void write(byte[] var1, int var2, int var3) {
      synchronized (this.field2) {
         if (var3 > this.field1.length - this.field5) {
            System.err.println("RingBuffer overflow! Dropping " + var3 + " bytes.");
         } else {
            for (int var5 = 0; var5 < var3; var5++) {
               this.field1[this.field3] = var1[var2 + var5];
               this.field3 = (this.field3 + 1) % this.field1.length;
            }

            this.field5 += var3;
            this.field2.notifyAll();
         }
      }
   }

   public boolean method2(byte[] var1, boolean var2) {
      int var3 = var1.length;
      synchronized (this.field2) {
         while (this.field5 < var3) {
            if (!var2) {
               return false;
            }

            this.field2.wait();
         }

         for (int var5 = 0; var5 < var3; var5++) {
            var1[var5] = this.field1[this.field4];
            this.field4 = (this.field4 + 1) % this.field1.length;
         }

         this.field5 -= var3;
         return true;
      }
   }
}
