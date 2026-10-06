package com.moonsworth.lunar.client.util.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;
import lombok.Generated;

public class RewindableInputStream extends InputStream {
   private final InputStream field1;
   private ByteArrayOutputStream field2 = new ByteArrayOutputStream();
   private byte[] field3 = null;
   private int position = 0;
   private int mark = 0;

   public RewindableInputStream(InputStream input1) {
      this.field1 = input1;
   }

   @Override
   public synchronized int read() {
      byte[] items1 = new byte[1];
      int number2 = this.read(items1, 0, 1);
      return number2 == -1 ? -1 : items1[0] & 0xFF;
   }

   @Override
   public synchronized int read(byte[] items1, int number2, int number3) {
      Objects.checkFromIndexSize(number2, number3, items1.length);
      if (number3 == 0) {
         return 0;
      }

      int number4 = this.position + number3;
      if (this.field2 != null && number4 > this.field2.size()) {
         int number5 = number4 - this.field2.size();
         byte[] items6 = new byte[Math.max(8192, number5)];

         int number7;
         while (this.field2.size() < number4 && (number7 = this.field1.read(items6)) != -1) {
            this.field2.write(items6, 0, number7);
         }

         if (this.field2.size() < number4) {
            this.field3 = this.field2.toByteArray();
            this.field1.close();
            this.field2 = null;
         }
      }

      byte[] items8 = this.field2 == null ? this.field3 : this.field2.toByteArray();
      if (this.position >= items8.length) {
         return -1;
      }

      int number9 = Math.min(number3, items8.length - this.position);
      System.arraycopy(items8, this.position, items1, number2, number9);
      this.position += number9;
      return number9;
   }

   @Override
   public int available() {
      return this.field3 != null ? this.field3.length - this.position : this.field1.available() + (this.field2.size() - this.position);
   }

   @Override
   public boolean markSupported() {
      return true;
   }

   @Override
   public synchronized void mark(int number1) {
      this.mark = this.position;
   }

   @Override
   public synchronized void reset() {
      this.position = this.mark;
      this.mark = 0;
   }

   @Override
   public void close() {
      this.field1.close();
      super.close();
   }

   @Generated
   public int getPosition() {
      return this.position;
   }
}
