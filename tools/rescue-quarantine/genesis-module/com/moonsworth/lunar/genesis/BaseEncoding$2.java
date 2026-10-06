package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;

class BaseEncoding$2 extends ByteSource {
   BaseEncoding$2(MixinHelper4_11 mixinhelper4_111, CharSource mixinhelper92) {
      this.field2 = mixinhelper4_111;
      this.field1 = mixinhelper92;
   }

   public InputStream openStream() {
      return this.field2.decodingStream(this.field1.openStream());
   }
}
