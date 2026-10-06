package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;

@VersionGate(1)
public class DummyNetworkManager extends NetworkManager {
   DummyNetworkManager() {
      super(EnumPacketDirection.SERVERBOUND);
   }
}
