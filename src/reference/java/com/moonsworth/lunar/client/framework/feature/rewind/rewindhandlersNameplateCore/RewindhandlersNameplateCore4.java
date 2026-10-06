package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import java.io.DataOutputStream;
import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.opus.Opus;

public abstract class RewindhandlersNameplateCore4 {
   protected final RewindhandlersNameplateCore3 field1 = new RewindhandlersNameplateCore3();
   private boolean paused = false;
   protected long field2;
   private Thread field3;
   protected final Set<DataOutputStream> field4 = new HashSet<>();

   protected void method1() {
      IntBuffer var1 = BufferUtils.createIntBuffer(1);
      this.field2 = Opus.opus_encoder_create(this.field1.frequency, this.field1.channels, 2049, var1);
      if (var1.get() != 0) {
         throw new RuntimeException("Failed to create Opus encoder: " + var1.get());
      }
   }

   public void method2(DataOutputStream var1) {
      this.field4.add(var1);
   }

   public void method3(DataOutputStream var1) {
      this.field4.remove(var1);
   }

   public abstract void start();

   public void method4(Runnable var1) {
      this.field3 = new Thread(var1);
      this.field3.start();
   }

   public void stop() {
      if (this.field3 != null) {
         this.field3.interrupt();

         try {
            this.field3.join();
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      }

      if (this.field2 != 0L) {
         Opus.opus_encoder_destroy(this.field2);
      }

      for (DataOutputStream var2 : this.field4) {
         var2.close();
      }
   }

   @Generated
   public RewindhandlersNameplateCore3 method5() {
      return this.field1;
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }

   @Generated
   public void setPaused(boolean var1) {
      this.paused = var1;
   }
}
