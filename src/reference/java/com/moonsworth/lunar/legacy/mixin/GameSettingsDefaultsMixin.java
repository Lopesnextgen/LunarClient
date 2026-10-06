package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EnumPlayerModelParts;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameSettings.class)
public class GameSettingsDefaultsMixin {
   @VersionGate(1)
   @Shadow
   public boolean useVbo;
   @VersionGate(min = 1)
   @Mutable
   @Final
   @Shadow
   public Set<EnumPlayerModelParts> setModelParts;

   public GameSettingsDefaultsMixin() {
   }

   @VersionGate(1)
   @Inject(method = "loadOptions", at = @At("HEAD"))
   private void lunar$enableVBOsDefault(CallbackInfo callback1) {
      this.useVbo = true;
   }

   @Inject(method = "saveOptions", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindPreventSave(CallbackInfo callback1) {
      if (Ref.method4().method40().method85().method19()) {
         callback1.cancel();
      }
   }

   @VersionGate(min = 1)
   @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V", at = @At("TAIL"))
   private void lunar$optimizeModelPartSet(CallbackInfo callback1) {
      this.setModelParts = EnumSet.allOf(EnumPlayerModelParts.class);
   }

   @VersionGate(min = 1)
   @Inject(method = "<init>()V", at = @At("TAIL"))
   private void lunar$optimizeModelPartSetDefault(CallbackInfo callback1) {
      this.setModelParts = EnumSet.allOf(EnumPlayerModelParts.class);
   }
}
