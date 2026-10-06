package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.settings.KeyBinding_v1_7;
import net.minecraft.util.IntHashMap_v1_7;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(KeyBinding_v1_7.class)
public class KeyBinding_v1_7Mixin2 {
   @Final
   @Shadow
   public static IntHashMap_v1_7 hash;

   @WrapWithCondition(
      method = "resetKeyBindingArrayAndHash",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/IntHashMap_v1_7;addKey(ILjava/lang/Object;)V")
   )
   private static <V> boolean lunar$shouldIncludeModKeybind(IntHashMap_v1_7 var0, int var1, V var2) {
      KeyBinding_v1_7 var3 = (KeyBinding_v1_7)var2;
      SimpleKeybindOption var4 = (SimpleKeybindOption)SimpleKeybindOption.keybindRegistry.inverse().get((MixinHelper_15)var3);
      return var4 != null && var4.method17()
         ? false
         : ThreadModuleDump63.method4() == null
            || ThreadModuleDump63.method4().method40() == null
            || !ThreadModuleDump63.method4().method40().method10().contains(var3.getKeyCategory());
   }

   @Inject(method = "setKeyBindState", at = @At("HEAD"), cancellable = true)
   private static void lunar$setKeybindState(int var0, boolean var1, CallbackInfo var2) {
      if (var0 != 0) {
         KeyBinding_v1_7 var3 = (KeyBinding_v1_7)hash.lookup(var0);
         if (var3 != null) {
            Framework7Extension var4 = ThreadModuleDump63.method4().method40().method5(var3.keyCategory);
            if (var4 != null && !var4.isEnabled()) {
               var2.cancel();
            }
         }
      }
   }
}
