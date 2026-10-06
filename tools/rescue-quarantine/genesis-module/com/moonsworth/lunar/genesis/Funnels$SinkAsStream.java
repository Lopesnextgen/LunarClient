package com.moonsworth.lunar.genesis;

import java.io.OutputStream;
import com.google.common.hash.PrimitiveSink;
import com.google.common.base.Preconditions;

class Funnels$SinkAsStream extends OutputStream {
   final PrimitiveSink field1;

   Funnels$SinkAsStream(PrimitiveSink mixinhelper4_31) {
      this.field1 = (PrimitiveSink)Preconditions.checkNotNull(mixinhelper4_31);
   }

   @Override
   public void write(int number1) {
      this.field1.method1((byte)number1);
   }

   @Override
   public void write(byte[] items1) {
      this.field1.method2(items1);
   }

   @Override
   public void write(byte[] items1, int number2, int number3) {
      this.field1.method3(items1, number2, number3);
   }

   @Override
   public String toString() {
      return "Funnels.asOutputStream(" + this.field1 + ")";
   }
}
