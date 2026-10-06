package com.moonsworth.lunar.v1_8.mixin;

import com.llamalad7.mixinextras.sugar.Local;
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
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NetHandlerPlayClient.class, priority = 100)
public abstract class NetHandlerPlayClientMixin implements INetHandlerPlayClient {
   @Shadow
   public Minecraft gameController;
   @Shadow
   public WorldClient clientWorldController;
   @Shadow
   public int currentServerMaxPlayers;
   @Final
   @Shadow
   public NetworkManager netManager;

   public NetHandlerPlayClientMixin() {
   }

   @Overwrite
   public void handleJoinGame(S01PacketJoinGame s01packetjoingame1) {
      PacketThreadUtil.checkThreadAndEnqueue(s01packetjoingame1, this, this.gameController);
      this.gameController.playerController = new PlayerControllerMP(this.gameController, (NetHandlerPlayClient)this);
      this.clientWorldController = new WorldClient(
         (NetHandlerPlayClient)this,
         new WorldSettings(0L, s01packetjoingame1.getGameType(), false, s01packetjoingame1.isHardcoreMode(), s01packetjoingame1.getWorldType()),
         s01packetjoingame1.getDimension(),
         s01packetjoingame1.getDifficulty(),
         this.gameController.mcProfiler
      );
      this.gameController.gameSettings.difficulty = s01packetjoingame1.getDifficulty();
      this.gameController.loadWorld(this.clientWorldController);
      this.gameController.thePlayer.dimension = s01packetjoingame1.getDimension();
      this.gameController.displayGuiScreen(new GuiDownloadTerrain((NetHandlerPlayClient)this));
      this.gameController.thePlayer.setEntityId(s01packetjoingame1.getEntityId());
      this.currentServerMaxPlayers = s01packetjoingame1.getMaxPlayers();
      this.gameController.thePlayer.setReducedDebug(s01packetjoingame1.isReducedDebugInfo());
      this.gameController.playerController.setGameType(s01packetjoingame1.getGameType());
      this.gameController.gameSettings.sendSettingsToServer();
      this.netManager
         .sendPacket(new C17PacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(ClientBrandRetriever.getClientModName())));
   }

   @Inject(
      method = "handleUpdateTileEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/server/S35PacketUpdateTileEntity;getTileEntityType()I"),
      cancellable = true
   )
   private void lunar$stopBadPackets(S35PacketUpdateTileEntity s35packetupdatetileentity1, CallbackInfo callback2, @Local TileEntity tileentity3) {
      if (tileentity3 == null) {
         callback2.cancel();
      }
   }
}
