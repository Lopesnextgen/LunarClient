package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnPaintingPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.SpawnPaintingPacketBridge {
   public SpawnPaintingPacketBuilder(List<PacketBuilder> list1) {
      super(S10PacketSpawnPainting.class, list1);
   }

   public PacketBridge method6(Supplier<Object[]> supplier1, PacketBridge bridge3_212) {
      S10PacketSpawnPainting s10packetspawnpainting3 = (S10PacketSpawnPainting)bridge3_212;
      return (PacketBridge)(new S13PacketDestroyEntities(new int[]{s10packetspawnpainting3.entityID}));
   }
}
