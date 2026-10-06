package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.util.BlockPos;

public class BlockBreakAnimPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper12 {
   public BlockBreakAnimPacketFactory(List<MixinHelper_19> var1) {
      super(S25PacketBlockBreakAnim.class, var1);
   }

   public Bridge3_21 method1(int var1, Horsestats20Extension2 var2, int var3) {
      return ThreadModuleDump63.MC_VERSION <= 0
         ? (Bridge3_21)(new S25PacketBlockBreakAnim(var1, var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ(), var3))
         : (Bridge3_21)(new S25PacketBlockBreakAnim(var1, (BlockPos)var2, var3));
   }
}
