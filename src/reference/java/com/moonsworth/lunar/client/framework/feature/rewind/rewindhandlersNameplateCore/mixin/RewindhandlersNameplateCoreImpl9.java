package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.lunarclient.apollo.module.beam.BeamModule;
import com.moonsworth.lunar.client.Highlight3Iterator6;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Iterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Iterator3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Iterator4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Iterator6;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class RewindhandlersNameplateCoreImpl9 extends RewindhandlersNameplateCore {
   @Override
   public void method2(EventClientTick var1, RewindHandlers5 var2, Rewind_4 var3) {
      if (var3.method5()) {
         var3.method9(new Nameplate2Iterator3(ThreadModuleDump63.method4().method63().IORHHHROCRRHORHRCHCCHHIHICCRCO()), var2.getTick());
         var3.method9(new Nameplate2Iterator6(ThreadModuleDump63.method4().method57().IORHHHROCRRHORHRCHCCHHIHICCRCO()), var2.getTick());
         var3.method9(new Nameplate2Iterator2(ThreadModuleDump63.method4().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH()), var2.getTick());
         var3.method9(new Nameplate2Iterator4(ThreadModuleDump63.method4().method46().method43()), var2.getTick());
         ThreadModuleDump63.method4().method84().method3(BeamModule.class).ifPresent((ConsumerExtension)var2x -> {
            Highlight3Iterator6 var3x = (Highlight3Iterator6)var2x;
            var3.method9(new Nameplate2Iterator(var3x.method4()), var2.getTick());
         });
      }
   }
}
