package com.moonsworth.lunar.client.util.collection;

public class RingBuffer {
   private final byte[] field1;
   private final Object field2 = new Object();
   private int field3;
   private int field4;
   private int field5;

   public RingBuffer(int index1) {
      this.field1 = new byte[index1];
   }

   public int method1() {
      synchronized (this.field2) {
         return this.field5;
      }
   }

   public void write(byte[] items1, int index2, int number3) {
      synchronized (this.field2) {
         if (number3 > this.field1.length - this.field5) {
            System.err.println("RingBuffer overflow! Dropping " + number3 + " bytes.");
         } else {
            for (int index5 = 0; index5 < number3; index5++) {
               this.field1[this.field3] = items1[index2 + index5];
               this.field3 = (this.field3 + 1) % this.field1.length;
            }

            this.field5 += number3;
            this.field2.notifyAll();
         }
      }
   }

   public boolean method2(byte[] items1, boolean flag2) {
      int number3 = items1.length;
      synchronized (this.field2) {
         while (this.field5 < number3) {
            if (!flag2) {
               return false;
            }

            this.field2.wait();
         }

         for (int index5 = 0; index5 < number3; index5++) {
            items1[index5] = this.field1[this.field4];
            this.field4 = (this.field4 + 1) % this.field1.length;
         }

         this.field5 -= number3;
         return true;
      }
   }
}
