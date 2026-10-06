package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6Extension;
import com.moonsworth.lunar.bridge.glintcolorizer.Glintcolorizer;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.channel.ChannelFuture;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin extends MinecraftServer implements Glintcolorizer {
   @Shadow
   public ThreadLanServerPing lanServerPing;
   @Shadow
   public boolean isPublic;
   @Final
   @Shadow
   public Minecraft mc;

   @Shadow
   public abstract void setGameType(GameType var1);

   @Shadow
   public abstract void setGameType(net.minecraft.world.WorldSettings.GameType var1);

   @Override
   public boolean bridge$isReady() {
      return this.serverIsInRunLoop();
   }

   @Override
   public int bridge$getPublishedPort() {
      return this.lanServerPing != null ? Integer.parseInt(this.lanServerPing.address) : -1;
   }

   @Override
   public void bridge$publishWorldToLan(ItemcounterType2 var1, boolean var2, int var3) {
      try {
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            this.getNetworkSystem().addEndpoint$v1_12(null, var3);
         } else {
            this.getNetworkSystem().addLanEndpoint(null, var3);
         }

         LOGGER.info("Started on {}", var3);
         this.isPublic = true;
         this.lanServerPing = new ThreadLanServerPing(this.getMOTD(), var3 + "");
         this.lanServerPing.start();
         this.bridge$updateLanWorld(var1, var2);
      } catch (IOException var5) {
         Slayer.method7("Could not host server");
      }
   }

   @Override
   public void bridge$updateLanWorld(ItemcounterType2 var1, boolean var2) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.setGameType(GameType.getByID(var1.getId()));
      } else {
         this.setGameType(net.minecraft.world.WorldSettings.GameType.getByID(var1.getId()));
      }

      this.bridge$setAllowCheats(var2);
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.mc.thePlayer.setPermissionLevel$v1_12(var2 ? 4 : 0);
      }
   }

   @Override
   public void bridge$closeLanServer(int var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         List var3 = ThreadModuleDump63.MC_VERSION >= 1 ? this.playerList.playerEntityList : this.playerList.playerEntityList$v1_7;

         for (int var4 = var3.size() - 1; var4 >= 0; var4--) {
            EntityPlayerMP var5 = (EntityPlayerMP)var3.get(var4);
            if (!var5.getUniqueID().equals(var2.bridge$getUniqueID())) {
               String var6 = "World has been closed";
               if (ThreadModuleDump63.MC_VERSION >= 5) {
                  var5.playerNetServerHandler.disconnect$v1_12((IChatComponent)(new ChatComponentText(var6)));
               } else {
                  var5.playerNetServerHandler.kickPlayerFromServer(var6);
               }
            }
         }

         List var10 = ThreadModuleDump63.MC_VERSION >= 1 ? this.networkSystem.endpoints : this.networkSystem.endpoints$v1_7;

         for (int var11 = var10.size() - 1; var11 >= 0; var11--) {
            Object var12 = var10.get(var11);
            if (var12 instanceof ChannelFuture var7 && var7.channel().localAddress() instanceof InetSocketAddress var8 && var8.getPort() == var1) {
               var7.channel().close().syncUninterruptibly();
               var10.remove(var12);
            }
         }

         if (this.lanServerPing != null) {
            this.lanServerPing.interrupt();
            this.lanServerPing = null;
         }
      }
   }

   @Override
   public void bridge$setAllowCheats(boolean var1) {
      this.playerList.setCommandsAllowedForAll(var1);
   }

   @Override
   public ItemcounterType2 bridge$getGameType() {
      WorldInfo var1 = this.getEntityWorld().getWorldInfo();
      return ThreadModuleDump63.MC_VERSION >= 5 ? ItemcounterType2.getByID(var1.getGameType().getID()) : ItemcounterType2.getByID(var1.getGameType().getID());
   }

   @Override
   public boolean bridge$isAllowCheats() {
      return this.getEntityWorld().getWorldInfo().areCommandsAllowed();
   }

   @Override
   public ItemcounterType bridge$getDifficulty() {
      World var1 = this.getEntityWorld();
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return ItemcounterType.byId(var1.getWorldInfo().getDifficulty().getId());
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1
            ? ItemcounterType.byId(var1.getWorldInfo().getDifficulty().getDifficultyId())
            : ItemcounterType.byId(var1.difficultySetting$v1_7.getDifficultyId());
      }
   }

   @Override
   public void bridge$setDifficulty(ItemcounterType var1) {
      EnumDifficulty var2 = ThreadModuleDump63.MC_VERSION >= 5 ? EnumDifficulty.byId$v1_12(var1.id) : EnumDifficulty.getDifficultyEnum(var1.id);
      this.setDifficultyForAllWorlds(var2);
      WorldClient var3 = this.mc.theWorld;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var3.getWorldInfo().setDifficulty(var2);
      } else {
         var3.difficultySetting$v1_7 = var2;
      }
   }

   @Override
   public List<Bridge6Extension> bridge$getPlayers() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.playerList.playerEntityList : this.playerList.playerEntityList$v1_7;
   }

   @Override
   public void bridge$haltServer() {
   }

   @Override
   public File bridge$getWorldDirectory() {
      return this.anvilFile;
   }
}
