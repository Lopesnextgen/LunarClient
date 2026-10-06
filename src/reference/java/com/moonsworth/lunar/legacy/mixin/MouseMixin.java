package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.gui.GuiScreenContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "org.lwjgl.input.Mouse")
public abstract class MouseMixin {
   public MouseMixin() {
   }

   @Inject(method = "isButtonDown", at = @At("HEAD"), cancellable = true)
   private static void lunar$rewindOverrideButtons(int number0, CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      if (Ref.method4() != null && Ref.method4().method40() != null) {
         RewindMod rewind2 = Ref.method4().method40().method85();
         if (rewind2.method19()) {
            RewindHandlers rewindhandlers3 = rewind2.method35();
            GuiScreenContext nameplate24 = ((ReplayContext)rewindhandlers3.method42().get()).method8();
            boolean flag5 = nameplate24.method12().getOrDefault(number0, false);
            if (!rewindhandlers3.method62()) {
               if (flag5 || rewindhandlers3.method57().method25()) {
                  callbackinforeturnable1.setReturnValue(flag5);
               }
            }
         }
      }
   }

   @Inject(method = "getX", at = @At("HEAD"), cancellable = true)
   private static void lunar$rewindOverrideX(CallbackInfoReturnable<Integer> callbackinforeturnable0) {
      if (Ref.method4() != null && Ref.method4().method40() != null) {
         RewindMod rewind1 = Ref.method4().method40().method85();
         if (rewind1.method19()) {
            RewindHandlers rewindhandlers2 = rewind1.method35();
            ReplayContext nameplate43 = (ReplayContext)rewindhandlers2.method42().get();
            GuiScreenContext nameplate24 = nameplate43.method8();
            if (!rewindhandlers2.method62() && rewindhandlers2.method48().method25() && rewindhandlers2.method45().method15().isFixedToPlayer() && nameplate24.method5() != null) {
               float value5 = nameplate43.method6().method41().getPartialTick();
               int number6 = LcuiScreen.method151().getScaledWidth();
               int number7 = LcuiScreen.method151().method3();
               callbackinforeturnable0.setReturnValue(nameplate24.method3(value5, number6) * number7);
            }
         }
      }
   }

   @Inject(method = "getY", at = @At("HEAD"), cancellable = true)
   private static void lunar$rewindOverrideY(CallbackInfoReturnable<Integer> callbackinforeturnable0) {
      if (Ref.method4() != null && Ref.method4().method40() != null) {
         RewindMod rewind1 = Ref.method4().method40().method85();
         if (rewind1.method19()) {
            RewindHandlers rewindhandlers2 = rewind1.method35();
            ReplayContext nameplate43 = (ReplayContext)rewindhandlers2.method42().get();
            GuiScreenContext nameplate24 = nameplate43.method8();
            if (!rewindhandlers2.method62() && rewindhandlers2.method48().method25() && nameplate24.method5() != null) {
               float value5 = nameplate43.method6().method41().getPartialTick();
               int number6 = LcuiScreen.method151().getScaledHeight();
               int number7 = LcuiScreen.method151().method3();
               callbackinforeturnable0.setReturnValue((number6 - nameplate24.method4(value5, number6)) * number7);
            }
         }
      }
   }
}
