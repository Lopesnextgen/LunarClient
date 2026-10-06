package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.ConnectionProtocol;
import com.moonsworth.lunar.bridge.PacketDirectionBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import io.netty.buffer.ByteBuf;
import javax.annotation.Nullable;
import lombok.Generated;

public class EventPacket extends LunarEvent {
   @Nullable
   private final PacketBridge field1;
   @Nullable
   private final ByteBuf field2;
   private final PacketDirectionBridge field3;
   private final ConnectionProtocol field4;

   @Generated
   public EventPacket(@Nullable PacketBridge bridge3_211, @Nullable ByteBuf buffer2, PacketDirectionBridge bridgetype_43, ConnectionProtocol bridgetype2_24) {
      this.field1 = bridge3_211;
      this.field2 = buffer2;
      this.field3 = bridgetype_43;
      this.field4 = bridgetype2_24;
   }

   @Nullable
   @Generated
   public PacketBridge method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   public ByteBuf method2() {
      return this.field2;
   }

   @Generated
   public PacketDirectionBridge method3() {
      return this.field3;
   }

   @Generated
   public ConnectionProtocol method4() {
      return this.field4;
   }
}
