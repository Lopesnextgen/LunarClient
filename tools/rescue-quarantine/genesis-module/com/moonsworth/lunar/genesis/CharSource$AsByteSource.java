package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.nio.charset.Charset;
import com.google.common.io.ByteSource;
import com.google.common.base.Preconditions;

final class CharSource$AsByteSource extends ByteSource {
   final Charset field1;

   CharSource$AsByteSource(MixinHelper9 mixinhelper91, Charset charset2) {
      this.field2 = mixinhelper91;
      this.field1 = (Charset)Preconditions.checkNotNull(charset2);
   }

   public MixinHelper9 method1(Charset charset1) {
      return charset1.equals(this.field1) ? this.field2 : super.method1(charset1);
   }

   public InputStream openStream() {
      return new ReaderInputStream(this.field2.openStream(), this.field1, 8192);
   }

   public String toString() {
      return this.field2.toString() + ".asByteSource(" + this.field1 + ")";
   }
}
