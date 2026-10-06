package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.world.World;

public class TimeUpdatePacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.TimeUpdatePacketBridge {
   public TimeUpdatePacketBuilder(List<PacketBuilder> list1) {
      super(S03PacketTimeUpdate.class, list1);
   }

   public PacketBridge method1(Itemcounter6 itemcounter61) {
      World world2 = (World)itemcounter61;
      return (PacketBridge)(
         new S03PacketTimeUpdate(
            world2.getTotalWorldTime(),
            world2.getWorldTime(),
            Ref.MC_VERSION <= 0
               ? world2.getGameRules().getGameRuleBooleanValue$v1_7("doDaylightCycle")
               : world2.getGameRules().getBoolean("doDaylightCycle")
         )
      );
   }
}
