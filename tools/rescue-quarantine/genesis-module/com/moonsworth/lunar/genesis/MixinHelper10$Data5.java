package com.moonsworth.lunar.genesis;

import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import com.google.common.io.ByteSink;
import com.google.common.io.CharSink;
import com.google.common.base.Preconditions;

final class MixinHelper10$Data5 extends CharSink {
   private final Charset field1;

   private MixinHelper10$Data5(ByteSink var1, Charset var2) {
      this.field2 = var1;
      this.field1 = Preconditions.checkNotNull(var2);
   }

   @Override
   public java.io.Writer openStream() {
      return new OutputStreamWriter(this.field2.openStream(), this.field1);
   }

   @Override
   public String toString() {
      return this.field2.toString() + ".asCharSink(" + this.field1 + ")";
   }
}
