package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickBegin;
import com.moonsworth.lunar.client.event.mixin.highlight.FovModifierEvent.Data;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.mod.movement.zoom.Zoom;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;

public class RewindHandlers3Impl7 extends RewindHandlers3 {
   public RewindHandlers3Impl7(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      this.handle(Data.class, this::method1);
      this.handle(EventRenderTickBegin.class, this::method2);
      this.handle(KeybindEvent.class, this::method3);
      this.handle(EventMouseButtonLegacy.class, this::method4);
   }

   public void method1(Data var1) {
      Optional var2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getZoomKey();
      Zoom var3 = ThreadModuleDump63.method4().method40().method50();
      boolean var4 = ((Nameplate4)this.field8.get()).method6().method45().getProvider().isFixedToPlayer()
         && ((Nameplate4)this.field8.get()).getProvider().getOrDefault(GuiType3.ZOOM, false);
      if (var3.isEnabled()) {
         var3.setActive(var4);
      } else {
         var2.ifPresent(var1x -> var1x.bridge$setKeyBindState(var4));
      }
   }

   public void method2(EventRenderTickBegin var1) {
      RewindHandlers var2 = ((Nameplate4)this.field8.get()).method6();
      boolean var3;
      if (var2.method45().getProvider().isFixedToPlayer()) {
         var3 = (Boolean)var2.method45().method36().get()
            || ((Nameplate4)this.field8.get()).getProvider().getOrDefault(GuiType3.F1, false);
      } else {
         var3 = (Boolean)var2.method45().method35().get();
      }

      ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setHideGui(var3);
   }

   private void method3(KeybindEvent var1) {
      if (var1.method11() == InputActionLegacy.DOWN) {
         this.method5(var1, var1.method10());
      }
   }

   private void method4(EventMouseButtonLegacy var1) {
      if (var1.method2() >= 0) {
         if (var1.method4() == InputActionLegacy.DOWN) {
            this.method5(var1, KeyCode.fromMouse(var1.method2()));
         }
      }
   }

   private void method5(com.moonsworth.lunar.client.highlight.HighlightImpl var1, KeyCode var2) {
      if (var2 == this.mc.bridge$getGameSettings().bridge$getScreenshotKey()) {
         var1.cancel();
      }
   }
}
