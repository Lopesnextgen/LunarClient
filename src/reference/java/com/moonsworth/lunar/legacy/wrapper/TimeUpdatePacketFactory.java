package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.world.World;

public class TimeUpdatePacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.TimeUpdatePacketTranslator {
   public TimeUpdatePacketFactory(List<MixinHelper_19> var1) {
      super(S03PacketTimeUpdate.class, var1);
   }

   public Bridge3_21 method1(Itemcounter6 var1) {
      World var2 = (World)var1;
      return (Bridge3_21)(
         new S03PacketTimeUpdate(
            var2.getTotalWorldTime(),
            var2.getWorldTime(),
            ThreadModuleDump63.MC_VERSION <= 0
               ? var2.getGameRules().getGameRuleBooleanValue$v1_7("doDaylightCycle")
               : var2.getGameRules().getBoolean("doDaylightCycle")
         )
      );
   }
}
