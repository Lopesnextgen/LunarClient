package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.EOFException;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.openal.AL10;

public abstract class Rewindhandlers2<T extends InputStream> {
   private static final int field1 = 4;
   private int sourceId;
   private final int[] bufferIds = new int[4];
   protected final String uri;
   protected final String mediaId;
   protected final File rootDirectory;
   protected T input;
   protected GuiType2 sampleFormat;
   protected int frequency;
   protected int frameSize;
   private long time = 0L;
   private long timeAnchor = 0L;
   private double playbackRate = 1.0;
   private boolean paused = false;
   protected ByteBuffer decodeBuffer;
   protected ShortBuffer sampleBuffer;
   protected ShortBuffer scaledBuffer;
   private List<Float> waveformSamples;
   protected final com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2 waveformRenderer;
   private String hash;
   protected String key;
   private boolean alive = false;
   private boolean eof = false;
   private float volume = 1.0F;

   public Rewindhandlers2(File var1, String var2, com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2 var3) {
      this.rootDirectory = var1;
      this.uri = var2;
      this.mediaId = var2.split("://")[1];
      this.waveformRenderer = var3;
   }

   public boolean isValid() {
      return true;
   }

   protected void init() {
      if (this.isValid()) {
         try {
            this.input = this.openInput();
         } catch (RuntimeException var2) {
            return;
         }

         this.sampleFormat = this.readSampleFormat();
         this.frequency = this.readSampleRate();
         this.frameSize = this.readFrameSize();
         this.hash = this.computeHash();
         this.setEof(false);
         int var1 = this.frameSize * this.sampleFormat.getChannels() * this.sampleFormat.getBytesPerSample();
         this.decodeBuffer = BufferUtils.createByteBuffer(var1);
         this.sampleBuffer = BufferUtils.createShortBuffer(var1);
         this.scaledBuffer = BufferUtils.createShortBuffer(var1);
      }
   }

   protected void loadWaveform() {
      if (this.isValid()) {
         this.waveformSamples = this.waveformRenderer.method1(this.hash, this);
      }
   }

   public List<Float> computeWaveform() {
      try (InputStream var1 = this.openInput()) {
         ArrayList var2 = new ArrayList();
         float var3 = (float)Math.pow(2.0, this.sampleFormat.getBytesPerSample() * 8 - 1);

         while (true) {
            try {
               this.decodeFrame((T)var1, this.decodeBuffer, this.sampleBuffer);
            } catch (EOFException var10) {
               break;
            }

            long var4 = 0L;
            long var6 = this.sampleBuffer.remaining();
            if (var6 <= 0L) {
               break;
            }

            for (int var8 = 0; var8 < var6; var8++) {
               var4 += Math.abs(this.sampleBuffer.get());
            }

            float var13 = (float)var4 / (float)var6;
            var2.add(var13 / var3);
         }

         return var2;
      }
   }

   static void checkAlError() {
      int var0 = AL10.alGetError();
      if (var0 != 0) {
         throw new RuntimeException(AL10.alGetString(var0));
      }
   }

   protected void createSource() {
      this.sourceId = AL10.alGenSources();
      checkAlError();

      for (int var1 = 0; var1 < 4; var1++) {
         this.bufferIds[var1] = AL10.alGenBuffers();
         checkAlError();
      }
   }

   protected abstract T openInput();

   protected abstract GuiType2 readSampleFormat();

   protected abstract int readSampleRate();

   protected abstract int readFrameSize();

   protected abstract String computeHash();

   public abstract long getDuration();

   public abstract String getName();

   public float getFramesPerSecond() {
      return (float)this.frequency / this.frameSize;
   }

   public void setVolume(float var1) {
      this.volume = var1;
      if (this.sourceId != 0) {
         AL10.alSourcef(this.sourceId, 4106, Math.min(1.0F, var1));
      }
   }

   public void setPlaybackRate(double var1) {
      if (this.sourceId != 0 && this.playbackRate != var1) {
         this.playbackRate = var1;
         AL10.alSourcef(this.sourceId, 4099, (float)var1);
      }
   }

   public void play() {
      if (this.isValid()) {
         if (this.decodeBuffer == null) {
            this.init();
         }

         if (this.sourceId == 0) {
            this.createSource();
         }

         if (this.input != null) {
            this.input.close();
         }

         try {
            this.input = this.openInput();
         } catch (RuntimeException var2) {
            var2.printStackTrace();
            return;
         }

         this.setEof(false);
         this.time = 0L;
         this.timeAnchor = ThreadModuleDump63.method3().bridge$getRealSystemTime();
         this.playbackRate = 1.0;
         AL10.alSourcei(this.sourceId, 514, 1);
         AL10.alDistanceModel(0);
         AL10.alListener3f(4100, 0.0F, 0.0F, 0.0F);
         AL10.alSourcef(this.sourceId, 4110, 1.0F);
         AL10.alSourcef(this.sourceId, 4106, 0.0F);
         this.paused = true;
      }
   }

   public void stop() {
      if (this.input != null) {
         AL10.alSourceStop(this.sourceId);
         int var1 = AL10.alGetSourcei(this.sourceId, 4118);

         for (int var2 = 0; var2 < var1; var2++) {
            AL10.alSourceUnqueueBuffers(this.sourceId);
         }

         this.input.close();
         this.input = null;
         this.setEof(true);
      }
   }

   public void updatePlayback(RewindHandlers var1, Highlight_3 var2, boolean var3) {
      if (this.sourceId != 0 && this.input != null) {
         if (var3) {
            this.paused = false;
            if (!var2.method13().method6()) {
               return;
            }
         } else {
            boolean var4 = var2.isPaused() || var1.method25() || Coordinates.isDragging();
            if (this.paused != var4) {
               this.paused = var4;
               if (this.paused) {
                  AL10.alSourcePause(this.sourceId);
               } else {
                  AL10.alSourcePlay(this.sourceId);
               }
            }
         }

         int var11 = AL10.alGetSourcei(this.sourceId, 4112);
         if (var11 != 4114 && !this.paused && !this.eof) {
            AL10.alSourcePlay(this.sourceId);
         } else if (this.eof && var11 == 4114) {
            AL10.alSourceStop(this.sourceId);
         }

         this.setAlive(true);
         if (!var3) {
            long var5 = ThreadModuleDump63.method3().bridge$getRealSystemTime();
            if (this.paused) {
               this.timeAnchor = var5;
               return;
            }

            this.advanceTime((long)((var5 - this.timeAnchor) * this.playbackRate));
         }

         int var12 = AL10.alGetSourcei(this.sourceId, 4118);

         for (int var6 = 0; var6 < var12; var6++) {
            this.queueBuffer(AL10.alSourceUnqueueBuffers(this.sourceId));
         }

         int var13 = AL10.alGetSourcei(this.sourceId, 4117);
         if (var13 == 0) {
            for (int var10 : this.bufferIds) {
               this.queueBuffer(var10);
            }
         }
      }
   }

   public void advanceTime(long var1) {
      this.time += var1;
      this.timeAnchor = ThreadModuleDump63.method3().bridge$getRealSystemTime();
   }

   public void skip(long var1) {
      if (!this.eof && this.input != null) {
         this.time += var1;
         long var3 = var1 * this.frequency / 1000L;
         long var5 = 0L;

         while (var5 <= var3 - this.frameSize && !this.eof) {
            var5 += this.frameSize;

            try {
               this.skipInput(this.input);
            } catch (EOFException var8) {
               this.setEof(true);
               return;
            }
         }
      }
   }

   protected abstract void skipInput(T var1);

   private void queueBuffer(int var1) {
      if (!this.eof) {
         try {
            this.decodeFrame(this.input, this.decodeBuffer, this.sampleBuffer);
         } catch (EOFException var5) {
            this.setEof(true);
            return;
         }

         ShortBuffer var2 = this.sampleBuffer;
         if (this.volume > 1.0F) {
            while (var2.remaining() >= 1) {
               short var3 = var2.get();
               int var4 = (int)(var3 * this.volume);
               if (var4 > 32767) {
                  var4 = 32767;
               } else if (var4 < -32768) {
                  var4 = -32768;
               }

               this.scaledBuffer.put((short)var4);
            }

            var2.flip();
            this.scaledBuffer.flip();
            var2 = this.scaledBuffer;
         }

         AL10.alBufferData(var1, this.sampleFormat.getAlFormat(), var2, this.frequency);
         checkAlError();
         AL10.alSourceQueueBuffers(this.sourceId, var1);
         checkAlError();
      }
   }

   protected abstract void decodeFrame(T var1, ByteBuffer var2, ShortBuffer var3);

   public void cleanup() {
      if (this.input != null) {
         this.stop();
         AL10.alDeleteSources(this.sourceId);

         for (int var4 : this.bufferIds) {
            AL10.alDeleteBuffers(var4);
         }

         this.sourceId = 0;
      }
   }

   public abstract Rewindhandlers2<T> copy();

   public void reload() {
      this.sourceId = 0;
      this.play();
   }

   @Generated
   public long getTime() {
      return this.time;
   }

   @Generated
   public double getPlaybackRate() {
      return this.playbackRate;
   }

   @Generated
   public List<Float> getWaveformSamples() {
      return this.waveformSamples;
   }

   @Generated
   public String getHash() {
      return this.hash;
   }

   @Generated
   public String getKey() {
      return this.key;
   }

   @Generated
   public void setKey(String var1) {
      this.key = var1;
   }

   @Generated
   public void setAlive(boolean var1) {
      this.alive = var1;
   }

   @Generated
   public boolean isAlive() {
      return this.alive;
   }

   @Generated
   public void setEof(boolean var1) {
      this.eof = var1;
   }

   public static class Data4 extends TypeAdapter<Rewindhandlers2<?>> {
      private final Rewind2_3 field1;

      public void loadWaveform(JsonWriter var1, Rewindhandlers2<?> var2) {
         var1.value(var2.field4);
      }

      public Rewindhandlers2<?> computeWaveform(JsonReader var1) {
         String var2 = var1.nextString();
         String var3 = var2.split("://")[0];
         return var3.equals("media")
            ? new Rewindhandlers2Impl(this.BUFFER_COUNT.method32(), var2, this.BUFFER_COUNT.method42(), this.BUFFER_COUNT.method43().method5(), this.BUFFER_COUNT.method44())
            : new Rewindhandlers2Impl2(this.BUFFER_COUNT.method32(), var2, this.BUFFER_COUNT.method42(), this.BUFFER_COUNT.method9(UUID.fromString(var3)));
      }

      @Generated
      public Data4(Rewind2_3 var1) {
         this.BUFFER_COUNT = var1;
      }
   }
}
