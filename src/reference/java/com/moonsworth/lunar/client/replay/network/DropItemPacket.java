package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class DropItemPacket extends ReplayPacket {
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
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         bridge5extension_52.bridge$drop(this.field1);
      }
   }

   @Generated
   public DropItemPacket() {
   }

   @Generated
   public DropItemPacket(boolean flag) {
      this.field1 = flag;
   }
}
