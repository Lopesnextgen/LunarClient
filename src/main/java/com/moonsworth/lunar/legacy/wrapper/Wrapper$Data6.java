package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;

@Annotation2(1)
public class Wrapper$Data6 extends NetworkManager {
   Wrapper$Data6() {
      super(EnumPacketDirection.SERVERBOUND);
   }
}
