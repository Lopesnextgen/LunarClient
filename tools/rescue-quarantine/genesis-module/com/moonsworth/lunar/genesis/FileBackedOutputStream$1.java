package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import com.google.common.io.ByteSource;

class FileBackedOutputStream$1 extends ByteSource {
   FileBackedOutputStream$1(OutputStreamSerializer outputstreamserializer1) {
      this.field1 = outputstreamserializer1;
   }

   public InputStream openStream() {
      return OutputStreamSerializer.method2(this.field1);
   }

   protected void finalize() {
      try {
         this.field1.reset();
      } catch (Throwable exception2) {
         exception2.printStackTrace(System.err);
      }
   }
}
