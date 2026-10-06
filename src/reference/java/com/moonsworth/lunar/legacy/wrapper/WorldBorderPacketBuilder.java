package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.WorldBorderPacketTranslator;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.SPacketWorldBorder.Action;
import net.minecraft.world.World;

public class WorldBorderPacketBuilder extends AbstractRewindPacketBuilder implements WorldBorderPacketTranslator {
   public WorldBorderPacketBuilder(List<MixinHelper_19> var1) {
      super(ThreadModuleDump63.MC_VERSION <= 0 ? null : S44PacketWorldBorder.class, var1);
   }

   @Override
   public Bridge3_21 method1(Itemcounter6 var1) {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         throw new UnsupportedOperationException("This packet is not available in 1.7");
      }

      World var2 = (World)var1;
      return (Bridge3_21)(new S44PacketWorldBorder(var2.getWorldBorder(), Action.INITIALIZE));
   }
}
