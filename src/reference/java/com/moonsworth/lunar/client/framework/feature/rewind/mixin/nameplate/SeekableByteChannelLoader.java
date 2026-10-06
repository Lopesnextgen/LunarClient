package com.moonsworth.lunar.client.framework.feature.rewind.mixin.nameplate;

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

   public SeekableByteChannelLoader(FileChannel var1, long var2, long var4) {
      this.field3 = Objects.requireNonNull(var1);
      this.field4 = var2;
      this.size = var4;
   }

   @Override
   public int read(ByteBuffer var1) {
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

      int var2 = (int)(this.position - this.field6);
      int var3 = (int)Math.min(Math.min(this.bufferLength - var2, var1.remaining()), this.size - this.position);
      ByteBuffer var4 = this.field5.duplicate();
      var4.position(var2).limit(var2 + var3);
      var1.put(var4);
      this.position += var3;
      this.field7 = this.position;
      return var3;
   }

   private void fill() {
      long var1;
      if (this.position < this.field7) {
         var1 = Math.max(0L, this.position - 57344L);
      } else {
         var1 = this.position;
      }

      int var3 = (int)Math.min(65536L, this.size - var1);
      this.field5.clear();
      this.field5.limit(var3);
      int var4 = 0;

      while (var4 < var3) {
         int var5 = this.field3.read(this.field5, this.field4 + var1 + var4);
         if (var5 <= 0) {
            break;
         }

         var4 += var5;
      }

      this.field6 = var1;
      this.bufferLength = var4;
   }

   @Override
   public int write(ByteBuffer var1) {
      throw new NonWritableChannelException();
   }

   @Override
   public long position() {
      this.ensureOpen();
      return this.position;
   }

   @Override
   public SeekableByteChannel position(long var1) {
      this.ensureOpen();
      if (var1 >= 0L && var1 <= this.size) {
         this.position = var1;
         return this;
      } else {
         throw new IllegalArgumentException("Position out of bounds: " + var1);
      }
   }

   @Override
   public long size() {
      this.ensureOpen();
      return this.size;
   }

   @Override
   public SeekableByteChannel truncate(long var1) {
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
