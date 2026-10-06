package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S1FPacketSetExperience;

public class SetExperiencePacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.SetExperiencePacketBridge {
   public SetExperiencePacketBuilder(List<PacketBuilder> list) {
      super(S1FPacketSetExperience.class, list);
   }

   public PacketBridge method1(Bridge5Extension_5 bridge5extension_51) {
      EntityPlayerSP player2 = (EntityPlayerSP)bridge5extension_51;
      return (PacketBridge)(new S1FPacketSetExperience(player2.experience, player2.experienceTotal, player2.experienceLevel));
   }
}
