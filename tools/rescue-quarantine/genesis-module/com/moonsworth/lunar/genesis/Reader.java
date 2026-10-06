package com.moonsworth.lunar.genesis;

import java.io.IOException;
import java.nio.CharBuffer;
import com.google.common.base.Preconditions;

@Annotation3
final class Reader extends java.io.Reader {
   private CharSequence seq;
   private int pos;
   private int mark;

   public Reader(CharSequence var1) {
      this.seq = Preconditions.checkNotNull(var1);
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
   public synchronized int read(CharBuffer var1) {
      Preconditions.checkNotNull(var1);
      this.checkOpen();
      if (!this.hasRemaining()) {
         return -1;
      }

      int var2 = Math.min(var1.remaining(), this.remaining());

      for (int var3 = 0; var3 < var2; var3++) {
         var1.put(this.seq.charAt(this.pos++));
      }

      return var2;
   }

   @Override
   public synchronized int read() {
      this.checkOpen();
      return this.hasRemaining() ? this.seq.charAt(this.pos++) : -1;
   }

   @Override
   public synchronized int read(char[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);
      this.checkOpen();
      if (!this.hasRemaining()) {
         return -1;
      }

      int var4 = Math.min(var3, this.remaining());

      for (int var5 = 0; var5 < var4; var5++) {
         var1[var2 + var5] = this.seq.charAt(this.pos++);
      }

      return var4;
   }

   @Override
   public synchronized long skip(long var1) {
      Preconditions.checkArgument(var1 >= 0L, "n (%s) may not be negative", var1);
      this.checkOpen();
      int var3 = (int)Math.min(this.remaining(), var1);
      this.pos += var3;
      return var3;
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
   public synchronized void mark(int var1) {
      Preconditions.checkArgument(var1 >= 0, "readAheadLimit (%s) may not be negative", var1);
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
