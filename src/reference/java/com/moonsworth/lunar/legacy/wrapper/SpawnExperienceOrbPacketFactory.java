package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.SpawnExperienceOrbPacketTranslator;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnExperienceOrbPacketFactory extends AbstractRewindPacketBuilder implements SpawnExperienceOrbPacketTranslator {
   public SpawnExperienceOrbPacketFactory(List<MixinHelper_19> var1) {
      super(S11PacketSpawnExperienceOrb.class, var1);
   }

   public Bridge3_21 method6(Supplier<Object[]> var1, Bridge3_21 var2) {
      S11PacketSpawnExperienceOrb var3 = (S11PacketSpawnExperienceOrb)var2;
      return (Bridge3_21)(new S13PacketDestroyEntities(new int[]{var3.entityID}));
   }
}
