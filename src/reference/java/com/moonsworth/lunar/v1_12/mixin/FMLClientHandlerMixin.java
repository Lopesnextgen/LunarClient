package com.moonsworth.lunar.v1_12.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridgeimpl.mixin.MixinHelper;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSerializer;
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
   public void ichor$initExtraServerListData(ServerData serverdata1, ServerStatusResponse serverstatusresponse2, CallbackInfo callback3) {
      if (this.extraServerListData == null) {
         this.setupServerList();
      }
   }

   @Inject(method = "beginMinecraftLoading", at = @At("HEAD"))
   private void initForgeBridge(Minecraft minecraft1, List<IResourcePack> list2, IReloadableResourceManager ireloadableresourcemanager3, IMetadataSerializer imetadataserializer4, CallbackInfo callback5) {
      Bridge.method39(new MixinHelper());
   }
}
