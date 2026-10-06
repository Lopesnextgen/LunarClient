package com.moonsworth.lunar.client.replay.audio;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.audio.AudioSampleFormat;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
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
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2Impl2;

public abstract class AudioStream<T extends InputStream> {
   private static final int field1 = 4;
   private int field2;
   private final int[] field3 = new int[4];
   protected final String field4;
   protected final String field5;
   protected final File field6;
   protected T field7;
   protected AudioSampleFormat field8;
   protected int frequency;
   protected int frameSize;
   private long time = 0L;
   private long field9 = 0L;
   private double field10 = 1.0;
   private boolean paused = false;
   protected ByteBuffer field11;
   protected ShortBuffer field12;
   protected ShortBuffer field13;
   private List<Float> field14;
   protected final com.moonsworth.lunar.client.replay.audio.AudioWaveformRenderer field15;
   private String hash;
   protected String key;
   private boolean field16 = false;
   private boolean eof = false;
   private float volume = 1.0F;

   public AudioStream(File file1, String text2, com.moonsworth.lunar.client.replay.audio.AudioWaveformRenderer rewindhandlers23) {
      this.field6 = file1;
      this.field4 = text2;
      this.field5 = text2.split("://")[1];
      this.field15 = rewindhandlers23;
   }

   public boolean isValid() {
      return true;
   }

   protected void init() {
      if (this.isValid()) {
         try {
            this.field7 = this.method4();
         } catch (RuntimeException exception2) {
            return;
         }

         this.field8 = this.method5();
         this.frequency = this.method6();
         this.frameSize = this.method7();
         this.hash = this.method8();
         this.setEof(false);
         int number1 = this.frameSize * this.field8.getChannels() * this.field8.getBytesPerSample();
         this.field11 = BufferUtils.createByteBuffer(number1);
         this.field12 = BufferUtils.createShortBuffer(number1);
         this.field13 = BufferUtils.createShortBuffer(number1);
      }
   }

   protected void method1() {
      if (this.isValid()) {
         this.field14 = this.field15.method1(this.hash, this);
      }
   }

   public List<Float> method2() {
      try (InputStream input1 = this.method4()) {
         ArrayList list2 = new ArrayList();
         float value3 = (float)Math.pow(2.0, this.field8.getBytesPerSample() * 8 - 1);

         while (true) {
            try {
               this.method17((T)input1, this.field11, this.field12);
            } catch (EOFException eofexception10) {
               break;
            }

            long number4 = 0L;
            long number6 = this.field12.remaining();
            if (number6 <= 0L) {
               break;
            }

            for (int index8 = 0; index8 < number6; index8++) {
               number4 += Math.abs(this.field12.get());
            }

            float value13 = (float)number4 / (float)number6;
            list2.add(value13 / value3);
         }

         return list2;
      }
   }

   static void method3() {
      int number0 = AL10.alGetError();
      if (number0 != 0) {
         throw new RuntimeException(AL10.alGetString(number0));
      }
   }

   protected void createSource() {
      this.field2 = AL10.alGenSources();
      method3();

      for (int index1 = 0; index1 < 4; index1++) {
         this.field3[index1] = AL10.alGenBuffers();
         method3();
      }
   }

   protected abstract T method4();

   protected abstract AudioSampleFormat method5();

   protected abstract int method6();

   protected abstract int method7();

   protected abstract String method8();

   public abstract long getDuration();

   public abstract String getName();

   public float method10() {
      return (float)this.frequency / this.frameSize;
   }

   public void method11(float value1) {
      this.volume = value1;
      if (this.field2 != 0) {
         AL10.alSourcef(this.field2, 4106, Math.min(1.0F, value1));
      }
   }

   public void method12(double value1) {
      if (this.field2 != 0 && this.field10 != value1) {
         this.field10 = value1;
         AL10.alSourcef(this.field2, 4099, (float)value1);
      }
   }

   public void play() {
      if (this.isValid()) {
         if (this.field11 == null) {
            this.init();
         }

         if (this.field2 == 0) {
            this.createSource();
         }

         if (this.field7 != null) {
            this.field7.close();
         }

         try {
            this.field7 = this.method4();
         } catch (RuntimeException exception2) {
            exception2.printStackTrace();
            return;
         }

         this.setEof(false);
         this.time = 0L;
         this.field9 = Ref.method3().bridge$getRealSystemTime();
         this.field10 = 1.0;
         AL10.alSourcei(this.field2, 514, 1);
         AL10.alDistanceModel(0);
         AL10.alListener3f(4100, 0.0F, 0.0F, 0.0F);
         AL10.alSourcef(this.field2, 4110, 1.0F);
         AL10.alSourcef(this.field2, 4106, 0.0F);
         this.paused = true;
      }
   }

   public void stop() {
      if (this.field7 != null) {
         AL10.alSourceStop(this.field2);
         int number1 = AL10.alGetSourcei(this.field2, 4118);

         for (int index2 = 0; index2 < number1; index2++) {
            AL10.alSourceUnqueueBuffers(this.field2);
         }

         this.field7.close();
         this.field7 = null;
         this.setEof(true);
      }
   }

   public void method13(RewindHandlers rewindhandlers1, ReplayTimeline highlight_32, boolean flag3) {
      if (this.field2 != 0 && this.field7 != null) {
         if (flag3) {
            this.paused = false;
            if (!highlight_32.method13().method6()) {
               return;
            }
         } else {
            boolean flag4 = highlight_32.isPaused() || rewindhandlers1.method25() || RewindEditorContext.isDragging();
            if (this.paused != flag4) {
               this.paused = flag4;
               if (this.paused) {
                  AL10.alSourcePause(this.field2);
               } else {
                  AL10.alSourcePlay(this.field2);
               }
            }
         }

         int number11 = AL10.alGetSourcei(this.field2, 4112);
         if (number11 != 4114 && !this.paused && !this.eof) {
            AL10.alSourcePlay(this.field2);
         } else if (this.eof && number11 == 4114) {
            AL10.alSourceStop(this.field2);
         }

         this.method22(true);
         if (!flag3) {
            long number5 = Ref.method3().bridge$getRealSystemTime();
            if (this.paused) {
               this.field9 = number5;
               return;
            }

            this.method14((long)((number5 - this.field9) * this.field10));
         }

         int number12 = AL10.alGetSourcei(this.field2, 4118);

         for (int index6 = 0; index6 < number12; index6++) {
            this.method16(AL10.alSourceUnqueueBuffers(this.field2));
         }

         int number13 = AL10.alGetSourcei(this.field2, 4117);
         if (number13 == 0) {
            for (int index10 : this.field3) {
               this.method16(index10);
            }
         }
      }
   }

   public void method14(long number1) {
      this.time += number1;
      this.field9 = Ref.method3().bridge$getRealSystemTime();
   }

   public void skip(long number1) {
      if (!this.eof && this.field7 != null) {
         this.time += number1;
         long number3 = number1 * this.frequency / 1000L;
         long number5 = 0L;

         while (number5 <= number3 - this.frameSize && !this.eof) {
            number5 += this.frameSize;

            try {
               this.method15(this.field7);
            } catch (EOFException eofexception8) {
               this.setEof(true);
               return;
            }
         }
      }
   }

   protected abstract void method15(T value1);

   private void method16(int number1) {
      if (!this.eof) {
         try {
            this.method17(this.field7, this.field11, this.field12);
         } catch (EOFException eofexception5) {
            this.setEof(true);
            return;
         }

         ShortBuffer shortbuffer2 = this.field12;
         if (this.volume > 1.0F) {
            while (shortbuffer2.remaining() >= 1) {
               short number3 = shortbuffer2.get();
               int number4 = (int)(number3 * this.volume);
               if (number4 > 32767) {
                  number4 = 32767;
               } else if (number4 < -32768) {
                  number4 = -32768;
               }

               this.field13.put((short)number4);
            }

            shortbuffer2.flip();
            this.field13.flip();
            shortbuffer2 = this.field13;
         }

         AL10.alBufferData(number1, this.field8.getAlFormat(), shortbuffer2, this.frequency);
         method3();
         AL10.alSourceQueueBuffers(this.field2, number1);
         method3();
      }
   }

   protected abstract void method17(T value1, ByteBuffer buffer2, ShortBuffer shortbuffer3);

   public void cleanup() {
      if (this.field7 != null) {
         this.stop();
         AL10.alDeleteSources(this.field2);

         for (int index4 : this.field3) {
            AL10.alDeleteBuffers(index4);
         }

         this.field2 = 0;
      }
   }

   public abstract AudioStream<T> method18();

   public void reload() {
      this.field2 = 0;
      this.play();
   }

   @Generated
   public long getTime() {
      return this.time;
   }

   @Generated
   public double method19() {
      return this.field10;
   }

   @Generated
   public List<Float> method20() {
      return this.field14;
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
   public void setKey(String text1) {
      this.key = text1;
   }

   @Generated
   public void method22(boolean flag1) {
      this.field16 = flag1;
   }

   @Generated
   public boolean method23() {
      return this.field16;
   }

   @Generated
   public void setEof(boolean flag1) {
      this.eof = flag1;
   }

   public static class AudioStreamAdapter extends TypeAdapter<AudioStream<?>> {
      private final ReplayProjectManager field1;

      public void method1(JsonWriter jsonwriter1, AudioStream<?> rewindhandlers22) {
         jsonwriter1.value(rewindhandlers22.field4);
      }

      public AudioStream<?> method2(JsonReader jsonreader1) {
         String text2 = jsonreader1.nextString();
         String text3 = text2.split("://")[0];
         return text3.equals("media")
            ? new MediaAudioStream(this.field1.method32(), text2, this.field1.method42(), this.field1.method43().method5(), this.field1.method44())
            : new Rewindhandlers2Impl2(this.field1.method32(), text2, this.field1.method42(), this.field1.method9(UUID.fromString(text3)));
      }

      @Generated
      public AudioStreamAdapter(ReplayProjectManager rewind2_31) {
         this.field1 = rewind2_31;
      }
   }
}
