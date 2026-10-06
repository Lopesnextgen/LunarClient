package com.moonsworth.lunar.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump88;
import com.moonsworth.lunar.legacy.mixin.EntityPlayerMixin3;
import com.moonsworth.lunar.legacy.wrapper.NetHandlerPlayClientImpl;
import java.util.UUID;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.Session;
import net.minecraft.world.World;

public class EntityClientPlayerMPImpl extends EntityClientPlayerMP implements ThreadModuleDump54 {
   private ThreadModuleDump54.Type dummyPlayerType = ThreadModuleDump54.Type.SELF;
   private boolean renderNametag;
   private GameProfile customGameProfile = Minecraft.getMinecraft().getSession().getProfile();

   public EntityClientPlayerMPImpl(Minecraft var1, World var2) {
      super(
         var1,
         var2,
         new Session(Minecraft.getMinecraft().session.username, UUID.randomUUID().toString(), Minecraft.getMinecraft().session.token, "mojang"),
         new NetHandlerPlayClientImpl(var1, true),
         new StatFileWriter()
      );
      var1.skinManager.loadProfileTextures(this.customGameProfile, this, true);
   }

   public void entityInit() {
      super.entityInit();
      if (!this.dataWatcher.watchedObjects.containsKey(10)) {
         this.dataWatcher.addObject(10, "");
      }
   }

   public boolean getAlwaysRenderNameTagForRender() {
      return false;
   }

   public ItemStack getCurrentArmor(int var1) {
      return null;
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

   @Generated
   public GameProfile getCustomGameProfile() {
      return this.customGameProfile;
   }

   @Generated
   public void setCustomGameProfile(GameProfile var1) {
      this.customGameProfile = var1;
   }
}
