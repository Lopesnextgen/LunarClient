package com.moonsworth.lunar.client.audio.ogg;

import com.google.common.io.LittleEndianDataOutputStream;
import com.google.common.primitives.Bytes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.Generated;

public class OggPage {
   public static final byte[] field1 = new byte[]{79, 103, 103, 83};
   public static final int field2 = 255;
   private int version = 0;
   private int field3 = 0;
   private long field4;
   private long field5;
   private long field6;
   private int field7;
   private byte[] field8 = new byte[0];
   private final List<byte[]> field9 = new LinkedList<>();

   private OggPage() {
   }

   public static OggPage method1() {
      return new OggPage();
   }

   public void method2(int number1) {
      this.field3 = number1 & 7;
   }

   public boolean method3() {
      return (this.field3 & 1) != 0;
   }

   public void method4() {
      this.field3 |= 1;
   }

   public boolean method5() {
      return (this.field3 & 2) != 0;
   }

   public void method6() {
      this.field3 |= 2;
   }

   public boolean method7() {
      return (this.field3 & 4) != 0;
   }

   public void method8() {
      this.field3 |= 4;
   }

   public int method9() {
      if (this.field7 == 0) {
         byte[] items1 = this.method16();
         this.field7 = Crc32.method1(items1);
      }

      return this.field7;
   }

   void method10(int number1) {
      this.field7 = number1;
   }

   public int method11() {
      return this.field8 != null ? this.field8.length : 0;
   }

   public boolean method12() {
      return Byte.toUnsignedInt(this.field8[this.method11() - 1]) < 255;
   }

   public void method13(byte[] items1) {
      this.field8 = Bytes.concat(new byte[][]{this.field8, this.method17(items1.length, false)});
      this.field9.add(items1);
   }

   public void method14(byte[] items1) {
      if (items1.length % 255 != 0) {
         throw new LotusfishException("Not a partial data packet");
      }

      this.field8 = Bytes.concat(new byte[][]{this.field8, this.method17(items1.length, true)});
      this.field9.add(items1);
   }

   public byte[] method15() {
      if (this.field7 == 0) {
         byte[] items1 = this.method16();
         this.field7 = Crc32.method1(items1);
      }

      return this.method16();
   }

   private byte[] method16() {
      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
      LittleEndianDataOutputStream littleendiandataoutputstream2 = new LittleEndianDataOutputStream(bytearrayoutputstream1);

      try {
         littleendiandataoutputstream2.write(field1);
         littleendiandataoutputstream2.write(this.version);
         littleendiandataoutputstream2.write(this.field3);
         littleendiandataoutputstream2.writeLong(this.field4);
         littleendiandataoutputstream2.writeInt((int)this.field5);
         littleendiandataoutputstream2.writeInt((int)this.field6);
         littleendiandataoutputstream2.writeInt(this.field7);
         littleendiandataoutputstream2.write(this.method11());
         littleendiandataoutputstream2.write(this.field8);

         for (byte[] items4 : this.field9) {
            littleendiandataoutputstream2.write(items4);
         }
      } catch (IOException exception5) {
         throw new RuntimeException("OggPage dump to byte array error", exception5);
      }

      return bytearrayoutputstream1.toByteArray();
   }

   private byte[] method17(int number1, boolean flag) {
      int index3 = number1 / 255;
      if (flag) {
         byte[] items6 = new byte[index3];
         Arrays.fill(items6, (byte)-1);
         return items6;
      } else {
         int number4 = number1 % 255;
         byte[] items5 = new byte[index3 + 1];
         Arrays.fill(items5, 0, index3, (byte)-1);
         items5[index3] = (byte)number4;
         return items5;
      }
   }

   @Generated
   public int getVersion() {
      return this.version;
   }

   @Generated
   public long method18() {
      return this.field4;
   }

   @Generated
   public void method19(long number1) {
      this.field4 = number1;
   }

   @Generated
   public long method20() {
      return this.field5;
   }

   @Generated
   public void method21(long number1) {
      this.field5 = number1;
   }

   @Generated
   public long method22() {
      return this.field6;
   }

   @Generated
   public void method23(long number1) {
      this.field6 = number1;
   }

   @Generated
   public byte[] method24() {
      return this.field8;
   }

   @Generated
   public List<byte[]> method25() {
      return this.field9;
   }
}
