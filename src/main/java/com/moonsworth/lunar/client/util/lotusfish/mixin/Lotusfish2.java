package com.moonsworth.lunar.client.util.lotusfish.mixin;

import com.google.common.io.LittleEndianDataOutputStream;
import com.google.common.primitives.Bytes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.Generated;

public class Lotusfish2 {
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

   private Lotusfish2() {
   }

   public static Lotusfish2 method1() {
      return new Lotusfish2();
   }

   public void method2(int var1) {
      this.field3 = var1 & 7;
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
         byte[] var1 = this.method16();
         this.field7 = Lotusfish.method1(var1);
      }

      return this.field7;
   }

   void method10(int var1) {
      this.field7 = var1;
   }

   public int method11() {
      return this.field8 != null ? this.field8.length : 0;
   }

   public boolean method12() {
      return Byte.toUnsignedInt(this.field8[this.method11() - 1]) < 255;
   }

   public void method13(byte[] var1) {
      this.field8 = Bytes.concat(new byte[][]{this.field8, this.method17(var1.length, false)});
      this.field9.add(var1);
   }

   public void method14(byte[] var1) {
      if (var1.length % 255 != 0) {
         throw new LotusfishException("Not a partial data packet");
      }

      this.field8 = Bytes.concat(new byte[][]{this.field8, this.method17(var1.length, true)});
      this.field9.add(var1);
   }

   public byte[] method15() {
      if (this.field7 == 0) {
         byte[] var1 = this.method16();
         this.field7 = Lotusfish.method1(var1);
      }

      return this.method16();
   }

   private byte[] method16() {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      LittleEndianDataOutputStream var2 = new LittleEndianDataOutputStream(var1);

      try {
         var2.write(field1);
         var2.write(this.version);
         var2.write(this.field3);
         var2.writeLong(this.field4);
         var2.writeInt((int)this.field5);
         var2.writeInt((int)this.field6);
         var2.writeInt(this.field7);
         var2.write(this.method11());
         var2.write(this.field8);

         for (byte[] var4 : this.field9) {
            var2.write(var4);
         }
      } catch (IOException var5) {
         throw new RuntimeException("OggPage dump to byte array error", var5);
      }

      return var1.toByteArray();
   }

   private byte[] method17(int var1, boolean var2) {
      int var3 = var1 / 255;
      if (var2) {
         byte[] var6 = new byte[var3];
         Arrays.fill(var6, (byte)-1);
         return var6;
      } else {
         int var4 = var1 % 255;
         byte[] var5 = new byte[var3 + 1];
         Arrays.fill(var5, 0, var3, (byte)-1);
         var5[var3] = (byte)var4;
         return var5;
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
   public void method19(long var1) {
      this.field4 = var1;
   }

   @Generated
   public long method20() {
      return this.field5;
   }

   @Generated
   public void method21(long var1) {
      this.field5 = var1;
   }

   @Generated
   public long method22() {
      return this.field6;
   }

   @Generated
   public void method23(long var1) {
      this.field6 = var1;
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
