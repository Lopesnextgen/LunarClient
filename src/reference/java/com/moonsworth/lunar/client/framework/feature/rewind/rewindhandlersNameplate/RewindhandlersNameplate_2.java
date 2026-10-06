package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import lombok.Generated;
import org.lwjgl.actually3.BufferUtils;

public class RewindhandlersNameplate_2 {
   private GuiType2 field1;
   private RewindhandlersNameplate field2;
   private Object field3;
   private Object field4;
   private final Bridge5_3 field5 = Bridge.method65();
   private ByteBuffer field6;
   private byte[] data;
   private File file;
   private OutputStream field7;
   private long field8;
   private long field9;

   public void method1() {
      if (this.field3 != null && this.field3 != 0L) {
         try {
            this.field5.method7(this.field3);
            this.field5.method8(this.field3);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      this.field3 = this.field5.method11(null);
      if (this.field3 != null && this.field3 != 0L) {
         int var1 = this.field2.method9() ? 5377 : 5376;
         if (!this.field5.method12(this.field3, this.field2.getFrequency(), var1, 5122)) {
            throw new IllegalStateException("The loopback device does not support the required format.");
         } else {
            int[] var2 = new int[]{4103, this.field2.getFrequency(), 6544, var1, 6545, 5122, 6554, 1, 0};
            this.field4 = this.field5.method9(this.field3, var2);
            if (this.field4 == null || this.field4 == 0L) {
               throw new IllegalStateException("Failed to create the loopback context.");
            }
         }
      } else {
         throw new IllegalStateException("Failed to open the loopback device.");
      }
   }

   public void method2(File var1, RewindhandlersNameplate var2, RewindHandlers var3) {
      this.file = var1;
      this.field2 = var2;
      this.field1 = GuiType2.getFormat(var2.method9() ? 2 : 1, 2);
      float var4 = var2.getFrequency() / 1000.0F;
      int var5 = (int)(var4 * Math.ceil(1000.0F / var2.method5())) * this.field1.getChannels();
      int var6 = var5 * this.field1.getBytesPerSample();
      this.field6 = BufferUtils.createByteBuffer(var6);
      this.data = new byte[var6];
      this.field7 = new BufferedOutputStream(new FileOutputStream(var1));
      this.field8 = 0L;
      this.field9 = 0L;
      ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$reload();
   }

   public void method3(RewindHandlers var1, long var2) {
      long var4 = var2 * this.field2.getFrequency() / this.field2.method5();
      int var6 = (int)(var4 - this.field8);
      this.field8 = var4;
      long var7 = var2 * 1000L / this.field2.method5();
      long var9 = var7 - this.field9;
      this.field9 = var7;
      this.field6.clear();
      this.field5.method13(this.field3, this.field6, var6);

      try {
         int var11 = var6 * this.field1.getChannels() * this.field1.getBytesPerSample();
         this.field6.get(this.data, 0, var11);
         this.field7.write(this.data, 0, var11);
      } catch (IOException var12) {
         var12.printStackTrace();
      }

      var1.method60().method4(var9);
   }

   public void method4(RewindHandlers var1) {
      this.field7.close();

      try {
         var1.method60().cleanup();
         this.field5.method7(this.field3);
         this.field5.method8(this.field3);
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         this.field3 = null;
         ThreadModuleDump63.method3().bridge$schedule(() -> ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$reload());
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
