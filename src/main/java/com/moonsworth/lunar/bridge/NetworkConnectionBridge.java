package com.moonsworth.lunar.bridge;

import java.net.SocketAddress;
import org.jetbrains.annotations.Nullable;

public interface NetworkConnectionBridge {
   Bridge_26 bridge$getClientPacketListener();

   @com.moonsworth.lunar.ichor.Annotation2(min = 22)
   default Bridge4_22 bridge$getClientboundProtocolInfo() {
      return null;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 22)
   default Bridge4_22 bridge$getServerboundProtocolInfo() {
      return null;
   }

   @Nullable
   String bridge$getEnteredHostName();

   void bridge$setEnteredHostName(String var1);

   SocketAddress bridge$getRemoteAddress();

   void bridge$channelInactive();
}
