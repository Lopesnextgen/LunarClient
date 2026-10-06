package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import com.google.common.io.ByteSource;

class FileBackedOutputStream$2 extends ByteSource {
   FileBackedOutputStream$2(OutputStreamSerializer outputstreamserializer1) {
      this.field1 = outputstreamserializer1;
   }

   public InputStream openStream() {
      return OutputStreamSerializer.method2(this.field1);
   }
}
