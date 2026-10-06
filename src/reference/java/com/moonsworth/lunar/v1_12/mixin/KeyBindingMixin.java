package com.moonsworth.lunar.v1_12.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyBindingMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {
   @Final
   @Shadow
   public static KeyBindingMap b;

   @WrapWithCondition(
      method = "resetKeyBindingArrayAndHash",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/settings/KeyBindingMap;addKey(ILnet/minecraft/client/settings/KeyBinding;)V")
   )
   private static boolean lunar$shouldIncludeModKeybind(KeyBindingMap var0, int var1, KeyBinding var2) {
      SimpleKeybindOption var3 = (SimpleKeybindOption)SimpleKeybindOption.keybindRegistry.inverse().get((MixinHelper_15)var2);
      return var3 != null && var3.method17()
         ? false
         : ThreadModuleDump63.method4() == null
            || ThreadModuleDump63.method4().method40() == null
            || !ThreadModuleDump63.method4().method40().method10().contains(var2.getKeyCategory());
   }

   @Inject(method = "setKeyBindState", at = @At("HEAD"), cancellable = true)
   private static void lunar$setKeybindState(int var0, boolean var1, CallbackInfo var2) {
      if (var0 != 0) {
         for (KeyBinding var4 : b.lookupAll(var0)) {
            if (var4 != null && ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
               Framework7Extension var5 = ThreadModuleDump63.method4().method40().method5(var4.keyCategory);
               if (var5 != null && !var5.isEnabled()) {
                  var2.cancel();
               }
            }
         }
      }
   }
}
