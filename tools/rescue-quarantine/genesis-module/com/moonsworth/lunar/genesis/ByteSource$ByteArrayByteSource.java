package com.moonsworth.lunar.genesis;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import com.google.common.hash.HashCode;
import com.google.common.base.Optional;
import com.google.common.hash.HashFunction;
import com.google.common.base.Ascii;
import com.google.common.io.ByteProcessor;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;

class ByteSource$ByteArrayByteSource extends MixinHelper12_2 {
   final byte[] field1;
   final int field2;
   final int field3;

   ByteSource$ByteArrayByteSource(byte[] items1) {
      this(items1, 0, items1.length);
   }

   ByteSource$ByteArrayByteSource(byte[] items1, int number2, int number3) {
      this.field1 = items1;
      this.field2 = number2;
      this.field3 = number3;
   }

   public InputStream openStream() {
      return new ByteArrayInputStream(this.field1, this.field2, this.field3);
   }

   public InputStream openBufferedStream() {
      return this.openStream();
   }

   public boolean isEmpty() {
      return this.field3 == 0;
   }

   public long size() {
      return this.field3;
   }

   public Optional<Long> method3() {
      return Optional.method2((long)this.field3);
   }

   public byte[] read() {
      return Arrays.copyOfRange(this.field1, this.field2, this.field2 + this.field3);
   }

   public <T> T method5(ByteProcessor<T> mixinhelper14_41) {
      mixinhelper14_41.processBytes(this.field1, this.field2, this.field3);
      return (T)mixinhelper14_41.getResult();
   }

   public long copyTo(OutputStream output1) {
      output1.write(this.field1, this.field2, this.field3);
      return this.field3;
   }

   public HashCode method6(HashFunction mixinhelper5_81) {
      return mixinhelper5_81.method6(this.field1, this.field2, this.field3);
   }

   public MixinHelper12_2 method2(long number1, long number3) {
      Preconditions.checkArgument(number1 >= 0L, "offset (%s) may not be negative", number1);
      Preconditions.checkArgument(number3 >= 0L, "length (%s) may not be negative", number3);
      number1 = Math.min(number1, this.field3);
      number3 = Math.min(number3, this.field3 - number1);
      int number5 = this.field2 + (int)number1;
      return new ByteSource$ByteArrayByteSource(this.field1, number5, (int)number3);
   }

   public String toString() {
      return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.method15().method1(this.field1, this.field2, this.field3), 30, "...") + ")";
   }
}
