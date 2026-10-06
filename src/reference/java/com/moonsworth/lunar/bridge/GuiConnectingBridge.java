package com.moonsworth.lunar.bridge;

import org.jetbrains.annotations.Nullable;

public interface GuiConnectingBridge extends GuiScreenBridge {
   @Nullable
   NetHandlerPlayClientBridge bridge$getClientPacketListener();
}
