package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.replay.recording.KeybindAction;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickStart;
import com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier.FovInput;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.mod.movement.zoom.Zoom;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;

public class ZoomKeyHandler extends RewindHandler {
   public ZoomKeyHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      this.handle(FovInput.class, this::method1);
      this.handle(EventRenderTickStart.class, this::method2);
      this.handle(EventKeybind.class, this::method3);
      this.handle(EventMouseButton.class, this::method4);
   }

   public void method1(FovInput data1) {
      Optional optional2 = Ref.method3().bridge$getGameSettings().bridge$getZoomKey();
      Zoom zoom3 = Ref.method4().method40().method50();
      boolean flag4 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method45().method15().isFixedToPlayer()
         && ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method15().getOrDefault(KeybindAction.ZOOM, false);
      if (zoom3.isEnabled()) {
         zoom3.setActive(flag4);
      } else {
         optional2.ifPresent(arg1x -> arg1x.bridge$setKeyBindState(flag4));
      }
   }

   public void method2(EventRenderTickStart data51) {
      RewindHandlers rewindhandlers2 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      boolean flag3;
      if (rewindhandlers2.method45().method15().isFixedToPlayer()) {
         flag3 = (Boolean)rewindhandlers2.method45().method36().get()
            || ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method15().getOrDefault(KeybindAction.F1, false);
      } else {
         flag3 = (Boolean)rewindhandlers2.method45().method35().get();
      }

      Ref.method3().bridge$getGameSettings().bridge$setHideGui(flag3);
   }

   private void method3(EventKeybind highlightimpl1) {
      if (highlightimpl1.method11() == InputAction.DOWN) {
         this.method5(highlightimpl1, highlightimpl1.method10());
      }
   }

   private void method4(EventMouseButton highlightimpl31) {
      if (highlightimpl31.method2() >= 0) {
         if (highlightimpl31.method4() == InputAction.DOWN) {
            this.method5(highlightimpl31, KeyCode.fromMouse(highlightimpl31.method2()));
         }
      }
   }

   private void method5(com.moonsworth.lunar.client.event.CancellableEvent highlightimpl1, KeyCode bridgetype_82) {
      if (bridgetype_82 == this.mc.bridge$getGameSettings().bridge$getScreenshotKey()) {
         highlightimpl1.cancel();
      }
   }
}
