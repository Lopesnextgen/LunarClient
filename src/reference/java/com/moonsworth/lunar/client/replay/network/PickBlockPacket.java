package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class PickBlockPacket extends ReplayPacket {
   @Override
   public void method1(ByteBufLoader bytebufloader1) {
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method3().bridge$pickBlock();
   }

   @Generated
   public PickBlockPacket() {
   }
}
