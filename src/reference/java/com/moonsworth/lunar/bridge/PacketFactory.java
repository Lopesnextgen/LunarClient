package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface PacketFactory {
   PacketBridge method1(PacketDirectionBridge bridgetype_41, ConnectionProtocol bridgetype2_22, int number3, Bridge7_9 bridge7_94);

   int method2(PacketDirectionBridge bridgetype_41, ConnectionProtocol bridgetype2_22, PacketBridge bridge3_213);

   @VersionGate(min = 6)
   default void method3(PacketDirectionBridge bridgetype_41, ConnectionProtocol bridgetype2_22, PacketBridge bridge3_213, Consumer<PacketBridge> consumer4) {
   }

   default ResourceLocationBridge method4(PacketDirectionBridge bridgetype_41, int value) {
      return null;
   }

   WorldJoinPacketBuilder method5();

   ServerDifficultyPacketBridge method6();

   SpawnPositionPacketBuilder method7();

   WorldBorderPacketBridge method8();

   TimeUpdatePacketBridge method9();

   ChunkDataPacketBridge method10();

   BlockBreakAnimationPacketBridge method11();

   EffectPacketBridge method12();

   SpawnMobPacketBridge method13();

   PlayerAbilitiesPacketBridge method14();

   HeldItemChangePacketBridge method15();

   SetExperiencePacketBridge method16();

   PlayerListItemPacketBridge method17();

   EntityPlayerPacketBuilder method18();

   EntityHeadLookPacketBridge method19();

   WindowItemsPacketBridge method20();

   OpenWindowPacketBridge method21();

   BossInfoPacketBridge method22();

   NoOpBatchPacketBridge method23();

   NoOpPacketListBridge method24();

   NoOpPacketBridge method25();

   NoOpWorldPacketBuilder method26();

   PlayerListHeaderFooterPacketBridge method27();

   EntityMovePacketBridge method28();

   ChunkUnloadPacketBuilder method29();

   NoOpLegacyPacketBridge method30();

   SpawnObjectPacketBridge method31();

   SpawnPaintingPacketBridge method32();

   SpawnExperienceOrbPacketBridge method33();

   NoOpNetHandlerPacketBridge method34();

   void method35(Consumer<Object[]> consumer1, PacketBridge bridge3_212);

   PacketBridge method36(Supplier<Object[]> supplier1, PacketBridge bridge3_212);
}
