package com.moonsworth.lunar.client.event.mixin.rewindhandlers;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.moonsworth.lunar.client.framework.listener.ScreenInteractionHandler;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class EventKeybind extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final KeyCode field1;
   private final InputAction field2;
   private boolean field3;

   public boolean method1(SimpleKeybindOption lightingextension491321) {
      return this.method2(lightingextension491321, true);
   }

   public boolean method2(SimpleKeybindOption lightingextension491321, boolean flag2) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (Ref.method3().bridge$getCurrentScreen() != null
            || this.field2 != InputAction.DOWN
            || this.field1 != lightingextension491321.get()
            || flag2 && ScreenInteractionHandler.method4()) {
            return false;
         }

         this.cancel();
         return true;
      } else {
         return false;
      }
   }

   public boolean method3(ModifierKeybindOption lightingextension491331) {
      return this.method4(lightingextension491331, true);
   }

   public boolean method4(ModifierKeybindOption lightingextension491331, boolean flag2) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (Ref.method3().bridge$getCurrentScreen() == null
            && this.field2 == InputAction.DOWN
            && lightingextension491331.method7()
            && this.field1 == ((KeyBind)(Object)lightingextension491331.get()).method8()
            && (!flag2 || !ScreenInteractionHandler.method4())) {
            if (lightingextension491331.method21()) {
               this.cancel();
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean method5(ModifierKeybindOption lightingextension491331, InputAction highlighttype2) {
      return this.method6(lightingextension491331, highlighttype2, true);
   }

   public boolean method6(ModifierKeybindOption lightingextension491331, InputAction highlighttype2, boolean flag3) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (Ref.method3().bridge$getCurrentScreen() == null
            && highlighttype2 == this.field2
            && lightingextension491331.method7()
            && this.field1 == ((KeyBind)(Object)lightingextension491331.get()).method8()
            && (!flag3 || !ScreenInteractionHandler.method4())) {
            if (lightingextension491331.method21()) {
               this.cancel();
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean method7(SimpleKeybindOption lightingextension491321, InputAction highlighttype2) {
      return this.method8(lightingextension491321, highlighttype2, true);
   }

   public boolean method8(SimpleKeybindOption lightingextension491321, InputAction highlighttype2, boolean flag3) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (Ref.method3().bridge$getCurrentScreen() != null
            || highlighttype2 != this.field2
            || this.field1 != lightingextension491321.get()
            || flag3 && ScreenInteractionHandler.method4()) {
            return false;
         }

         this.cancel();
         return true;
      } else {
         return false;
      }
   }

   private boolean method9(KeyCode bridgetype_81) {
      ReplayMod replaymod2 = Client.method109().method40().method64();
      return !replaymod2.isEnabled() ? false : ExternalLinkRegistry.method2(RecordingExternalLink.class).map(arg2x -> {
         if (!replaymod2.method13()) {
            return false;
         }

         for (KeyBindingBridge mixinhelper_155 : arg2x.method10()) {
            if (mixinhelper_155.bridge$getKey() == bridgetype_81) {
               return true;
            }
         }

         return false;
      }).orElse(false);
   }

   @Generated
   public KeyCode method10() {
      return this.field1;
   }

   @Generated
   public InputAction method11() {
      return this.field2;
   }

   @Generated
   public boolean method12() {
      return this.field3;
   }

   @Generated
   public EventKeybind(KeyCode bridgetype_81, InputAction highlighttype2, boolean flag3) {
      this.field1 = bridgetype_81;
      this.field2 = highlighttype2;
      this.field3 = flag3;
   }
}
