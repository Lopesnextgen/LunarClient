package com.moonsworth.lunar.genesis;

import java.io.DataOutput;

@Annotation3
public interface DataOutputExtension extends DataOutput {
   @Override
   void write(int var1);

   @Override
   void write(byte[] var1);

   @Override
   void write(byte[] var1, int var2, int var3);

   @Override
   void writeBoolean(boolean var1);

   @Override
   void writeByte(int var1);

   @Override
   void writeShort(int var1);

   @Override
   void writeChar(int var1);

   @Override
   void writeInt(int var1);

   @Override
   void writeLong(long var1);

   @Override
   void writeFloat(float var1);

   @Override
   void writeDouble(double var1);

   @Override
   void writeChars(String var1);

   @Override
   void writeUTF(String var1);

   @Deprecated
   @Override
   void writeBytes(String var1);

   byte[] toByteArray();
}
