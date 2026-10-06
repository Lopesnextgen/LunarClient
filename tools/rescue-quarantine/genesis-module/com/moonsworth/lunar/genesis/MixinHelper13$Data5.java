package com.moonsworth.lunar.genesis;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

class MixinHelper13$Data5 implements DataOutputExtension {
   final DataOutput field1;
   final ByteArrayOutputStream field2;

   MixinHelper13$Data5(ByteArrayOutputStream var1) {
      this.field2 = var1;
      this.field1 = new DataOutputStream(var1);
   }

   @Override
   public void write(int var1) {
      try {
         this.field1.write(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void write(byte[] var1) {
      try {
         this.field1.write(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      try {
         this.field1.write(var1, var2, var3);
      } catch (IOException var5) {
         throw new AssertionError(var5);
      }
   }

   @Override
   public void writeBoolean(boolean var1) {
      try {
         this.field1.writeBoolean(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeByte(int var1) {
      try {
         this.field1.writeByte(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeBytes(String var1) {
      try {
         this.field1.writeBytes(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeChar(int var1) {
      try {
         this.field1.writeChar(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeChars(String var1) {
      try {
         this.field1.writeChars(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeDouble(double var1) {
      try {
         this.field1.writeDouble(var1);
      } catch (IOException var4) {
         throw new AssertionError(var4);
      }
   }

   @Override
   public void writeFloat(float var1) {
      try {
         this.field1.writeFloat(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeInt(int var1) {
      try {
         this.field1.writeInt(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeLong(long var1) {
      try {
         this.field1.writeLong(var1);
      } catch (IOException var4) {
         throw new AssertionError(var4);
      }
   }

   @Override
   public void writeShort(int var1) {
      try {
         this.field1.writeShort(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public void writeUTF(String var1) {
      try {
         this.field1.writeUTF(var1);
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   @Override
   public byte[] toByteArray() {
      return this.field2.toByteArray();
   }
}
