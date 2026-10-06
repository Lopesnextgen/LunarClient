package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.common.hash.Hashing;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight.Highlight;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
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

public class Rewindhandlers2Impl extends Rewindhandlers2<AudioInputStream> {
   private final Highlight_4 mediaPool;
   private final Highlight musicTrackManager;
   private AudioFormat audioFormat;
   private byte[] data;
   private final File mediaFile;
   private boolean supported = true;
   private long duration;
   private long position = 0L;

   public Rewindhandlers2Impl(
      File var1, String var2, com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2 var3, Highlight_4 var4, Highlight var5
   ) {
      super(var1, var2, var3);
      this.mediaPool = var4;
      this.musicTrackManager = var5;
      File var6 = var4.method6(UUID.fromString(this.mediaId));
      if (var5.method5().contains(var6.getName())) {
         this.mediaFile = new File(var5.method3(), var6.getName());
      } else {
         this.mediaFile = var6;
      }

      this.init();
      this.loadWaveform();
   }

   @Override
   public void setKey(String var1) {
      boolean var2 = !Objects.equals(this.key, var1);
      super.setKey(var1);
      if (var2) {
         try {
            this.loadWaveform();
            Coordinates.refreshTimeline();
            this.play();
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }
      }
   }

   @Override
   public String getName() {
      return this.mediaFile.getName();
   }

   @Override
   public boolean isValid() {
      return this.supported && this.mediaFile != null && this.mediaFile.isFile();
   }

   @Override
   protected void createSource() {
      super.createSource();
   }

   private void readDuration(Supplier<AudioInputStream> var1) {
      File var2 = new File(this.mediaFile, "audio_cache");
      var2.mkdirs();
      File var3 = new File(var2, this.getHash() + ".json");
      if (var3.isFile()) {
         try (FileReader var17 = new FileReader(var3)) {
            JsonObject var19 = (JsonObject)ThreadModuleDump48.field22.fromJson(var17, JsonObject.class);
            this.duration = var19.get("duration").getAsLong();
         }
      } else {
         try (AudioInputStream var4 = (AudioInputStream)var1.get()) {
            float var5 = this.audioFormat.getFrameRate() / 1000.0F;
            long var6 = 0L;
            if (var4.getFrameLength() != -1L) {
               this.duration = (long)((double)var4.getFrameLength() / var5);
            } else {
               byte[] var8 = new byte[4096];

               int var9;
               while ((var9 = var4.read(var8)) != -1) {
                  var6 += var9;
               }

               this.duration = (long)(
                  (double)var6 / this.sampleFormat.getChannels() / this.sampleFormat.getBytesPerSample() / var5
               );
            }
         }

         JsonObject var16 = new JsonObject();
         var16.addProperty("duration", this.duration);

         try (FileWriter var18 = new FileWriter(var3)) {
            ThreadModuleDump48.field22.toJson(var16, var18);
         }
      }
   }

   @Override
   protected void init() {
      super.init();
      if (this.isValid()) {
         this.data = new byte[this.frameSize * this.audioFormat.getChannels() * (this.audioFormat.getSampleSizeInBits() / 8)];
         this.readDuration(this::method9);
      }
   }

   protected AudioInputStream method9() {
      try {
         AudioInputStream var1 = AudioSystem.getAudioInputStream(this.mediaFile);
         this.audioFormat = var1.getFormat();
         if (this.audioFormat.getEncoding() != Encoding.PCM_SIGNED || this.audioFormat.isBigEndian() || this.audioFormat.getSampleSizeInBits() > 16) {
            int var2 = this.audioFormat.getChannels() == -1 ? 2 : this.audioFormat.getChannels();
            this.audioFormat = new AudioFormat(Encoding.PCM_SIGNED, this.audioFormat.getSampleRate(), 16, var2, var2 * 2, this.audioFormat.getSampleRate(), false);
            var1 = AudioSystem.getAudioInputStream(this.audioFormat, var1);
         }

         this.position = 0L;
         return var1;
      } catch (Exception var3) {
         this.supported = false;
         throw new RuntimeException(var3);
      }
   }

   @Override
   protected GuiType2 readSampleFormat() {
      return GuiType2.getFormat(this.audioFormat.getChannels(), this.audioFormat.getSampleSizeInBits() / 8);
   }

   @Override
   protected int readSampleRate() {
      return (int)this.audioFormat.getSampleRate();
   }

   @Override
   protected int readFrameSize() {
      return 1024;
   }

   @Override
   protected String method8() {
      byte[] var1 = this.mediaId.getBytes(StandardCharsets.UTF_8);
      byte[] var2 = new byte[(int)Math.min(4096L, this.mediaFile.length()) + var1.length];
      System.arraycopy(var1, 0, var2, 0, var1.length);

      try (FileInputStream var3 = new FileInputStream(this.mediaFile)) {
         var3.read(var2, var1.length, var2.length - var1.length);
      }

      return Hashing.md5().hashBytes(var2).toString();
   }

   protected void method8(AudioInputStream var1) {
      long var2 = this.data.length;
      long var4 = 0L;

      while (var4 < var2) {
         int var6 = (int)Math.min(this.data.length, var2 - var4);
         int var7 = var1.read(this.data, 0, var6);
         if (var7 == -1) {
            this.IICIIOHRCHCIOIIHROHHHOCRHIIIHR(true);
            break;
         }

         var4 += var7;
      }

      this.position += var4;
   }

   protected void method9(AudioInputStream var1, ByteBuffer var2, ShortBuffer var3) {
      int var4 = var1.read(this.data);
      if (var4 == -1) {
         this.IICIIOHRCHCIOIIHROHHHOCRHIIIHR(true);
      } else {
         this.position += var4;
         if (this.key != null) {
            Random var5 = new Random(this.key.hashCode() + this.position);

            for (int var6 = 0; var6 < this.data.length; var6++) {
               this.data[var6] = (byte)(this.data[var6] + var5.nextInt(256) - 128);
            }
         }

         var2.clear();
         var2.put(this.data);
         var2.flip();
         var3.clear();
         var3.put(var2.asShortBuffer());
         var3.flip();
      }
   }

   @Override
   public Rewindhandlers2<AudioInputStream> copy() {
      return new Rewindhandlers2Impl(
         this.mediaFile, this.uri, this.waveformRenderer, this.mediaPool, this.musicTrackManager
      );
   }

   @Generated
   @Override
   public long getDuration() {
      return this.duration;
   }
}
