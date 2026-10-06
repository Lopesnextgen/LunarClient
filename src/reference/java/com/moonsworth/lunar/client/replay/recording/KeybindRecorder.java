package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.recording.KeybindAction;
import com.moonsworth.lunar.client.replay.network.KeybindPacket;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.mod.movement.zoom.Zoom;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;

public class KeybindRecorder extends RecorderEventListener {
   private boolean field1;
   private boolean field2;
   private boolean field3;

   public KeybindRecorder() {
   }

   @Override
   public void method2(EventTick highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      GameOptionsBridge mixinhelper2_84 = Ref.method3().bridge$getGameSettings();
      KeyBindingBridge mixinhelper_155 = mixinhelper2_84.bridge$keyBindPlayerList();
      Tab tab6 = Ref.method4().method40().method48();
      boolean flag7 = tab6.isEnabled() ? tab6.isActive() : mixinhelper_155.bridge$isKeyDown();
      if (rewind_43.method5() || flag7 != this.field1) {
         rewind_43.method9(new KeybindPacket(KeybindAction.TAB, flag7), rewindhandlers52.getTick());
         this.field1 = flag7;
      }

      Optional optional8 = mixinhelper2_84.bridge$getZoomKey();
      Zoom zoom9 = Ref.method4().method40().method50();
      boolean flag10 = zoom9.isEnabled() ? zoom9.isActive() : optional8.isPresent() && ((KeyBindingBridge)optional8.get()).bridge$isKeyDown();
      if (rewind_43.method5() || flag10 != this.field2) {
         rewind_43.method9(new KeybindPacket(KeybindAction.ZOOM, flag10), rewindhandlers52.getTick());
         this.field2 = flag10;
      }

      boolean flag11 = mixinhelper2_84.bridge$isHideGui();
      if (rewind_43.method5() || flag11 != this.field3) {
         rewind_43.method9(new KeybindPacket(KeybindAction.F1, flag11), rewindhandlers52.getTick());
         this.field3 = flag11;
      }
   }
}
