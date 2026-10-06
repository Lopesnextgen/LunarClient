package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S13PacketDestroyEntities;

public class SpawnObjectPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper24 {
   public SpawnObjectPacketFactory(List<MixinHelper_19> var1) {
      super(S0EPacketSpawnObject.class, var1);
   }

   public Bridge3_21 method6(Supplier<Object[]> var1, Bridge3_21 var2) {
      S0EPacketSpawnObject var3 = (S0EPacketSpawnObject)var2;
      return (Bridge3_21)(new S13PacketDestroyEntities(new int[]{var3.field_179775_c}));
   }
}
