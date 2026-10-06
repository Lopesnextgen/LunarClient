package com.moonsworth.lunar.client.audio.ogg;

import com.google.common.io.LittleEndianDataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class OggPageReader {
   private final LittleEndianDataInputStream field1;

   private OggPageReader(InputStream input1) {
      this.field1 = new LittleEndianDataInputStream(input1);
   }

   public static OggPageReader method1(String text0) {
      return new OggPageReader(new BufferedInputStream(new FileInputStream(text0)));
   }

   public static OggPageReader method2(InputStream input0) {
      return new OggPageReader(input0);
   }

   public OggPage method3() {
      return this.method5() ? this.method6() : null;
   }

   public OggPage method4(long number1) {
      while (this.method5()) {
         OggPage lotusfish23 = this.method6();
         if (lotusfish23.method20() == number1) {
            return lotusfish23;
         }
      }

      return null;
   }

   private boolean method5() {
      int index1 = 0;

      while (index1 < OggPage.field1.length) {
         int number2 = this.field1.read();
         if (number2 == -1) {
            return false;
         }

         if (number2 == OggPage.field1[index1]) {
            index1++;
         } else {
            index1 = number2 == OggPage.field1[0] ? 1 : 0;
         }
      }

      return true;
   }

   private OggPage method6() {
      OggPage lotusfish21 = OggPage.method1();
      int number2 = this.field1.readUnsignedByte();
      if (number2 != 0) {
         throw new LotusfishException("Unsupported Ogg page version: " + number2);
      }

      lotusfish21.method2(this.field1.readUnsignedByte());
      lotusfish21.method19(this.field1.readLong());
      lotusfish21.method21(Integer.toUnsignedLong(this.field1.readInt()));
      lotusfish21.method23(Integer.toUnsignedLong(this.field1.readInt()));
      lotusfish21.method10(this.field1.readInt());
      int number3 = this.field1.readUnsignedByte();
      byte[] items4 = this.field1.readNBytes(number3);
      int number5 = 0;

      for (byte index9 : items4) {
         int number10 = Byte.toUnsignedInt(index9);
         number5 += number10;
         if (number10 < 255) {
            byte[] items11 = this.field1.readNBytes(number5);
            lotusfish21.method13(items11);
            number5 = 0;
         }
      }

      if (number5 != 0) {
         byte[] items12 = this.field1.readNBytes(number5);
         lotusfish21.method14(items12);
      }

      return lotusfish21;
   }
}
