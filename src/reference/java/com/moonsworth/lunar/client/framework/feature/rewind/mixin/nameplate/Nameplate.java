package com.moonsworth.lunar.client.framework.feature.rewind.mixin.nameplate;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public final class Nameplate {
   private static final int field1 = 101010256;
   private static final int field2 = 117853008;
   private static final int field3 = 101075792;
   private static final int field4 = 33639248;
   private static final int field5 = 67324752;
   private static final long field6 = 4294967295L;
   private static final int field7 = 65535;

   public static Nameplate.Data4 method1(FileChannel var0, String var1) {
      long var2 = var0.size();
      long var4 = method7(var0, var2);
      Nameplate.Data var6 = method2(var0, var4);
      Nameplate.Data2 var7;
      if (var6.method1()) {
         var7 = method3(var0, var4);
      } else {
         var7 = new Nameplate.Data2(var6.method2(), var6.method3(), var6.method4());
      }

      long var8 = var7.method2();
      long var10 = var7.method2() + var7.size();

      for (long var12 = 0L; var12 < var7.method1() && var8 < var10; var12++) {
         ByteBuffer var14 = method8(var0, var8, 46);
         if (method10(var14, 0) != 33639248L) {
            throw new IOException("Invalid central directory header at " + var8);
         }

         int var15 = method9(var14, 10);
         long var16 = method10(var14, 20);
         long var18 = method10(var14, 24);
         int var20 = method9(var14, 28);
         int var21 = method9(var14, 30);
         int var22 = method9(var14, 32);
         long var23 = method10(var14, 42);
         ByteBuffer var25 = method8(var0, var8 + 46L, var20);
         String var26 = StandardCharsets.UTF_8.decode(var25).toString();
         ByteBuffer var27 = method8(var0, var8 + 46L + var20, var21);
         Nameplate.Data5 var28 = method4(var27, var18, var16, var23);
         var18 = var28.method1();
         var23 = var28.method2();
         if (var26.equals(var1)) {
            if (var15 != 0) {
               throw new IOException(var1 + " is not STORED");
            }

            Nameplate.Data3 var29 = method6(var0, var23);
            long var30 = var23 + 30L + var29.field1 + var29.extraLength;
            return new Nameplate.Data4(var30, var18);
         }

         var8 += 46L + var20 + var21 + var22;
      }

      throw new FileNotFoundException(var1);
   }

   private static Nameplate.Data method2(FileChannel var0, long var1) {
      ByteBuffer var3 = method8(var0, var1, 22);
      if (method10(var3, 0) != 101010256L) {
         throw new IOException("Invalid EOCD at " + var1);
      }

      int var4 = method9(var3, 10);
      long var5 = method10(var3, 12);
      long var7 = method10(var3, 16);
      return new Nameplate.Data(var4, var5, var7);
   }

   private static Nameplate.Data2 method3(FileChannel var0, long var1) {
      long var3 = var1 - 20L;
      if (var3 < 0L) {
         throw new IOException("ZIP64 EOCD locator not found");
      }

      ByteBuffer var5 = method8(var0, var3, 20);
      if (method10(var5, 0) != 117853008L) {
         throw new IOException("ZIP64 EOCD locator not found at " + var3);
      }

      long var6 = method11(var5, 8);
      ByteBuffer var8 = method8(var0, var6, 56);
      if (method10(var8, 0) != 101075792L) {
         throw new IOException("Invalid ZIP64 EOCD at " + var6);
      }

      long var9 = method11(var8, 32);
      long var11 = method11(var8, 40);
      long var13 = method11(var8, 48);
      return new Nameplate.Data2(var9, var11, var13);
   }

   private static Nameplate.Data5 method4(ByteBuffer var0, long var1, long var3, long var5) {
      boolean var7 = var1 == 4294967295L;
      boolean var8 = var3 == 4294967295L;
      boolean var9 = var5 == 4294967295L;
      if (!var7 && !var8 && !var9) {
         return new Nameplate.Data5(var1, var5);
      }

      int var10 = 0;

      while (var10 + 4 <= var0.limit()) {
         int var11 = method9(var0, var10);
         int var12 = method9(var0, var10 + 2);
         int var13 = var10 + 4;
         int var14 = var13 + var12;
         if (var14 > var0.limit()) {
            throw new IOException("Invalid ZIP extra field");
         }

         if (var11 == 1) {
            int var15 = var13;
            if (var7) {
               method5(var0, var15, var14);
               var1 = method11(var0, var15);
               var15 += 8;
            }

            if (var8) {
               method5(var0, var15, var14);
               var15 += 8;
            }

            if (var9) {
               method5(var0, var15, var14);
               var5 = method11(var0, var15);
            }

            return new Nameplate.Data5(var1, var5);
         }

         var10 = var14;
      }

      throw new IOException("Required ZIP64 extra field missing");
   }

   private static void method5(ByteBuffer var0, int var1, int var2) {
      if (var1 + 8 > var2) {
         throw new IOException("Truncated ZIP64 extra field");
      }
   }

   private static Nameplate.Data3 method6(FileChannel var0, long var1) {
      ByteBuffer var3 = method8(var0, var1, 30);
      if (method10(var3, 0) != 67324752L) {
         throw new IOException("Invalid local file header at " + var1);
      }

      int var4 = method9(var3, 26);
      int var5 = method9(var3, 28);
      return new Nameplate.Data3(var4, var5);
   }

   private static long method7(FileChannel var0, long var1) {
      char var3 = '\uffff';
      byte var4 = 22;
      int var5 = (int)Math.min(var1, var4 + var3);
      long var6 = var1 - var5;
      ByteBuffer var8 = method8(var0, var6, var5);

      for (int var9 = var5 - var4; var9 >= 0; var9--) {
         if (method10(var8, var9) == 101010256L) {
            return var6 + var9;
         }
      }

      throw new IOException("Could not find ZIP end of central directory");
   }

   private static ByteBuffer method8(FileChannel var0, long var1, int var3) {
      ByteBuffer var4 = ByteBuffer.allocate(var3);
      var4.order(ByteOrder.LITTLE_ENDIAN);

      while (var4.hasRemaining()) {
         int var5 = var0.read(var4, var1 + var4.position());
         if (var5 < 0) {
            throw new EOFException();
         }
      }

      var4.flip();
      return var4;
   }

   private static int method9(ByteBuffer var0, int var1) {
      return Short.toUnsignedInt(var0.getShort(var1));
   }

   private static long method10(ByteBuffer var0, int var1) {
      return Integer.toUnsignedLong(var0.getInt(var1));
   }

   private static long method11(ByteBuffer var0, int var1) {
      return var0.getLong(var1);
   }

   private class Data {
      private final int field1;
      private final long field2;
      private final long field3;

      private Data(int var1, long var2, long var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var4;
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

   private class Data2 {
      private final long field1;
      private final long field2;
      private final long field3;

      private Data2(long var1, long var3, long var5) {
         this.field1 = var1;
         this.field2 = var3;
         this.field3 = var5;
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

   private class Data3 {
      private final int field1;
      private final int extraLength;

      private Data3(int var1, int var2) {
         this.field1 = var1;
         this.extraLength = var2;
      }

      public int method1() {
         return this.field1;
      }

      public int method2() {
         return this.extraLength;
      }
   }

   public class Data4 {
      private final long dataOffset;
      private final long field1;

      public Data4(long var1, long var3) {
         this.dataOffset = var1;
         this.field1 = var3;
      }

      public long method1() {
         return this.dataOffset;
      }

      public long method2() {
         return this.field1;
      }
   }

   private class Data5 {
      private final long field1;
      private final long field2;

      private Data5(long var1, long var3) {
         this.field1 = var1;
         this.field2 = var3;
      }

      public long method1() {
         return this.field1;
      }

      public long method2() {
         return this.field2;
      }
   }
}
