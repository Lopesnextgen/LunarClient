package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.hitbox.Hitbox;
import com.moonsworth.lunar.bridge.hitbox.Hitbox2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent;
import com.moonsworth.lunar.client.event.combat.PlayerKnockbackEvent;
import com.moonsworth.lunar.client.event.entity.EventEntityMove;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventEntityItemSpawnLegacy;
import com.moonsworth.lunar.client.event.mixin.gui.SlotUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.BossBarUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.TitleEvent;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectReasonEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerTickEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump88;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.network.play.server.SPacketPlayerListItem.AddPlayerData;
import net.minecraft.network.play.server.SPacketTitle.Type;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.IThreadListener;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public abstract class NetHandlerPlayClientEventMixin implements ClientPacketListenerBridge, INetHandlerPlayClient {
   @Shadow
   public WorldClient world;
   @Shadow
   public Minecraft client;

   @Annotation2(min = 1)
   @Inject(
      method = "handleCloseWindow",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/PacketThreadUtil;checkThreadAndEnqueue(Lnet/minecraft/network/Packet;Lnet/minecraft/network/INetHandler;Lnet/minecraft/util/IThreadListener;)V",
         shift = Shift.AFTER
      ),
      cancellable = true
   )
   @Dynamic
   private void lunar$dontCloseChat(S2EPacketCloseWindow var1, CallbackInfo var2) {
      if (Client.method109().method40().method47().method40().get() && Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
         var2.cancel();
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "handleCloseWindow", at = @At("HEAD"), cancellable = true)
   private void lunar$dontCloseChat$v1_7(S2EPacketCloseWindow var1, CallbackInfo var2) {
      if (Client.method109().method40().method47().method40().get() && Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
         var2.cancel();
      }
   }

   @Inject(method = "handleDisconnect", at = @At("HEAD"))
   private void lunar$onKickedEvent(S40PacketDisconnect var1, CallbackInfo var2) {
      String var3;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var3 = var1.reason.getUnformattedText();
      } else {
         var3 = var1.reason$v1_7.getUnformattedText();
      }

      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method12(DisconnectReasonEvent.class, () -> new DisconnectReasonEvent(var3)));
   }

   @Inject(method = "handleSpawnObject", at = @At("RETURN"))
   private void lunar$onProjectileLaunchEvent(S0EPacketSpawnObject var1, CallbackInfo var2) {
      Entity var3 = this.world.getEntityByID(var1.field_179775_c);
      if (var3 instanceof IProjectile) {
         Entity var5 = this.world.getEntityByID(var1.field_149020_k);
         if (var5 == null) {
            var5 = this.world.getEntityByID(var1.field_149020_k - 1);
         }

         Entity var6 = var5;
         ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileLaunch.class, () -> new ProjectileBaseEvent.EventProjectileLaunch((BridgeExtension2_3)var3, (BridgeExtension)var6));
      } else if (var3 instanceof EntityFishHook var4) {
         ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileLaunch.class, () -> new ProjectileBaseEvent.EventProjectileLaunch((BridgeExtension2_3)var3, (BridgeExtension)var4.angler));
      }
   }

   @Inject(method = "handleSpawnObject", at = @At("HEAD"), cancellable = true)
   private void lunar$skipIfWorldUnloaded(S0EPacketSpawnObject var1, CallbackInfo var2) {
      if (this.world == null) {
         var2.cancel();
      }
   }

   @Annotation2(max = 0)
   @WrapWithCondition(
      method = "handleEntityEquipment",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setCurrentItemOrArmor$v1_7(ILnet/minecraft/item/ItemStack;)V")
   )
   @Dynamic
   private boolean lunar$keepWithinBounds(Entity var1, int var2, ItemStack var3) {
      ItemStack[] var4 = var1.getInventory();
      if (var2 < 0 || var4 == null) {
         return false;
      } else {
         return var1 instanceof EntityOtherPlayerMP ? var2 < var4.length + 1 : var2 < var4.length;
      }
   }

   @WrapOperation(
      method = "handleStatistics",
      at = {
            @At(
               value = "INVOKE",
               target = "Lnet/minecraft/stats/StatisticsManager;func_150873_a(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/stats/StatBase;I)V"
            ),
            @At(
               value = "INVOKE",
               target = "Lnet/minecraft/stats/StatisticsManager;unlockAchievement$v1_8(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/stats/StatBase;I)V"
            )
      },
      require = 1
   )
   @Dynamic
   private void lunar$useCustomSetValue(net.minecraft.stats.StatFileWriter var1, EntityPlayer var2, StatBase var3, int var4, Operation<Void> var5) {
      ((Hitbox)var1).bridge$setValueFromPacket((Bridge6_10)var2, (Hitbox2)var3, var4);
   }

   @Annotation2(min = 5)
   @Inject(
      method = "handleCollectItem",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;removeEntityFromWorld(I)Lnet/minecraft/entity/Entity;")
   )
   private void lunar$onItemPickup(S0DPacketCollectItem var1, CallbackInfo var2, @Local EntityLivingBase var3, @Local Entity var4) {
      if (var3 == ThreadModuleDump63.method7() && var4 instanceof EntityItem var5) {
         ItemStack var6 = var5.getItem();
         Item var7 = var6.getItem();
         if (var7 != Items.AIR$v1_12) {
            StatBase var8 = StatList.getObjectsPickedUpStats$v1_12(var7);
            if (var8 != null) {
               ThreadModuleDump63.method7().bridge$getStatsCounter().bridge$increment(ThreadModuleDump63.method7(), (Hitbox2)var8, var1.getAmount$v1_12());
            }
         }
      }
   }

   @Annotation2(min = 1)
   @WrapMethod(method = "handleTitle$v1_8")
   private void lunar$onTitle(S45PacketTitle var1, Operation<Void> var2) {
      Bridge2_42 var3 = (Bridge2_42)var1.getMessage();
      Type var4 = var1.getType();
      if (var3 != null && (var4 == Type.TITLE || var4 == Type.SUBTITLE)) {
         PacketThreadUtil.checkThreadAndEnqueue(var1, this, (IThreadListener)this.client);
         TitleEvent var5 = ClientEventBus.method29()
            .method12(TitleEvent.class, () -> new TitleEvent(AdventureTextBridge.asAdventure(var3), TitleEvent.Type.SERVER, var4 == Type.SUBTITLE));
         if (var5 != null && var5.isCancelled()) {
            return;
         }
      }

      var2.call(new Object[]{var1});
   }

   @Inject(
      method = "handleCollectItem",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;removeEntityFromWorld(I)Lnet/minecraft/entity/Entity;")
   )
   private void lunar$onItemPickup$event(S0DPacketCollectItem var1, CallbackInfo var2) {
      int var3 = ThreadModuleDump63.MC_VERSION <= 0 ? var1.func_149354_c$v1_7() : var1.getCollectedItemEntityID();
      if (this.world.getEntityByID(var3) instanceof EntityItem var5) {
         ItemStack var6 = ThreadModuleDump63.MC_VERSION <= 1 ? var5.getEntityItem() : var5.getItem();
         ClientEventBus.method29().method12(EventEntityItemSpawnLegacy.class, () -> new EventEntityItemSpawnLegacy((ItemStackBridge)var6, (BridgeExtension)var5));
      }
   }

   @Inject(method = "handleTeams", at = @At("TAIL"))
   private void lunar$onUpdateScoreboard(S3EPacketTeams var1, CallbackInfo var2) {
      int var3 = ThreadModuleDump63.MC_VERSION <= 0 ? var1.func_149307_h$v1_7() : var1.getAction();
      if (var3 == 2) {
         ClientEventBus.method29().method12(ScoreboardUpdateEvent.class, () -> new ScoreboardUpdateEvent((Lighting4)this.world.getScoreboard()));
         ScorePlayerTeam var4 = this.world.getScoreboard().getTeam(var1.name);
         if (var4 != null) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               for (String var6 : var4.membershipSet) {
                  if (this.world.getPlayerEntityByName(var6) instanceof ThreadModuleDump88 var7) {
                     var7.lunar$onNameTagUpdate();
                  }
               }
            } else {
               for (Object var11 : var4.membershipSet$v1_7) {
                  if (var11 instanceof String var12 && this.world.getPlayerEntityByName(var12) instanceof ThreadModuleDump88 var13) {
                     var13.lunar$onNameTagUpdate();
                  }
               }
            }
         }
      }
   }

   @Redirect(
      method = "handleSetSlot",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;setItemStack(Lnet/minecraft/item/ItemStack;)V")
   )
   private void lunar$onSetItemStack(InventoryPlayer var1, ItemStack var2, S2FPacketSetSlot var3) {
      ItemStackBridge var4 = (ItemStackBridge)var1.getItemStack();
      var1.setItemStack(var2);
      ClientEventBus.method29().method12(SlotUpdateEvent.class, () -> new SlotUpdateEvent(var3.theSlot, var4, (ItemStackBridge)var3.item));
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "handleSetSlot",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;setInventorySlotContents(ILnet/minecraft/item/ItemStack;)V")
   )
   private void lunar$onSetInventorySlot(InventoryPlayer var1, int var2, ItemStack var3, S2FPacketSetSlot var4) {
      ItemStackBridge var5 = (ItemStackBridge)var1.getStackInSlot(var2);
      var1.setInventorySlotContents(var2, var3);
      ClientEventBus.method29().method12(SlotUpdateEvent.class, () -> new SlotUpdateEvent(var4.theSlot, var5, (ItemStackBridge)var4.item));
   }

   @Redirect(
      method = "handleSetSlot",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Container;putStackInSlot(ILnet/minecraft/item/ItemStack;)V")
   )
   private void lunar$onSetContainerSlot(Container var1, int var2, ItemStack var3, S2FPacketSetSlot var4) {
      ItemStackBridge var5 = (ItemStackBridge)var1.getSlot(var2).getStack();
      var1.putStackInSlot(var2, var3);
      ClientEventBus.method29().method12(SlotUpdateEvent.class, () -> new SlotUpdateEvent(var4.theSlot, var5, (ItemStackBridge)var4.item));
   }

   @Annotation2(max = 0)
   @Inject(method = "handlePlayerPosLook", at = @At("HEAD"))
   private void lunar$onTeleportPlayer$pre$v1_7(S08PacketPlayerPosLook var1, CallbackInfo var2) {
      ThreadModuleDump63.method3()
         .bridge$submit(
            () -> ClientEventBus.method29()
               .method12(
                  com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPreEvent.class,
                  () -> new com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPreEvent(var1.x, var1.y, var1.z, var1.yaw, var1.pitch)
               )
         );
   }

   @Annotation2(min = 1)
   @Inject(
      method = "handlePlayerPosLook",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/PacketThreadUtil;checkThreadAndEnqueue(Lnet/minecraft/network/Packet;Lnet/minecraft/network/INetHandler;Lnet/minecraft/util/IThreadListener;)V",
         shift = Shift.AFTER
      )
   )
   @Dynamic
   private void lunar$onTeleportPlayer$pre$v1_8(S08PacketPlayerPosLook var1, CallbackInfo var2) {
      ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPreEvent.class,
            () -> new com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPreEvent(var1.x, var1.y, var1.z, var1.yaw, var1.pitch)
         );
   }

   @Inject(method = "handlePlayerPosLook", at = @At("TAIL"))
   private void lunar$onTeleportPlayer$post(S08PacketPlayerPosLook var1, CallbackInfo var2) {
      ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPostEvent.class,
            () -> {
               Object var0 = ThreadModuleDump63.MC_VERSION <= 0 ? Minecraft.getMinecraft().thePlayer$v1_7 : Minecraft.getMinecraft().thePlayer;
               return new com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPostEvent(
                  ((EntityPlayerSP)var0).posX,
                  ((EntityPlayerSP)var0).posY,
                  ((EntityPlayerSP)var0).posZ,
                  ((EntityPlayerSP)var0).rotationYaw,
                  ((EntityPlayerSP)var0).rotationPitch
               );
            }
         );
   }

   @Inject(method = "handlePlayerListItem", at = @At("RETURN"))
   private void lunar$eventUpdateTabList(CallbackInfo var1) {
      ClientEventBus.method29()
         .method12(com.moonsworth.lunar.client.event.mixin.gui.TabListUpdateEvent.class, com.moonsworth.lunar.client.event.mixin.gui.TabListUpdateEvent::new);
   }

   @Annotation2(min = 1)
   @Inject(method = "handlePlayerListItem", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
   private void lunar$syncPlayerInfo(S38PacketPlayerListItem var1, CallbackInfo var2, @Local NetworkPlayerInfo var3, @Local AddPlayerData var4) {
      if (this.world.getPlayerEntityByUUID(var3.gameProfile.getId()) instanceof AbstractClientPlayer var5) {
         var5.playerInfo = var3;
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "handleUpdateBossInfo$v1_12", at = @At("RETURN"))
   private void lunar$eventUpdateBossInfo$v1_12(SPacketUpdateBossInfo var1, CallbackInfo var2) {
      ClientEventBus.method29().method12(BossBarUpdateEvent.class, () -> new BossBarUpdateEvent(AdventureTextBridge.asAdventure((Bridge2_42)var1.name)));
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

   @Inject(method = "handleMaps", at = @At("RETURN"))
   private void lunar$mapUpdateEvent(S34PacketMaps var1, CallbackInfo var2) {
      Itemcounter2_3 var3 = (Itemcounter2_3)ItemMap.loadMapData(var1.getMapId(), this.world);
      if (var3 != null) {
         ClientEventBus.method29().method12(com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventMapUpdateLegacy.class, () -> {
            List var2x = var3.bridge$getMapDecorations().values().stream().toList();
            return new com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventMapUpdateLegacy(var1.getMapId(), var3, true, var2x);
         });
      }
   }

   @Inject(method = "handleEntityMovement", at = @At("TAIL"))
   private void lunar$onEntityMovement(S14PacketEntity var1, CallbackInfo var2) {
      ClientEventBus.method29().method12(EventEntityMove.class, () -> {
         Entity var2x = this.world.getEntityByID(var1.field_179775_c);
         return new EventEntityMove((BridgeExtension)var2x, var1.field_149069_g);
      });
   }

   @Inject(method = "handleEntityVelocity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setVelocity(DDD)V", shift = Shift.AFTER))
   private void lunar$handleEntityVelocity(S12PacketEntityVelocity var1, CallbackInfo var2, @Local Entity var3) {
      if (var3 instanceof EntityPlayerSP) {
         ClientEventBus.method29().method12(PlayerKnockbackEvent.class, () -> new PlayerKnockbackEvent((Bridge5Extension_5)var3));
      }
   }

   @Annotation2(1)
   @Inject(method = "handleConfirmTransaction", at = @At("RETURN"))
   private void lunar$handleConfirmTransaction(S32PacketConfirmTransaction var1, CallbackInfo var2) {
      if (!GuiRewindhandlersHandler23.field7.method7().method2()) {
         if (!var1.func_148888_e()) {
            ClientEventBus.method29().method12(ServerTickEvent.class, ServerTickEvent::new);
         }
      }
   }

   @Inject(method = "handleChat", at = @At("RETURN"))
   private void lunar$handleChat(S02PacketChat var1, CallbackInfo var2) {
      ClientEventBus.method29().method12(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data3.class, () -> {
         Bridge2_42 var1x = ThreadModuleDump63.MC_VERSION >= 1 ? (Bridge2_42)var1.getChatComponent() : (Bridge2_42)var1.chatComponent$v1_7;
         Component var2x = AdventureTextBridge.asAdventure(var1x);
         int var3;
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            var3 = var1.getType().ordinal();
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            var3 = var1.type;
         } else {
            var3 = var1.isChat$v1_7 ? 0 : 1;
         }

         return new com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data3(var2x, var3);
      });
   }

   @Annotation2(max = 1)
   @WrapOperation(
      method = "handleStatistics",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/NetHandlerPlayClient;hasStatistics:Z", opcode = 180)
   )
   @Dynamic
   private boolean lunar$rewindForceAchievementsToDisplay(NetHandlerPlayClient var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? true : (Boolean)var2.call(new Object[]{var1});
   }

   @Inject(
      method = "handleAnimation",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntityByID(I)Lnet/minecraft/entity/Entity;", shift = Shift.AFTER),
      cancellable = true
   )
   private void lunar$cancelParticles(S0BPacketAnimation var1, CallbackInfo var2) {
      if ((var1.type == 4 || var1.type == 5) && ThreadModuleDump63.method4().method40().method22().method13()) {
         var2.cancel();
      }
   }
}
