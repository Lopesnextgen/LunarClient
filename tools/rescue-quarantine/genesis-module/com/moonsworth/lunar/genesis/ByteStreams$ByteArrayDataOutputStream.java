package com.moonsworth.lunar.genesis;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import com.google.common.io.ByteArrayDataOutput;

class ByteStreams$ByteArrayDataOutputStream implements ByteArrayDataOutput {
   final DataOutput field1;
   final ByteArrayOutputStream field2;

   ByteStreams$ByteArrayDataOutputStream(ByteArrayOutputStream bytearrayoutputstream1) {
      this.field2 = bytearrayoutputstream1;
      this.field1 = new DataOutputStream(bytearrayoutputstream1);
   }

   public void write(int number1) {
      try {
         this.field1.write(number1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void write(byte[] items1) {
      try {
         this.field1.write(items1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void write(byte[] items1, int number2, int number3) {
      try {
         this.field1.write(items1, number2, number3);
      } catch (IOException exception5) {
         throw new AssertionError(exception5);
      }
   }

   public void writeBoolean(boolean flag1) {
      try {
         this.field1.writeBoolean(flag1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeByte(int number1) {
      try {
         this.field1.writeByte(number1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeBytes(String text1) {
      try {
         this.field1.writeBytes(text1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeChar(int number1) {
      try {
         this.field1.writeChar(number1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeChars(String text1) {
      try {
         this.field1.writeChars(text1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeDouble(double value1) {
      try {
         this.field1.writeDouble(value1);
      } catch (IOException exception4) {
         throw new AssertionError(exception4);
      }
   }

   public void writeFloat(float value1) {
      try {
         this.field1.writeFloat(value1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeInt(int number1) {
      try {
         this.field1.writeInt(number1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeLong(long number1) {
      try {
         this.field1.writeLong(number1);
      } catch (IOException exception4) {
         throw new AssertionError(exception4);
      }
   }

   public void writeShort(int number1) {
      try {
         this.field1.writeShort(number1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public void writeUTF(String text1) {
      try {
         this.field1.writeUTF(text1);
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   public byte[] toByteArray() {
      return this.field2.toByteArray();
   }
}
