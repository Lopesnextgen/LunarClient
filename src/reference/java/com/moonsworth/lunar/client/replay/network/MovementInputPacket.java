package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MovementInputBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class MovementInputPacket extends ReplayPacket {
   private float field1;
   private float field2;
   private boolean sneaking;
   private boolean jumping;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readFloat();
      this.field2 = bytebufloader1.readFloat();
      this.sneaking = bytebufloader1.readBoolean();
      this.jumping = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeFloat(this.field1);
      bytebufloader1.writeFloat(this.field2);
      bytebufloader1.writeBoolean(this.sneaking);
      bytebufloader1.writeBoolean(this.jumping);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         MovementInputBridge bridge_213 = bridge5extension_52.bridge$getMovementInput();
         bridge_213.bridge$setMoveForward(this.field1);
         bridge_213.bridge$setMoveStrafe(this.field2);
         bridge_213.bridge$setSneak(this.sneaking);
         bridge_213.bridge$setJump(this.jumping);
      }
   }

   @Generated
   public MovementInputPacket(float value1, float value2, boolean flag3, boolean flag4) {
      this.field1 = value1;
      this.field2 = value2;
      this.sneaking = flag3;
      this.jumping = flag4;
   }

   @Generated
   public MovementInputPacket() {
   }
}
