package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager_v1_12;

@Annotation2(min = 5)
public class Wrapper$Data5 extends NetworkManager_v1_12 {
   Wrapper$Data5() {
      super(EnumPacketDirection.SERVERBOUND);
   }
}
