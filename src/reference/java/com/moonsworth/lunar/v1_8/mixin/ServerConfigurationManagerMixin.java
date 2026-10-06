package com.moonsworth.lunar.v1_8.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerConfigurationManager.class)
public abstract class ServerConfigurationManagerMixin {
   @Final
   @Shadow
   public MinecraftServer mcServer;
   @Final
   @Shadow
   public static Logger logger;

   public ServerConfigurationManagerMixin() {
   }

   @Shadow
   public abstract NBTTagCompound readPlayerDataFromFile(EntityPlayerMP player1);

   @Shadow
   public abstract void setPlayerGameTypeBasedOnOther(EntityPlayerMP player1, EntityPlayerMP player2, World world3);

   @Shadow
   public abstract void sendScoreboard(ServerScoreboard serverscoreboard1, EntityPlayerMP player2);

   @Shadow
   public abstract void sendChatMsg(IChatComponent text1);

   @Shadow
   public abstract void playerLoggedIn(EntityPlayerMP player1);

   @Shadow
   public abstract void updateTimeAndWeatherForPlayer(EntityPlayerMP player1, WorldServer world2);

   @Shadow
   public abstract MinecraftServer getServerInstance();

   @Shadow
   public abstract int getMaxPlayers();

   @Overwrite
   public void initializeConnectionToPlayer(NetworkManager networkmanager1, EntityPlayerMP player2, NetHandlerPlayServer nethandlerplayserver3) {
      GameProfile gameprofile4 = player2.getGameProfile();
      PlayerProfileCache playerprofilecache5 = this.mcServer.getPlayerProfileCache();
      GameProfile gameprofile6 = playerprofilecache5.getProfileByUUID(gameprofile4.getId());
      String text7 = gameprofile6 == null ? gameprofile4.getName() : gameprofile6.getName();
      playerprofilecache5.addEntry(gameprofile4);
      NBTTagCompound compound8 = this.readPlayerDataFromFile(player2);
      player2.setWorld(this.mcServer.worldServerForDimension(player2.dimension));
      player2.theItemInWorldManager.setWorld((WorldServer)player2.worldObj);
      String text9 = "local";
      if (networkmanager1.getRemoteAddress() != null) {
         text9 = networkmanager1.getRemoteAddress().toString();
      }

      logger.info(
         player2.getName() + "[" + text9 + "] logged in with entity id " + player2.getEntityId() + " at (" + player2.posX + ", " + player2.posY + ", " + player2.posZ + ")"
      );
      WorldServer world10 = this.mcServer.worldServerForDimension(player2.dimension);
      WorldInfo worldinfo11 = world10.getWorldInfo();
      BlockPos pos12 = world10.getSpawnPoint();
      this.setPlayerGameTypeBasedOnOther(player2, (EntityPlayerMP)null, world10);
      NetHandlerPlayServer nethandlerplayserver13 = new NetHandlerPlayServer(this.mcServer, networkmanager1, player2);
      nethandlerplayserver13.sendPacket(
         new S01PacketJoinGame(
            player2.getEntityId(),
            player2.theItemInWorldManager.getGameType(),
            worldinfo11.isHardcoreModeEnabled(),
            world10.provider.getDimensionId(),
            world10.getDifficulty(),
            this.getMaxPlayers(),
            worldinfo11.getTerrainType(),
            world10.getGameRules().getBoolean("reducedDebugInfo")
         )
      );
      nethandlerplayserver13.sendPacket(new S3FPacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(this.getServerInstance().getServerModName())));
      this.lunar$registerBukkitApiChannel(networkmanager1, player2, nethandlerplayserver13);
      nethandlerplayserver13.sendPacket(new S41PacketServerDifficulty(worldinfo11.getDifficulty(), worldinfo11.isDifficultyLocked()));
      nethandlerplayserver13.sendPacket(new S05PacketSpawnPosition(pos12));
      nethandlerplayserver13.sendPacket(new S39PacketPlayerAbilities(player2.capabilities));
      nethandlerplayserver13.sendPacket(new S09PacketHeldItemChange(player2.inventory.currentItem));
      player2.getStatFile().func_150877_d();
      player2.getStatFile().sendAchievements(player2);
      this.sendScoreboard((ServerScoreboard)world10.getScoreboard(), player2);
      this.mcServer.refreshStatusNextTick();
      ChatComponentTranslation chatcomponenttranslation14;
      if (!player2.getName().equalsIgnoreCase(text7)) {
         chatcomponenttranslation14 = new ChatComponentTranslation("multiplayer.player.joined.renamed", new Object[]{player2.getDisplayName(), text7});
      } else {
         chatcomponenttranslation14 = new ChatComponentTranslation("multiplayer.player.joined", new Object[]{player2.getDisplayName()});
      }

      chatcomponenttranslation14.getChatStyle().setColor(EnumChatFormatting.yellowColor);
      this.sendChatMsg(chatcomponenttranslation14);
      this.playerLoggedIn(player2);
      nethandlerplayserver13.setPlayerLocation(player2.posX, player2.posY, player2.posZ, player2.rotationYaw, player2.rotationPitch);
      this.updateTimeAndWeatherForPlayer(player2, world10);
      if (this.mcServer.getResourcePackUrl().length() > 0) {
         player2.loadResourcePack(this.mcServer.getResourcePackUrl(), this.mcServer.getResourcePackHash());
      }

      for (PotionEffect effect16 : player2.getActivePotionEffects()) {
         nethandlerplayserver13.sendPacket(new S1DPacketEntityEffect(player2.getEntityId(), effect16));
      }

      player2.addSelfToInternalCraftingInventory();
      if (compound8 != null && compound8.hasKey("Riding", 10)) {
         Entity entity17 = EntityList.createEntityFromNBT(compound8.getCompoundTag("Riding"), world10);
         if (entity17 != null) {
            entity17.forceSpawn = true;
            world10.spawnEntityInWorld(entity17);
            player2.mountEntity(entity17);
            entity17.forceSpawn = false;
         }
      }
   }

   private void lunar$registerBukkitApiChannel(NetworkManager networkmanager1, EntityPlayerMP player2, @Local NetHandlerPlayServer nethandlerplayserver3) {
      nethandlerplayserver3.sendPacket(new S3FPacketCustomPayload("REGISTER", new PacketBuffer(Unpooled.buffer()).writeString("lunarclient:pm")));
   }
}
