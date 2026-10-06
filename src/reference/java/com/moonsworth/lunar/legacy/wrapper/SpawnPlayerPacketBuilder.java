package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnPlayerPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.EntityPlayerPacketBuilder {
   public SpawnPlayerPacketBuilder(List<PacketBuilder> list1) {
      super(S0CPacketSpawnPlayer.class, list1);
   }

   public PacketBridge method1(Bridge6_10 bridge6_101) {
      return (PacketBridge)(new S0CPacketSpawnPlayer((EntityPlayer)bridge6_101));
   }

   public PacketBridge method6(Supplier<Object[]> supplier1, PacketBridge bridge3_212) {
      S0CPacketSpawnPlayer s0cpacketspawnplayer3 = (S0CPacketSpawnPlayer)bridge3_212;
      return (PacketBridge)(new S13PacketDestroyEntities(new int[]{s0cpacketspawnplayer3.field_179775_c}));
   }
}
