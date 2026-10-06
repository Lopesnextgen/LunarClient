package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.SpawnExperienceOrbPacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnExperienceOrbPacketBuilder extends RewindPacketBuilder implements SpawnExperienceOrbPacketBridge {
   public SpawnExperienceOrbPacketBuilder(List<PacketBuilder> list1) {
      super(S11PacketSpawnExperienceOrb.class, list1);
   }

   public PacketBridge method6(Supplier<Object[]> supplier1, PacketBridge bridge3_212) {
      S11PacketSpawnExperienceOrb s11packetspawnexperienceorb3 = (S11PacketSpawnExperienceOrb)bridge3_212;
      return (PacketBridge)(new S13PacketDestroyEntities(new int[]{s11packetspawnexperienceorb3.entityID}));
   }
}
