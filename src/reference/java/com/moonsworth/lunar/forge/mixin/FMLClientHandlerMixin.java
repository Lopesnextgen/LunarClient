package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.forge.Ichor6Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FMLClientHandler.class)
public class FMLClientHandlerMixin {
   public FMLClientHandlerMixin() {
   }

   @Inject(method = "beginMinecraftLoading", at = @At("HEAD"))
   private void check$beginMinecraftLoading(Minecraft minecraft1, List list, IReloadableResourceManager ireloadableresourcemanager3, CallbackInfo callback4) {
      Ichor6Iterator.field2.info("FMLClientHandler: Beginning Forge mod loading", new Object[0]);
   }

   @Inject(method = "finishMinecraftLoading", at = @At("HEAD"))
   private void check$finishMinecraftLoading(CallbackInfo callback1) {
      Ichor6Iterator.field2.info("FMLClientHandler: Finishing Forge mod loading", new Object[0]);
   }

   @Redirect(method = "finishMinecraftLoading", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;refreshResources()V"))
   private void ichor$finishMinecraftLoading(Minecraft minecraft1) {
   }
}
