package com.moonsworth.lunar.v1_8.mixin;

import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.ServerStatusResponse;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FMLClientHandler.class)
public abstract class FMLClientHandlerMixin {
   @Shadow
   public Map<ServerStatusResponse, JsonObject> extraServerListData;

   public FMLClientHandlerMixin() {
   }

   @Shadow
   public abstract void setupServerList();

   @Inject(method = "bindServerListData", at = @At("HEAD"))
   public void ichor$initExtraServerListData(ServerData data, ServerStatusResponse serverstatusresponse2, CallbackInfo callback3) {
      if (this.extraServerListData == null) {
         this.setupServerList();
      }
   }
}
