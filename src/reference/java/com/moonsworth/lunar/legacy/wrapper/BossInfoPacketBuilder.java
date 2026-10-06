package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.network.play.server.SPacketUpdateBossInfo.Operation;

public class BossInfoPacketBuilder extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper16 {
   public BossInfoPacketBuilder(List<MixinHelper_19> var1) {
      super(ThreadModuleDump63.MC_VERSION <= 1 ? null : SPacketUpdateBossInfo.class, var1);
   }

   @Override
   public List<Bridge3_21> method2() {
      ArrayList var1 = new ArrayList();
      if (ThreadModuleDump63.MC_VERSION == 5) {
         for (BossInfoClient var3 : Minecraft.getMinecraft().ingameGUI.getBossOverlay$v1_12().mapBossInfos.values()) {
            var1.add((Bridge3_21)(new SPacketUpdateBossInfo(Operation.ADD, var3)));
         }
      }

      return var1;
   }
}
