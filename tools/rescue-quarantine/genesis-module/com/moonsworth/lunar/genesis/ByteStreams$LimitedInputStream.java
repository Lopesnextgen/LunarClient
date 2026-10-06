package com.moonsworth.lunar.genesis;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.google.common.base.Preconditions;

final class ByteStreams$LimitedInputStream extends FilterInputStream {
   private long left;
   private long mark = -1L;

   ByteStreams$LimitedInputStream(InputStream input1, long number2) {
      super(input1);
      Preconditions.checkNotNull(input1);
      Preconditions.checkArgument(number2 >= 0L, "limit must be non-negative");
      this.left = number2;
   }

   @Override
   public int available() {
      return (int)Math.min(this.in.available(), this.left);
   }

   @Override
   public synchronized void mark(int number1) {
      this.in.mark(number1);
      this.mark = this.left;
   }

   @Override
   public int read() {
      if (this.left == 0L) {
         return -1;
      }

      int number1 = this.in.read();
      if (number1 != -1) {
         this.left--;
      }

      return number1;
   }

   @Override
   public int read(byte[] items1, int index2, int index3) {
      if (this.left == 0L) {
         return -1;
      }

      index3 = (int)Math.min(index3, this.left);
      int number4 = this.in.read(items1, index2, index3);
      if (number4 != -1) {
         this.left -= number4;
      }

      return number4;
   }

   @Override
   public synchronized void reset() {
      if (!this.in.markSupported()) {
         throw new IOException("Mark not supported");
      }

      if (this.mark == -1L) {
         throw new IOException("Mark not set");
      }

      this.in.reset();
      this.left = this.mark;
   }

   @Override
   public long skip(long index1) {
      index1 = Math.min(index1, this.left);
      long number3 = this.in.skip(index1);
      this.left -= number3;
      return number3;
   }
}
