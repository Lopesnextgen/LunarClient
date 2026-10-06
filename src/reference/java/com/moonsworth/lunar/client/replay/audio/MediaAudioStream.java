package com.moonsworth.lunar.client.replay.audio;

import com.google.common.hash.Hashing;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.audio.AudioSampleFormat;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.audio.MusicTrackManager;
import com.moonsworth.lunar.client.framework.LunarConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat.Encoding;
import lombok.Generated;

public class MediaAudioStream extends AudioStream<AudioInputStream> {
   private final MediaPool field17;
   private final MusicTrackManager field18;
   private AudioFormat field19;
   private byte[] data;
   private final File field20;
   private boolean supported = true;
   private long duration;
   private long position = 0L;

   public MediaAudioStream(
      File file1, String text2, com.moonsworth.lunar.client.replay.audio.AudioWaveformRenderer rewindhandlers23, MediaPool highlight_44, MusicTrackManager highlight5
   ) {
      super(file1, text2, rewindhandlers23);
      this.field17 = highlight_44;
      this.field18 = highlight5;
      File file6 = highlight_44.method6(UUID.fromString(this.IIHIOOIORIHROHCHIICIOHORHIOCCI));
      if (highlight5.method5().contains(file6.getName())) {
         this.field20 = new File(highlight5.method3(), file6.getName());
      } else {
         this.field20 = file6;
      }

      this.init();
      this.method1();
   }

   @Override
   public void setKey(String text1) {
      boolean flag2 = !Objects.equals(this.key, text1);
      super.setKey(text1);
      if (flag2) {
         try {
            this.method1();
            RewindEditorContext.refreshTimeline();
            this.play();
         } catch (IOException exception4) {
            throw new RuntimeException(exception4);
         }
      }
   }

   @Override
   public String getName() {
      return this.field20.getName();
   }

   @Override
   public boolean isValid() {
      return this.supported && this.field20 != null && this.field20.isFile();
   }

   @Override
   protected void createSource() {
      super.createSource();
   }

   private void method2(Supplier<AudioInputStream> supplier1) {
      File file2 = new File(this.OCRCHCIIHRRCOCIRORHIOHOIIIICIR, "audio_cache");
      file2.mkdirs();
      File file3 = new File(file2, this.getHash() + ".json");
      if (file3.isFile()) {
         try (FileReader filereader17 = new FileReader(file3)) {
            JsonObject json19 = (JsonObject)LunarConstants.field22.fromJson(filereader17, JsonObject.class);
            this.duration = json19.get("duration").getAsLong();
         }
      } else {
         try (AudioInputStream audioinputstream4 = (AudioInputStream)supplier1.get()) {
            float value5 = this.field19.getFrameRate() / 1000.0F;
            long number6 = 0L;
            if (audioinputstream4.getFrameLength() != -1L) {
               this.duration = (long)((double)audioinputstream4.getFrameLength() / value5);
            } else {
               byte[] items8 = new byte[4096];

               int number9;
               while ((number9 = audioinputstream4.read(items8)) != -1) {
                  number6 += number9;
               }

               this.duration = (long)(
                  (double)number6 / this.OROIROHROHHIICOCROIICRCIIOHOCR.getChannels() / this.OROIROHROHHIICOCROIICRCIIOHOCR.getBytesPerSample() / value5
               );
            }
         }

         JsonObject json16 = new JsonObject();
         json16.addProperty("duration", this.duration);

         try (FileWriter filewriter18 = new FileWriter(file3)) {
            LunarConstants.field22.toJson(json16, filewriter18);
         }
      }
   }

   @Override
   protected void init() {
      super.init();
      if (this.isValid()) {
         this.data = new byte[this.frameSize * this.field19.getChannels() * (this.field19.getSampleSizeInBits() / 8)];
         this.method2(this::method9);
      }
   }

   protected AudioInputStream method9() {
      try {
         AudioInputStream audioinputstream1 = AudioSystem.getAudioInputStream(this.field20);
         this.field19 = audioinputstream1.getFormat();
         if (this.field19.getEncoding() != Encoding.PCM_SIGNED || this.field19.isBigEndian() || this.field19.getSampleSizeInBits() > 16) {
            int number2 = this.field19.getChannels() == -1 ? 2 : this.field19.getChannels();
            this.field19 = new AudioFormat(Encoding.PCM_SIGNED, this.field19.getSampleRate(), 16, number2, number2 * 2, this.field19.getSampleRate(), false);
            audioinputstream1 = AudioSystem.getAudioInputStream(this.field19, audioinputstream1);
         }

         this.position = 0L;
         return audioinputstream1;
      } catch (Exception exception3) {
         this.supported = false;
         throw new RuntimeException(exception3);
      }
   }

   @Override
   protected AudioSampleFormat method5() {
      return AudioSampleFormat.getFormat(this.field19.getChannels(), this.field19.getSampleSizeInBits() / 8);
   }

   @Override
   protected int method6() {
      return (int)this.field19.getSampleRate();
   }

   @Override
   protected int method7() {
      return 1024;
   }

   @Override
   protected String method8() {
      byte[] items1 = this.IIHIOOIORIHROHCHIICIOHORHIOCCI.getBytes(StandardCharsets.UTF_8);
      byte[] items2 = new byte[(int)Math.min(4096L, this.field20.length()) + items1.length];
      System.arraycopy(items1, 0, items2, 0, items1.length);

      try (FileInputStream stream3 = new FileInputStream(this.field20)) {
         stream3.read(items2, items1.length, items2.length - items1.length);
      }

      return Hashing.md5().hashBytes(items2).toString();
   }

   protected void method8(AudioInputStream audioinputstream1) {
      long number2 = this.data.length;
      long number4 = 0L;

      while (number4 < number2) {
         int index6 = (int)Math.min(this.data.length, number2 - number4);
         int number7 = audioinputstream1.read(this.data, 0, index6);
         if (number7 == -1) {
            this.method16(true);
            break;
         }

         number4 += number7;
      }

      this.position += number4;
   }

   protected void method9(AudioInputStream audioinputstream1, ByteBuffer buffer2, ShortBuffer shortbuffer3) {
      int number4 = audioinputstream1.read(this.data);
      if (number4 == -1) {
         this.method16(true);
      } else {
         this.position += number4;
         if (this.key != null) {
            Random random5 = new Random(this.key.hashCode() + this.position);

            for (int index6 = 0; index6 < this.data.length; index6++) {
               this.data[index6] = (byte)(this.data[index6] + random5.nextInt(256) - 128);
            }
         }

         buffer2.clear();
         buffer2.put(this.data);
         buffer2.flip();
         shortbuffer3.clear();
         shortbuffer3.put(buffer2.asShortBuffer());
         shortbuffer3.flip();
      }
   }

   @Override
   public AudioStream<AudioInputStream> method18() {
      return new MediaAudioStream(
         this.OCRCHCIIHRRCOCIRORHIOHOIIIICIR, this.CIHHIRCROOCCRRROIRROICOCCHCORI, this.CICRHOIIIRORRHCHIICIROHCIRCORH, this.field17, this.field18
      );
   }

   @Generated
   @Override
   public long getDuration() {
      return this.duration;
   }
}
