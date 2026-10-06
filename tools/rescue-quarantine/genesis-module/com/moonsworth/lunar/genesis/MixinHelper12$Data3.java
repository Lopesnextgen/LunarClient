package com.moonsworth.lunar.genesis;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.base.Preconditions;

class MixinHelper12$Data3 extends CharSource {
   final Charset field1;

   MixinHelper12$Data3(ByteSource var1, Charset var2) {
      this.field2 = var1;
      this.field1 = Preconditions.checkNotNull(var2);
   }

   @Override
   public ByteSource method1(Charset var1) {
      return var1.equals(this.field1) ? this.field2 : super.method1(var1);
   }

   @Override
   public java.io.Reader openStream() {
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
