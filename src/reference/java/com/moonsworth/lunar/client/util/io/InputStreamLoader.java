package com.moonsworth.lunar.client.util.io;

import java.io.IOException;
import java.io.InputStream;
import com.moonsworth.lunar.client.util.Data;

public class InputStreamLoader extends InputStream {
   private final byte[] field1;
   private final int field2;
   private final int field3;
   private boolean field4 = false;
   private int field5 = 0;
   private int field6 = 0;
   private int count = 0;
   private boolean writerClosed = false;
   private boolean field7 = false;

   public InputStreamLoader(int number1) {
      this(number1, 0);
   }

   public InputStreamLoader(int index1, int number2) {
      if (index1 <= 0) {
         throw new IllegalArgumentException("RingBufferStream capacity must be positive.");
      }

      if (number2 >= 0 && number2 <= index1) {
         this.field1 = new byte[index1];
         this.field2 = index1;
         this.field3 = number2;
         if (this.field3 == 0) {
            this.field4 = true;
         }
      } else {
         throw new IllegalArgumentException("Low watermark must be between 0 and capacity.");
      }
   }

   public synchronized void write(byte number1) {
      this.write(new byte[]{number1}, 0, 1);
   }

   public synchronized void write(byte[] items1, int number2, int number3) {
      if (this.field7) {
         throw new IOException("Stream closed by reader; cannot write.");
      }

      if (this.writerClosed) {
         throw new IOException("Writer closed; cannot write.");
      }

      if (items1 == null) {
         throw new NullPointerException("Data buffer cannot be null.");
      }

      if (number2 >= 0 && number3 >= 0 && number2 + number3 <= items1.length) {
         if (number3 != 0) {
            int number4 = 0;

            while (number4 < number3) {
               while (this.count == this.field2 && !this.writerClosed && !this.field7) {
                  try {
                     this.wait();
                  } catch (InterruptedException interruptedexception10) {
                     Thread.currentThread().interrupt();
                     throw new IOException("Write interrupted", interruptedexception10);
                  }
               }

               if (this.field7) {
                  throw new IOException("Stream closed by reader; write aborted.");
               }

               if (this.writerClosed) {
                  throw new IOException("Writer closed; write aborted.");
               }

               int number5 = number3 - number4;
               int number6 = this.field2 - this.count;
               int number7 = Math.min(number5, number6);
               if (number7 == 0) {
                  if (this.writerClosed || this.field7) {
                     throw new IOException("Stream closed and buffer full, cannot complete write.");
                  }
               } else {
                  int number8 = Math.min(number7, this.field2 - this.field6);
                  System.arraycopy(items1, number2 + number4, this.field1, this.field6, number8);
                  this.field6 = (this.field6 + number8) % this.field2;
                  this.count += number8;
                  number4 += number8;
                  if (number4 < number3 && number7 > number8) {
                     int number9 = number7 - number8;
                     System.arraycopy(items1, number2 + number4, this.field1, this.field6, number9);
                     this.field6 = (this.field6 + number9) % this.field2;
                     this.count += number9;
                     number4 += number9;
                  }

                  if (!this.field4 && this.count >= this.field3) {
                     this.field4 = true;
                  }

                  this.notifyAll();
               }
            }
         }
      } else {
         throw new IndexOutOfBoundsException("Invalid offset/length for data buffer.");
      }
   }

   public synchronized void method1() {
      if (!this.writerClosed) {
         this.writerClosed = true;
         if (!this.field4) {
            this.field4 = true;
         }

         this.notifyAll();
      }
   }

   @Override
   public synchronized int read() {
      while ((this.count == 0 || !this.field4) && !this.writerClosed && !this.field7 && (!this.field4 || this.count <= 0)) {
         if (!this.field4 && this.count > 0 && (this.writerClosed || this.field7)) {
            this.field4 = true;
            break;
         }

         try {
            this.wait();
         } catch (InterruptedException interruptedexception2) {
            Thread.currentThread().interrupt();
            throw new IOException("Read interrupted", interruptedexception2);
         }
      }

      if (!this.field4 && (this.writerClosed || this.field7)) {
         this.field4 = true;
      }

      if (this.count == 0 && (this.writerClosed || this.field7)) {
         return -1;
      }

      if (this.count == 0 && !this.field4) {
         return -1;
      }

      int number1 = this.field1[this.field5] & 255;
      this.field5 = (this.field5 + 1) % this.field2;
      this.count--;
      this.notifyAll();
      return number1;
   }

   @Override
   public synchronized int read(byte[] items1, int number2, int number3) {
      if (items1 == null) {
         throw new NullPointerException();
      }

      if (number2 < 0 || number3 < 0 || number3 > items1.length - number2) {
         throw new IndexOutOfBoundsException();
      }

      if (number3 == 0) {
         return 0;
      }

      while ((this.count == 0 || !this.field4) && !this.writerClosed && !this.field7 && (!this.field4 || this.count <= 0)) {
         if (!this.field4 && this.count > 0 && (this.writerClosed || this.field7)) {
            this.field4 = true;
            break;
         }

         try {
            this.wait();
         } catch (InterruptedException interruptedexception8) {
            Thread.currentThread().interrupt();
            throw new IOException("Read interrupted", interruptedexception8);
         }
      }

      if (!this.field4 && (this.writerClosed || this.field7)) {
         this.field4 = true;
      }

      if (this.count != 0 || !this.writerClosed && !this.field7) {
         if (this.count == 0 && !this.field4) {
            return -1;
         }

         int number4 = Math.min(number3, this.count);
         int number5 = 0;
         int number6 = Math.min(number4, this.field2 - this.field5);
         System.arraycopy(this.field1, this.field5, items1, number2 + number5, number6);
         this.field5 = (this.field5 + number6) % this.field2;
         this.count -= number6;
         number5 += number6;
         if (number5 < number4) {
            int number7 = number4 - number5;
            System.arraycopy(this.field1, this.field5, items1, number2 + number5, number7);
            this.field5 = (this.field5 + number7) % this.field2;
            this.count -= number7;
         }

         this.notifyAll();
         return number4;
      } else {
         return -1;
      }
   }

   @Override
   public synchronized int available() {
      return !this.field4 && !this.writerClosed && !this.field7 ? 0 : this.count;
   }

   @Override
   public synchronized void close() {
      if (!this.field7) {
         this.field7 = true;
         if (!this.field4) {
            this.field4 = true;
         }

         this.notifyAll();
      }
   }

   public synchronized boolean method2() {
      return this.field7;
   }
}
