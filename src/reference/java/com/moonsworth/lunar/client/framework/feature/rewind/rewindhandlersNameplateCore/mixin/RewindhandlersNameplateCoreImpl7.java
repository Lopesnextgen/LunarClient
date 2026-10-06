package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.mod.movement.zoom.Zoom;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;

public class RewindhandlersNameplateCoreImpl7 extends RewindhandlersNameplateCore {
   private boolean field1;
   private boolean field2;
   private boolean field3;

   @Override
   public void method2(EventClientTick highlightImpl2, RewindHandlers5 handler, Rewind_4 rewind_4) {
      GameOptionsBridge var4 = ThreadModuleDump63.method3().bridge$getGameSettings();
      MixinHelper_15 var5 = var4.bridge$keyBindPlayerList();
      Tab var6 = ThreadModuleDump63.method4().method40().method48();
      boolean var7 = var6.isEnabled() ? var6.isActive() : var5.bridge$isKeyDown();
      if (rewind_4.method5() || var7 != this.field1) {
         rewind_4.method9(new Nameplate2Impl(GuiType3.TAB, var7), handler.getTick());
         this.field1 = var7;
      }

      Optional var8 = var4.bridge$getZoomKey();
      Zoom var9 = ThreadModuleDump63.method4().method40().method50();
      boolean var10 = var9.isEnabled() ? var9.isActive() : var8.isPresent() && ((MixinHelper_15)var8.get()).bridge$isKeyDown();
      if (rewind_4.method5() || var10 != this.field2) {
         rewind_4.method9(new Nameplate2Impl(GuiType3.ZOOM, var10), handler.getTick());
         this.field2 = var10;
      }

      boolean var11 = var4.bridge$isHideGui();
      if (rewind_4.method5() || var11 != this.field3) {
         rewind_4.method9(new Nameplate2Impl(GuiType3.F1, var11), handler.getTick());
         this.field3 = var11;
      }
   }
}
