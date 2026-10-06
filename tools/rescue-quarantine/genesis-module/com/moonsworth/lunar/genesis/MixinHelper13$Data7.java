package com.moonsworth.lunar.genesis;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.google.common.base.Preconditions;

final class MixinHelper13$Data7 extends FilterInputStream {
   private long left;
   private long mark = -1L;

   MixinHelper13$Data7(InputStream var1, long var2) {
      super(var1);
      Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(var2 >= 0L, "limit must be non-negative");
      this.left = var2;
   }

   @Override
   public int available() {
      return (int)Math.min(this.in.available(), this.left);
   }

   @Override
   public synchronized void mark(int var1) {
      this.in.mark(var1);
      this.mark = this.left;
   }

   @Override
   public int read() {
      if (this.left == 0L) {
         return -1;
      }

      int var1 = this.in.read();
      if (var1 != -1) {
         this.left--;
      }

      return var1;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      if (this.left == 0L) {
         return -1;
      }

      var3 = (int)Math.min(var3, this.left);
      int var4 = this.in.read(var1, var2, var3);
      if (var4 != -1) {
         this.left -= var4;
      }

      return var4;
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
   public long skip(long var1) {
      var1 = Math.min(var1, this.left);
      long var3 = this.in.skip(var1);
      this.left -= var3;
      return var3;
   }
}
