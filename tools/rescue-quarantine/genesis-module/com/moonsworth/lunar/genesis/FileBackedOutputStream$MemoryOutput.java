package com.moonsworth.lunar.genesis;

import java.io.ByteArrayOutputStream;

class FileBackedOutputStream$MemoryOutput extends ByteArrayOutputStream {
   private FileBackedOutputStream$MemoryOutput() {
   }

   byte[] getBuffer() {
      return this.buf;
   }

   int getCount() {
      return this.count;
   }
}
