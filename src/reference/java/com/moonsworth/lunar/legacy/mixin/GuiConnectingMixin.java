package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension69;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiConnecting.class)
public abstract class GuiConnectingMixin implements Bridge5Extension69 {
   @Shadow
   public NetworkManager_v1_12 networkManager$v1_12;
   @Shadow
   public NetworkManager networkManager;
   @Shadow
   public NetworkManager_v1_7 networkManager$v1_7;

   @Nullable
   @Override
   public ClientPacketListenerBridge bridge$getClientPacketListener() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         NetworkManager_v1_12 var1 = this.networkManager$v1_12;
         if (var1 != null && var1.packetListener instanceof ClientPacketListenerBridge var2) {
            return var2;
         }
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         NetworkManager var4 = this.networkManager;
         if (var4 != null && var4.packetListener instanceof ClientPacketListenerBridge var6) {
            return var6;
         }
      } else {
         NetworkManager_v1_7 var5 = this.networkManager$v1_7;
         if (var5 != null && var5.packetListener instanceof ClientPacketListenerBridge var7) {
            return var7;
         }
      }

      return null;
   }
}
