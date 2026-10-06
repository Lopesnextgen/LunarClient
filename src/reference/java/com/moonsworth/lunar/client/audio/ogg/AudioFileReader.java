package com.moonsworth.lunar.client.audio.ogg;

import com.moonsworth.lunar.client.util.io.InputStreamLoader;
import com.moonsworth.lunar.client.util.lotusfish.mixin.Lotusfish2;
import com.moonsworth.lunar.client.util.lotusfish.mixin.Lotusfish3;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat.Encoding;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.opus.Opus;

public class AudioFileReader extends javax.sound.sampled.spi.AudioFileReader {
   private static final Type field1 = new Type("OGG_OPUS", "opus");
   private static final ExecutorService field2 = Executors.newCachedThreadPool(var0 -> {
      Thread var1 = Executors.defaultThreadFactory().newThread(var0);
      var1.setDaemon(true);
      var1.setName("OggOpusDecodingThread-" + var1.getId());
      return var1;
   });
   private static final int field3 = 16384;
   private static final int field4 = 262144;
   private static final int field5 = 5760;
   private static final int field6 = 8192;

   @Override
   public AudioFileFormat getAudioFileFormat(File var1) {
      try (BufferedInputStream var2 = new BufferedInputStream(new FileInputStream(var1))) {
         var2.mark(4);
         byte[] var3 = new byte[4];
         if (var2.read(var3) != 4 || !"OggS".equals(new String(var3, StandardCharsets.US_ASCII))) {
            throw new UnsupportedAudioFileException("Not an Ogg stream");
         }

         var2.reset();
         int var4 = method1(var2);
         char var5 = '뮀';
         AudioFormat var6 = new AudioFormat(Encoding.PCM_SIGNED, var5, 16, var4, var4 * 2, var5, false);
         return new AudioFileFormat(field1, var6, -1);
      }
   }

   private static int method1(BufferedInputStream var0) {
      Lotusfish3 var1 = Lotusfish3.method2(var0);
      Lotusfish2 var2 = var1.method3();
      if (var2 != null && !var2.method25().isEmpty()) {
         byte[] var3 = var2.method25().get(0);
         if (var3.length >= 19
            && var3[0] == 79
            && var3[1] == 112
            && var3[2] == 117
            && var3[3] == 115
            && var3[4] == 72
            && var3[5] == 101
            && var3[6] == 97
            && var3[7] == 100) {
            int var4 = var3[9] & 255;
            if (var4 != 0 && var4 <= 255) {
               return var4;
            } else {
               throw new UnsupportedAudioFileException("Invalid channel count in OpusHead: " + var4);
            }
         } else {
            throw new UnsupportedAudioFileException("Not an Opus stream (Missing 'OpusHead' signature)");
         }
      } else {
         throw new UnsupportedAudioFileException("Empty or invalid Ogg stream (no first page/packets)");
      }
   }

   @Override
   public AudioInputStream getAudioInputStream(File var1) {
      AudioFileFormat var2 = this.getAudioFileFormat(var1);
      AudioFormat var3 = var2.getFormat();
      int var4 = var3.getChannels() * (var3.getSampleSizeInBits() / 8) * (int)var3.getSampleRate();
      int var5 = (int)(var4 * 0.2);
      var5 = Math.min(var5, 131072);
      var5 = Math.max(var5, 16384);
      InputStreamLoader var6 = new InputStreamLoader(262144, var5);
      field2.execute(
         () -> {
            long var3x = 0L;
            int var9x = 0;
            long var10 = 0L;

            label321: {
               try (BufferedInputStream var12 = new BufferedInputStream(new FileInputStream(var1))) {
                  Lotusfish3 var13 = Lotusfish3.method2(var12);
                  IntBuffer var14 = BufferUtils.createIntBuffer(1);
                  var3x = Opus.opus_decoder_create((int)var3.getSampleRate(), var3.getChannels(), var14);
                  if (var14.get(0) == 0) {
                     ShortBuffer var5x = BufferUtils.createShortBuffer(var3.getChannels() * 5760);
                     ByteBuffer var6x = BufferUtils.createByteBuffer(8192);
                     ByteBuffer var7x = BufferUtils.createByteBuffer(var5x.capacity() * 2);
                     byte[] var8x = new byte[var7x.capacity()];
                     boolean var15 = false;
                     boolean var16 = false;

                     Lotusfish2 var17;
                     while (!Thread.currentThread().isInterrupted() && !var6.method2() && (var17 = var13.method3()) != null) {
                        Iterator var18 = var17.method25().iterator();

                        while (true) {
                           if (var18.hasNext()) {
                              byte[] var19 = (byte[])var18.next();
                              if (!Thread.currentThread().isInterrupted() && !var6.method2()) {
                                 if (var19.length == 0) {
                                    continue;
                                 }

                                 if (!var15
                                    && var19.length == 19
                                    && var19[0] == 79
                                    && var19[1] == 112
                                    && var19[2] == 117
                                    && var19[3] == 115
                                    && var19[4] == 72
                                    && var19[5] == 101
                                    && var19[6] == 97
                                    && var19[7] == 100) {
                                    var15 = true;
                                    var9x = (var19[11] & 255) << 8 | var19[10] & 255;
                                    continue;
                                 }

                                 if (var15
                                    && !var16
                                    && var19.length >= 8
                                    && var19[0] == 79
                                    && var19[1] == 112
                                    && var19[2] == 117
                                    && var19[3] == 115
                                    && var19[4] == 84
                                    && var19[5] == 97
                                    && var19[6] == 103
                                    && var19[7] == 115) {
                                    var16 = true;
                                    continue;
                                 }

                                 if (!var15 || !var16) {
                                    System.err.println("Warning: Decoding data packet before Opus headers fully processed.");
                                    var15 = true;
                                    var16 = true;
                                 }

                                 var6x.clear();
                                 if (var19.length > var6x.capacity()) {
                                    var6x = BufferUtils.createByteBuffer(var19.length);
                                 }

                                 var6x.put(var19).flip();
                                 var5x.clear();
                                 int var20 = Opus.opus_decode(var3x, var6x, var5x, 5760, 0);
                                 if (var20 < 0) {
                                    System.err.println("Opus decode error: " + Opus.opus_strerror(var20) + " (Code: " + var20 + ")");
                                    continue;
                                 }

                                 if (var20 == 0) {
                                    continue;
                                 }

                                 int var21 = var20;
                                 int var22 = 0;
                                 int var23 = var21 * var3.getChannels();
                                 if (var10 < var9x) {
                                    long var24 = var9x - var10;
                                    if (var24 >= var21) {
                                       var10 += var21;
                                       continue;
                                    }

                                    var22 = (int)var24 * var3.getChannels();
                                    var10 = var9x;
                                 }

                                 var5x.position(var22);
                                 var5x.limit(var23);
                                 if (var5x.remaining() > 0) {
                                    int var39 = var5x.remaining() * 2;
                                    var7x.clear();
                                    var7x.asShortBuffer().put(var5x);
                                    var7x.limit(var39);
                                    if (var39 > 0) {
                                       var7x.get(var8x, 0, var39);
                                       var6.write(var8x, 0, var39);
                                    }
                                 }
                                 continue;
                              }
                           }

                           if (var6.method2()) {
                              return;
                           }
                           break;
                        }
                     }
                     break label321;
                  }

                  System.err.println("Opus decoder create failed: " + Opus.opus_strerror(var14.get(0)));
                  var6.method1();
               } catch (EOFException var35) {
                  return;
               } catch (IOException var36) {
                  if (!var6.method2()
                     && (
                        var36.getMessage() == null
                           || !var36.getMessage().contains("Write interrupted") && !var36.getMessage().contains("Stream closed by reader")
                     )) {
                     System.err.println("I/O Exception in Opus decoding thread: " + var36.getClass().getName() + ": " + var36.getMessage());
                  }

                  return;
               } catch (Exception var37) {
                  System.err.println("Unexpected exception in Opus decoding thread: " + var37.getClass().getName() + ": " + var37.getMessage());
                  var37.printStackTrace();
                  return;
               } finally {
                  if (var3x != 0L) {
                     Opus.opus_decoder_destroy(var3x);
                  }

                  var6.method1();
               }

               return;
            }
         }
      );
      BufferedInputStream var7 = new BufferedInputStream(var6, 16384);
      return new AudioInputStream(var7, var3, -1L);
   }

   @Override
   public AudioFileFormat getAudioFileFormat(InputStream var1) {
      throw new UnsupportedAudioFileException("InputStream processing not supported.");
   }

   @Override
   public AudioInputStream getAudioInputStream(InputStream var1) {
      throw new UnsupportedAudioFileException("InputStream processing not supported.");
   }

   @Override
   public AudioFileFormat getAudioFileFormat(URL var1) {
      if (!"file".equalsIgnoreCase(var1.getProtocol())) {
         throw new UnsupportedAudioFileException("Only file:// URLs are supported.");
      }

      try {
         return this.getAudioFileFormat(new File(var1.toURI()));
      } catch (URISyntaxException var3) {
         throw new IOException("Invalid URL syntax: " + var3.getMessage(), var3);
      }
   }

   @Override
   public AudioInputStream getAudioInputStream(URL var1) {
      if (!"file".equalsIgnoreCase(var1.getProtocol())) {
         throw new UnsupportedAudioFileException("Only file:// URLs are supported.");
      }

      try {
         return this.getAudioInputStream(new File(var1.toURI()));
      } catch (URISyntaxException var3) {
         throw new IOException("Invalid URL syntax: " + var3.getMessage(), var3);
      }
   }
}
