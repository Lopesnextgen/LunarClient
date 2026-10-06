package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.SpawnPositionPacketFactory;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class SpawnPointPacketBuilder extends AbstractRewindPacketBuilder implements SpawnPositionPacketFactory {
   public SpawnPointPacketBuilder(List<MixinHelper_19> var1) {
      super(S05PacketSpawnPosition.class, var1);
   }

   @Override
   public Bridge3_21 method1(Itemcounter6 var1) {
      World var2 = (World)var1;
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         ChunkCoordinates var3 = var2.getSpawnPoint();
         return (Bridge3_21)(new S05PacketSpawnPosition(var3.posX, var3.posY, var3.posZ));
      } else {
         return (Bridge3_21)(new S05PacketSpawnPosition(var2.getSpawnPoint()));
      }
   }
}
