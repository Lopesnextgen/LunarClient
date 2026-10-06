package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.net.SocketAddress;
import org.jetbrains.annotations.Nullable;

public interface NetworkManagerBridge {
   INetHandlerBridge bridge$getClientPacketListener();

   @VersionGate(min = 22)
   default ProtocolInfoBridge bridge$getClientboundProtocolInfo() {
      return null;
   }

   @VersionGate(min = 22)
   default ProtocolInfoBridge bridge$getServerboundProtocolInfo() {
      return null;
   }

   @Nullable
   String bridge$getEnteredHostName();

   void bridge$setEnteredHostName(String text1);

   SocketAddress bridge$getRemoteAddress();

   void bridge$channelInactive();
}
