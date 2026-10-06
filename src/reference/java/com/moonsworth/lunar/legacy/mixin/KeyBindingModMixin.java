package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IntHashMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(1)
@Mixin(KeyBinding.class)
public class KeyBindingModMixin {
   @Final
   @Shadow
   public static IntHashMap<KeyBinding> HASH;

   public KeyBindingModMixin() {
   }

   @WrapWithCondition(
      method = "resetKeyBindingArrayAndHash",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/IntHashMap_v1_8;addKey(ILjava/lang/Object;)V")
   )
   private static <V> boolean lunar$shouldIncludeModKeybind(IntHashMap<KeyBinding> inthashmap0, int number1, V value2) {
      KeyBinding keybinding3 = (KeyBinding)value2;
      SimpleKeybindOption lightingextension491324 = (SimpleKeybindOption)SimpleKeybindOption.keybindRegistry.inverse().get((KeyBindingBridge)keybinding3);
      return lightingextension491324 != null && lightingextension491324.method17()
         ? false
         : Ref.method4() == null
            || Ref.method4().method40() == null
            || !Ref.method4().method40().method10().contains(keybinding3.getKeyCategory());
   }

   @Inject(method = "setKeyBindState", at = @At("HEAD"), cancellable = true)
   private static void lunar$setKeybindState(int number0, boolean flag1, CallbackInfo callback2) {
      if (number0 != 0) {
         KeyBinding keybinding3 = (KeyBinding)HASH.lookup(number0);
         if (keybinding3 != null) {
            Framework7Extension framework7extension4 = Ref.method4().method40().method5(keybinding3.keyCategory);
            if (framework7extension4 != null && !framework7extension4.isEnabled()) {
               callback2.cancel();
            }
         }
      }
   }
}
