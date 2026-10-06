package com.moonsworth.lunar.client.replay.project;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class InputStreamLoader extends InputStream {
   private final SeekableByteChannel field1;
   private final ByteBuffer field2 = ByteBuffer.allocate(1);

   public InputStreamLoader(SeekableByteChannel seekablebytechannel1) {
      this.field1 = seekablebytechannel1;
   }

   @Override
   public int available() {
      return (int)Math.min(this.field1.size() - this.field1.position(), 2147483647L);
   }

   @Override
   public int read() {
      this.field2.clear();
      int number1 = this.field1.read(this.field2);
      if (number1 == -1) {
         return -1;
      }

      this.field2.flip();
      return this.field2.get() & 0xFF;
   }

   @Override
   public int read(byte[] items1, int number2, int number3) {
      ByteBuffer buffer4 = ByteBuffer.wrap(items1, number2, number3);
      return this.field1.read(buffer4);
   }

   @Override
   public void close() {
      this.field1.close();
   }
}
