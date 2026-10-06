package com.moonsworth.lunar.genesis;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

@Annotation3
class Writer extends java.io.Writer {
   private final Appendable field1;
   private boolean closed;

   Writer(Appendable var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public void write(char[] var1, int var2, int var3) {
      this.checkNotClosed();
      this.field1.append(new String(var1, var2, var3));
   }

   @Override
   public void write(int var1) {
      this.checkNotClosed();
      this.field1.append((char)var1);
   }

   @Override
   public void write(@Nullable String var1) {
      this.checkNotClosed();
      this.field1.append(var1);
   }

   @Override
   public void write(@Nullable String var1, int var2, int var3) {
      this.checkNotClosed();
      this.field1.append(var1, var2, var2 + var3);
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
   public java.io.Writer append(char var1) {
      this.checkNotClosed();
      this.field1.append(var1);
      return this;
   }

   @Override
   public java.io.Writer append(@Nullable CharSequence var1) {
      this.checkNotClosed();
      this.field1.append(var1);
      return this;
   }

   @Override
   public java.io.Writer append(@Nullable CharSequence var1, int var2, int var3) {
      this.checkNotClosed();
      this.field1.append(var1, var2, var3);
      return this;
   }

   private void checkNotClosed() {
      if (this.closed) {
         throw new IOException("Cannot write to a closed writer.");
      }
   }
}
