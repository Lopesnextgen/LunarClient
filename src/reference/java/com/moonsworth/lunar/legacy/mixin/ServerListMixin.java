package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.ServerListBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.server.PinnedServerManager;
import com.moonsworth.lunar.client.network.server.PinnedServer;
import java.util.List;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerList.class)
public abstract class ServerListMixin implements ServerListBridge {
   @Shadow
   public List<ServerData> servers;

   public ServerListMixin() {
   }

   @Shadow
   public abstract ServerData getServerData(int index1);

   @Shadow
   public abstract void loadServerList();

   @Shadow
   public abstract void saveServerList();

   @Inject(method = "swapServers(II)V", at = @At("HEAD"), cancellable = true)
   private void lunar$swapServers(int number1, int number2, CallbackInfo callback3) {
      if (!this.bridge$canSwapServers(number1, number2)) {
         callback3.cancel();
      }
   }

   @Inject(method = "loadServerList", at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V", shift = Shift.AFTER))
   private void lunar$addPinnedServers(CallbackInfo callback1) {
      PinnedServerManager foghandler2112 = Client.method109().method59();

      for (PinnedServer keystrokes24 : foghandler2112.method10().values()) {
         ServerData serverdata5 = new ServerData(keystrokes24.name(), keystrokes24.method2(), false);
         ServerDataBridge bridge3_196 = (ServerDataBridge)serverdata5;
         bridge3_196.bridge$setIsPinned(true);
         foghandler2112.method12(bridge3_196);
         this.servers.add(serverdata5);
      }
   }

   @WrapWithCondition(method = "saveServerList", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagList;appendTag(Lnet/minecraft/nbt/NBTBase;)V"))
   private boolean lunar$dontSavePinnedServer(NBTTagList list1, NBTBase nbt2, @Local ServerData serverdata3) {
      return !((ServerDataBridge)serverdata3).bridge$isPinned();
   }

   @Override
   public void bridge$load() {
      this.loadServerList();
   }

   @Override
   public void bridge$add(ServerDataBridge bridge3_191) {
      this.servers.add((ServerData)bridge3_191);
   }

   @Override
   public void bridge$save() {
      this.saveServerList();
   }

   @Override
   public boolean bridge$containsUnpinnedAddress(String text1) {
      for (ServerData serverdata3 : this.servers) {
         ServerDataBridge bridge3_194 = (ServerDataBridge)serverdata3;
         if (!bridge3_194.bridge$isPinned() && text1.equalsIgnoreCase(bridge3_194.bridge$serverIP())) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean bridge$canSwapServers(int number1, int number2) {
      ServerDataBridge bridge3_193 = (ServerDataBridge)this.getServerData(number1);
      ServerDataBridge bridge3_194 = (ServerDataBridge)this.getServerData(number2);
      return !bridge3_193.bridge$isPinned() && !bridge3_194.bridge$isPinned();
   }
}
