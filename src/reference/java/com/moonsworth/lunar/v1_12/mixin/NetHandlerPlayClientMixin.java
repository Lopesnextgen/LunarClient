package com.moonsworth.lunar.v1_12.mixin;

import io.netty.buffer.Unpooled;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = NetHandlerPlayClient.class, priority = 100)
public abstract class NetHandlerPlayClientMixin implements INetHandlerPlayClient {
   @Shadow
   public Minecraft client;
   @Shadow
   public WorldClient world;
   @Shadow
   public int currentServerMaxPlayers;
   @Final
   @Shadow
   public NetworkManager netManager;

   public NetHandlerPlayClientMixin() {
   }

   @Overwrite
   public void handleJoinGame(S01PacketJoinGame s01packetjoingame1) {
      PacketThreadUtil.checkThreadAndEnqueue(s01packetjoingame1, this, this.client);
      this.client.playerController = new PlayerControllerMP(this.client, (NetHandlerPlayClient)this);
      this.world = new WorldClient(
         (NetHandlerPlayClient)this,
         new WorldSettings(0L, s01packetjoingame1.getGameType(), false, s01packetjoingame1.isHardcoreMode(), s01packetjoingame1.getWorldType()),
         s01packetjoingame1.getDimension(),
         s01packetjoingame1.getDifficulty(),
         this.client.mcProfiler
      );
      this.client.gameSettings.difficulty = s01packetjoingame1.getDifficulty();
      this.client.loadWorld(this.world);
      this.client.player.dimension = s01packetjoingame1.getDimension();
      this.client.displayGuiScreen(new GuiDownloadTerrain());
      this.client.player.setEntityId(s01packetjoingame1.getPlayerId());
      this.currentServerMaxPlayers = s01packetjoingame1.getMaxPlayers();
      this.client.player.setReducedDebug(s01packetjoingame1.isReducedDebugInfo());
      this.client.playerController.setGameType(s01packetjoingame1.getGameType());
      this.client.gameSettings.sendSettingsToServer();
      this.netManager
         .sendPacket(new C17PacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(ClientBrandRetriever.getClientModName())));
   }
}
