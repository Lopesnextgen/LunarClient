package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.v1.Location;
import com.lunarclient.apollo.common.v1.PlayerLocation;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.lunarclient.apollo.module.packetenrichment.raytrace.RayTraceResult;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.packetenrichment.v1.PacketInfo;
import com.lunarclient.apollo.packetenrichment.v1.PlayerAttackMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatCloseMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatOpenMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerInfo;
import com.lunarclient.apollo.packetenrichment.v1.PlayerInventoryCloseMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerInventoryOpenMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerUseItemBucketMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerUseItemMessage;
import com.moonsworth.lunar.bridge.Bridge2_27;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge4_15;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.MovementStateBridge;
import com.moonsworth.lunar.client.event.screen.ScreenUpdateEvent;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.combat.PreAttackEntityEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator31 extends ApolloModuleHandler {
   private final Map<UUID, Double> field4 = new HashMap<>();
   private boolean field5;

   public Highlight3Iterator31() {
      super("packet_enrichment", "PacketEnrichment");
      this.handle(PreAttackEntityEvent.class, this::method3);
      this.handle(ScreenUpdateEvent.class, this::method4);
   }

   @Override
   protected void onEnable() {
      this.field4.clear();
      this.field5 = false;
   }

   @Override
   protected void onDisable() {
      this.field4.clear();
      this.field5 = false;
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(
         PacketEnrichmentModule.PLAYER_ATTACK_PACKET,
         PacketEnrichmentModule.PLAYER_CHAT_OPEN_PACKET,
         PacketEnrichmentModule.PLAYER_CHAT_CLOSE_PACKET,
         PacketEnrichmentModule.PLAYER_INVENTORY_OPEN_PACKET,
         PacketEnrichmentModule.PLAYER_INVENTORY_CLOSE_PACKET,
         PacketEnrichmentModule.PLAYER_USE_ITEM_PACKET,
         PacketEnrichmentModule.PLAYER_USE_ITEM_BUCKET_PACKET
      );
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
   }

   private void method3(PreAttackEntityEvent var1) {
      if (var1.method1() instanceof Bridge5Extension_5 var2 && var1.method2() instanceof Bridge6_10 var3) {
         Double var10 = this.field4.remove(var3.bridge$getUniqueID());
         if (var10 != null) {
            PacketInfo var5 = this.method12();
            PlayerInfo var6 = this.method9(var2);
            PlayerInfo var7 = this.method10(var3);
            if (var6 != null && var7 != null) {
               PlayerAttackMessage var8 = PlayerAttackMessage.newBuilder()
                  .setPacketInfo(var5)
                  .setTargetInfo(var7)
                  .setAttackerInfo(var6)
                  .setDistance(var10)
                  .build();
               this.sendPacket(var8);
            }
         }
      }
   }

   private void method4(ScreenUpdateEvent var1) {
      Bridge5Extension6 var2 = var1.method1();
      boolean var3 = (var2 instanceof Bridge4_15 || var2 instanceof Bridge2_27) && var2 instanceof Bridge5Extension_3;
      if (var3 != this.field5) {
         this.field5 = var3;
         boolean var4 = var3
            ? (Boolean)this.getOptions().get(PacketEnrichmentModule.PLAYER_INVENTORY_OPEN_PACKET)
            : (Boolean)this.getOptions().get(PacketEnrichmentModule.PLAYER_INVENTORY_CLOSE_PACKET);
         if (var4) {
            this.method5(var3);
         }
      }
   }

   public void method5(boolean var1) {
      if (ThreadModuleDump63.method9() != null) {
         PlayerInfo var2 = this.method9(ThreadModuleDump63.method7());
         if (var2 != null) {
            Object var3;
            if (var1) {
               var3 = PlayerInventoryOpenMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(var2).build();
            } else {
               var3 = PlayerInventoryCloseMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(var2).build();
            }

            this.sendPacket((Message)var3);
         }
      }
   }

   public void method6(boolean var1) {
      PlayerInfo var2 = this.method9(ThreadModuleDump63.method7());
      if (var2 != null) {
         Object var3;
         if (var1) {
            var3 = PlayerChatOpenMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(var2).build();
         } else {
            var3 = PlayerChatCloseMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(var2).build();
         }

         this.sendPacket((Message)var3);
      }
   }

   public void method7(boolean var1) {
      PlayerInfo var2 = this.method9(ThreadModuleDump63.method7());
      if (var2 != null) {
         PlayerUseItemMessage var3 = PlayerUseItemMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(var2).setMainHand(var1).build();
         this.sendPacket(var3);
      }
   }

   public void method8(RayTraceResult var1) {
      PlayerInfo var2 = this.method9(ThreadModuleDump63.method7());
      if (var2 != null) {
         PlayerUseItemBucketMessage var3 = PlayerUseItemBucketMessage.newBuilder()
            .setPacketInfo(this.method12())
            .setPlayerInfo(var2)
            .setRayTraceResult(NetworkTypes.toProtobuf(var1))
            .build();
         this.sendPacket(var3);
      }
   }

   private PlayerInfo method9(Bridge5Extension_5 var1) {
      PlayerLocation var2 = this.method11(var1);
      if (var2 == null) {
         return null;
      }

      Bridge3_19 var3 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
      if (var3 == null) {
         return null;
      }

      MovementStateBridge var4 = var1.bridge$getMovementInput();
      return PlayerInfo.newBuilder()
         .setPlayerUuid(NetworkTypes.toProtobuf(var1.bridge$getUniqueID()))
         .setLocation(var2)
         .setSneaking(var1.bridge$isSneaking())
         .setSprinting(var1.bridge$isSprinting())
         .setJumping(var4.bridge$isJumping())
         .setForwardSpeed(var4.bridge$getForwardSpeed())
         .setStrafeSpeed(var4.bridge$getStrafeSpeed())
         .build();
   }

   private PlayerInfo method10(Bridge6_10 var1) {
      PlayerLocation var2 = this.method11(var1);
      if (var2 == null) {
         return null;
      }

      Bridge3_19 var3 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
      return var3 == null
         ? null
         : PlayerInfo.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(var1.bridge$getUniqueID()))
            .setLocation(var2)
            .setSneaking(var1.bridge$isSneaking())
            .setSprinting(var1.bridge$isSprinting())
            .build();
   }

   private PlayerLocation method11(Bridge6_10 var1) {
      return var1 == null
         ? null
         : PlayerLocation.newBuilder()
            .setLocation(
               Location.newBuilder()
                  .setWorld(ThreadModuleDump63.method4().getWorld())
                  .setX(var1.bridge$getPosX())
                  .setY(var1.bridge$getPosY())
                  .setZ(var1.bridge$getPosZ())
                  .build()
            )
            .setYaw((float)var1.bridge$getRotationYaw())
            .setPitch((float)var1.bridge$getRotationPitch())
            .build();
   }

   public PacketInfo method12() {
      return this.method13(System.currentTimeMillis());
   }

   public PacketInfo method13(long var1) {
      return PacketInfo.newBuilder().setInstantiationTime(NetworkTypes.toProtobuf(var1)).build();
   }

   @Generated
   public Map<UUID, Double> method14() {
      return this.field4;
   }

   @Generated
   public boolean method15() {
      return this.field5;
   }
}
