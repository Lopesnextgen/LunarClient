package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnPlayerPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.EntityPlayerPacketFactory {
   public SpawnPlayerPacketFactory(List<MixinHelper_19> var1) {
      super(S0CPacketSpawnPlayer.class, var1);
   }

   public Bridge3_21 method1(Bridge6_10 var1) {
      return (Bridge3_21)(new S0CPacketSpawnPlayer((EntityPlayer)var1));
   }

   public Bridge3_21 method6(Supplier<Object[]> var1, Bridge3_21 var2) {
      S0CPacketSpawnPlayer var3 = (S0CPacketSpawnPlayer)var2;
      return (Bridge3_21)(new S13PacketDestroyEntities(new int[]{var3.field_179775_c}));
   }
}
