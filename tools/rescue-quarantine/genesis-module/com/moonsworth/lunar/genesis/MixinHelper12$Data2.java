package com.moonsworth.lunar.genesis;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import com.google.common.io.ByteSource;
import com.google.common.hash.HashCode;
import com.google.common.io.ByteProcessor;
import com.google.common.base.Preconditions;
import com.google.common.base.Ascii;
import com.google.common.io.BaseEncoding;

class MixinHelper12$Data2 extends ByteSource {
   final byte[] field1;
   final int field2;
   final int field3;

   MixinHelper12$Data2(byte[] var1) {
      this(var1, 0, var1.length);
   }

   MixinHelper12$Data2(byte[] var1, int var2, int var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   @Override
   public InputStream openStream() {
      return new ByteArrayInputStream(this.field1, this.field2, this.field3);
   }

   @Override
   public InputStream openBufferedStream() {
      return this.openStream();
   }

   @Override
   public boolean isEmpty() {
      return this.field3 == 0;
   }

   @Override
   public long size() {
      return this.field3;
   }

   @Override
   public SerializableBase_2<Long> method3() {
      return SerializableBase_2.method2((long)this.field3);
   }

   @Override
   public byte[] read() {
      return Arrays.copyOfRange(this.field1, this.field2, this.field2 + this.field3);
   }

   @Override
   public <T> T method5(ByteProcessor<T> var1) {
      var1.processBytes(this.field1, this.field2, this.field3);
      return (T)var1.getResult();
   }

   @Override
   public long copyTo(OutputStream var1) {
      var1.write(this.field1, this.field2, this.field3);
      return this.field3;
   }

   @Override
   public HashCode method6(MixinHelper5_8 var1) {
      return var1.method6(this.field1, this.field2, this.field3);
   }

   @Override
   public ByteSource method2(long var1, long var3) {
      Preconditions.checkArgument(var1 >= 0L, "offset (%s) may not be negative", var1);
      Preconditions.checkArgument(var3 >= 0L, "length (%s) may not be negative", var3);
      var1 = Math.min(var1, this.field3);
      var3 = Math.min(var3, this.field3 - var1);
      int var5 = this.field2 + (int)var1;
      return new MixinHelper12$Data2(this.field1, var5, (int)var3);
   }

   @Override
   public String toString() {
      return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.method15().method1(this.field1, this.field2, this.field3), 30, "...") + ")";
   }
}
