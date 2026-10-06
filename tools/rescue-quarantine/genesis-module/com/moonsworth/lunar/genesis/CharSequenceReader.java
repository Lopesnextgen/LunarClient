package com.moonsworth.lunar.genesis;

import java.io.IOException;
import java.nio.CharBuffer;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

@GwtIncompatible
final class CharSequenceReader extends java.io.Reader {
   private CharSequence seq;
   private int pos;
   private int mark;

   public CharSequenceReader(CharSequence text1) {
      this.seq = (CharSequence)Preconditions.checkNotNull(text1);
   }

   private void checkOpen() {
      if (this.seq == null) {
         throw new IOException("reader closed");
      }
   }

   private boolean hasRemaining() {
      return this.remaining() > 0;
   }

   private int remaining() {
      return this.seq.length() - this.pos;
   }

   @Override
   public synchronized int read(CharBuffer buffer1) {
      Preconditions.checkNotNull(buffer1);
      this.checkOpen();
      if (!this.hasRemaining()) {
         return -1;
      }

      int number2 = Math.min(buffer1.remaining(), this.remaining());

      for (int index3 = 0; index3 < number2; index3++) {
         buffer1.put(this.seq.charAt(this.pos++));
      }

      return number2;
   }

   @Override
   public synchronized int read() {
      this.checkOpen();
      return this.hasRemaining() ? this.seq.charAt(this.pos++) : -1;
   }

   @Override
   public synchronized int read(char[] items1, int index2, int number3) {
      Preconditions.checkPositionIndexes(index2, index2 + number3, items1.length);
      this.checkOpen();
      if (!this.hasRemaining()) {
         return -1;
      }

      int number4 = Math.min(number3, this.remaining());

      for (int index5 = 0; index5 < number4; index5++) {
         items1[index2 + index5] = this.seq.charAt(this.pos++);
      }

      return number4;
   }

   @Override
   public synchronized long skip(long number1) {
      Preconditions.checkArgument(number1 >= 0L, "n (%s) may not be negative", number1);
      this.checkOpen();
      int number3 = (int)Math.min(this.remaining(), number1);
      this.pos += number3;
      return number3;
   }

   @Override
   public synchronized boolean ready() {
      this.checkOpen();
      return true;
   }

   @Override
   public boolean markSupported() {
      return true;
   }

   @Override
   public synchronized void mark(int number1) {
      Preconditions.checkArgument(number1 >= 0, "readAheadLimit (%s) may not be negative", number1);
      this.checkOpen();
      this.mark = this.pos;
   }

   @Override
   public synchronized void reset() {
      this.checkOpen();
      this.pos = this.mark;
   }

   @Override
   public synchronized void close() {
      this.seq = null;
   }
}
