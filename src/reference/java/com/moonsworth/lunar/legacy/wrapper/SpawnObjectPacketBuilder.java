package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnObjectPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.SpawnObjectPacketBridge {
   public SpawnObjectPacketBuilder(List<PacketBuilder> list1) {
      super(S0EPacketSpawnObject.class, list1);
   }

   public PacketBridge method6(Supplier<Object[]> supplier1, PacketBridge bridge3_212) {
      S0EPacketSpawnObject s0epacketspawnobject3 = (S0EPacketSpawnObject)bridge3_212;
      return (PacketBridge)(new S13PacketDestroyEntities(new int[]{s0epacketspawnobject3.field_179775_c}));
   }
}
