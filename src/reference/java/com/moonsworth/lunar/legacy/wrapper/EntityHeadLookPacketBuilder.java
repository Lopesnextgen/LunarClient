package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;

public class EntityHeadLookPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.EntityHeadLookPacketBridge {
   public EntityHeadLookPacketBuilder(List<PacketBuilder> list) {
      super(S19PacketEntityHeadLook.class, list);
   }

   public PacketBridge method1(EntityLivingBridge bridgeextension2_51) {
      net.minecraft.entity.EntityLivingBase entity2 = (net.minecraft.entity.EntityLivingBase)bridgeextension2_51;
      return (PacketBridge)(new S19PacketEntityHeadLook(entity2, (byte)(entity2.rotationYawHead * 256.0F / 360.0F)));
   }
}
