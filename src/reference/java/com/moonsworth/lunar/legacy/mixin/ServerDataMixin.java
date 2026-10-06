package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.server.PinnedServerManager;
import com.moonsworth.lunar.client.network.server.PinnedServerManager.Type;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.LongConsumer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerData.ServerResourceMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerData.class)
public abstract class ServerDataMixin implements ServerDataBridge {
   @Shadow
   public String serverIP;
   @Shadow
   public long pingToServer;
   @Shadow
   public String serverIcon;
   @Shadow
   public String serverName;
   @Shadow
   public String populationInfo;
   @Unique
   private String lunar$lunarServer;
   @Unique
   private LongConsumer lunar$callback;
   @Unique
   private boolean lunar$pinnedClicked;
   @Unique
   boolean lunar$pinned;

   public ServerDataMixin() {
   }

   @Shadow
   public abstract void setResourceMode(ServerResourceMode serverresourcemode1);

   @Shadow
   public abstract boolean isLanServer$v1_7();

   @Shadow
   public abstract boolean isOnLAN();

   public String bridge$serverIP() {
      return this.serverIP;
   }

   public void bridge$setPingToServer(long value) {
      this.pingToServer = value;
   }

   public long bridge$getPingToServer() {
      return this.pingToServer;
   }

   public String getLunarServer() {
      return this.lunar$lunarServer;
   }

   public String bridge$getServerName() {
      return this.serverName;
   }

   public void setLunarServer(String text1) {
      this.lunar$lunarServer = text1;
   }

   public String bridge$getBase64Icon() {
      return this.serverIcon;
   }

   public String bridge$getPopulationInfo() {
      return this.populationInfo;
   }

   public void bridge$setPingCallback(LongConsumer longconsumer1) {
      this.lunar$callback = longconsumer1;
   }

   public LongConsumer bridge$getPingCallback() {
      return this.lunar$callback;
   }

   public void bridge$disableResourcePack() {
      this.setResourceMode(ServerResourceMode.DISABLED);
   }

   public void bridge$enableResourcePack() {
      this.setResourceMode(ServerResourceMode.ENABLED);
   }

   @Inject(method = "setResourceMode", at = @At("HEAD"))
   private void lunar$savePinnedPackChoice(ServerResourceMode serverresourcemode1, CallbackInfo callback2) {
      if (this.lunar$pinned && Client.method109() != null) {
         PinnedServerManager foghandler2113 = Client.method109().method59();
         switch (serverresourcemode1) {
            case ENABLED:
               foghandler2113.method10(this.serverIP, Type.ENABLED);
               break;
            case DISABLED:
               foghandler2113.method10(this.serverIP, Type.DISABLED);
               break;
            default:
               foghandler2113.method11(this.serverIP);
         }
      }
   }

   public boolean bridge$wasPinnedClicked() {
      return this.lunar$pinnedClicked;
   }

   public void bridge$setPinnedClicked(boolean flag1) {
      this.lunar$pinnedClicked = flag1;
   }

   public boolean bridge$isPinned() {
      return this.lunar$pinned;
   }

   public void bridge$setIsPinned(boolean flag1) {
      this.lunar$pinned = flag1;
   }

   public ServerDataBridge bridge$init(String text1) {
      return (ServerDataBridge)(new ServerData(this.serverName, text1, Ref.MC_VERSION == 0 ? this.isLanServer$v1_7() : this.isOnLAN()));
   }
}
