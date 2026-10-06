package com.moonsworth.lunar.genesis;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import com.google.common.io.CharSource;
import com.google.common.base.Preconditions;

class ByteSource$AsCharSource extends CharSource {
   final Charset field1;

   ByteSource$AsCharSource(MixinHelper12_2 mixinhelper12_21, Charset charset2) {
      this.field2 = mixinhelper12_21;
      this.field1 = (Charset)Preconditions.checkNotNull(charset2);
   }

   @Override
   public MixinHelper12_2 method1(Charset charset1) {
      return charset1.equals(this.field1) ? this.field2 : super.method1(charset1);
   }

   @Override
   public Reader openStream() {
      return new InputStreamReader(this.field2.openStream(), this.field1);
   }

   @Override
   public String read() {
      return new String(this.field2.read(), this.field1);
   }

   @Override
   public String toString() {
      return this.field2.toString() + ".asCharSource(" + this.field1 + ")";
   }
}
