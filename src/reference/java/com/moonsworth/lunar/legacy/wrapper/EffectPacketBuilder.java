package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.util.BlockPos;

public class EffectPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.EffectPacketBridge {
   public EffectPacketBuilder(List<PacketBuilder> list1) {
      super(S28PacketEffect.class, list1);
   }

   public PacketBridge method1(int number1, Horsestats20Extension2 horsestats20extension22, int number3) {
      return Ref.MC_VERSION <= 0
         ? (PacketBridge)(new S28PacketEffect(number1, horsestats20extension22.bridge$getX(), horsestats20extension22.bridge$getY(), horsestats20extension22.bridge$getZ(), number3, false))
         : (PacketBridge)(new S28PacketEffect(number1, (BlockPos)horsestats20extension22, number3, false));
   }
}
