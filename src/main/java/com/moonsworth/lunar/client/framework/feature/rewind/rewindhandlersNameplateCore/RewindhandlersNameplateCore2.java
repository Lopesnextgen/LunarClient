package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class RewindhandlersNameplateCore2 {
   public static void method1(ByteBuffer var0, ByteBuffer var1, int var2, int var3) {
      for (int var4 = 0; var4 < var2; var4++) {
         int var5 = var4 * var3 * 4;
         float var6 = var0.getFloat(var5);
         float var7 = var3 > 1 ? var0.getFloat(var5 + 4) : var6;
         float var8 = 0.5F * (var6 + var7);
         short var9 = (short)Math.max(Math.min(var8 * 32767.0F, 32767.0F), -32768.0F);
         var1.putShort(var9);
      }

      var1.flip();
   }

   public static void method2(ByteBuffer var0, ByteBuffer var1, float var2, float var3, int var4, int var5) {
      byte[] var6 = new byte[var0.limit()];
      var0.get(var6);
      boolean var7 = var0.order() == ByteOrder.BIG_ENDIAN;
      AudioFormat var8 = new AudioFormat(var2, 16, var4, true, var7);
      AudioFormat var9 = new AudioFormat(var3, 16, var4, true, var7);
      if (!AudioSystem.isConversionSupported(var9, var8)) {
         throw new IllegalArgumentException("Audio conversion not supported from " + var2 + "Hz to " + var3 + "Hz");
      }

      try (
         ByteArrayInputStream var10 = new ByteArrayInputStream(var6);
         ByteArrayOutputStream var11 = new ByteArrayOutputStream();
      ) {
         AudioInputStream var12 = new AudioInputStream(var10, var8, var6.length / var8.getFrameSize());
         AudioInputStream var13 = AudioSystem.getAudioInputStream(var9, var12);
         byte[] var14 = new byte[4096];

         int var15;
         while ((var15 = var13.read(var14)) != -1) {
            var11.write(var14, 0, var15);
         }

         var13.close();
         byte[] var16 = var11.toByteArray();
         if (var16.length >= var5) {
            var1.put(var16, var16.length - var5, var5);
         } else {
            var1.put(var16);

            for (int var17 = var16.length; var17 < var5; var17++) {
               var1.put((byte)0);
            }
         }

         var1.flip();
      } catch (IOException var22) {
         throw new RuntimeException("IOException during in-memory audio resampling", var22);
      }
   }
}
