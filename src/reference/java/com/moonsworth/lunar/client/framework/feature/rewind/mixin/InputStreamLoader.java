package com.moonsworth.lunar.client.framework.feature.rewind.mixin;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class InputStreamLoader extends InputStream {
   private final SeekableByteChannel field1;
   private final ByteBuffer field2 = ByteBuffer.allocate(1);

   public InputStreamLoader(SeekableByteChannel var1) {
      this.field1 = var1;
   }

   @Override
   public int available() {
      return (int)Math.min(this.field1.size() - this.field1.position(), 2147483647L);
   }

   @Override
   public int read() {
      this.field2.clear();
      int var1 = this.field1.read(this.field2);
      if (var1 == -1) {
         return -1;
      }

      this.field2.flip();
      return this.field2.get() & 0xFF;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      ByteBuffer var4 = ByteBuffer.wrap(var1, var2, var3);
      return this.field1.read(var4);
   }

   @Override
   public void close() {
      this.field1.close();
   }
}
