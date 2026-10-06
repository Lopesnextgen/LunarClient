package com.moonsworth.lunar.client.replay.network;

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
      protected Enum<?>[] method1(Class<?> clazz1) {
         return (Enum<?>[])clazz1.getEnumConstants();
      }
   };

   public ByteBufLoader(ByteBuf buffer1) {
      this.field1 = buffer1;
   }

   public String readString() {
      int index1 = this.readVarInt();
      if (index1 > 32767) {
         throw new DecoderException("String length is longer than maximum allowed (" + index1 + " > 32767)");
      }

      if (index1 < 0) {
         throw new DecoderException("String length is less than zero");
      }

      byte[] items2 = new byte[index1];
      this.readBytes(items2);
      return new String(items2, Charsets.UTF_8);
   }

   public ByteBufLoader method1(String text1) {
      if (text1 == null) {
         text1 = "";
      }

      byte[] items2 = text1.getBytes(Charsets.UTF_8);
      this.method11(items2.length);
      this.writeBytes(items2);
      return this;
   }

   public byte[] readByteArray() {
      int index1 = this.readVarInt();
      if (index1 > Math.min(this.readableBytes(), 327670000)) {
         throw new DecoderException("ByteArray with size " + index1 + " is bigger than allowed, " + this.readableBytes() + " bytes available");
      }

      byte[] items2 = new byte[index1];
      this.readBytes(items2);
      return items2;
   }

   public ByteBufLoader method2(byte[] items1) {
      this.method11(items1.length);
      this.writeBytes(items1);
      return this;
   }

   public List<Integer> method3() {
      int number1 = this.readVarInt();
      if (number1 > Math.min(this.readableBytes(), 32767)) {
         throw new DecoderException("VarIntList with size " + number1 + " is bigger than buffer than allowed");
      }

      ArrayList list2 = new ArrayList();

      for (int index3 = 0; index3 < number1; index3++) {
         list2.add(this.readVarInt());
      }

      return list2;
   }

   public int[] method4() {
      int index1 = this.readVarInt();
      if (index1 > Math.min(this.readableBytes(), 32767)) {
         throw new DecoderException("VarIntArray with size " + index1 + " is bigger than buffer than allowed");
      }

      int[] items2 = new int[index1];

      for (int index3 = 0; index3 < items2.length; index3++) {
         items2[index3] = this.readVarInt();
      }

      return items2;
   }

   public ByteBufLoader method5(List<Integer> list1) {
      this.method11(list1.size());

      for (int index3 : list1) {
         this.method11(index3);
      }

      return this;
   }

   public ByteBufLoader method6(int[] items1) {
      this.method11(items1.length);

      for (int index5 : items1) {
         this.method11(index5);
      }

      return this;
   }

   public long[] method7() {
      int index1 = this.readVarInt();
      if (index1 > Math.min(this.readableBytes() / 8, 32767)) {
         throw new DecoderException("LongArray with size " + index1 + " is bigger than allowed");
      }

      long[] items2 = new long[index1];

      for (int index3 = 0; index3 < items2.length; index3++) {
         items2[index3] = this.readLong();
      }

      return items2;
   }

   public ByteBufLoader method8(long[] items1) {
      this.method11(items1.length);

      for (long index5 : items1) {
         this.writeLong(index5);
      }

      return this;
   }

   public <T extends Enum<T>> T method9(Class<T> clazz1) {
      return (T)field3.get(clazz1)[this.readVarInt()];
   }

   public ByteBufLoader method10(Enum<?> value1) {
      this.method11(value1.ordinal());
      return this;
   }

   public int readVarInt() {
      int number1 = 0;
      int index2 = 0;

      byte number3;
      do {
         number3 = this.readByte();
         number1 |= (number3 & 127) << index2++ * 7;
         if (index2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((number3 & 128) == 128);

      return number1;
   }

   public ByteBufLoader method11(int number1) {
      while ((number1 & -128) != 0) {
         this.writeByte(number1 & 127 | 128);
         number1 >>>= 7;
      }

      this.writeByte(number1);
      return this;
   }

   public static int method12(DataInputStream input0) {
      int number1 = 0;
      int index2 = 0;

      byte number3;
      do {
         number3 = input0.readByte();
         number1 |= (number3 & 127) << index2++ * 7;
         if (index2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((number3 & 128) == 128);

      return number1;
   }

   public static void method13(DataOutputStream output0, int number1) {
      while ((number1 & -128) != 0) {
         output0.writeByte(number1 & 127 | 128);
         number1 >>>= 7;
      }

      output0.writeByte(number1);
   }

   public UUID method14() {
      return new UUID(this.readLong(), this.readLong());
   }

   public ByteBufLoader method15(UUID uuid1) {
      this.writeLong(uuid1.getMostSignificantBits());
      this.writeLong(uuid1.getLeastSignificantBits());
      return this;
   }

   public int capacity() {
      return this.field1.capacity();
   }

   public ByteBuf capacity(int number1) {
      return this.field1.capacity(number1);
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

   public ByteBuf order(ByteOrder byteorder1) {
      return this.field1.order(byteorder1);
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

   public ByteBuf readerIndex(int number1) {
      return this.field1.readerIndex(number1);
   }

   public int writerIndex() {
      return this.field1.writerIndex();
   }

   public ByteBuf writerIndex(int number1) {
      return this.field1.writerIndex(number1);
   }

   public ByteBuf setIndex(int number1, int number2) {
      return this.field1.setIndex(number1, number2);
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

   public boolean isReadable(int number1) {
      return this.field1.isReadable(number1);
   }

   public boolean isWritable() {
      return this.field1.isWritable();
   }

   public boolean isWritable(int number1) {
      return this.field1.isWritable(number1);
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

   public ByteBuf ensureWritable(int number1) {
      return this.field1.ensureWritable(number1);
   }

   public int ensureWritable(int number1, boolean flag2) {
      return this.field1.ensureWritable(number1, flag2);
   }

   public boolean getBoolean(int index1) {
      return this.field1.getBoolean(index1);
   }

   public byte getByte(int index1) {
      return this.field1.getByte(index1);
   }

   public short getUnsignedByte(int number1) {
      return this.field1.getUnsignedByte(number1);
   }

   public short getShort(int index1) {
      return this.field1.getShort(index1);
   }

   public int getUnsignedShort(int number1) {
      return this.field1.getUnsignedShort(number1);
   }

   public int getMedium(int number1) {
      return this.field1.getMedium(number1);
   }

   public int getUnsignedMedium(int number1) {
      return this.field1.getUnsignedMedium(number1);
   }

   public int getInt(int index1) {
      return this.field1.getInt(index1);
   }

   public long getUnsignedInt(int number1) {
      return this.field1.getUnsignedInt(number1);
   }

   public long getLong(int index1) {
      return this.field1.getLong(index1);
   }

   public char getChar(int number1) {
      return this.field1.getChar(number1);
   }

   public float getFloat(int index1) {
      return this.field1.getFloat(index1);
   }

   public double getDouble(int index1) {
      return this.field1.getDouble(index1);
   }

   public ByteBuf getBytes(int number1, ByteBuf buffer2) {
      return this.field1.getBytes(number1, buffer2);
   }

   public ByteBuf getBytes(int number1, ByteBuf buffer2, int number3) {
      return this.field1.getBytes(number1, buffer2, number3);
   }

   public ByteBuf getBytes(int number1, ByteBuf buffer2, int number3, int number4) {
      return this.field1.getBytes(number1, buffer2, number3, number4);
   }

   public ByteBuf getBytes(int number1, byte[] items2) {
      return this.field1.getBytes(number1, items2);
   }

   public ByteBuf getBytes(int number1, byte[] items2, int number3, int number4) {
      return this.field1.getBytes(number1, items2, number3, number4);
   }

   public ByteBuf getBytes(int number1, ByteBuffer buffer2) {
      return this.field1.getBytes(number1, buffer2);
   }

   public ByteBuf getBytes(int number1, OutputStream output2, int number3) {
      return this.field1.getBytes(number1, output2, number3);
   }

   public int getBytes(int number1, GatheringByteChannel gatheringbytechannel2, int number3) {
      return this.field1.getBytes(number1, gatheringbytechannel2, number3);
   }

   public ByteBuf setBoolean(int number1, boolean flag2) {
      return this.field1.setBoolean(number1, flag2);
   }

   public ByteBuf setByte(int index1, int index2) {
      return this.field1.setByte(index1, index2);
   }

   public ByteBuf setShort(int index1, int index2) {
      return this.field1.setShort(index1, index2);
   }

   public ByteBuf setMedium(int number1, int number2) {
      return this.field1.setMedium(number1, number2);
   }

   public ByteBuf setInt(int index1, int index2) {
      return this.field1.setInt(index1, index2);
   }

   public ByteBuf setLong(int index1, long index2) {
      return this.field1.setLong(index1, index2);
   }

   public ByteBuf setChar(int number1, int number2) {
      return this.field1.setChar(number1, number2);
   }

   public ByteBuf setFloat(int index1, float value2) {
      return this.field1.setFloat(index1, value2);
   }

   public ByteBuf setDouble(int index1, double value2) {
      return this.field1.setDouble(index1, value2);
   }

   public ByteBuf setBytes(int number1, ByteBuf buffer2) {
      return this.field1.setBytes(number1, buffer2);
   }

   public ByteBuf setBytes(int number1, ByteBuf buffer2, int number3) {
      return this.field1.setBytes(number1, buffer2, number3);
   }

   public ByteBuf setBytes(int number1, ByteBuf buffer2, int number3, int number4) {
      return this.field1.setBytes(number1, buffer2, number3, number4);
   }

   public ByteBuf setBytes(int number1, byte[] items2) {
      return this.field1.setBytes(number1, items2);
   }

   public ByteBuf setBytes(int number1, byte[] items2, int number3, int number4) {
      return this.field1.setBytes(number1, items2, number3, number4);
   }

   public ByteBuf setBytes(int number1, ByteBuffer buffer2) {
      return this.field1.setBytes(number1, buffer2);
   }

   public int setBytes(int number1, InputStream input2, int number3) {
      return this.field1.setBytes(number1, input2, number3);
   }

   public int setBytes(int number1, ScatteringByteChannel scatteringbytechannel2, int number3) {
      return this.field1.setBytes(number1, scatteringbytechannel2, number3);
   }

   public ByteBuf setZero(int number1, int number2) {
      return this.field1.setZero(number1, number2);
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

   public ByteBuf readBytes(int number1) {
      return this.field1.readBytes(number1);
   }

   public ByteBuf readSlice(int number1) {
      return this.field1.readSlice(number1);
   }

   public ByteBuf readBytes(ByteBuf buffer1) {
      return this.field1.readBytes(buffer1);
   }

   public ByteBuf readBytes(ByteBuf buffer1, int number2) {
      return this.field1.readBytes(buffer1, number2);
   }

   public ByteBuf readBytes(ByteBuf buffer1, int number2, int number3) {
      return this.field1.readBytes(buffer1, number2, number3);
   }

   public ByteBuf readBytes(byte[] items1) {
      return this.field1.readBytes(items1);
   }

   public ByteBuf readBytes(byte[] items1, int number2, int number3) {
      return this.field1.readBytes(items1, number2, number3);
   }

   public ByteBuf readBytes(ByteBuffer buffer1) {
      return this.field1.readBytes(buffer1);
   }

   public ByteBuf readBytes(OutputStream output1, int number2) {
      return this.field1.readBytes(output1, number2);
   }

   public int readBytes(GatheringByteChannel gatheringbytechannel1, int number2) {
      return this.field1.readBytes(gatheringbytechannel1, number2);
   }

   public ByteBuf skipBytes(int number1) {
      return this.field1.skipBytes(number1);
   }

   public ByteBuf writeBoolean(boolean flag1) {
      return this.field1.writeBoolean(flag1);
   }

   public ByteBuf writeByte(int number1) {
      return this.field1.writeByte(number1);
   }

   public ByteBuf writeShort(int number1) {
      return this.field1.writeShort(number1);
   }

   public ByteBuf writeMedium(int number1) {
      return this.field1.writeMedium(number1);
   }

   public ByteBuf writeInt(int number1) {
      return this.field1.writeInt(number1);
   }

   public ByteBuf writeLong(long number1) {
      return this.field1.writeLong(number1);
   }

   public ByteBuf writeChar(int number1) {
      return this.field1.writeChar(number1);
   }

   public ByteBuf writeFloat(float value1) {
      return this.field1.writeFloat(value1);
   }

   public ByteBuf writeDouble(double value1) {
      return this.field1.writeDouble(value1);
   }

   public ByteBuf writeBytes(ByteBuf buffer1) {
      return this.field1.writeBytes(buffer1);
   }

   public ByteBuf writeBytes(ByteBuf buffer1, int number2) {
      return this.field1.writeBytes(buffer1, number2);
   }

   public ByteBuf writeBytes(ByteBuf buffer1, int number2, int number3) {
      return this.field1.writeBytes(buffer1, number2, number3);
   }

   public ByteBuf writeBytes(byte[] items1) {
      return this.field1.writeBytes(items1);
   }

   public ByteBuf writeBytes(byte[] items1, int number2, int number3) {
      return this.field1.writeBytes(items1, number2, number3);
   }

   public ByteBuf writeBytes(ByteBuffer buffer1) {
      return this.field1.writeBytes(buffer1);
   }

   public int writeBytes(InputStream input1, int number2) {
      return this.field1.writeBytes(input1, number2);
   }

   public int writeBytes(ScatteringByteChannel scatteringbytechannel1, int number2) {
      return this.field1.writeBytes(scatteringbytechannel1, number2);
   }

   public ByteBuf writeZero(int number1) {
      return this.field1.writeZero(number1);
   }

   public int indexOf(int index1, int index2, byte index3) {
      return this.field1.indexOf(index1, index2, index3);
   }

   public int bytesBefore(byte number1) {
      return this.field1.bytesBefore(number1);
   }

   public int bytesBefore(int number1, byte number2) {
      return this.field1.bytesBefore(number1, number2);
   }

   public int bytesBefore(int number1, int number2, byte number3) {
      return this.field1.bytesBefore(number1, number2, number3);
   }

   public int forEachByte(ByteBufProcessor bytebufprocessor1) {
      return this.field1.forEachByte(bytebufprocessor1);
   }

   public int forEachByte(int number1, int number2, ByteBufProcessor bytebufprocessor3) {
      return this.field1.forEachByte(number1, number2, bytebufprocessor3);
   }

   public int forEachByteDesc(ByteBufProcessor bytebufprocessor1) {
      return this.field1.forEachByteDesc(bytebufprocessor1);
   }

   public int forEachByteDesc(int number1, int number2, ByteBufProcessor bytebufprocessor3) {
      return this.field1.forEachByteDesc(number1, number2, bytebufprocessor3);
   }

   public ByteBuf copy() {
      return this.field1.copy();
   }

   public ByteBuf copy(int number1, int number2) {
      return this.field1.copy(number1, number2);
   }

   public ByteBuf slice() {
      return this.field1.slice();
   }

   public ByteBuf slice(int number1, int number2) {
      return this.field1.slice(number1, number2);
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

   public ByteBuffer nioBuffer(int number1, int number2) {
      return this.field1.nioBuffer(number1, number2);
   }

   public ByteBuffer internalNioBuffer(int number1, int number2) {
      return this.field1.internalNioBuffer(number1, number2);
   }

   public ByteBuffer[] nioBuffers() {
      return this.field1.nioBuffers();
   }

   public ByteBuffer[] nioBuffers(int number1, int number2) {
      return this.field1.nioBuffers(number1, number2);
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

   public String toString(Charset charset1) {
      return this.field1.toString(charset1);
   }

   public String toString(int number1, int number2, Charset charset3) {
      return this.field1.toString(number1, number2, charset3);
   }

   public int hashCode() {
      return this.field1.hashCode();
   }

   public boolean equals(Object obj1) {
      return this.field1.equals(obj1);
   }

   public int compareTo(ByteBuf buffer1) {
      return this.field1.compareTo(buffer1);
   }

   public String toString() {
      return this.field1.toString();
   }

   public ByteBuf retain(int number1) {
      return this.field1.retain(number1);
   }

   public boolean release() {
      return this.field1.release();
   }

   public boolean release(int number1) {
      return this.field1.release(number1);
   }

   public int refCnt() {
      return this.field1.refCnt();
   }

   public ByteBuf retain() {
      return this.field1.retain();
   }
}
