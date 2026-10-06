package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.moonsworth.lunar.bridge.NetworkConnectionBridge;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.server.network.NetHandlerLoginServer.LoginState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerLoginServer.class)
public class NetHandlerLoginServerMixin {
   @Shadow
   public GameProfile loginGameProfile;
   @Shadow
   public LoginState currentLoginState;
   @Final
   @Shadow
   public NetworkManager_v1_12 networkManager$v1_12;
   @Final
   @Shadow
   public NetworkManager networkManager;
   @Final
   @Shadow
   public NetworkManager_v1_7 networkManager$v1_7;

   @Inject(
      method = "processEncryptionResponse",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/server/network/NetHandlerLoginServer$2;start()V"),
            @At(value = "INVOKE", target = "Lnet/minecraft/server/network/NetHandlerLoginServer$1;start()V")
      },
      cancellable = true
   )
   private void lunar$sendPreAuthEvent(CallbackInfo var1) {
      NetworkConnectionBridge var2;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var2 = (NetworkConnectionBridge)this.networkManager$v1_12;
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         var2 = (NetworkConnectionBridge)this.networkManager;
      } else {
         var2 = (NetworkConnectionBridge)this.networkManager$v1_7;
      }

      FogIterator.Data4 var3 = ThreadModuleDump63.method4().method81().method13(var2);
      if (var3 != null) {
         var1.cancel();
         GameProfile var4 = new GameProfile(var3.uuid(), var3.username());

         for (FogIterator.Data3 var8 : var3.method1()) {
            Property var9 = new Property(var8.name(), var8.value(), var8.signature());
            var4.getProperties().put(var8.name(), var9);
         }

         this.loginGameProfile = var4;
         this.currentLoginState = LoginState.READY_TO_ACCEPT;
      }
   }
}
