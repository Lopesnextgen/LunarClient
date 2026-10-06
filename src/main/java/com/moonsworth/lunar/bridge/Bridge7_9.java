package com.moonsworth.lunar.bridge;

import io.netty.buffer.ByteBuf;

public interface Bridge7_9 {
   ByteBuf bridge$writeInt(int number1);

   int bridge$readInt();

   void bridge$writeString(String text1);

   String bridge$readStringFromBuffer(int number1);

   ByteBuf bridge$writeLong(long number1);

   long bridge$readLong();

   boolean bridge$readBoolean();

   void bridge$writeVarIntToBuffer(int number1);

   int bridge$readVarIntFromBuffer();

   ByteBuf bridge$writeShort(int number1);

   short bridge$readShort();

   ByteBuf bridge$writeBytes(byte[] items1);

   ByteBuf bridge$readBytes(byte[] items1);

   float bridge$readFloat();

   ByteBuf bridge$writeBoolean(boolean flag1);

   int bridge$readableBytes();

   boolean bridge$release();

   ByteBuf bridge$writeFloat(float value1);
}
