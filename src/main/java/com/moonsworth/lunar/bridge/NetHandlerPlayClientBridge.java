package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import java.util.UUID;

public interface NetHandlerPlayClientBridge extends INetHandlerBridge {
   void bridge$addToSendQueue(PacketBridge bridge3_211);

   String bridge$getRegisterPacketName();

   String bridge$getLCChannelName();

   void bridge$quit();

   void bridge$transferQuit();

   NetworkManagerBridge bridge$getNetworkManager();

   List<PlayerInfoBridge> bridge$getPlayerInfoMap();

   List<PlayerInfoBridge> bridge$getSortedPlayerInfoMap();

   PlayerInfoBridge bridge$getPlayerInfo(UUID uuid1);

   @VersionGate(min = 15)
   byte[] bridge$serializeMessageSignatureCache();

   @VersionGate(min = 15)
   void bridge$deserializeMessageSignatureCache(byte[] items1);

   String bridge$getServerBrand();

   @VersionGate(min = 35)
   default void bridge$setClientLoaded(boolean flag) {
   }
}
