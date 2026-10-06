package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class SnapshotStartPacket extends ReplayPacket {
   private boolean field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeBoolean(this.field1);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      if (nameplate41.method18() && this.field1) {
         nameplate41.method36(nameplate41.getTick());
      }
   }

   @Generated
   public SnapshotStartPacket(boolean flag) {
      this.field1 = flag;
   }

   @Generated
   public SnapshotStartPacket() {
   }

   @Generated
   public boolean method4() {
      return this.field1;
   }
}
