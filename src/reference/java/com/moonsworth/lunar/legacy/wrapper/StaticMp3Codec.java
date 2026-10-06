package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;

public class StaticMp3Codec implements ICodec {
   private boolean field1 = false;
   private boolean initialized = false;
   private AudioFormat field2 = null;
   private AudioInputStream field3 = null;

   public StaticMp3Codec() {
   }

   public void reverseByteOrder(boolean flag1) {
   }

   public boolean initialize(URL url1) {
      AudioInputStream audioinputstream2 = AudioStreamCache.method1(url1);
      if (audioinputstream2 != null) {
         this.field3 = audioinputstream2;
         this.field2 = audioinputstream2.getFormat();
      }

      if (this.field3 == null) {
         SoundSystemConfig.getLogger().errorMessage("StaticMp3Codec", "Unable to set up audio input stream in method 'initialize'", 0);
         this.cleanup();
         return false;
      } else {
         this.initialized = true;
         return true;
      }
   }

   public boolean initialized() {
      return this.initialized;
   }

   public SoundBuffer read() {
      return null;
   }

   public SoundBuffer readAll() {
      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
      byte[] items2 = new byte[SoundSystemConfig.getFileChunkSize()];
      AudioInputStream audioinputstream4 = this.field3;

      try {
         int number3;
         try (audioinputstream4) {
            while ((number3 = audioinputstream4.read(items2)) != -1) {
               bytearrayoutputstream1.write(items2, 0, number3);
            }
         } catch (Exception exception15) {
            SoundSystemConfig.getLogger().errorMessage("StaticMp3Codec", "Exception thrown while reading from the AudioInputStream", 0);
            SoundSystemConfig.getLogger().printStackTrace(exception15, 1);
            Ref.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("failedToPlaySound", new Object[0]));
            return null;
         }
      } finally {
         this.field1 = true;
      }

      return new SoundBuffer(bytearrayoutputstream1.toByteArray(), this.field2);
   }

   public boolean endOfStream() {
      return this.field1;
   }

   public void cleanup() {
      try {
         this.field3.close();
      } catch (Exception exception5) {
         SoundSystemConfig.getLogger().errorMessage("StaticMp3Codec", "Exception thrown while reading cleaning up AudioInputStream", 0);
         SoundSystemConfig.getLogger().printStackTrace(exception5, 1);
      } finally {
         this.field1 = true;
      }
   }

   public AudioFormat getAudioFormat() {
      return this.field2;
   }
}
