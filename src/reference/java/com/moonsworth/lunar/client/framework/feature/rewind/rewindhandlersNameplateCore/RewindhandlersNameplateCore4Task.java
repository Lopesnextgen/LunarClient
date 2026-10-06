package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.opus.Opus;

public class RewindhandlersNameplateCore4Task extends RewindhandlersNameplateCore4 {
   private static final GuiType2 field5 = GuiType2.MONO16;
   private final Object field6;
   private final Bridge5_3 field7 = Bridge.method65();

   public RewindhandlersNameplateCore4Task(Rewind var1) {
      this.field1.frequency = 48000;
      this.field1.bytesPerSample = field5.getBytesPerSample();
      this.field1.channels = field5.getChannels();
      this.field1.frameSize = 960;
      String var2 = var1.method30().get();

      try {
         if (!var1.method30().method7().call().contains(var2)) {
            var2 = "default";
            var1.method30().OIRHOOIICOCIOOHICRRRICORIHHIHC(var2);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      if (var2.equalsIgnoreCase("default") || var2.isEmpty()) {
         var2 = null;
      }

      this.field6 = this.field7
         .method1(
            var2,
            this.field1.frequency,
            field5.getAlFormat(),
            this.field1.frameSize * this.field1.channels * 4
         );
      if (this.field6 != null && this.field6 != 0L) {
         this.method1();
      } else {
         throw new IllegalStateException("Failed to open the microphone device.");
      }
   }

   @Override
   public void start() {
      this.field7.method2(this.field6);
      int var1 = this.field1.frameSize * this.field1.channels;
      int var2 = var1 * this.field1.bytesPerSample;
      ByteBuffer var3 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      ByteBuffer var4 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      byte[] var5 = new byte[var2];
      ShortBuffer var6 = var3.asShortBuffer();
      this.method4(
         () -> {
            while (!Thread.currentThread().isInterrupted()) {
               int var5x = this.field7.method3(this.field6, 786);

               while (var5x >= this.field1.frameSize) {
                  var5x -= this.field1.frameSize;
                  this.field1
                     .setDuration(
                        this.field1.getDuration()
                           + this.field1.frameSize * 1000L / this.field1.frequency
                     );
                  this.field7.method6(this.field6, var3, this.field1.frameSize);
                  if (!this.isPaused()) {
                     try {
                        var4.clear();
                        int var6x = Opus.opus_encode(this.field2, var6, this.field1.frameSize, var4);
                        var4.get(var5, 0, var6x);

                        for (DataOutputStream var8 : this.field4) {
                           ByteBufLoader.method13(var8, var6x);
                           var8.write(var5, 0, var6x);
                        }
                     } catch (IOException var10) {
                        Inventorymod2.method5(var10, "Rewind");
                     }
                  }
               }

               try {
                  Thread.sleep(5L);
               } catch (InterruptedException var9) {
                  Thread.currentThread().interrupt();
               }
            }
         }
      );
   }

   @Override
   public void stop() {
      super.stop();
      this.field7.method7(this.field6);
      this.field7.method8(this.field6);
   }
}
