package com.moonsworth.lunar.genesis;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

@GwtIncompatible
class AppendableWriter extends java.io.Writer {
   private final Appendable field1;
   private boolean closed;

   AppendableWriter(Appendable appendable1) {
      this.field1 = Preconditions.checkNotNull(appendable1);
   }

   @Override
   public void write(char[] items1, int number2, int number3) {
      this.checkNotClosed();
      this.field1.append(new String(items1, number2, number3));
   }

   @Override
   public void write(int number1) {
      this.checkNotClosed();
      this.field1.append((char)number1);
   }

   @Override
   public void write(@Nullable String text1) {
      this.checkNotClosed();
      this.field1.append(text1);
   }

   @Override
   public void write(@Nullable String text1, int number2, int number3) {
      this.checkNotClosed();
      this.field1.append(text1, number2, number2 + number3);
   }

   @Override
   public void flush() {
      this.checkNotClosed();
      if (this.field1 instanceof Flushable) {
         ((Flushable)this.field1).flush();
      }
   }

   @Override
   public void close() {
      this.closed = true;
      if (this.field1 instanceof Closeable) {
         ((Closeable)this.field1).close();
      }
   }

   @Override
   public java.io.Writer append(char character1) {
      this.checkNotClosed();
      this.field1.append(character1);
      return this;
   }

   @Override
   public java.io.Writer append(@Nullable CharSequence text1) {
      this.checkNotClosed();
      this.field1.append(text1);
      return this;
   }

   @Override
   public java.io.Writer append(@Nullable CharSequence text1, int number2, int number3) {
      this.checkNotClosed();
      this.field1.append(text1, number2, number3);
      return this;
   }

   private void checkNotClosed() {
      if (this.closed) {
         throw new IOException("Cannot write to a closed writer.");
      }
   }
}
