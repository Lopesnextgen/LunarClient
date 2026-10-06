package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.cache.Cache;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRunDirectory;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class Minimap3 {
   private static File field1 = null;

   public static boolean method1() {
      return field1 != null;
   }

   public static void method2(EventRunDirectory var0) {
      field1 = new File(var0.method2(), "minimap");
      field1.mkdirs();
   }

   public static void method3(Minimap var0, int var1) {
      if (method1()) {
         var0.method23().forEach((var2, var3) -> {
            try {
               method5(var1, var2, var0.method22(), var0);
            } catch (IOException var5) {
               throw new RuntimeException(var5);
            }
         });
      }
   }

   public static void method4(Minimap var0, int var1) {
      method3(var0, var1);
      field1 = null;
   }

   public static void method5(int var0, long var1, Cache<Long, com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4> var3, Minimap var4) {
      File var5 = new File(field1, var0 + "/" + var1 + ".dat");
      var5.getParentFile().mkdirs();

      try (RandomAccessFile var6 = new RandomAccessFile(var5, "rw")) {
         var6.setLength(0L);
         var6.seek(4L);
         int var7 = 0;
         int var8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var1);
         int var9 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var1);

         for (byte var10 = 0; var10 < 32; var10++) {
            for (byte var11 = 0; var11 < 32; var11++) {
               long var12 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method2(var8 * 32 + var10, var9 * 32 + var11);
               var4.method24().remove(var12);
               com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4 var14 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4)var3.getIfPresent(
                  var12
               );
               if (var14 != null) {
                  int[] var15 = var14.getColors();
                  ByteArrayOutputStream var16 = new ByteArrayOutputStream();
                  Deflater var17 = new Deflater();

                  try (
                     DeflaterOutputStream var18 = new DeflaterOutputStream(var16, var17);
                     DataOutputStream var19 = new DataOutputStream(var18);
                  ) {
                     for (int var23 : var15) {
                        var19.writeInt(var23);
                     }

                     var19.close();
                     var18.close();
                     byte[] var30 = var16.toByteArray();
                     var6.writeByte(var10);
                     var6.writeByte(var11);
                     var6.writeInt(var30.length);
                     var6.write(var30);
                     var7++;
                  }
               }
            }
         }

         var6.seek(0L);
         var6.writeInt(var7);
      }
   }

   public static Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4> method6(int var0, long var1, Minimap var3) {
      int var4 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var1) * 32;
      int var5 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var1) * 32;
      File var6 = new File(field1, var0 + "/" + var1 + ".dat");
      if (!var6.exists()) {
         var6 = new File(field1, var1 + ".dat");
      }

      if (var6.exists()) {
         try (RandomAccessFile var7 = new RandomAccessFile(var6, "rw")) {
            if (var7.length() == 0L) {
               return new Long2ObjectArrayMap(0);
            }

            var7.seek(0L);
            int var8 = var7.readInt();
            Long2ObjectArrayMap var9 = new Long2ObjectArrayMap();

            for (int var10 = 0; var10 < var8; var10++) {
               byte var11 = var7.readByte();
               byte var12 = var7.readByte();
               int var13 = var7.readInt();
               byte[] var14 = new byte[var13];
               var7.readFully(var14);
               int[] var15 = new int[256];
               Inflater var16 = new Inflater();
               ByteArrayInputStream var17 = new ByteArrayInputStream(var14);

               try (
                  InflaterInputStream var18 = new InflaterInputStream(var17, var16);
                  DataInputStream var19 = new DataInputStream(var18);
               ) {
                  for (int var20 = 0; var20 < 256; var20++) {
                     var15[var20] = var19.readInt();
                  }

                  int var31 = var4 + var11;
                  int var21 = var5 + var12;
                  com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4 var22 = new com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4();
                  var22.setColors(var15);
                  var22.setBuilt(true);
                  var9.put(com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method2(var31, var21), var22);
               }
            }

            var3.method22().putAll(var9);
            return var9;
         }
      } else {
         return new Long2ObjectArrayMap(0);
      }
   }
}
