package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S1FPacketSetExperience;

public class SetExperiencePacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper20 {
   public SetExperiencePacketFactory(List<MixinHelper_19> var1) {
      super(S1FPacketSetExperience.class, var1);
   }

   public Bridge3_21 method1(Bridge5Extension_5 var1) {
      EntityPlayerSP var2 = (EntityPlayerSP)var1;
      return (Bridge3_21)(new S1FPacketSetExperience(var2.experience, var2.experienceTotal, var2.experienceLevel));
   }
}
