package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.world.World;

public class ServerDifficultyPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.ServerDifficultyPacketBridge {
   public ServerDifficultyPacketBuilder(List<PacketBuilder> list1) {
      super(Ref.MC_VERSION <= 0 ? null : S41PacketServerDifficulty.class, list1);
   }

   @Override
   public PacketBridge method1(Itemcounter6 itemcounter61) {
      if (Ref.MC_VERSION <= 0) {
         throw new UnsupportedOperationException("This packet is not available in 1.7");
      }

      World world2 = (World)itemcounter61;
      return (PacketBridge)(new S41PacketServerDifficulty(world2.getDifficulty(), world2.worldInfo.isDifficultyLocked()));
   }
}
