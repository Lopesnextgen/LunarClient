package com.moonsworth.lunar.genesis;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

class MixinHelper13$Data6 implements DataInputExtension {
   final DataInput field1;

   MixinHelper13$Data6(ByteArrayInputStream var1) {
      this.field1 = new DataInputStream(var1);
   }

   @Override
   public void readFully(byte[] var1) {
      try {
         this.field1.readFully(var1);
      } catch (IOException var3) {
         throw new IllegalStateException(var3);
      }
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) {
      try {
         this.field1.readFully(var1, var2, var3);
      } catch (IOException var5) {
         throw new IllegalStateException(var5);
      }
   }

   @Override
   public int skipBytes(int var1) {
      try {
         return this.field1.skipBytes(var1);
      } catch (IOException var3) {
         throw new IllegalStateException(var3);
      }
   }

   @Override
   public boolean readBoolean() {
      try {
         return this.field1.readBoolean();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public byte readByte() {
      try {
         return this.field1.readByte();
      } catch (EOFException var2) {
         throw new IllegalStateException(var2);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public int readUnsignedByte() {
      try {
         return this.field1.readUnsignedByte();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public short readShort() {
      try {
         return this.field1.readShort();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public int readUnsignedShort() {
      try {
         return this.field1.readUnsignedShort();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public char readChar() {
      try {
         return this.field1.readChar();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public int readInt() {
      try {
         return this.field1.readInt();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public long readLong() {
      try {
         return this.field1.readLong();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public float readFloat() {
      try {
         return this.field1.readFloat();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public double readDouble() {
      try {
         return this.field1.readDouble();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public String readLine() {
      try {
         return this.field1.readLine();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }

   @Override
   public String readUTF() {
      try {
         return this.field1.readUTF();
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }
}
