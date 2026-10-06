package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.util.io.RewindableInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat.Encoding;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;
import paulscode.sound.libraries.ChannelLWJGLOpenAL;

public class ICodecImpl implements ICodec {
   private static final int field1 = 16384;
   private static final LoadingCache<URL, RewindableInputStream> field2 = CacheBuilder.newBuilder()
      .expireAfterAccess(60L, TimeUnit.SECONDS)
      .removalListener(arg0 -> {
         try {
            RewindableInputStream inputstreamloader21 = (RewindableInputStream)arg0.getValue();
            if (inputstreamloader21 != null) {
               inputstreamloader21.close();
            }
         } catch (IOException exception2) {
         }
      })
      .build(new CacheLoader<URL, RewindableInputStream>() {
         @NotNull
         public RewindableInputStream method1(@NotNull URL url1) {
            return new RewindableInputStream(new BufferedInputStream(url1.openStream(), 16384));
         }
      });
   private static final boolean field3 = false;
   private static final boolean field4 = true;
   private static final boolean field5 = false;
   private boolean field6 = false;
   private boolean initialized = false;
   private AudioFormat field7 = null;
   private AudioInputStream field8 = null;
   private final SoundSystemLogger field9 = SoundSystemConfig.getLogger();
   private ChannelLWJGLOpenAL field10;

   public ICodecImpl() {
   }

   public void reverseByteOrder(boolean flag1) {
   }

   public boolean initialize(URL url1) {
      if (this.field7 != null) {
         return true;
      }

      this.field7 = new AudioFormat(Encoding.PCM_SIGNED, 44100.0F, 16, 2, 4, 44100.0F, false);
      new Thread(() -> {
         this.method1(true, false);
         if (url1 == null) {
            this.method3("URL null in method 'initialize'");
            this.cleanup();
         } else {
            try {
               RewindableInputStream inputstreamloader22 = (RewindableInputStream)field2.get(url1);
               if (inputstreamloader22.getPosition() == 0) {
                  inputstreamloader22.mark(16384);
                  inputstreamloader22.readNBytes(16384);
               }

               inputstreamloader22.reset();
               AudioInputStream audioinputstream3 = AudioSystem.getAudioInputStream(inputstreamloader22);
               int number4 = ClampUtils.clamp(audioinputstream3.getFormat().getChannels(), 1, 2);
               int number5 = (int)audioinputstream3.getFormat().getSampleRate();
               this.field7 = new AudioFormat(Encoding.PCM_SIGNED, number5, 16, number4, number4 * 2, number5, false);
               this.field8 = AudioSystem.getAudioInputStream(this.field7, audioinputstream3);
            } catch (Exception exception6) {
               this.method3("Unable to set up input streams in method 'initialize'");
               this.method4(exception6);
               this.cleanup();
               return;
            }

            if (this.field8 == null) {
               this.method3("Unable to set up audio input stream in method 'initialize'");
               this.cleanup();
            } else {
               this.method2(true, false);
               this.method1(true, true);
               LinkedList list7 = new LinkedList();

               for (int index8 = 0; index8 < SoundSystemConfig.getNumberStreamingBuffers(); index8++) {
                  SoundBuffer soundbuffer9 = this.read();
                  if (soundbuffer9 == null) {
                     break;
                  }

                  list7.add(soundbuffer9.audioData);
               }

               if (!list7.isEmpty()) {
                  this.field10.preLoadBuffers(list7);
               }
            }
         }
      }).start();
      return true;
   }

   public boolean initialized() {
      return !this.method2(false, false);
   }

   public SoundBuffer read() {
      if (!this.method1(false, false)) {
         return new SoundBuffer(new byte[SoundSystemConfig.getStreamingBufferSize()], this.field7);
      }

      if (this.field8 == null) {
         this.method2(true, true);
         return null;
      }

      AudioFormat audioformat1 = this.field8.getFormat();
      if (audioformat1 == null) {
         this.method3("Audio Format null in method 'read'");
         this.method2(true, true);
         return null;
      }

      int index2 = 0;
      byte[] items4 = new byte[SoundSystemConfig.getStreamingBufferSize()];

      try {
         while (!this.method2(false, false) && index2 < items4.length) {
            int number3;
            if ((number3 = this.field8.read(items4, index2, items4.length - index2)) <= 0) {
               this.method2(true, true);
               break;
            }

            index2 += number3;
         }
      } catch (IOException exception6) {
         this.method2(true, true);
         return null;
      } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception7) {
         this.method2(true, true);
         return null;
      }

      if (index2 <= 0) {
         this.method2(true, true);
         return null;
      } else {
         return new SoundBuffer(items4, audioformat1);
      }
   }

   public SoundBuffer readAll() {
      if (this.field7 == null) {
         this.method3("Audio Format null in method 'readAll'");
         return null;
      }

      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
      byte[] items2 = new byte[SoundSystemConfig.getFileChunkSize()];

      try {
         int number3;
         try {
            while ((number3 = this.field8.read(items2)) != -1) {
               bytearrayoutputstream1.write(items2, 0, number3);
            }
         } catch (IOException exception15) {
            this.method3("Exception thrown while reading from the AudioInputStream");
            this.method4(exception15);
            return null;
         }
      } finally {
         try {
            this.field8.close();
         } catch (IOException exception14) {
         }

         this.method2(true, true);
      }

      return new SoundBuffer(bytearrayoutputstream1.toByteArray(), this.field7);
   }

   public boolean endOfStream() {
      return this.method2(false, false);
   }

   public void cleanup() {
      this.method2(true, true);
   }

   public AudioFormat getAudioFormat() {
      return this.field7;
   }

   private synchronized boolean method1(boolean flag1, boolean flag2) {
      if (flag1) {
         this.initialized = flag2;
      }

      return this.initialized;
   }

   private synchronized boolean method2(boolean flag1, boolean flag2) {
      if (flag1) {
         this.field6 = flag2;
      }

      return this.field6;
   }

   private void method3(String text1) {
      this.field9.errorMessage("Mp3Codec", text1, 0);
   }

   private void method4(Exception exception1) {
      this.field9.printStackTrace(exception1, 1);
   }

   @Generated
   public void method5(ChannelLWJGLOpenAL channellwjglopenal1) {
      this.field10 = channellwjglopenal1;
   }
}
