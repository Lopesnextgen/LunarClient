package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.rewindhandlers.Nameplate2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.rewindhandlers.Nameplate2Iterator2;
import com.moonsworth.lunar.client.event.resourcepack.ResourcePackUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.ServerResourcePackUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerResourcePackRemoveEvent;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.List;

public class RewindhandlersNameplateCoreImpl10 extends RewindhandlersNameplateCore {
   private void method1(RewindHandlers5 var1, Rewind_4 var2) {
      File var3 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "resourcepacks");
      List var4 = ThreadModuleDump63.method3().bridge$getClientResourcePacksFiles();
      List var5 = var4.stream().map(var1x -> Gui5.method2(var1x, var3)).toList();
      Nameplate2Iterator2 var6 = new Nameplate2Iterator2(var5);
      var2.method9(var6, var1.getTick());
   }

   @Override
   public void method2(EventClientTick var1, RewindHandlers5 var2, Rewind_4 var3) {
      if (var3.method5()) {
         List var4 = ThreadModuleDump63.method3().bridge$getServerResourcePacksFiles();
         Nameplate2Iterator var5 = new Nameplate2Iterator(var3.method1(var4));
         var3.method9(var5, var2.getTick());
         this.method1(var2, var3);
      }
   }

   @Override
   public void method15(ResourcePackUpdateEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      this.method1(var2, var3);
   }

   @Override
   public void method16(ServerResourcePackUpdateEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new Nameplate2Iterator(var3.method1(var1.getResourcePacks())), var2.getTick());
   }

   @Override
   public void method17(ServerResourcePackRemoveEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new Nameplate2Iterator(List.of()), var2.getTick());
   }
}
