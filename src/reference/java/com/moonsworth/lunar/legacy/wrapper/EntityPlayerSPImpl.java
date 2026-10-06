package com.moonsworth.lunar.legacy.wrapper;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump88;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.mixin.EntityPlayerMixin3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.World;

@Annotation2(min = 1)
public class EntityPlayerSPImpl extends EntityPlayerSP implements ThreadModuleDump54 {
   private ThreadModuleDump54.Type dummyPlayerType = ThreadModuleDump54.Type.SELF;
   private boolean renderNametag;

   @Annotation2(min = 5)
   private EntityPlayerSPImpl(Minecraft var1, World var2, NetHandlerPlayClient var3, StatFileWriter var4, boolean var5) {
      super(var1, var2, var3, var4, null);
   }

   @Annotation2(1)
   private EntityPlayerSPImpl(Minecraft var1, World var2, NetHandlerPlayClient var3, StatFileWriter var4) {
      super(var1, var2, var3, var4);
   }

   public static EntityPlayerSPImpl method1(Minecraft var0, World var1, NetHandlerPlayClient var2, StatFileWriter var3) {
      return ThreadModuleDump63.MC_VERSION >= 5 ? new EntityPlayerSPImpl(var0, var1, var2, var3, true) : new EntityPlayerSPImpl(var0, var1, var2, var3);
   }

   public void entityInit() {
      super.entityInit();
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         if (!this.dataManager.entries$v1_12.containsKey(10)) {
            this.dataManager.register(new DataParameter(10, DataSerializers.STRING), "");
         }
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         if (!this.dataManager.watchedObjects.containsKey(10)) {
            this.dataManager.addObject(10, "");
         }
      } else if (!this.dataManager.watchedObjects$v1_7.containsKey(10)) {
         this.dataManager.addObject(10, "");
      }
   }

   @Annotation2(min = 1)
   public boolean getAlwaysRenderNameTagForRender() {
      return false;
   }

   @Annotation2(1)
   public ItemStack getCurrentArmor(int var1) {
      return null;
   }

   public boolean getAlwaysRenderNameTag() {
      return false;
   }

   public boolean hasCustomName() {
      return false;
   }

   public boolean isUser() {
      return false;
   }

   public boolean isSpectatedByPlayer(EntityPlayerMP var1) {
      return false;
   }

   public boolean isSpectator() {
      return false;
   }

   public NetworkPlayerInfo getPlayerInfo() {
      if (this.playerInfo == null) {
         this.playerInfo = new NetworkPlayerInfoImpl(this, this.getGameProfile());
      }

      return this.playerInfo;
   }

   @Override
   public void incrementTicksExisted() {
      this.ticksExisted++;
   }

   @Override
   public ThreadModuleDump54.Type getDummyPlayerType() {
      return this.dummyPlayerType;
   }

   @Override
   public void setDummyPlayerType(ThreadModuleDump54.Type var1) {
      this.dummyPlayerType = var1;
   }

   @Override
   public void setRenderNametag(boolean var1) {
      this.renderNametag = var1;
   }

   @Override
   public boolean shouldRenderNametag() {
      return this.renderNametag;
   }

   @Override
   public void setGameProfile(GameProfile var1) {
      ((EntityPlayerMixin3)this).bridge$setGameProfile(var1);
      if (this instanceof ThreadModuleDump88 var2) {
         var2.lunar$onNameTagUpdate();
      }
   }
}
