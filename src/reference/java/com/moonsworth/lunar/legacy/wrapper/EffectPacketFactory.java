package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.util.BlockPos;

public class EffectPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper23 {
   public EffectPacketFactory(List<MixinHelper_19> var1) {
      super(S28PacketEffect.class, var1);
   }

   public Bridge3_21 method1(int var1, Horsestats20Extension2 var2, int var3) {
      return ThreadModuleDump63.MC_VERSION <= 0
         ? (Bridge3_21)(new S28PacketEffect(var1, var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ(), var3, false))
         : (Bridge3_21)(new S28PacketEffect(var1, (BlockPos)var2, var3, false));
   }
}
