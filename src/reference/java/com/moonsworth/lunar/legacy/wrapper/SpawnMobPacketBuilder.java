package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnMobPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.SpawnMobPacketBridge {
   public SpawnMobPacketBuilder(List<PacketBuilder> list1) {
      super(S0FPacketSpawnMob.class, list1);
   }

   public PacketBridge method6(Supplier<Object[]> supplier1, PacketBridge bridge3_212) {
      S0FPacketSpawnMob s0fpacketspawnmob3 = (S0FPacketSpawnMob)bridge3_212;
      return (PacketBridge)(new S13PacketDestroyEntities(new int[]{s0fpacketspawnmob3.field_179775_c}));
   }
}
