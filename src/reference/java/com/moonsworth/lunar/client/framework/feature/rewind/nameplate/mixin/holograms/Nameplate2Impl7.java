package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl7 extends Nameplate2 {
   @Override
   public void method1(ByteBufLoader var1) {
   }

   @Override
   public void method2(ByteBufLoader var1) {
   }

   @Override
   public void method3(Nameplate4 var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         var2.bridge$stopUsingItem();
      }
   }
}
