package com.moonsworth.lunar.client.replay.audio;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AlcBridge;
import com.moonsworth.lunar.client.replay.audio.AudioSampleFormat;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import lombok.Generated;
import org.lwjgl.actually3.BufferUtils;
import com.moonsworth.lunar.client.replay.export.ExportSettings;

public class LoopbackAudioRecorder {
   private AudioSampleFormat field1;
   private ExportSettings field2;
   private Object field3;
   private Object field4;
   private final AlcBridge field5 = Bridge.method65();
   private ByteBuffer field6;
   private byte[] data;
   private File file;
   private OutputStream field7;
   private long field8;
   private long field9;

   public LoopbackAudioRecorder() {
   }

   public void method1() {
      if (this.field3 != null && this.field3 != 0L) {
         try {
            this.field5.method7(this.field3);
            this.field5.method8(this.field3);
         } catch (Exception exception3) {
            exception3.printStackTrace();
         }
      }

      this.field3 = this.field5.method11(null);
      if (this.field3 != null && this.field3 != 0L) {
         int number1 = this.field2.method9() ? 5377 : 5376;
         if (!this.field5.method12(this.field3, this.field2.getFrequency(), number1, 5122)) {
            throw new IllegalStateException("The loopback device does not support the required format.");
         } else {
            int[] items2 = new int[]{4103, this.field2.getFrequency(), 6544, number1, 6545, 5122, 6554, 1, 0};
            this.field4 = this.field5.method9(this.field3, items2);
            if (this.field4 == null || this.field4 == 0L) {
               throw new IllegalStateException("Failed to create the loopback context.");
            }
         }
      } else {
         throw new IllegalStateException("Failed to open the loopback device.");
      }
   }

   public void method2(File file1, ExportSettings rewindhandlersnameplate2, RewindHandlers rewindhandlers3) {
      this.file = file1;
      this.field2 = rewindhandlersnameplate2;
      this.field1 = AudioSampleFormat.getFormat(rewindhandlersnameplate2.method9() ? 2 : 1, 2);
      float value4 = rewindhandlersnameplate2.getFrequency() / 1000.0F;
      int number5 = (int)(value4 * Math.ceil(1000.0F / rewindhandlersnameplate2.method5())) * this.field1.getChannels();
      int index6 = number5 * this.field1.getBytesPerSample();
      this.field6 = BufferUtils.createByteBuffer(index6);
      this.data = new byte[index6];
      this.field7 = new BufferedOutputStream(new FileOutputStream(file1));
      this.field8 = 0L;
      this.field9 = 0L;
      Ref.method3().bridge$getSoundHandler().bridge$reload();
   }

   public void method3(RewindHandlers rewindhandlers1, long number2) {
      long number4 = number2 * this.field2.getFrequency() / this.field2.method5();
      int number6 = (int)(number4 - this.field8);
      this.field8 = number4;
      long number7 = number2 * 1000L / this.field2.method5();
      long number9 = number7 - this.field9;
      this.field9 = number7;
      this.field6.clear();
      this.field5.method13(this.field3, this.field6, number6);

      try {
         int index11 = number6 * this.field1.getChannels() * this.field1.getBytesPerSample();
         this.field6.get(this.data, 0, index11);
         this.field7.write(this.data, 0, index11);
      } catch (IOException exception12) {
         exception12.printStackTrace();
      }

      rewindhandlers1.method60().method4(number9);
   }

   public void method4(RewindHandlers rewindhandlers1) {
      this.field7.close();

      try {
         rewindhandlers1.method60().cleanup();
         this.field5.method7(this.field3);
         this.field5.method8(this.field3);
      } catch (Exception exception6) {
         exception6.printStackTrace();
      } finally {
         this.field3 = null;
         Ref.method3().bridge$schedule(() -> Ref.method3().bridge$getSoundHandler().bridge$reload());
      }
   }

   public void destroy() {
      if (this.field4 != null && Bridge.getMinecraftVersion().method19()) {
         this.field5.method10(this.field4);
      }
   }

   @Generated
   public Object method5() {
      return this.field3;
   }

   @Generated
   public Object method6() {
      return this.field4;
   }

   @Generated
   public File getFile() {
      return this.file;
   }
}
