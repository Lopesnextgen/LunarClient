package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

final class MixinHelper11$Data3 extends java.io.Writer {
   private static final MixinHelper11$Data3 field1 = new MixinHelper11$Data3();

   private MixinHelper11$Data3() {
   }

   @Override
   public void write(int var1) {
   }

   @Override
   public void write(char[] var1) {
      Preconditions.checkNotNull(var1);
   }

   @Override
   public void write(char[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);
   }

   @Override
   public void write(String var1) {
      Preconditions.checkNotNull(var1);
   }

   @Override
   public void write(String var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length());
   }

   @Override
   public java.io.Writer append(@Nullable CharSequence var1) {
      return this;
   }

   @Override
   public java.io.Writer append(@Nullable CharSequence var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var3, var1 == null ? "null".length() : var1.length());
      return this;
   }

   @Override
   public java.io.Writer append(char var1) {
      return this;
   }

   @Override
   public void flush() {
   }

   @Override
   public void close() {
   }

   @Override
   public String toString() {
      return "CharStreams.nullWriter()";
   }
}
