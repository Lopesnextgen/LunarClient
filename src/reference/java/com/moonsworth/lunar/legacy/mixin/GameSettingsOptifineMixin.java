package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.settings.KeyBinding_v1_7;
import net.minecraft.client.settings.GameSettings.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameSettings.class)
public abstract class GameSettingsOptifineMixin implements GameOptionsBridge {
   @Shadow
   public KeyBinding_v1_7 ofKeyBindZoom$v1_7;
   @Shadow
   public KeyBinding ofKeyBindZoom;
   @Shadow
   public boolean ofRenderRegions;

   @Shadow
   public abstract void updateVSync();

   @Override
   public void bridge$updateVSync() {
      this.updateVSync();
   }

   @Override
   public Optional<MixinHelper_15> bridge$getZoomKey() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? Optional.of((MixinHelper_15)this.ofKeyBindZoom) : Optional.of((MixinHelper_15)this.ofKeyBindZoom$v1_7);
   }

   @Annotation2(min = 1)
   @Inject(method = "isKeyDown$v1_8", at = @At("HEAD"), cancellable = true)
   private static void lunar$isKeyDown$v1_8(KeyBinding var0, CallbackInfoReturnable<Boolean> var1) {
      if (var0 == ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getZoomKey().orElse(null)
         && ThreadModuleDump63.method4().method40().method50().isEnabled()) {
         var1.setReturnValue(false);
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "isKeyDown$v1_7", at = @At("HEAD"), cancellable = true)
   private static void lunar$isKeyDown$v1_7(KeyBinding_v1_7 var0, CallbackInfoReturnable<Boolean> var1) {
      if (var0 == ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getZoomKey().orElse(null)
         && ThreadModuleDump63.method4().method40().method50().isEnabled()) {
         var1.setReturnValue(false);
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "setOptionValue", at = @At("HEAD"))
   private void lunar$setOptionValue$head(Options var1, int var2, CallbackInfo var3) {
      Bridge.method5().ifPresent(var0 -> var0.setReloading(true));
   }

   @Annotation2(min = 1)
   @Inject(method = "setOptionValueOF$v1_8", at = @At("HEAD"))
   private void lunar$setOptionValueOF$head(Options var1, int var2, CallbackInfo var3) {
      Bridge.method5().ifPresent(var0 -> var0.setReloading(true));
   }

   @Annotation2(max = 0)
   @Inject(method = "setOptionValue", at = @At("RETURN"))
   private void lunar$setOptionValue$tail(Options var1, int var2, CallbackInfo var3) {
      Bridge.method5().ifPresent(var0 -> var0.setReloading(false));
   }

   @Annotation2(min = 1)
   @Inject(method = "setOptionValueOF$v1_8", at = @At("RETURN"))
   private void lunar$setOptionValueOF$tail(Options var1, int var2, CallbackInfo var3) {
      Bridge.method5().ifPresent(var0 -> var0.setReloading(false));
   }

   @Inject(method = "updateWaterOpacity", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindSkipUpdateWaterOpacity(CallbackInfo var1) {
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "setOptionValueOF$v1_8", at = @At("TAIL"))
   private void lunar$onOptionsChange(Options var1, int var2, CallbackInfo var3) {
      if (var1 == Options.RENDER_REGIONS) {
         ThreadModuleDump63.method4().method41().method7().method15().OIRHOOIICOCIOOHICRRRICORIHHIHC(this.ofRenderRegions);
      }
   }
}
