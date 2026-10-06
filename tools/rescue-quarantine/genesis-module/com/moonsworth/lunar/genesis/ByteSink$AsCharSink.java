package com.moonsworth.lunar.genesis;

import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import com.google.common.io.CharSink;
import com.google.common.base.Preconditions;

final class ByteSink$AsCharSink extends CharSink {
   private final Charset field1;

   private ByteSink$AsCharSink(MixinHelper10_8 mixinhelper10_81, Charset charset2) {
      this.field2 = mixinhelper10_81;
      this.field1 = Preconditions.checkNotNull(charset2);
   }

   public java.io.Writer openStream() {
      return new OutputStreamWriter(this.field2.openStream(), this.field1);
   }

   public String toString() {
      return this.field2.toString() + ".asCharSink(" + this.field1 + ")";
   }
}
