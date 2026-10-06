package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.recording.ReplayLocation;
import com.moonsworth.lunar.client.replay.recording.ReplayEnvironment;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class LocationPacket extends ReplayPacket {
   private ReplayEnvironment field1;
   private String value;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.method9(ReplayEnvironment.class);
      this.value = bytebufloader1.readString();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method10(this.field1);
      bytebufloader1.method1(this.value);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      nameplate41.method30(new ReplayLocation(this.field1, this.value));
   }

   @Generated
   public LocationPacket(ReplayEnvironment replayEnvironment, String text) {
      this.field1 = replayEnvironment;
      this.value = text;
   }

   @Generated
   public LocationPacket() {
   }
}
