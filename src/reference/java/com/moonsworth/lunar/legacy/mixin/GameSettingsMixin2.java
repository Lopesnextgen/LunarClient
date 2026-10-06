package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
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
public class GameSettingsMixin2 {
   @Annotation2(1)
   @Shadow
   public boolean useVbo;
   @Annotation2(min = 1)
   @Mutable
   @Final
   @Shadow
   public Set<EnumPlayerModelParts> setModelParts;

   @Annotation2(1)
   @Inject(method = "loadOptions", at = @At("HEAD"))
   private void lunar$enableVBOsDefault(CallbackInfo var1) {
      this.useVbo = true;
   }

   @Inject(method = "saveOptions", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindPreventSave(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V", at = @At("TAIL"))
   private void lunar$optimizeModelPartSet(CallbackInfo var1) {
      this.setModelParts = EnumSet.allOf(EnumPlayerModelParts.class);
   }

   @Annotation2(min = 1)
   @Inject(method = "<init>()V", at = @At("TAIL"))
   private void lunar$optimizeModelPartSetDefault(CallbackInfo var1) {
      this.setModelParts = EnumSet.allOf(EnumPlayerModelParts.class);
   }
}
