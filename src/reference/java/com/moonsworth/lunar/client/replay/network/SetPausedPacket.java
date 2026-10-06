package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class SetPausedPacket extends ReplayPacket {
   private boolean paused;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.paused = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeBoolean(this.paused);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      nameplate41.method33(this.paused);
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      return new SetPausedPacket(!this.paused);
   }

   @Generated
   public SetPausedPacket(boolean flag) {
      this.paused = flag;
   }

   @Generated
   public SetPausedPacket() {
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }
}
