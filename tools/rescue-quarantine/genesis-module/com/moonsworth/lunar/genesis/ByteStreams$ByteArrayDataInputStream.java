package com.moonsworth.lunar.genesis;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import com.google.common.io.ByteArrayDataInput;

class ByteStreams$ByteArrayDataInputStream implements ByteArrayDataInput {
   final DataInput field1;

   ByteStreams$ByteArrayDataInputStream(ByteArrayInputStream bytearrayinputstream1) {
      this.field1 = new DataInputStream(bytearrayinputstream1);
   }

   public void readFully(byte[] items1) {
      try {
         this.field1.readFully(items1);
      } catch (IOException exception3) {
         throw new IllegalStateException(exception3);
      }
   }

   public void readFully(byte[] items1, int number2, int number3) {
      try {
         this.field1.readFully(items1, number2, number3);
      } catch (IOException exception5) {
         throw new IllegalStateException(exception5);
      }
   }

   public int skipBytes(int number1) {
      try {
         return this.field1.skipBytes(number1);
      } catch (IOException exception3) {
         throw new IllegalStateException(exception3);
      }
   }

   public boolean readBoolean() {
      try {
         return this.field1.readBoolean();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public byte readByte() {
      try {
         return this.field1.readByte();
      } catch (EOFException eofexception2) {
         throw new IllegalStateException(eofexception2);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public int readUnsignedByte() {
      try {
         return this.field1.readUnsignedByte();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public short readShort() {
      try {
         return this.field1.readShort();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public int readUnsignedShort() {
      try {
         return this.field1.readUnsignedShort();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public char readChar() {
      try {
         return this.field1.readChar();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public int readInt() {
      try {
         return this.field1.readInt();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public long readLong() {
      try {
         return this.field1.readLong();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public float readFloat() {
      try {
         return this.field1.readFloat();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public double readDouble() {
      try {
         return this.field1.readDouble();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public String readLine() {
      try {
         return this.field1.readLine();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }

   public String readUTF() {
      try {
         return this.field1.readUTF();
      } catch (IOException exception2) {
         throw new IllegalStateException(exception2);
      }
   }
}
