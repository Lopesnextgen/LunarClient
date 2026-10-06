package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl extends Nameplate2 {
   private int tick;

   @Override
   public void method1(ByteBufLoader var1) {
      this.tick = var1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.tick);
   }

   @Override
   public void method3(Nameplate4 var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            var2.bridge$setAttackStrengthTicker(this.tick);
         }

         if (ThreadModuleDump63.MC_VERSION >= 35 && var1.method20() < 2) {
            var2.bridge$setItemSwapTicker(this.tick);
         }
      }
   }

   @Generated
   public Nameplate2Impl(int var1) {
      this.tick = var1;
   }

   @Generated
   public Nameplate2Impl() {
   }
}
