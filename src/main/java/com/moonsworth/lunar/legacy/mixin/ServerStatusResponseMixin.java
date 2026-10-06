package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ServerStatusResponseBridge;
import net.minecraft.network.ServerStatusResponse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerStatusResponse.class)
public abstract class ServerStatusResponseMixin implements ServerStatusResponseBridge {
   @Unique
   private String lunar$lunarServer;

   public ServerStatusResponseMixin() {
   }

   public String getLunarServer() {
      return this.lunar$lunarServer;
   }

   public void setLunarServer(String text) {
      this.lunar$lunarServer = text;
   }
}
