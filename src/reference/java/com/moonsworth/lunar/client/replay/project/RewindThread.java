package com.moonsworth.lunar.client.replay.project;

import com.moonsworth.lunar.client.replay.project.RewindType;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
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
   private final Queue<ReplayPacket> field5 = new LinkedList<>();

   public RewindThread(RewindType rewindtype1) {
      this.field2 = rewindtype1;
      this.field4 = UUID.randomUUID();
      this.field1 = new File(RewindPaths.field5, "packets.dat_" + System.currentTimeMillis() + "_" + this.field4);
      this.field1.deleteOnExit();
      FileOutputStream stream2 = new FileOutputStream(this.field1);
      this.field3 = new DataOutputStream(new BufferedOutputStream(stream2));
   }

   public RewindThread(UUID uuid1, File file2, RewindType rewindtype3) {
      this.field4 = uuid1;
      this.field1 = file2;
      this.field2 = rewindtype3;
      this.field3 = null;
   }

   @Override
   public void run() {
      while (!this.field5.isEmpty()) {
         try {
            ReplayHandler.method12(this.field5.poll(), this.field3, this.field2);
            Thread.yield();
         } catch (Exception exception2) {
            throw new RuntimeException(exception2);
         }
      }
   }

   public synchronized void method1(ReplayPacket nameplate21) {
      this.field5.add(nameplate21);
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
