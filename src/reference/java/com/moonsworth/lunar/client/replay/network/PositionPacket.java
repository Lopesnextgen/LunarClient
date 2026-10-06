package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.timeline.EntityPositionApplier;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class PositionPacket extends ReplayPacket implements EntityPositionApplier {
   private double x;
   private double y;
   private double z;
   private boolean field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.x = bytebufloader1.readDouble();
      this.y = bytebufloader1.readDouble();
      this.z = bytebufloader1.readDouble();
      this.field1 = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeDouble(this.x);
      bytebufloader1.writeDouble(this.y);
      bytebufloader1.writeDouble(this.z);
      bytebufloader1.writeBoolean(this.field1);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null && (!nameplate41.method18() || !bridge5extension_52.bridge$isRiding())) {
         LocalPlayerContext nameplate33 = nameplate41.method7();
         nameplate41.method3(() -> this.method5(nameplate33, this.x, this.y, this.z, this.field1));
         if (!nameplate41.method18()) {
            this.method5(nameplate33, this.x, this.y, this.z, this.field1);
            Ref.method3().bridge$displayScreen(null);
            nameplate41.method32(true);
            Ref.method3().bridge$schedule(() -> LunarEventBus.method29().method12(EventSecond.class, EventSecond::new));
            if (Ref.MC_VERSION >= 7) {
               CameraBridge bridge2_194 = Ref.method3().bridge$getGameRenderer().bridge$getCamera();
               bridge2_194.bridge$setEyeHeight(bridge5extension_52.bridge$getEyeHeight());
            }
         }
      }
   }

   @Generated
   public PositionPacket(double value1, double value3, double value5, boolean flag7) {
      this.x = value1;
      this.y = value3;
      this.z = value5;
      this.field1 = flag7;
   }

   @Generated
   public PositionPacket() {
   }
}
