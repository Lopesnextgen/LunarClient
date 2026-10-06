package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.io.ByteSource;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

@GwtIncompatible
final class MultiInputStream extends InputStream {
   private Iterator<? extends ByteSource> it;
   private @Nullable InputStream in;

   public MultiInputStream(Iterator<? extends ByteSource> iterator1) {
      this.it = (Iterator<? extends ByteSource>)Preconditions.checkNotNull(iterator1);
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
         int number1 = this.in.read();
         if (number1 != -1) {
            return number1;
         }

         this.advance();
      }

      return -1;
   }

   @Override
   public int read(byte @Nullable [] items1, int index2, int index3) {
      while (this.in != null) {
         int number4 = this.in.read(items1, index2, index3);
         if (number4 != -1) {
            return number4;
         }

         this.advance();
      }

      return -1;
   }

   @Override
   public long skip(long index1) {
      if (this.in != null && index1 > 0L) {
         long number3 = this.in.skip(index1);
         if (number3 != 0L) {
            return number3;
         } else {
            return this.read() == -1 ? 0L : 1L + this.in.skip(index1 - 1L);
         }
      } else {
         return 0L;
      }
   }
}
