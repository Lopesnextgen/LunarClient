package com.moonsworth.lunar.genesis;

import java.io.OutputStream;
import com.google.common.base.Preconditions;

final class ByteStreams$1 extends OutputStream {
   ByteStreams$1() {
   }

   @Override
   public void write(int number1) {
   }

   @Override
   public void write(byte[] items1) {
      Preconditions.checkNotNull(items1);
   }

   @Override
   public void write(byte[] items1, int number2, int number3) {
      Preconditions.checkNotNull(items1);
   }

   @Override
   public String toString() {
      return "ByteStreams.nullOutputStream()";
   }
}
