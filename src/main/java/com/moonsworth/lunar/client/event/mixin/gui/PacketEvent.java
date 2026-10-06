package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.BridgeType2_2;
import com.moonsworth.lunar.bridge.PacketDirection;
import com.moonsworth.lunar.client.highlight.Highlight;
import io.netty.buffer.ByteBuf;
import javax.annotation.Nullable;
import lombok.Generated;

public class PacketEvent extends Highlight {
   @Nullable
   private final Bridge3_21 field1;
   @Nullable
   private final ByteBuf field2;
   private final PacketDirection field3;
   private final BridgeType2_2 field4;

   @Generated
   public PacketEvent(@Nullable Bridge3_21 var1, @Nullable ByteBuf var2, PacketDirection packet, BridgeType2_2 bridgeType2_2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = packet;
      this.field4 = bridgeType2_2;
   }

   @Nullable
   @Generated
   public Bridge3_21 method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   public ByteBuf method2() {
      return this.field2;
   }

   @Generated
   public PacketDirection method3() {
      return this.field3;
   }

   @Generated
   public BridgeType2_2 method4() {
      return this.field4;
   }
}
