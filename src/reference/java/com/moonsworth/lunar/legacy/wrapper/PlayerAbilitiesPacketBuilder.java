package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;

public class PlayerAbilitiesPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.PlayerAbilitiesPacketBridge {
   public PlayerAbilitiesPacketBuilder(List<PacketBuilder> list) {
      super(S39PacketPlayerAbilities.class, list);
   }

   @Override
   public PacketBridge method1(Bridge5Extension_5 bridge5extension_51) {
      EntityPlayerSP player2 = (EntityPlayerSP)bridge5extension_51;
      return (PacketBridge)(new S39PacketPlayerAbilities(player2.capabilities));
   }
}
