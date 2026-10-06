package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.gui.RewindingContext;
import lombok.Generated;

public class LookPacket extends ReplayPacket {
   private float yaw;
   private float pitch;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.yaw = bytebufloader1.readFloat();
      this.pitch = bytebufloader1.readFloat();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeFloat(this.yaw);
      bytebufloader1.writeFloat(this.pitch);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      LocalPlayerContext nameplate32 = nameplate41.method7();
      nameplate32.method1(this.yaw, this.pitch);
      RewindingContext nameplate53 = nameplate41.method10();
      nameplate53.method1(nameplate32.method7(), nameplate32.method8());
      if (!nameplate41.method18()) {
         nameplate32.method1(this.yaw, this.pitch);
      }
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      RewindingContext nameplate52 = nameplate41.method10();
      Object[] items3 = nameplate52.method2();
      return new LookPacket((Float)items3[0], (Float)items3[1]);
   }

   @Generated
   public LookPacket(float value, float value2) {
      this.yaw = value;
      this.pitch = value2;
   }

   @Generated
   public LookPacket() {
   }
}
