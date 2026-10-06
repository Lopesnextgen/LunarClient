package com.moonsworth.lunar.genesis;

import java.io.Reader;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.io.CharSource;
import com.google.common.base.Preconditions;

@GwtIncompatible
class MultiReader extends Reader {
   private final Iterator<? extends CharSource> field1;
   private @Nullable Reader current;

   MultiReader(Iterator<? extends CharSource> iterator1) {
      this.field1 = iterator1;
      this.advance();
   }

   private void advance() {
      this.close();
      if (this.field1.hasNext()) {
         this.current = this.field1.next().openStream();
      }
   }

   @Override
   public int read(char @Nullable [] items1, int index2, int index3) {
      if (this.current == null) {
         return -1;
      } else {
         int number4 = this.current.read(items1, index2, index3);
         if (number4 == -1) {
            this.advance();
            return this.read(items1, index2, index3);
         } else {
            return number4;
         }
      }
   }

   @Override
   public long skip(long index1) {
      Preconditions.checkArgument(index1 >= 0L, "n is negative");
      if (index1 > 0L) {
         while (this.current != null) {
            long number3 = this.current.skip(index1);
            if (number3 > 0L) {
               return number3;
            }

            this.advance();
         }
      }

      return 0L;
   }

   @Override
   public boolean ready() {
      return this.current != null && this.current.ready();
   }

   @Override
   public void close() {
      if (this.current != null) {
         try {
            this.current.close();
         } finally {
            this.current = null;
         }
      }
   }
}
