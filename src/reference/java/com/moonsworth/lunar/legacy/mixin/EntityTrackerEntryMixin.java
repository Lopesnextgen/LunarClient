package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge7_7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;
import net.minecraft.network.Packet;
import net.minecraft.network.Packet_v1_7;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin implements Bridge7_7 {
   @Shadow
   public int encodedPosX;
   @Shadow
   public int encodedPosZ;
   @Unique
   private Consumer<Bridge3_21> lunar$packetsConsumer;
   @Unique
   private final NetHandlerPlayServer lunar$dummyHandler = ThreadModuleDump63.MC_VERSION == 0
      ? new NetHandlerPlayServer(null, (NetworkManager_v1_7)null, null)
      : (
         ThreadModuleDump63.MC_VERSION == 1
            ? new NetHandlerPlayServer(null, (NetworkManager)null, null)
            : new NetHandlerPlayServer(null, (NetworkManager_v1_12)null, null)
      );

   @Override
   public void bridge$setPacketsConsumer(Consumer<Bridge3_21> var1) {
      this.lunar$packetsConsumer = var1;
   }

   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayerMP;connection:Lnet/minecraft/network/NetHandlerPlayServer;")
   )
   private NetHandlerPlayServer lunar$createDummyHandler(EntityPlayerMP var1, Operation<NetHandlerPlayServer> var2) {
      return var1 == null && this.lunar$packetsConsumer != null ? this.lunar$dummyHandler : (NetHandlerPlayServer)var2.call(new Object[]{var1});
   }

   @Unique
   private Object lunar$fixPlayerSpawn(EntityTrackerEntry var1, Operation<?> var2, EntityPlayerMP var3) {
      if (var3 == null && this.lunar$packetsConsumer != null) {
         if (var1.trackedEntity instanceof EntityPlayerSP) {
            return null;
         }

         if (var1.trackedEntity instanceof EntityOtherPlayerMP) {
            return Bridge.method59().method18().method1((Bridge6_10)var1.trackedEntity);
         }
      }

      return var2.call(new Object[]{var1});
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTrackerEntry;func_151260_c$v1_7()Lnet/minecraft/network/Packet_v1_7;")
   )
   private Packet_v1_7 lunar$fixPlayerSpawn$v1_7(EntityTrackerEntry var1, Operation<Packet_v1_7> var2, EntityPlayerMP var3) {
      return (Packet_v1_7)this.lunar$fixPlayerSpawn(var1, var2, var3);
   }

   @Annotation2(1)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTrackerEntry;createSpawnPacket$v1_8()Lnet/minecraft/network/Packet_v1_8;")
   )
   private Packet<?> lunar$fixPlayerSpawn$v1_8(EntityTrackerEntry var1, Operation<Packet<?>> var2, EntityPlayerMP var3) {
      return (Packet<?>)this.lunar$fixPlayerSpawn(var1, var2, var3);
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTrackerEntry;createSpawnPacket$v1_12()Lnet/minecraft/network/Packet_v1_8;")
   )
   private Packet<?> lunar$fixPlayerSpawn$v1_12(EntityTrackerEntry var1, Operation<Packet<?>> var2, EntityPlayerMP var3) {
      return (Packet<?>)this.lunar$fixPlayerSpawn(var1, var2, var3);
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetHandlerPlayServer;sendPacket$v1_7(Lnet/minecraft/network/Packet_v1_7;)V")
   )
   private void lunar$processPackets$v1_7(NetHandlerPlayServer var1, Packet_v1_7 var2, Operation<Void> var3, EntityPlayerMP var4) {
      this.lunar$processPackets(var1, (Bridge3_21)var2, var3, var4);
   }

   @Annotation2(1)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetHandlerPlayServer;sendPacket$v1_8(Lnet/minecraft/network/Packet_v1_8;)V")
   )
   private void lunar$processPackets$v1_8(NetHandlerPlayServer var1, Packet<?> var2, Operation<Void> var3, EntityPlayerMP var4) {
      this.lunar$processPackets(var1, (Bridge3_21)var2, var3, var4);
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetHandlerPlayServer;sendPacket$v1_12(Lnet/minecraft/network/Packet_v1_8;)V")
   )
   private void lunar$processPackets$v1_12(NetHandlerPlayServer var1, Packet<?> var2, Operation<Void> var3, EntityPlayerMP var4) {
      this.lunar$processPackets(var1, (Bridge3_21)var2, var3, var4);
   }

   @Unique
   private void lunar$processPackets(NetHandlerPlayServer var1, Bridge3_21 var2, Operation<Void> var3, EntityPlayerMP var4) {
      if (var4 == null && this.lunar$packetsConsumer != null) {
         this.lunar$packetsConsumer.accept(var2);
      } else {
         var3.call(new Object[]{var1, var2});
      }
   }

   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTrackerEntry;isPlayerWatchingThisChunk(Lnet/minecraft/entity/player/EntityPlayerMP;)Z")
   )
   private boolean lunar$forceProcessing$isPlayerWatchingThisChunk(EntityTrackerEntry var1, EntityPlayerMP var2, Operation<Boolean> var3) {
      return var2 == null && this.lunar$packetsConsumer != null ? true : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @Annotation2(max = 0)
   @WrapOperation(method = "updatePlayerEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayerMP;posX:D", ordinal = 0))
   private double lunar$forceProcessing$posX$v1_8(EntityPlayerMP var1, Operation<Double> var2) {
      return var1 == null && this.lunar$packetsConsumer != null ? this.encodedPosX / 32.0 : (Double)var2.call(new Object[]{var1});
   }

   @Annotation2(max = 0)
   @WrapOperation(method = "updatePlayerEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayerMP;posZ:D", ordinal = 0))
   private double lunar$forceProcessing$posZ$v1_8(EntityPlayerMP var1, Operation<Double> var2) {
      return var1 == null && this.lunar$packetsConsumer != null ? this.encodedPosZ / 32.0 : (Double)var2.call(new Object[]{var1});
   }

   @Annotation2(1)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTrackerEntry;func_180233_c$v1_8(Lnet/minecraft/entity/player/EntityPlayerMP;)Z")
   )
   private boolean lunar$forceProcessing$func_180233_c$v1_8(EntityTrackerEntry var1, EntityPlayerMP var2, Operation<Boolean> var3) {
      return var2 == null && this.lunar$packetsConsumer != null ? true : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTrackerEntry;isVisibleTo$v1_12(Lnet/minecraft/entity/player/EntityPlayerMP;)Z")
   )
   private boolean lunar$forceProcessing$isVisibleTo$v1_12(EntityTrackerEntry var1, EntityPlayerMP var2, Operation<Boolean> var3) {
      return var2 == null && this.lunar$packetsConsumer != null ? true : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @WrapOperation(method = "updatePlayerEntity", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
   private boolean lunar$forceProcessing$add(Set<EntityPlayerMP> var1, Object var2, Operation<Boolean> var3) {
      return var2 == null && this.lunar$packetsConsumer != null ? true : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @Annotation2(min = 5)
   @WrapWithCondition(
      method = "updatePlayerEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayerMP;addEntity$v1_12(Lnet/minecraft/entity/Entity;)V")
   )
   private boolean lunar$forceProcessing$addEntity(EntityPlayerMP var1, Entity var2) {
      return var1 != null || this.lunar$packetsConsumer == null;
   }

   @Annotation2(max = 0)
   @Inject(method = "func_151259_a$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$sendPacketToTrackedPlayers$v1_7(Packet_v1_7 var1, CallbackInfo var2) {
      if (this.lunar$packetsConsumer != null) {
         this.lunar$packetsConsumer.accept((Bridge3_21)var1);
         var2.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = {"sendPacketToTrackedPlayers$v1_8", "sendPacketToTrackedPlayers$v1_12"}, at = @At("HEAD"), cancellable = true)
   private void lunar$sendPacketToTrackedPlayers$v1_8(Packet<?> var1, CallbackInfo var2) {
      if (this.lunar$packetsConsumer != null) {
         this.lunar$packetsConsumer.accept((Bridge3_21)var1);
         var2.cancel();
      }
   }

   @WrapOperation(
      method = {"updatePlayerList$v1_7", "updatePlayerList$v1_8"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/storage/MapData;updateVisiblePlayers(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)V"
      )
   )
   private void lunar$doNotUpdateMaps(MapData var1, EntityPlayer var2, ItemStack var3, Operation<Void> var4) {
      if (var2 != null || this.lunar$packetsConsumer == null) {
         var4.call(new Object[]{var1, var2, var3});
      }
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "updatePlayerList$v1_7",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/item/ItemMap;createMapDataPacket$v1_7(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/network/Packet_v1_7;"
      )
   )
   private Packet_v1_7 lunar$doNotSendMaps$v1_7(ItemMap var1, ItemStack var2, World var3, EntityPlayer var4, Operation<Packet_v1_7> var5) {
      return this.lunar$packetsConsumer != null ? null : (Packet_v1_7)var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(1)
   @WrapOperation(
      method = "updatePlayerList$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/item/ItemMap;createMapDataPacket$v1_8(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/network/Packet_v1_8;"
      )
   )
   @Dynamic
   private Packet<?> lunar$doNotSendMaps$v1_8(ItemMap var1, ItemStack var2, World var3, EntityPlayer var4, Operation<Packet<?>> var5) {
      return this.lunar$packetsConsumer != null ? null : (Packet)var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "updatePlayerList$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/item/ItemMap;createMapDataPacket$v1_12(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/network/Packet_v1_8;"
      )
   )
   private Packet<?> lunar$doNotSendMaps$v1_12(ItemMap var1, ItemStack var2, World var3, EntityPlayer var4, Operation<Packet<?>> var5) {
      return this.lunar$packetsConsumer != null ? null : (Packet)var5.call(new Object[]{var1, var2, var3, var4});
   }
}
