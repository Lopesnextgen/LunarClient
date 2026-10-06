package com.moonsworth.lunar.client.event.mixin.rewindhandlers;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers3;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class KeybindEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final KeyCode field1;
   private final InputActionLegacy field2;
   private boolean field3;

   public boolean method1(SimpleKeybindOption var1) {
      return this.method2(var1, true);
   }

   public boolean method2(SimpleKeybindOption var1, boolean var2) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (ThreadModuleDump63.method3().bridge$getCurrentScreen() != null
            || this.field2 != InputActionLegacy.DOWN
            || this.field1 != var1.get()
            || var2 && GuiRewindhandlers3.method4()) {
            return false;
         }

         this.cancel();
         return true;
      } else {
         return false;
      }
   }

   public boolean method3(ModifierKeybindOption var1) {
      return this.method4(var1, true);
   }

   public boolean method4(ModifierKeybindOption var1, boolean var2) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (ThreadModuleDump63.method3().bridge$getCurrentScreen() == null
            && this.field2 == InputActionLegacy.DOWN
            && var1.method7()
            && this.field1 == ((KeyCombo)var1.get()).method8()
            && (!var2 || !GuiRewindhandlers3.method4())) {
            if (var1.method21()) {
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

   public boolean method5(ModifierKeybindOption var1, InputActionLegacy var2) {
      return this.method6(var1, var2, true);
   }

   public boolean method6(ModifierKeybindOption var1, InputActionLegacy var2, boolean var3) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (ThreadModuleDump63.method3().bridge$getCurrentScreen() == null
            && var2 == this.field2
            && var1.method7()
            && this.field1 == ((KeyCombo)var1.get()).method8()
            && (!var3 || !GuiRewindhandlers3.method4())) {
            if (var1.method21()) {
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

   public boolean method7(SimpleKeybindOption var1, InputActionLegacy var2) {
      return this.method8(var1, var2, true);
   }

   public boolean method8(SimpleKeybindOption var1, InputActionLegacy var2, boolean var3) {
      if (this.field1 != KeyCode.KEY_NONE && !this.method9(this.field1)) {
         if (ThreadModuleDump63.method3().bridge$getCurrentScreen() != null
            || var2 != this.field2
            || this.field1 != var1.get()
            || var3 && GuiRewindhandlers3.method4()) {
            return false;
         }

         this.cancel();
         return true;
      } else {
         return false;
      }
   }

   private boolean method9(KeyCode var1) {
      ReplayMod var2 = Client.method109().method40().method64();
      return !var2.isEnabled() ? false : Fishing.method2(Fishing2Extension.class).map(var2x -> {
         if (!var2.method13()) {
            return false;
         }

         for (MixinHelper_15 var5 : var2x.method10()) {
            if (var5.bridge$getKey() == var1) {
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
   public InputActionLegacy method11() {
      return this.field2;
   }

   @Generated
   public boolean method12() {
      return this.field3;
   }

   @Generated
   public KeybindEvent(KeyCode var1, InputActionLegacy var2, boolean var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }
}
