package com.moonsworth.lunar.bridge;

import java.util.List;
import java.util.UUID;

public interface ClientPacketListenerBridge extends Bridge_26 {
   void bridge$addToSendQueue(Bridge3_21 var1);

   String bridge$getRegisterPacketName();

   String bridge$getLCChannelName();

   void bridge$quit();

   void bridge$transferQuit();

   NetworkConnectionBridge bridge$getNetworkManager();

   List<Bridge2_33> bridge$getPlayerInfoMap();

   List<Bridge2_33> bridge$getSortedPlayerInfoMap();

   Bridge2_33 bridge$getPlayerInfo(UUID var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 15)
   byte[] bridge$serializeMessageSignatureCache();

   @com.moonsworth.lunar.ichor.Annotation2(min = 15)
   void bridge$deserializeMessageSignatureCache(byte[] var1);

   String bridge$getServerBrand();

   @com.moonsworth.lunar.ichor.Annotation2(min = 35)
   default void bridge$setClientLoaded(boolean var1) {
   }
}
