package com.moonsworth.lunar.genesis;

import java.io.Writer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

final class CharStreams$NullWriter extends Writer {
   private static final CharStreams$NullWriter field1 = new CharStreams$NullWriter();

   private CharStreams$NullWriter() {
   }

   @Override
   public void write(int number1) {
   }

   @Override
   public void write(char[] items1) {
      Preconditions.checkNotNull(items1);
   }

   @Override
   public void write(char[] items1, int number2, int number3) {
      Preconditions.checkPositionIndexes(number2, number2 + number3, items1.length);
   }

   @Override
   public void write(String text1) {
      Preconditions.checkNotNull(text1);
   }

   @Override
   public void write(String text1, int number2, int number3) {
      Preconditions.checkPositionIndexes(number2, number2 + number3, text1.length());
   }

   @Override
   public Writer append(@Nullable CharSequence text1) {
      return this;
   }

   @Override
   public Writer append(@Nullable CharSequence text1, int number2, int number3) {
      Preconditions.checkPositionIndexes(number2, number3, text1 == null ? "null".length() : text1.length());
      return this;
   }

   @Override
   public Writer append(char character1) {
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
