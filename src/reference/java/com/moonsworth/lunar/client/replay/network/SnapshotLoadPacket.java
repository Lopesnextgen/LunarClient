package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import java.util.UUID;
import lombok.Generated;

public class SnapshotLoadPacket extends ReplayPacket {
   private UUID id;
   private boolean field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.id = bytebufloader1.method14();
      this.field1 = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method15(this.id);
      bytebufloader1.writeBoolean(this.field1);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      if (!nameplate41.method18() || !this.field1) {
         nameplate41.method6().method40().method27(this.id.toString());
      }
   }

   @Generated
   public SnapshotLoadPacket(UUID uuid1, boolean flag) {
      this.id = uuid1;
      this.field1 = flag;
   }

   @Generated
   public SnapshotLoadPacket() {
   }
}
