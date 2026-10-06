package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.NetworkConnectionBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerHandshakeTCP.class)
public class NetHandlerHandshakeTCPMixin {
   @Final
   @Shadow
   public NetworkManager_v1_12 networkManager$v1_12;
   @Final
   @Shadow
   public NetworkManager networkManager;
   @Final
   @Shadow
   public NetworkManager_v1_7 networkManager$v1_7;

   @Inject(method = "processHandshake", at = @At("HEAD"))
   private void lunar$recordEnteredHostName(C00Handshake var1, CallbackInfo var2) {
      NetworkConnectionBridge var3;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var3 = (NetworkConnectionBridge)this.networkManager$v1_12;
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         var3 = (NetworkConnectionBridge)this.networkManager;
      } else {
         var3 = (NetworkConnectionBridge)this.networkManager$v1_7;
      }

      var3.bridge$setEnteredHostName(var1.ip);
   }
}
