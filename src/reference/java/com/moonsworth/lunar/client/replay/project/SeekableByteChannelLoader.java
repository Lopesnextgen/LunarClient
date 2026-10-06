package com.moonsworth.lunar.client.replay.project;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.util.Objects;

public final class SeekableByteChannelLoader implements SeekableByteChannel {
   private static final int field1 = 65536;
   private static final int field2 = 8192;
   private final FileChannel field3;
   private final long field4;
   private final long size;
   private long position;
   private boolean open = true;
   private final ByteBuffer field5 = ByteBuffer.allocateDirect(65536);
   private long field6 = -1L;
   private int bufferLength = 0;
   private long field7 = -1L;

   public SeekableByteChannelLoader(FileChannel filechannel1, long number2, long number4) {
      this.field3 = Objects.requireNonNull(filechannel1);
      this.field4 = number2;
      this.size = number4;
   }

   @Override
   public int read(ByteBuffer buffer1) {
      this.ensureOpen();
      if (this.position >= this.size) {
         return -1;
      }

      if (this.field6 < 0L || this.position < this.field6 || this.position >= this.field6 + this.bufferLength) {
         this.fill();
         if (this.bufferLength == 0) {
            return -1;
         }
      }

      int number2 = (int)(this.position - this.field6);
      int number3 = (int)Math.min(Math.min(this.bufferLength - number2, buffer1.remaining()), this.size - this.position);
      ByteBuffer buffer4 = this.field5.duplicate();
      buffer4.position(number2).limit(number2 + number3);
      buffer1.put(buffer4);
      this.position += number3;
      this.field7 = this.position;
      return number3;
   }

   private void fill() {
      long index1;
      if (this.position < this.field7) {
         index1 = Math.max(0L, this.position - 57344L);
      } else {
         index1 = this.position;
      }

      int number3 = (int)Math.min(65536L, this.size - index1);
      this.field5.clear();
      this.field5.limit(number3);
      int index4 = 0;

      while (index4 < number3) {
         int number5 = this.field3.read(this.field5, this.field4 + index1 + index4);
         if (number5 <= 0) {
            break;
         }

         index4 += number5;
      }

      this.field6 = index1;
      this.bufferLength = index4;
   }

   @Override
   public int write(ByteBuffer buffer1) {
      throw new NonWritableChannelException();
   }

   @Override
   public long position() {
      this.ensureOpen();
      return this.position;
   }

   @Override
   public SeekableByteChannel position(long number1) {
      this.ensureOpen();
      if (number1 >= 0L && number1 <= this.size) {
         this.position = number1;
         return this;
      } else {
         throw new IllegalArgumentException("Position out of bounds: " + number1);
      }
   }

   @Override
   public long size() {
      this.ensureOpen();
      return this.size;
   }

   @Override
   public SeekableByteChannel truncate(long number1) {
      throw new NonWritableChannelException();
   }

   @Override
   public boolean isOpen() {
      return this.open && this.field3.isOpen();
   }

   @Override
   public void close() {
      this.open = false;
      this.field3.close();
   }

   private void ensureOpen() {
      if (!this.isOpen()) {
         throw new ClosedChannelException();
      }
   }
}
