package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.cache.Cache;
import com.moonsworth.lunar.client.event.mixin.fishing.EventGameDirectory;
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

public class MinimapRegionFile {
   private static File field1 = null;

   public MinimapRegionFile() {
   }

   public static boolean method1() {
      return field1 != null;
   }

   public static void method2(EventGameDirectory highlightimpl220) {
      field1 = new File(highlightimpl220.method2(), "minimap");
      field1.mkdirs();
   }

   public static void method3(MinimapMap minimap0, int number1) {
      if (method1()) {
         minimap0.method23().forEach((arg2, arg3) -> {
            try {
               method5(number1, arg2, minimap0.method22(), minimap0);
            } catch (IOException exception5) {
               throw new RuntimeException(exception5);
            }
         });
      }
   }

   public static void method4(MinimapMap minimap0, int number1) {
      method3(minimap0, number1);
      field1 = null;
   }

   public static void method5(int number0, long number1, Cache<Long, com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap> cache3, MinimapMap minimap4) {
      File file5 = new File(field1, number0 + "/" + number1 + ".dat");
      file5.getParentFile().mkdirs();

      try (RandomAccessFile randomaccessfile6 = new RandomAccessFile(file5, "rw")) {
         randomaccessfile6.setLength(0L);
         randomaccessfile6.seek(4L);
         int index7 = 0;
         int number8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(number1);
         int number9 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(number1);

         for (byte index10 = 0; index10 < 32; index10++) {
            for (byte index11 = 0; index11 < 32; index11++) {
               long index12 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method2(number8 * 32 + index10, number9 * 32 + index11);
               minimap4.method24().remove(index12);
               com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap minimap414 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap)cache3.getIfPresent(
                  index12
               );
               if (minimap414 != null) {
                  int[] items15 = minimap414.method5();
                  ByteArrayOutputStream bytearrayoutputstream16 = new ByteArrayOutputStream();
                  Deflater deflater17 = new Deflater();

                  try (
                     DeflaterOutputStream deflateroutputstream18 = new DeflaterOutputStream(bytearrayoutputstream16, deflater17);
                     DataOutputStream output19 = new DataOutputStream(deflateroutputstream18);
                  ) {
                     for (int index23 : items15) {
                        output19.writeInt(index23);
                     }

                     output19.close();
                     deflateroutputstream18.close();
                     byte[] items30 = bytearrayoutputstream16.toByteArray();
                     randomaccessfile6.writeByte(index10);
                     randomaccessfile6.writeByte(index11);
                     randomaccessfile6.writeInt(items30.length);
                     randomaccessfile6.write(items30);
                     index7++;
                  }
               }
            }
         }

         randomaccessfile6.seek(0L);
         randomaccessfile6.writeInt(index7);
      }
   }

   public static Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap> method6(int number0, long number1, MinimapMap minimap3) {
      int number4 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(number1) * 32;
      int number5 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(number1) * 32;
      File file6 = new File(field1, number0 + "/" + number1 + ".dat");
      if (!file6.exists()) {
         file6 = new File(field1, number1 + ".dat");
      }

      if (file6.exists()) {
         try (RandomAccessFile randomaccessfile7 = new RandomAccessFile(file6, "rw")) {
            if (randomaccessfile7.length() == 0L) {
               return new Long2ObjectArrayMap(0);
            }

            randomaccessfile7.seek(0L);
            int number8 = randomaccessfile7.readInt();
            Long2ObjectArrayMap long2objectarraymap9 = new Long2ObjectArrayMap();

            for (int index10 = 0; index10 < number8; index10++) {
               byte number11 = randomaccessfile7.readByte();
               byte number12 = randomaccessfile7.readByte();
               int index13 = randomaccessfile7.readInt();
               byte[] items14 = new byte[index13];
               randomaccessfile7.readFully(items14);
               int[] items15 = new int[256];
               Inflater inflater16 = new Inflater();
               ByteArrayInputStream bytearrayinputstream17 = new ByteArrayInputStream(items14);

               try (
                  InflaterInputStream inflaterinputstream18 = new InflaterInputStream(bytearrayinputstream17, inflater16);
                  DataInputStream input19 = new DataInputStream(inflaterinputstream18);
               ) {
                  for (int index20 = 0; index20 < 256; index20++) {
                     items15[index20] = input19.readInt();
                  }

                  int number31 = number4 + number11;
                  int number21 = number5 + number12;
                  com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap minimap422 = new com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap();
                  minimap422.method6(items15);
                  minimap422.setBuilt(true);
                  long2objectarraymap9.put(com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method2(number31, number21), minimap422);
               }
            }

            minimap3.method22().putAll(long2objectarraymap9);
            return long2objectarraymap9;
         }
      } else {
         return new Long2ObjectArrayMap(0);
      }
   }
}
