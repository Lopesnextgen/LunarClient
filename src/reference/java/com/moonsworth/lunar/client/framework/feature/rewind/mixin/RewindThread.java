package com.moonsworth.lunar.client.framework.feature.rewind.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.RewindType;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import lombok.Generated;

public class RewindThread extends Thread {
   private final File field1;
   private final RewindType field2;
   private final DataOutputStream field3;
   private final UUID field4;
   private final Queue<Nameplate2> field5 = new LinkedList<>();

   public RewindThread(RewindType var1) {
      this.field2 = var1;
      this.field4 = UUID.randomUUID();
      this.field1 = new File(Gui.field5, "packets.dat_" + System.currentTimeMillis() + "_" + this.field4);
      this.field1.deleteOnExit();
      FileOutputStream var2 = new FileOutputStream(this.field1);
      this.field3 = new DataOutputStream(new BufferedOutputStream(var2));
   }

   public RewindThread(UUID var1, File var2, RewindType var3) {
      this.field4 = var1;
      this.field1 = var2;
      this.field2 = var3;
      this.field3 = null;
   }

   @Override
   public void run() {
      while (!this.field5.isEmpty()) {
         try {
            Rewind_4.method12(this.field5.poll(), this.field3, this.field2);
            Thread.yield();
         } catch (Exception var2) {
            throw new RuntimeException(var2);
         }
      }
   }

   public synchronized void method1(Nameplate2 var1) {
      this.field5.add(var1);
   }

   public void method2() {
      this.field1.delete();
   }

   public void close() {
      if (this.field3 != null) {
         this.field3.close();
      }
   }

   @Generated
   public File getFile() {
      return this.field1;
   }

   @Generated
   public UUID method3() {
      return this.field4;
   }
}
