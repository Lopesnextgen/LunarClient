package com.moonsworth.lunar.client.replay.project;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public final class ZipEntryLocator {
   private static final int field1 = 101010256;
   private static final int field2 = 117853008;
   private static final int field3 = 101075792;
   private static final int field4 = 33639248;
   private static final int field5 = 67324752;
   private static final long field6 = 4294967295L;
   private static final int field7 = 65535;

   public ZipEntryLocator() {
   }

   public static ZipEntryLocator.ZipEntryLocation method1(FileChannel filechannel0, String text1) {
      long number2 = filechannel0.size();
      long number4 = method7(filechannel0, number2);
      ZipEntryLocator.EndOfCentralDirectory data6 = method2(filechannel0, number4);
      ZipEntryLocator.Zip64EndOfCentralDirectory data27;
      if (data6.method1()) {
         data27 = method3(filechannel0, number4);
      } else {
         data27 = new ZipEntryLocator.Zip64EndOfCentralDirectory(data6.method2(), data6.method3(), data6.method4());
      }

      long number8 = data27.method2();
      long number10 = data27.method2() + data27.size();

      for (long index12 = 0L; index12 < data27.method1() && number8 < number10; index12++) {
         ByteBuffer buffer14 = method8(filechannel0, number8, 46);
         if (method10(buffer14, 0) != 33639248L) {
            throw new IOException("Invalid central directory header at " + number8);
         }

         int number15 = method9(buffer14, 10);
         long number16 = method10(buffer14, 20);
         long number18 = method10(buffer14, 24);
         int number20 = method9(buffer14, 28);
         int number21 = method9(buffer14, 30);
         int number22 = method9(buffer14, 32);
         long number23 = method10(buffer14, 42);
         ByteBuffer buffer25 = method8(filechannel0, number8 + 46L, number20);
         String text26 = StandardCharsets.UTF_8.decode(buffer25).toString();
         ByteBuffer buffer27 = method8(filechannel0, number8 + 46L + number20, number21);
         ZipEntryLocator.Zip64ExtraField data528 = method4(buffer27, number18, number16, number23);
         number18 = data528.method1();
         number23 = data528.method2();
         if (text26.equals(text1)) {
            if (number15 != 0) {
               throw new IOException(text1 + " is not STORED");
            }

            ZipEntryLocator.ZipLocalFileHeader data329 = method6(filechannel0, number23);
            long number30 = number23 + 30L + data329.field1 + data329.extraLength;
            return new ZipEntryLocator.ZipEntryLocation(number30, number18);
         }

         number8 += 46L + number20 + number21 + number22;
      }

      throw new FileNotFoundException(text1);
   }

   private static ZipEntryLocator.EndOfCentralDirectory method2(FileChannel filechannel0, long number1) {
      ByteBuffer buffer3 = method8(filechannel0, number1, 22);
      if (method10(buffer3, 0) != 101010256L) {
         throw new IOException("Invalid EOCD at " + number1);
      }

      int number4 = method9(buffer3, 10);
      long number5 = method10(buffer3, 12);
      long number7 = method10(buffer3, 16);
      return new ZipEntryLocator.EndOfCentralDirectory(number4, number5, number7);
   }

   private static ZipEntryLocator.Zip64EndOfCentralDirectory method3(FileChannel filechannel0, long number1) {
      long number3 = number1 - 20L;
      if (number3 < 0L) {
         throw new IOException("ZIP64 EOCD locator not found");
      }

      ByteBuffer buffer5 = method8(filechannel0, number3, 20);
      if (method10(buffer5, 0) != 117853008L) {
         throw new IOException("ZIP64 EOCD locator not found at " + number3);
      }

      long number6 = method11(buffer5, 8);
      ByteBuffer buffer8 = method8(filechannel0, number6, 56);
      if (method10(buffer8, 0) != 101075792L) {
         throw new IOException("Invalid ZIP64 EOCD at " + number6);
      }

      long number9 = method11(buffer8, 32);
      long number11 = method11(buffer8, 40);
      long number13 = method11(buffer8, 48);
      return new ZipEntryLocator.Zip64EndOfCentralDirectory(number9, number11, number13);
   }

   private static ZipEntryLocator.Zip64ExtraField method4(ByteBuffer buffer0, long number1, long number3, long number5) {
      boolean flag7 = number1 == 4294967295L;
      boolean flag8 = number3 == 4294967295L;
      boolean flag9 = number5 == 4294967295L;
      if (!flag7 && !flag8 && !flag9) {
         return new ZipEntryLocator.Zip64ExtraField(number1, number5);
      }

      int number10 = 0;

      while (number10 + 4 <= buffer0.limit()) {
         int number11 = method9(buffer0, number10);
         int number12 = method9(buffer0, number10 + 2);
         int number13 = number10 + 4;
         int number14 = number13 + number12;
         if (number14 > buffer0.limit()) {
            throw new IOException("Invalid ZIP extra field");
         }

         if (number11 == 1) {
            int number15 = number13;
            if (flag7) {
               method5(buffer0, number15, number14);
               number1 = method11(buffer0, number15);
               number15 += 8;
            }

            if (flag8) {
               method5(buffer0, number15, number14);
               number15 += 8;
            }

            if (flag9) {
               method5(buffer0, number15, number14);
               number5 = method11(buffer0, number15);
            }

            return new ZipEntryLocator.Zip64ExtraField(number1, number5);
         }

         number10 = number14;
      }

      throw new IOException("Required ZIP64 extra field missing");
   }

   private static void method5(ByteBuffer buffer0, int number1, int number2) {
      if (number1 + 8 > number2) {
         throw new IOException("Truncated ZIP64 extra field");
      }
   }

   private static ZipEntryLocator.ZipLocalFileHeader method6(FileChannel filechannel0, long number1) {
      ByteBuffer buffer3 = method8(filechannel0, number1, 30);
      if (method10(buffer3, 0) != 67324752L) {
         throw new IOException("Invalid local file header at " + number1);
      }

      int number4 = method9(buffer3, 26);
      int number5 = method9(buffer3, 28);
      return new ZipEntryLocator.ZipLocalFileHeader(number4, number5);
   }

   private static long method7(FileChannel filechannel0, long number1) {
      char character3 = '\uffff';
      byte number4 = 22;
      int number5 = (int)Math.min(number1, number4 + character3);
      long number6 = number1 - number5;
      ByteBuffer buffer8 = method8(filechannel0, number6, number5);

      for (int index9 = number5 - number4; index9 >= 0; index9--) {
         if (method10(buffer8, index9) == 101010256L) {
            return number6 + index9;
         }
      }

      throw new IOException("Could not find ZIP end of central directory");
   }

   private static ByteBuffer method8(FileChannel filechannel0, long index1, int number3) {
      ByteBuffer buffer4 = ByteBuffer.allocate(number3);
      buffer4.order(ByteOrder.LITTLE_ENDIAN);

      while (buffer4.hasRemaining()) {
         int number5 = filechannel0.read(buffer4, index1 + buffer4.position());
         if (number5 < 0) {
            throw new EOFException();
         }
      }

      buffer4.flip();
      return buffer4;
   }

   private static int method9(ByteBuffer buffer0, int index1) {
      return Short.toUnsignedInt(buffer0.getShort(index1));
   }

   private static long method10(ByteBuffer buffer0, int index1) {
      return Integer.toUnsignedLong(buffer0.getInt(index1));
   }

   private static long method11(ByteBuffer buffer0, int index1) {
      return buffer0.getLong(index1);
   }

   private class EndOfCentralDirectory {
      private final int field1;
      private final long field2;
      private final long field3;

      private EndOfCentralDirectory(int number1, long number2, long number4) {
         this.field1 = number1;
         this.field2 = number2;
         this.field3 = number4;
      }

      boolean method1() {
         return this.field1 == 65535 || this.field2 == 4294967295L || this.field3 == 4294967295L;
      }

      public int method2() {
         return this.field1;
      }

      public long method3() {
         return this.field2;
      }

      public long method4() {
         return this.field3;
      }
   }

   private class Zip64EndOfCentralDirectory {
      private final long field1;
      private final long field2;
      private final long field3;

      private Zip64EndOfCentralDirectory(long number1, long number3, long number5) {
         this.field1 = number1;
         this.field2 = number3;
         this.field3 = number5;
      }

      public long method1() {
         return this.field1;
      }

      public long size() {
         return this.field2;
      }

      public long method2() {
         return this.field3;
      }
   }

   private class ZipLocalFileHeader {
      private final int field1;
      private final int extraLength;

      private ZipLocalFileHeader(int number1, int number2) {
         this.field1 = number1;
         this.extraLength = number2;
      }

      public int method1() {
         return this.field1;
      }

      public int method2() {
         return this.extraLength;
      }
   }

   public class ZipEntryLocation {
      private final long dataOffset;
      private final long field1;

      public ZipEntryLocation(long number1, long number3) {
         this.dataOffset = number1;
         this.field1 = number3;
      }

      public long method1() {
         return this.dataOffset;
      }

      public long method2() {
         return this.field1;
      }
   }

   private class Zip64ExtraField {
      private final long field1;
      private final long field2;

      private Zip64ExtraField(long number1, long number3) {
         this.field1 = number1;
         this.field2 = number3;
      }

      public long method1() {
         return this.field1;
      }

      public long method2() {
         return this.field2;
      }
   }
}
