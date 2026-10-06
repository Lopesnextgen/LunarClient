package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.io.ByteSource;
import com.google.common.base.Preconditions;

@Annotation3
final class InputStreamLoader extends InputStream {
   private Iterator<? extends ByteSource> it;
   private @Nullable InputStream in;

   public InputStreamLoader(Iterator<? extends ByteSource> var1) {
      this.it = Preconditions.checkNotNull(var1);
      this.advance();
   }

   @Override
   public void close() {
      if (this.in != null) {
         try {
            this.in.close();
         } finally {
            this.in = null;
         }
      }
   }

   private void advance() {
      this.close();
      if (this.it.hasNext()) {
         this.in = this.it.next().openStream();
      }
   }

   @Override
   public int available() {
      return this.in == null ? 0 : this.in.available();
   }

   @Override
   public boolean markSupported() {
      return false;
   }

   @Override
   public int read() {
      while (this.in != null) {
         int var1 = this.in.read();
         if (var1 != -1) {
            return var1;
         }

         this.advance();
      }

      return -1;
   }

   @Override
   public int read(byte @Nullable [] var1, int var2, int var3) {
      while (this.in != null) {
         int var4 = this.in.read(var1, var2, var3);
         if (var4 != -1) {
            return var4;
         }

         this.advance();
      }

      return -1;
   }

   @Override
   public long skip(long var1) {
      if (this.in != null && var1 > 0L) {
         long var3 = this.in.skip(var1);
         if (var3 != 0L) {
            return var3;
         } else {
            return this.read() == -1 ? 0L : 1L + this.in.skip(var1 - 1L);
         }
      } else {
         return 0L;
      }
   }
}
