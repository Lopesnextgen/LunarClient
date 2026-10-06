package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;

public class AudioStreamCodec implements ICodec {
   private boolean field1 = false;
   private boolean initialized = false;
   private AudioFormat field2 = null;
   private AudioInputStream field3 = null;

   public void reverseByteOrder(boolean var1) {
   }

   public boolean initialize(URL var1) {
      AudioInputStream var2 = AudioStreamLoader.method1(var1);
      if (var2 != null) {
         this.field3 = var2;
         this.field2 = var2.getFormat();
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
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      byte[] var2 = new byte[SoundSystemConfig.getFileChunkSize()];
      AudioInputStream var4 = this.field3;

      try {
         int var3;
         try (var4) {
            while ((var3 = var4.read(var2)) != -1) {
               var1.write(var2, 0, var3);
            }
         } catch (Exception var15) {
            SoundSystemConfig.getLogger().errorMessage("StaticMp3Codec", "Exception thrown while reading from the AudioInputStream", 0);
            SoundSystemConfig.getLogger().printStackTrace(var15, 1);
            ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("failedToPlaySound", new Object[0]));
            return null;
         }
      } finally {
         this.field1 = true;
      }

      return new SoundBuffer(var1.toByteArray(), this.field2);
   }

   public boolean endOfStream() {
      return this.field1;
   }

   public void cleanup() {
      try {
         this.field3.close();
      } catch (Exception var5) {
         SoundSystemConfig.getLogger().errorMessage("StaticMp3Codec", "Exception thrown while reading cleaning up AudioInputStream", 0);
         SoundSystemConfig.getLogger().printStackTrace(var5, 1);
      } finally {
         this.field1 = true;
      }
   }

   public AudioFormat getAudioFormat() {
      return this.field2;
   }
}
