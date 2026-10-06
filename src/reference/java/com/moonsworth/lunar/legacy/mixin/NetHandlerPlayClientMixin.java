package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.NetworkConnectionBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityStatusUpdate;
import com.moonsworth.lunar.client.event.combat.EventTotemActivation;
import com.moonsworth.lunar.client.event.entity.EntitySpawnEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldTimeUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.ServerBrandEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.event.mixin.gui.PluginMessageEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.Packet_v1_7;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.client.CPacketResourcePackStatus.Action;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IThreadListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(NetHandlerPlayClient.class)
public abstract class NetHandlerPlayClientMixin implements ClientPacketListenerBridge, INetHandlerPlayClient {
   @Shadow
   public Minecraft client;
   @Shadow
   public WorldClient world;
   @Shadow
   public List playerInfoList$v1_7;
   @Shadow
   public Map playerInfoMap;
   @Final
   @Shadow
   public NetworkManager_v1_12 netManager$v1_12;
   @Final
   @Shadow
   public NetworkManager netManager;
   @Final
   @Shadow
   public NetworkManager_v1_7 netManager$v1_7;

   @Shadow
   public abstract Collection<NetworkPlayerInfo> getPlayerInfoMap();

   @Shadow
   public abstract void sendPacket(Packet<?> var1);

   @Shadow
   public abstract void addToSendQueue(Packet_v1_7 var1);

   @Shadow
   public abstract void addToSendQueue(Packet var1);

   @Inject(method = "handleCustomPayload(Lnet/minecraft/network/play/server/SPacketCustomPayload;)V", at = @At("HEAD"), cancellable = true)
   private void impl$handleCustomPayload$HEAD(S3FPacketCustomPayload var1, CallbackInfo var2) {
      if (!var1.channel.startsWith("MC|")) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            PacketThreadUtil.checkThreadAndEnqueue((Packet)var1, this, (IThreadListener)this.client);
         }

         byte[] var3;
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            PacketBuffer var4 = var1.getBufferData();
            var3 = new byte[var4.readableBytes()];
            var4.readBytes(var3);
         } else {
            var3 = var1.field_149020_k;
         }

         PluginMessageEvent var5 = ClientEventBus.method29().method12(PluginMessageEvent.class, () -> new PluginMessageEvent(var1.channel, var3));
         if (var5 != null && var5.isCancelled()) {
            var2.cancel();
         }
      }
   }

   @Inject(method = "handleCustomPayload(Lnet/minecraft/network/play/server/SPacketCustomPayload;)V", at = @At("RETURN"))
   private void impl$handleCustomPayload$RETURN(S3FPacketCustomPayload var1, CallbackInfo var2) {
      if (var1.channel.startsWith("MC|")) {
         ClientEventBus.method29().method12(ServerBrandEvent.class, () -> new ServerBrandEvent(var1.channel));
      }
   }

   @Inject(method = "handleJoinGame(Lnet/minecraft/network/play/server/SPacketJoinGame;)V", at = @At("RETURN"))
   private void impl$handleJoinGame(S01PacketJoinGame var1, CallbackInfo var2) {
      ClientEventBus.method29().method12(ServerJoinEvent.class, ServerJoinEvent::new);
   }

   @Inject(method = "handleSpawnPlayer", at = @At("RETURN"))
   private void lunar$handleSpawnPlayer(S0CPacketSpawnPlayer var1, CallbackInfo var2) {
      Entity var3 = this.world.getEntityByID(var1.field_179775_c);
      if (var3 != null) {
         ClientEventBus.method29().method12(EntitySpawnEvent.class, () -> new EntitySpawnEvent((BridgeExtension)var3, (Itemcounter6)this.world));
      }
   }

   @Inject(method = "handleSpawnMob", at = @At("RETURN"))
   private void lunar$handleSpawnMob(S0FPacketSpawnMob var1, CallbackInfo var2) {
      Entity var3 = this.world.getEntityByID(var1.field_179775_c);
      if (var3 != null) {
         ClientEventBus.method29().method12(EntitySpawnEvent.class, () -> new EntitySpawnEvent((BridgeExtension)var3, (Itemcounter6)this.world));
      }
   }

   @Inject(method = "handleEntityMetadata", at = @At("RETURN"))
   private void lunar$handleEntityMetadata(S1CPacketEntityMetadata var1, CallbackInfo var2) {
      Entity var3 = this.world.getEntityByID(var1.field_179775_c);
      if (var3 != null) {
         ClientEventBus.method29().method12(EntitySpawnEvent.class, () -> new EntitySpawnEvent((BridgeExtension)var3, (Itemcounter6)this.world));
      }
   }

   @Inject(method = "handleEntityStatus(Lnet/minecraft/network/play/server/SPacketEntityStatus;)V", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
   private void lunar$handleEntityStatus(S19PacketEntityStatus var1, CallbackInfo var2, Entity var3) {
      byte var4 = var1.logicOpcode;
      if (var3 != null && var4 != 21 && var4 != 35) {
         ClientEventBus.method29().method12(EventEntityStatusUpdate.class, () -> new EventEntityStatusUpdate((BridgeExtension)var3, var1.logicOpcode));
      }
   }

   @Inject(
      method = "handleEntityStatus(Lnet/minecraft/network/play/server/SPacketEntityStatus;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/particle/ParticleManager;emitParticleAtEntity$v1_12(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/EnumParticleTypes;I)V"
      )
   )
   @Annotation2(min = 5)
   private void lunar$handleEntityTotem(S19PacketEntityStatus var1, CallbackInfo var2, @Local Entity var3) {
      if (var3 != Minecraft.getMinecraft().thePlayer) {
         ClientEventBus.method29().method12(EventTotemActivation.class, () -> new EventTotemActivation((BridgeExtension)var3));
      }
   }

   @Override
   public void bridge$addToSendQueue(Bridge3_21 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.sendPacket((Packet<?>)var1);
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.addToSendQueue((Packet)var1);
      } else {
         this.addToSendQueue((Packet_v1_7)var1);
      }
   }

   @Override
   public String bridge$getRegisterPacketName() {
      return "REGISTER";
   }

   @Override
   public String bridge$getLCChannelName() {
      return "Lunar-Client";
   }

   @Override
   public void bridge$quit() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.netManager$v1_12.closeChannel((IChatComponent)(new ChatComponentText("Quitting")));
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.netManager.closeChannel((IChatComponent)(new ChatComponentText("Quitting")));
      } else {
         this.netManager$v1_7.closeChannel((IChatComponent)(new ChatComponentText("Quitting")));
      }
   }

   @Override
   public void bridge$transferQuit() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         if (this.netManager$v1_12.channel.isOpen()) {
            this.netManager$v1_12.channel.close().awaitUninterruptibly(2000L);
            this.netManager$v1_12.terminationReason = (IChatComponent)(new ChatComponentText("Quitting"));
         }
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         if (this.netManager.channel.isOpen()) {
            this.netManager.channel.close().awaitUninterruptibly(2000L);
            this.netManager.terminationReason = (IChatComponent)(new ChatComponentText("Quitting"));
         }
      } else if (this.netManager$v1_7.channel.isOpen()) {
         this.netManager$v1_7.channel.close().awaitUninterruptibly(2000L);
         this.netManager$v1_7.terminationReason = (IChatComponent)(new ChatComponentText("Quitting"));
      }
   }

   @Overwrite
   public void handleTimeUpdate(S03PacketTimeUpdate var1) {
      if (this.client.theWorld != null) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            PacketThreadUtil.checkThreadAndEnqueue((Packet)var1, this, (IThreadListener)this.client);
         }

         EventWorldTimeUpdate var2 = ClientEventBus.method29().method12(EventWorldTimeUpdate.class, () -> new EventWorldTimeUpdate(var1.worldTime, var1.totalWorldTime));
         if (var2 == null || !var2.isCancelled()) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               this.client.theWorld.setTotalWorldTime(var1.totalWorldTime);
            } else {
               this.client.theWorld.func_82738_a$v1_7(var1.totalWorldTime);
            }

            this.client.theWorld.setWorldTime(var1.worldTime);
         }
      }
   }

   @Inject(method = "cleanup", at = @At("HEAD"))
   private void lunar$cleanup(CallbackInfo var1) {
      com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent.method1();
   }

   @Inject(method = {"onDisconnect$v1_7", "onDisconnect$v1_8"}, at = @At("HEAD"))
   private void lunar$onDisconnect(CallbackInfo var1) {
      ThreadModuleDump63.method3().bridge$submit(com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent::method1);
   }

   @Annotation2(min = 1)
   @Inject(method = "handleResourcePack$v1_8", at = @At("HEAD"), cancellable = true)
   public void impl$handleResourcePack(S48PacketResourcePackSend var1, CallbackInfo var2) {
      String var3 = var1.getURL();

      try {
         URI var4 = new URI(var3);
         String var5 = var4.getScheme();
         boolean var6 = "level".equals(var5);
         if (!"http".equals(var5) && !"https".equals(var5) && !var6) {
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               this.netManager$v1_12.sendPacket(new C19PacketResourcePackStatus(Action.FAILED_DOWNLOAD));
            } else {
               this.netManager.sendPacket(new C19PacketResourcePackStatus(var1.getHash(), Action.FAILED_DOWNLOAD));
            }

            throw new URISyntaxException(var3, "Wrong protocol");
         } else {
            var3 = URLDecoder.decode(var3.substring("level://".length()), StandardCharsets.UTF_8);
            if (var6 && (var3.contains("..") || !var3.endsWith("/resources.zip"))) {
               System.out.println("Malicious server tried to access " + var3);
               EntityPlayerSP var7 = Minecraft.getMinecraft().thePlayer;
               if (var7 != null) {
                  if (ThreadModuleDump63.MC_VERSION >= 5) {
                     IChatComponent var8 = (IChatComponent)(
                        new ChatComponentText(
                           AdventureChatFormatting.RED
                              + AdventureChatFormatting.BOLD.toString()
                              + "[WARNING] The current server has attempted to be malicious but we have stopped them."
                        )
                     );
                     Minecraft.getMinecraft().addScheduledTask(() -> var7.sendMessage$v1_12(var8));
                  } else {
                     IChatComponent var11 = (IChatComponent)(
                        new ChatComponentText(
                           AdventureChatFormatting.RED
                              + AdventureChatFormatting.BOLD.toString()
                              + "[WARNING] The current server has attempted to be malicious but we have stopped them."
                        )
                     );
                     Minecraft.getMinecraft().addScheduledTask(() -> var7.addChatMessage(var11));
                  }
               }

               throw new URISyntaxException(var3, "Invalid levelstorage resourcepack path");
            }
         }
      } catch (URISyntaxException var9) {
         var2.cancel();
         var2.cancel();
      }
   }

   @Override
   public NetworkConnectionBridge bridge$getNetworkManager() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return (NetworkConnectionBridge)this.netManager$v1_12;
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? (NetworkConnectionBridge)this.netManager : (NetworkConnectionBridge)this.netManager$v1_7;
      }
   }

   @Override
   public List<Bridge2_33> bridge$getPlayerInfoMap() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? new ArrayList<>(this.getPlayerInfoMap()) : this.playerInfoList$v1_7;
   }

   @Override
   public List<Bridge2_33> bridge$getSortedPlayerInfoMap() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return GuiPlayerTabOverlay.field_175252_a.sortedCopy(this.getPlayerInfoMap());
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Inject(method = "handleWindowItems", at = @At("HEAD"), cancellable = true)
   public void impl$handleWindowItems(S30PacketWindowItems var1, CallbackInfo var2) {
      Object var3;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var3 = this.client.thePlayer;
      } else {
         var3 = this.client.thePlayer$v1_7;
      }

      if (var3 == null) {
         var2.cancel();
      }
   }

   @Inject(method = "handleSetSlot", at = @At("HEAD"), cancellable = true)
   public void impl$handleSetSlot(S2FPacketSetSlot var1, CallbackInfo var2) {
      Object var3;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var3 = this.client.thePlayer;
      } else {
         var3 = this.client.thePlayer$v1_7;
      }

      if (var3 == null) {
         var2.cancel();
      }
   }

   @Inject(method = "handleEntityHeadLook", at = @At("HEAD"), cancellable = true)
   private void lunar$handleEntityHeadLook(S19PacketEntityHeadLook var1, CallbackInfo var2) {
      if (this.world == null) {
         var2.cancel();
      }
   }

   @Override
   public Bridge2_33 bridge$getPlayerInfo(UUID var1) {
      return (Bridge2_33)this.playerInfoMap.get(var1);
   }

   @Annotation2(min = 1)
   @Inject(method = {"handleJoinGame", "handleRespawn"}, at = @At("HEAD"))
   private void lunar$captureSpectatedEntity$v1_8(CallbackInfo var1) {
      ThreadModuleDump63.method3().bridge$setSpectatedEntity(null);
   }

   @Annotation2(min = 1)
   @WrapWithCondition(
      method = "handleCamera$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setRenderViewEntity$v1_8(Lnet/minecraft/entity/Entity;)V")
   )
   private boolean lunar$captureSpectatedEntity$v1_8(Minecraft var1, Entity var2) {
      ThreadModuleDump63.method3().bridge$setSpectatedEntity(var2 == ThreadModuleDump63.method7() ? null : (BridgeExtension)var2);
      return true;
   }

   @Override
   public String bridge$getServerBrand() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.client.thePlayer.clientBrand : "";
   }
}
