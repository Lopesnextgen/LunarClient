package com.moonsworth.lunar.client.network.apollo;

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
import com.moonsworth.lunar.bridge.GuiContainerCreativeBridge;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.MovementStateBridge;
import com.moonsworth.lunar.client.event.screen.EventScreenUpdate;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;

public class PacketEnrichmentApolloHandler extends ApolloModuleHandler {
   private final Map<UUID, Double> field4 = new HashMap<>();
   private boolean field5;

   public PacketEnrichmentApolloHandler() {
      super("packet_enrichment", "PacketEnrichment");
      this.handle(EventPreAttackEntity.class, this::method3);
      this.handle(EventScreenUpdate.class, this::method4);
   }

   protected void onEnable() {
      this.field4.clear();
      this.field5 = false;
   }

   protected void onDisable() {
      this.field4.clear();
      this.field5 = false;
   }

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

   public void method3(HighlightImpl_3 highlightimpl_31) {
   }

   private void method3(EventPreAttackEntity highlightimpl5_21) {
      if (highlightimpl5_21.method1() instanceof Bridge5Extension_5 bridge5extension_52 && highlightimpl5_21.method2() instanceof Bridge6_10 bridge6_103) {
         Double value10 = this.field4.remove(bridge6_103.bridge$getUniqueID());
         if (value10 != null) {
            PacketInfo packetinfo5 = this.method12();
            PlayerInfo playerinfo6 = this.method9(bridge5extension_52);
            PlayerInfo playerinfo7 = this.method10(bridge6_103);
            if (playerinfo6 != null && playerinfo7 != null) {
               PlayerAttackMessage playerattackmessage8 = PlayerAttackMessage.newBuilder()
                  .setPacketInfo(packetinfo5)
                  .setTargetInfo(playerinfo7)
                  .setAttackerInfo(playerinfo6)
                  .setDistance(value10)
                  .build();
               this.sendPacket(playerattackmessage8);
            }
         }
      }
   }

   private void method4(EventScreenUpdate event) {
      GuiScreenBridge bridge5extension62 = event.method1();
      boolean flag3 = (bridge5extension62 instanceof GuiRecipeBookBridge || bridge5extension62 instanceof GuiContainerCreativeBridge) && bridge5extension62 instanceof GuiContainerBridge;
      if (flag3 != this.field5) {
         this.field5 = flag3;
         boolean flag4 = flag3
            ? (Boolean)this.getOptions().get(PacketEnrichmentModule.PLAYER_INVENTORY_OPEN_PACKET)
            : (Boolean)this.getOptions().get(PacketEnrichmentModule.PLAYER_INVENTORY_CLOSE_PACKET);
         if (flag4) {
            this.method5(flag3);
         }
      }
   }

   public void method5(boolean flag1) {
      if (Ref.method9() != null) {
         PlayerInfo playerinfo2 = this.method9(Ref.method7());
         if (playerinfo2 != null) {
            Object obj3;
            if (flag1) {
               obj3 = PlayerInventoryOpenMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(playerinfo2).build();
            } else {
               obj3 = PlayerInventoryCloseMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(playerinfo2).build();
            }

            this.sendPacket((Message)obj3);
         }
      }
   }

   public void method6(boolean flag1) {
      PlayerInfo playerinfo2 = this.method9(Ref.method7());
      if (playerinfo2 != null) {
         Object obj3;
         if (flag1) {
            obj3 = PlayerChatOpenMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(playerinfo2).build();
         } else {
            obj3 = PlayerChatCloseMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(playerinfo2).build();
         }

         this.sendPacket((Message)obj3);
      }
   }

   public void method7(boolean flag1) {
      PlayerInfo playerinfo2 = this.method9(Ref.method7());
      if (playerinfo2 != null) {
         PlayerUseItemMessage playeruseitemmessage3 = PlayerUseItemMessage.newBuilder().setPacketInfo(this.method12()).setPlayerInfo(playerinfo2).setMainHand(flag1).build();
         this.sendPacket(playeruseitemmessage3);
      }
   }

   public void method8(RayTraceResult raytraceresult1) {
      PlayerInfo playerinfo2 = this.method9(Ref.method7());
      if (playerinfo2 != null) {
         PlayerUseItemBucketMessage playeruseitembucketmessage3 = PlayerUseItemBucketMessage.newBuilder()
            .setPacketInfo(this.method12())
            .setPlayerInfo(playerinfo2)
            .setRayTraceResult(NetworkTypes.toProtobuf(raytraceresult1))
            .build();
         this.sendPacket(playeruseitembucketmessage3);
      }
   }

   private PlayerInfo method9(Bridge5Extension_5 bridge5extension_51) {
      PlayerLocation playerlocation2 = this.method11(bridge5extension_51);
      if (playerlocation2 == null) {
         return null;
      }

      ServerDataBridge bridge3_193 = Ref.method3().bridge$getCurrentServerData();
      if (bridge3_193 == null) {
         return null;
      }

      MovementStateBridge bridge_214 = bridge5extension_51.bridge$getMovementInput();
      return PlayerInfo.newBuilder()
         .setPlayerUuid(NetworkTypes.toProtobuf(bridge5extension_51.bridge$getUniqueID()))
         .setLocation(playerlocation2)
         .setSneaking(bridge5extension_51.bridge$isSneaking())
         .setSprinting(bridge5extension_51.bridge$isSprinting())
         .setJumping(bridge_214.bridge$isJumping())
         .setForwardSpeed(bridge_214.bridge$getForwardSpeed())
         .setStrafeSpeed(bridge_214.bridge$getStrafeSpeed())
         .build();
   }

   private PlayerInfo method10(Bridge6_10 bridge6_101) {
      PlayerLocation playerlocation2 = this.method11(bridge6_101);
      if (playerlocation2 == null) {
         return null;
      }

      ServerDataBridge bridge3_193 = Ref.method3().bridge$getCurrentServerData();
      return bridge3_193 == null
         ? null
         : PlayerInfo.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(bridge6_101.bridge$getUniqueID()))
            .setLocation(playerlocation2)
            .setSneaking(bridge6_101.bridge$isSneaking())
            .setSprinting(bridge6_101.bridge$isSprinting())
            .build();
   }

   private PlayerLocation method11(Bridge6_10 bridge6_101) {
      return bridge6_101 == null
         ? null
         : PlayerLocation.newBuilder()
            .setLocation(
               Location.newBuilder()
                  .setWorld(Ref.method4().getWorld())
                  .setX(bridge6_101.bridge$getPosX())
                  .setY(bridge6_101.bridge$getPosY())
                  .setZ(bridge6_101.bridge$getPosZ())
                  .build()
            )
            .setYaw((float)bridge6_101.bridge$getRotationYaw())
            .setPitch((float)bridge6_101.bridge$getRotationPitch())
            .build();
   }

   public PacketInfo method12() {
      return this.method13(System.currentTimeMillis());
   }

   public PacketInfo method13(long value) {
      return PacketInfo.newBuilder().setInstantiationTime(NetworkTypes.toProtobuf(value)).build();
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
