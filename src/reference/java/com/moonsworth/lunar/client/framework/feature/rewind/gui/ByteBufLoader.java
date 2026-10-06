package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.handler.codec.DecoderException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ByteBufLoader extends ByteBuf {
   private final ByteBuf field1;
   private static final int field2 = 32767;
   private static final ClassValue<Enum<?>[]> field3 = new ClassValue<Enum<?>[]>() {
      protected Enum<?>[] method1(Class<?> var1) {
         return (Enum<?>[])var1.getEnumConstants();
      }
   };

   public ByteBufLoader(ByteBuf var1) {
      this.field1 = var1;
   }

   public String readString() {
      int var1 = this.readVarInt();
      if (var1 > 32767) {
         throw new DecoderException("String length is longer than maximum allowed (" + var1 + " > 32767)");
      }

      if (var1 < 0) {
         throw new DecoderException("String length is less than zero");
      }

      byte[] var2 = new byte[var1];
      this.readBytes(var2);
      return new String(var2, Charsets.UTF_8);
   }

   public ByteBufLoader method1(String var1) {
      if (var1 == null) {
         var1 = "";
      }

      byte[] var2 = var1.getBytes(Charsets.UTF_8);
      this.method11(var2.length);
      this.writeBytes(var2);
      return this;
   }

   public byte[] readByteArray() {
      int var1 = this.readVarInt();
      if (var1 > Math.min(this.readableBytes(), 327670000)) {
         throw new DecoderException("ByteArray with size " + var1 + " is bigger than allowed, " + this.readableBytes() + " bytes available");
      }

      byte[] var2 = new byte[var1];
      this.readBytes(var2);
      return var2;
   }

   public ByteBufLoader method2(byte[] var1) {
      this.method11(var1.length);
      this.writeBytes(var1);
      return this;
   }

   public List<Integer> method3() {
      int var1 = this.readVarInt();
      if (var1 > Math.min(this.readableBytes(), 32767)) {
         throw new DecoderException("VarIntList with size " + var1 + " is bigger than buffer than allowed");
      }

      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < var1; var3++) {
         var2.add(this.readVarInt());
      }

      return var2;
   }

   public int[] method4() {
      int var1 = this.readVarInt();
      if (var1 > Math.min(this.readableBytes(), 32767)) {
         throw new DecoderException("VarIntArray with size " + var1 + " is bigger than buffer than allowed");
      }

      int[] var2 = new int[var1];

      for (int var3 = 0; var3 < var2.length; var3++) {
         var2[var3] = this.readVarInt();
      }

      return var2;
   }

   public ByteBufLoader method5(List<Integer> var1) {
      this.method11(var1.size());

      for (int var3 : var1) {
         this.method11(var3);
      }

      return this;
   }

   public ByteBufLoader method6(int[] var1) {
      this.method11(var1.length);

      for (int var5 : var1) {
         this.method11(var5);
      }

      return this;
   }

   public long[] method7() {
      int var1 = this.readVarInt();
      if (var1 > Math.min(this.readableBytes() / 8, 32767)) {
         throw new DecoderException("LongArray with size " + var1 + " is bigger than allowed");
      }

      long[] var2 = new long[var1];

      for (int var3 = 0; var3 < var2.length; var3++) {
         var2[var3] = this.readLong();
      }

      return var2;
   }

   public ByteBufLoader method8(long[] var1) {
      this.method11(var1.length);

      for (long var5 : var1) {
         this.writeLong(var5);
      }

      return this;
   }

   public <T extends Enum<T>> T method9(Class<T> var1) {
      return (T)field3.get(var1)[this.readVarInt()];
   }

   public ByteBufLoader method10(Enum<?> var1) {
      this.method11(var1.ordinal());
      return this;
   }

   public int readVarInt() {
      int var1 = 0;
      int var2 = 0;

      byte var3;
      do {
         var3 = this.readByte();
         var1 |= (var3 & 127) << var2++ * 7;
         if (var2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((var3 & 128) == 128);

      return var1;
   }

   public ByteBufLoader method11(int var1) {
      while ((var1 & -128) != 0) {
         this.writeByte(var1 & 127 | 128);
         var1 >>>= 7;
      }

      this.writeByte(var1);
      return this;
   }

   public static int method12(DataInputStream var0) {
      int var1 = 0;
      int var2 = 0;

      byte var3;
      do {
         var3 = var0.readByte();
         var1 |= (var3 & 127) << var2++ * 7;
         if (var2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((var3 & 128) == 128);

      return var1;
   }

   public static void method13(DataOutputStream var0, int var1) {
      while ((var1 & -128) != 0) {
         var0.writeByte(var1 & 127 | 128);
         var1 >>>= 7;
      }

      var0.writeByte(var1);
   }

   public UUID method14() {
      return new UUID(this.readLong(), this.readLong());
   }

   public ByteBufLoader method15(UUID var1) {
      this.writeLong(var1.getMostSignificantBits());
      this.writeLong(var1.getLeastSignificantBits());
      return this;
   }

   public int capacity() {
      return this.field1.capacity();
   }

   public ByteBuf capacity(int var1) {
      return this.field1.capacity(var1);
   }

   public int maxCapacity() {
      return this.field1.maxCapacity();
   }

   public ByteBufAllocator alloc() {
      return this.field1.alloc();
   }

   public ByteOrder order() {
      return this.field1.order();
   }

   public ByteBuf order(ByteOrder var1) {
      return this.field1.order(var1);
   }

   public ByteBuf unwrap() {
      return this.field1.unwrap();
   }

   public boolean isDirect() {
      return this.field1.isDirect();
   }

   public int readerIndex() {
      return this.field1.readerIndex();
   }

   public ByteBuf readerIndex(int var1) {
      return this.field1.readerIndex(var1);
   }

   public int writerIndex() {
      return this.field1.writerIndex();
   }

   public ByteBuf writerIndex(int var1) {
      return this.field1.writerIndex(var1);
   }

   public ByteBuf setIndex(int var1, int var2) {
      return this.field1.setIndex(var1, var2);
   }

   public int readableBytes() {
      return this.field1.readableBytes();
   }

   public int writableBytes() {
      return this.field1.writableBytes();
   }

   public int maxWritableBytes() {
      return this.field1.maxWritableBytes();
   }

   public boolean isReadable() {
      return this.field1.isReadable();
   }

   public boolean isReadable(int var1) {
      return this.field1.isReadable(var1);
   }

   public boolean isWritable() {
      return this.field1.isWritable();
   }

   public boolean isWritable(int var1) {
      return this.field1.isWritable(var1);
   }

   public ByteBuf clear() {
      return this.field1.clear();
   }

   public ByteBuf markReaderIndex() {
      return this.field1.markReaderIndex();
   }

   public ByteBuf resetReaderIndex() {
      return this.field1.resetReaderIndex();
   }

   public ByteBuf markWriterIndex() {
      return this.field1.markWriterIndex();
   }

   public ByteBuf resetWriterIndex() {
      return this.field1.resetWriterIndex();
   }

   public ByteBuf discardReadBytes() {
      return this.field1.discardReadBytes();
   }

   public ByteBuf discardSomeReadBytes() {
      return this.field1.discardSomeReadBytes();
   }

   public ByteBuf ensureWritable(int var1) {
      return this.field1.ensureWritable(var1);
   }

   public int ensureWritable(int var1, boolean var2) {
      return this.field1.ensureWritable(var1, var2);
   }

   public boolean getBoolean(int var1) {
      return this.field1.getBoolean(var1);
   }

   public byte getByte(int var1) {
      return this.field1.getByte(var1);
   }

   public short getUnsignedByte(int var1) {
      return this.field1.getUnsignedByte(var1);
   }

   public short getShort(int var1) {
      return this.field1.getShort(var1);
   }

   public int getUnsignedShort(int var1) {
      return this.field1.getUnsignedShort(var1);
   }

   public int getMedium(int var1) {
      return this.field1.getMedium(var1);
   }

   public int getUnsignedMedium(int var1) {
      return this.field1.getUnsignedMedium(var1);
   }

   public int getInt(int var1) {
      return this.field1.getInt(var1);
   }

   public long getUnsignedInt(int var1) {
      return this.field1.getUnsignedInt(var1);
   }

   public long getLong(int var1) {
      return this.field1.getLong(var1);
   }

   public char getChar(int var1) {
      return this.field1.getChar(var1);
   }

   public float getFloat(int var1) {
      return this.field1.getFloat(var1);
   }

   public double getDouble(int var1) {
      return this.field1.getDouble(var1);
   }

   public ByteBuf getBytes(int var1, ByteBuf var2) {
      return this.field1.getBytes(var1, var2);
   }

   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      return this.field1.getBytes(var1, var2, var3);
   }

   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      return this.field1.getBytes(var1, var2, var3, var4);
   }

   public ByteBuf getBytes(int var1, byte[] var2) {
      return this.field1.getBytes(var1, var2);
   }

   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      return this.field1.getBytes(var1, var2, var3, var4);
   }

   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      return this.field1.getBytes(var1, var2);
   }

   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      return this.field1.getBytes(var1, var2, var3);
   }

   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      return this.field1.getBytes(var1, var2, var3);
   }

   public ByteBuf setBoolean(int var1, boolean var2) {
      return this.field1.setBoolean(var1, var2);
   }

   public ByteBuf setByte(int var1, int var2) {
      return this.field1.setByte(var1, var2);
   }

   public ByteBuf setShort(int var1, int var2) {
      return this.field1.setShort(var1, var2);
   }

   public ByteBuf setMedium(int var1, int var2) {
      return this.field1.setMedium(var1, var2);
   }

   public ByteBuf setInt(int var1, int var2) {
      return this.field1.setInt(var1, var2);
   }

   public ByteBuf setLong(int var1, long var2) {
      return this.field1.setLong(var1, var2);
   }

   public ByteBuf setChar(int var1, int var2) {
      return this.field1.setChar(var1, var2);
   }

   public ByteBuf setFloat(int var1, float var2) {
      return this.field1.setFloat(var1, var2);
   }

   public ByteBuf setDouble(int var1, double var2) {
      return this.field1.setDouble(var1, var2);
   }

   public ByteBuf setBytes(int var1, ByteBuf var2) {
      return this.field1.setBytes(var1, var2);
   }

   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      return this.field1.setBytes(var1, var2, var3);
   }

   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      return this.field1.setBytes(var1, var2, var3, var4);
   }

   public ByteBuf setBytes(int var1, byte[] var2) {
      return this.field1.setBytes(var1, var2);
   }

   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      return this.field1.setBytes(var1, var2, var3, var4);
   }

   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      return this.field1.setBytes(var1, var2);
   }

   public int setBytes(int var1, InputStream var2, int var3) {
      return this.field1.setBytes(var1, var2, var3);
   }

   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      return this.field1.setBytes(var1, var2, var3);
   }

   public ByteBuf setZero(int var1, int var2) {
      return this.field1.setZero(var1, var2);
   }

   public boolean readBoolean() {
      return this.field1.readBoolean();
   }

   public byte readByte() {
      return this.field1.readByte();
   }

   public short readUnsignedByte() {
      return this.field1.readUnsignedByte();
   }

   public short readShort() {
      return this.field1.readShort();
   }

   public int readUnsignedShort() {
      return this.field1.readUnsignedShort();
   }

   public int readMedium() {
      return this.field1.readMedium();
   }

   public int readUnsignedMedium() {
      return this.field1.readUnsignedMedium();
   }

   public int readInt() {
      return this.field1.readInt();
   }

   public long readUnsignedInt() {
      return this.field1.readUnsignedInt();
   }

   public long readLong() {
      return this.field1.readLong();
   }

   public char readChar() {
      return this.field1.readChar();
   }

   public float readFloat() {
      return this.field1.readFloat();
   }

   public double readDouble() {
      return this.field1.readDouble();
   }

   public ByteBuf readBytes(int var1) {
      return this.field1.readBytes(var1);
   }

   public ByteBuf readSlice(int var1) {
      return this.field1.readSlice(var1);
   }

   public ByteBuf readBytes(ByteBuf var1) {
      return this.field1.readBytes(var1);
   }

   public ByteBuf readBytes(ByteBuf var1, int var2) {
      return this.field1.readBytes(var1, var2);
   }

   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      return this.field1.readBytes(var1, var2, var3);
   }

   public ByteBuf readBytes(byte[] var1) {
      return this.field1.readBytes(var1);
   }

   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      return this.field1.readBytes(var1, var2, var3);
   }

   public ByteBuf readBytes(ByteBuffer var1) {
      return this.field1.readBytes(var1);
   }

   public ByteBuf readBytes(OutputStream var1, int var2) {
      return this.field1.readBytes(var1, var2);
   }

   public int readBytes(GatheringByteChannel var1, int var2) {
      return this.field1.readBytes(var1, var2);
   }

   public ByteBuf skipBytes(int var1) {
      return this.field1.skipBytes(var1);
   }

   public ByteBuf writeBoolean(boolean var1) {
      return this.field1.writeBoolean(var1);
   }

   public ByteBuf writeByte(int var1) {
      return this.field1.writeByte(var1);
   }

   public ByteBuf writeShort(int var1) {
      return this.field1.writeShort(var1);
   }

   public ByteBuf writeMedium(int var1) {
      return this.field1.writeMedium(var1);
   }

   public ByteBuf writeInt(int var1) {
      return this.field1.writeInt(var1);
   }

   public ByteBuf writeLong(long var1) {
      return this.field1.writeLong(var1);
   }

   public ByteBuf writeChar(int var1) {
      return this.field1.writeChar(var1);
   }

   public ByteBuf writeFloat(float var1) {
      return this.field1.writeFloat(var1);
   }

   public ByteBuf writeDouble(double var1) {
      return this.field1.writeDouble(var1);
   }

   public ByteBuf writeBytes(ByteBuf var1) {
      return this.field1.writeBytes(var1);
   }

   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      return this.field1.writeBytes(var1, var2);
   }

   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      return this.field1.writeBytes(var1, var2, var3);
   }

   public ByteBuf writeBytes(byte[] var1) {
      return this.field1.writeBytes(var1);
   }

   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      return this.field1.writeBytes(var1, var2, var3);
   }

   public ByteBuf writeBytes(ByteBuffer var1) {
      return this.field1.writeBytes(var1);
   }

   public int writeBytes(InputStream var1, int var2) {
      return this.field1.writeBytes(var1, var2);
   }

   public int writeBytes(ScatteringByteChannel var1, int var2) {
      return this.field1.writeBytes(var1, var2);
   }

   public ByteBuf writeZero(int var1) {
      return this.field1.writeZero(var1);
   }

   public int indexOf(int var1, int var2, byte var3) {
      return this.field1.indexOf(var1, var2, var3);
   }

   public int bytesBefore(byte var1) {
      return this.field1.bytesBefore(var1);
   }

   public int bytesBefore(int var1, byte var2) {
      return this.field1.bytesBefore(var1, var2);
   }

   public int bytesBefore(int var1, int var2, byte var3) {
      return this.field1.bytesBefore(var1, var2, var3);
   }

   public int forEachByte(ByteBufProcessor var1) {
      return this.field1.forEachByte(var1);
   }

   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      return this.field1.forEachByte(var1, var2, var3);
   }

   public int forEachByteDesc(ByteBufProcessor var1) {
      return this.field1.forEachByteDesc(var1);
   }

   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      return this.field1.forEachByteDesc(var1, var2, var3);
   }

   public ByteBuf copy() {
      return this.field1.copy();
   }

   public ByteBuf copy(int var1, int var2) {
      return this.field1.copy(var1, var2);
   }

   public ByteBuf slice() {
      return this.field1.slice();
   }

   public ByteBuf slice(int var1, int var2) {
      return this.field1.slice(var1, var2);
   }

   public ByteBuf duplicate() {
      return this.field1.duplicate();
   }

   public int nioBufferCount() {
      return this.field1.nioBufferCount();
   }

   public ByteBuffer nioBuffer() {
      return this.field1.nioBuffer();
   }

   public ByteBuffer nioBuffer(int var1, int var2) {
      return this.field1.nioBuffer(var1, var2);
   }

   public ByteBuffer internalNioBuffer(int var1, int var2) {
      return this.field1.internalNioBuffer(var1, var2);
   }

   public ByteBuffer[] nioBuffers() {
      return this.field1.nioBuffers();
   }

   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return this.field1.nioBuffers(var1, var2);
   }

   public boolean hasArray() {
      return this.field1.hasArray();
   }

   public byte[] array() {
      return this.field1.array();
   }

   public int arrayOffset() {
      return this.field1.arrayOffset();
   }

   public boolean hasMemoryAddress() {
      return this.field1.hasMemoryAddress();
   }

   public long memoryAddress() {
      return this.field1.memoryAddress();
   }

   public String toString(Charset var1) {
      return this.field1.toString(var1);
   }

   public String toString(int var1, int var2, Charset var3) {
      return this.field1.toString(var1, var2, var3);
   }

   public int hashCode() {
      return this.field1.hashCode();
   }

   public boolean equals(Object var1) {
      return this.field1.equals(var1);
   }

   public int compareTo(ByteBuf var1) {
      return this.field1.compareTo(var1);
   }

   public String toString() {
      return this.field1.toString();
   }

   public ByteBuf retain(int var1) {
      return this.field1.retain(var1);
   }

   public boolean release() {
      return this.field1.release();
   }

   public boolean release(int var1) {
      return this.field1.release(var1);
   }

   public int refCnt() {
      return this.field1.refCnt();
   }

   public ByteBuf retain() {
      return this.field1.retain();
   }
}
