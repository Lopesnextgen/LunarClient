package com.moonsworth.lunar.client.replay.recording;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioSampleConverter {
   public AudioSampleConverter() {
   }

   public static void method1(ByteBuffer buffer0, ByteBuffer buffer1, int value, int value2) {
      for (int index4 = 0; index4 < value; index4++) {
         int index5 = index4 * value2 * 4;
         float value6 = buffer0.getFloat(index5);
         float value7 = value2 > 1 ? buffer0.getFloat(index5 + 4) : value6;
         float value8 = 0.5F * (value6 + value7);
         short number9 = (short)Math.max(Math.min(value8 * 32767.0F, 32767.0F), -32768.0F);
         buffer1.putShort(number9);
      }

      buffer1.flip();
   }

   public static void method2(ByteBuffer buffer0, ByteBuffer buffer1, float value, float value2, int value3, int value4) {
      byte[] items6 = new byte[buffer0.limit()];
      buffer0.get(items6);
      boolean flag7 = buffer0.order() == ByteOrder.BIG_ENDIAN;
      AudioFormat audioformat8 = new AudioFormat(value, 16, value3, true, flag7);
      AudioFormat audioformat9 = new AudioFormat(value2, 16, value3, true, flag7);
      if (!AudioSystem.isConversionSupported(audioformat9, audioformat8)) {
         throw new IllegalArgumentException("Audio conversion not supported from " + value + "Hz to " + value2 + "Hz");
      }

      try (
         ByteArrayInputStream bytearrayinputstream10 = new ByteArrayInputStream(items6);
         ByteArrayOutputStream bytearrayoutputstream11 = new ByteArrayOutputStream();
      ) {
         AudioInputStream audioinputstream12 = new AudioInputStream(bytearrayinputstream10, audioformat8, items6.length / audioformat8.getFrameSize());
         AudioInputStream audioinputstream13 = AudioSystem.getAudioInputStream(audioformat9, audioinputstream12);
         byte[] items14 = new byte[4096];

         int number15;
         while ((number15 = audioinputstream13.read(items14)) != -1) {
            bytearrayoutputstream11.write(items14, 0, number15);
         }

         audioinputstream13.close();
         byte[] items16 = bytearrayoutputstream11.toByteArray();
         if (items16.length >= value4) {
            buffer1.put(items16, items16.length - value4, value4);
         } else {
            buffer1.put(items16);

            for (int index17 = items16.length; index17 < value4; index17++) {
               buffer1.put((byte)0);
            }
         }

         buffer1.flip();
      } catch (IOException exception22) {
         throw new RuntimeException("IOException during in-memory audio resampling", exception22);
      }
   }
}
