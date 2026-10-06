package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.timeline.EntityPositionApplier;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class RelativePositionPacket extends ReplayPacket implements EntityPositionApplier {
   public static final int field1 = 60;
   public static final double field2 = 4096.0;
   private short field3;
   private short field4;
   private short field5;
   private boolean field6;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field3 = bytebufloader1.readShort();
      this.field4 = bytebufloader1.readShort();
      this.field5 = bytebufloader1.readShort();
      this.field6 = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeShort(this.field3);
      bytebufloader1.writeShort(this.field4);
      bytebufloader1.writeShort(this.field5);
      bytebufloader1.writeBoolean(this.field6);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null && !bridge5extension_52.bridge$isRiding()) {
         LocalPlayerContext nameplate33 = nameplate41.method7();
         nameplate41.method3(() -> {
            double value2x = nameplate33.getX() + this.field3 / 4096.0;
            double value4 = nameplate33.getY() + this.field4 / 4096.0;
            double value6 = nameplate33.getZ() + this.field5 / 4096.0;
            this.method5(nameplate33, value2x, value4, value6, this.field6);
         });
      }
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      return new RelativePositionPacket((short)(-this.field3), (short)(-this.field4), (short)(-this.field5), this.field6);
   }

   @Generated
   public RelativePositionPacket(short number1, short number2, short number3, boolean flag4) {
      this.field3 = number1;
      this.field4 = number2;
      this.field5 = number3;
      this.field6 = flag4;
   }

   @Generated
   public RelativePositionPacket() {
   }
}
