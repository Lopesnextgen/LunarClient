package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Arrays;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.opus.Opus;

public class RewindhandlersNameplateCore4Iterator extends RewindhandlersNameplateCore4 {
   private static final GuiType2 field5 = GuiType2.MONO16;
   private static final int field6 = 48000;
   private static final int field7 = 20;
   private Process field8;

   public RewindhandlersNameplateCore4Iterator() {
      this.field1.setChannels(field5.getChannels());
      this.field1.setFrequency(48000);
      this.field1.setFrameSize((int)(this.field1.getFrequency() * 0.02));
      this.field1.setBytesPerSample(field5.getBytesPerSample());
      this.method1();
   }

   @Override
   public void start() {
      int var1 = this.field1.getFrameSize() * this.field1.getChannels();
      int var2 = var1 * this.field1.getBytesPerSample();
      int var3 = this.field1.getFrameSize() * 2 * 4;
      ByteBuffer var4 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      ShortBuffer var5 = var4.asShortBuffer();
      ByteBuffer var6 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      byte[] var7 = new byte[var2];
      ByteBuffer var8 = BufferUtils.createByteBuffer(var3).order(ByteOrder.LITTLE_ENDIAN);
      ByteBuffer var9 = BufferUtils.createByteBuffer(var3).order(ByteOrder.LITTLE_ENDIAN);
      ByteBuffer var10 = ByteBuffer.allocate(var3 * 3).order(ByteOrder.LITTLE_ENDIAN);
      this.method4(() -> {
         try {
            long var9x = System.currentTimeMillis();
            if (!this.method2()) {
               throw new RuntimeException("Failed to start audio capture");
            }

            boolean var11 = false;
            long var12 = 0L;
            byte[] var14 = new byte[var3];

            while (!Thread.currentThread().isInterrupted() && this.field8 != null && this.field8.isAlive()) {
               int var15;
               if (var12 >= 20L) {
                  Arrays.fill(var14, (byte)0);
                  var15 = var14.length;
                  var12 -= 20L;
               } else {
                  var15 = this.field8.getInputStream().read(var14);
                  if (!var11) {
                     var11 = true;
                     var12 = System.currentTimeMillis() - var9x;
                  }
               }

               if (var15 < 0) {
                  break;
               }

               if (!this.isPaused()) {
                  var10.put(var14, 0, var15);
               }

               while (var10.position() >= var3) {
                  var10.flip();
                  var9.clear().put(var10.array(), 0, var3).flip();
                  var10.position(var3).compact();
                  var8.clear().put(var9).flip();
                  var9.clear();
                  RewindhandlersNameplateCore2.method1(var8, var9, this.field1.getFrameSize(), 1);
                  var4.clear().put(var9).flip();
                  var6.clear();
                  int var16 = Opus.opus_encode(this.field2, var5, this.field1.getFrameSize(), var6);
                  var6.get(var7, 0, var16);

                  for (DataOutputStream var18 : this.field4) {
                     ByteBufLoader.method13(var18, var16);
                     var18.write(var7, 0, var16);
                  }
               }
            }
         } catch (IOException var22) {
            Inventorymod2.method5(var22, "Rewind");
         } finally {
            if (this.field8 != null) {
               this.field8.destroy();
            }
         }
      });
   }

   private boolean method2() {
      try {
         String var1 = "natives/rewind-audio-helper";
         if (!com.moonsworth.lunar.client.nameplate.Nameplate.field4) {
            var1 = "natives/common/" + (ThreadModuleDumpType2.isAppleSilicon() ? "aarch64" : "x86") + "/macos/rewind-audio-helper";
         }

         File var2 = new File(var1);
         if (var2.isFile()) {
            var2.setExecutable(true);
         }

         ProcessBuilder var3 = new ProcessBuilder(var1);
         var3.redirectError(Redirect.INHERIT);
         this.field8 = var3.start();
         return true;
      } catch (Exception var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public void stop() {
      if (this.field8 != null) {
         this.field8.destroy();
      }

      super.stop();
   }
}
