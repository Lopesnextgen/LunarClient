package com.moonsworth.lunar.genesis;

import java.io.OutputStream;
import com.google.common.io.ByteSink;
import com.google.common.io.CharSink;

class BaseEncoding$1 extends ByteSink {
   BaseEncoding$1(MixinHelper4_11 mixinhelper4_111, CharSink mixinhelper_112) {
      this.field2 = mixinhelper4_111;
      this.field1 = mixinhelper_112;
   }

   public OutputStream openStream() {
      return this.field2.encodingStream(this.field1.openStream());
   }
}
