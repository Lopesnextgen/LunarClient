package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;

public class EntityHeadLookPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.EntityHeadLookPacketTranslator {
   public EntityHeadLookPacketFactory(List<MixinHelper_19> var1) {
      super(S19PacketEntityHeadLook.class, var1);
   }

   public Bridge3_21 method1(BridgeExtension2_5 var1) {
      net.minecraft.entity.EntityLivingBase var2 = (net.minecraft.entity.EntityLivingBase)var1;
      return (Bridge3_21)(new S19PacketEntityHeadLook(var2, (byte)(var2.rotationYawHead * 256.0F / 360.0F)));
   }
}
