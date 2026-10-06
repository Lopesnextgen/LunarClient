package com.moonsworth.lunar.client.util.lotusfish.mixin;

import com.google.common.io.LittleEndianDataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class Lotusfish3 {
   private final LittleEndianDataInputStream field1;

   private Lotusfish3(InputStream var1) {
      this.field1 = new LittleEndianDataInputStream(var1);
   }

   public static Lotusfish3 method1(String var0) {
      return new Lotusfish3(new BufferedInputStream(new FileInputStream(var0)));
   }

   public static Lotusfish3 method2(InputStream var0) {
      return new Lotusfish3(var0);
   }

   public Lotusfish2 method3() {
      return this.method5() ? this.method6() : null;
   }

   public Lotusfish2 method4(long var1) {
      while (this.method5()) {
         Lotusfish2 var3 = this.method6();
         if (var3.method20() == var1) {
            return var3;
         }
      }

      return null;
   }

   private boolean method5() {
      int var1 = 0;

      while (var1 < Lotusfish2.field1.length) {
         int var2 = this.field1.read();
         if (var2 == -1) {
            return false;
         }

         if (var2 == Lotusfish2.field1[var1]) {
            var1++;
         } else {
            var1 = var2 == Lotusfish2.field1[0] ? 1 : 0;
         }
      }

      return true;
   }

   private Lotusfish2 method6() {
      Lotusfish2 var1 = Lotusfish2.method1();
      int var2 = this.field1.readUnsignedByte();
      if (var2 != 0) {
         throw new LotusfishException("Unsupported Ogg page version: " + var2);
      }

      var1.method2(this.field1.readUnsignedByte());
      var1.method19(this.field1.readLong());
      var1.method21(Integer.toUnsignedLong(this.field1.readInt()));
      var1.method23(Integer.toUnsignedLong(this.field1.readInt()));
      var1.method10(this.field1.readInt());
      int var3 = this.field1.readUnsignedByte();
      byte[] var4 = this.field1.readNBytes(var3);
      int var5 = 0;

      for (byte var9 : var4) {
         int var10 = Byte.toUnsignedInt(var9);
         var5 += var10;
         if (var10 < 255) {
            byte[] var11 = this.field1.readNBytes(var5);
            var1.method13(var11);
            var5 = 0;
         }
      }

      if (var5 != 0) {
         byte[] var12 = this.field1.readNBytes(var5);
         var1.method14(var12);
      }

      return var1;
   }
}
