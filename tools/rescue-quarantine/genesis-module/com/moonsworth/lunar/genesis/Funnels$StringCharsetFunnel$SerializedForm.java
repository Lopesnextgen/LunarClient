package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.nio.charset.Charset;

class Funnels$StringCharsetFunnel$SerializedForm implements Serializable {
   private final String field1;
   private static final long field2 = 0L;

   Funnels$StringCharsetFunnel$SerializedForm(Charset charset1) {
      this.field1 = charset1.name();
   }

   private Object readResolve() {
      return MixinHelper3_6.method3(Charset.forName(this.field1));
   }
}
