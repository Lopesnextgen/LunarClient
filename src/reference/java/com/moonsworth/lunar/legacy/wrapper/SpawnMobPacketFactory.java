package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnMobPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper11 {
   public SpawnMobPacketFactory(List<MixinHelper_19> var1) {
      super(S0FPacketSpawnMob.class, var1);
   }

   public Bridge3_21 method6(Supplier<Object[]> var1, Bridge3_21 var2) {
      S0FPacketSpawnMob var3 = (S0FPacketSpawnMob)var2;
      return (Bridge3_21)(new S13PacketDestroyEntities(new int[]{var3.field_179775_c}));
   }
}
