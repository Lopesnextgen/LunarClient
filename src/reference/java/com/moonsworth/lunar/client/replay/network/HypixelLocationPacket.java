package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.framework.LunarConstants;
import lombok.Generated;

public class HypixelLocationPacket extends ReplayPacket {
   private String value;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.value = bytebufloader1.readString();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method1(this.value);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      HypixelLocation rewindhandlers22 = (HypixelLocation)LunarConstants.field22.fromJson(this.value, HypixelLocation.class);
      HypixelLocationListener.field7.method8(rewindhandlers22);
   }

   @Generated
   public HypixelLocationPacket(String text) {
      this.value = text;
   }

   @Generated
   public HypixelLocationPacket() {
   }
}
