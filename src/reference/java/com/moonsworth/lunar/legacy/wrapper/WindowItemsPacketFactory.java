package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ContainerMarker;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.server.S30PacketWindowItems;

public class WindowItemsPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper25 {
   public WindowItemsPacketFactory(List<MixinHelper_19> var1) {
      super(S30PacketWindowItems.class, var1);
   }

   public Bridge3_21 method1(Bridge5Extension_5 var1) {
      EntityPlayerSP var2 = (EntityPlayerSP)var1;
      return this.method2((ContainerMarker)var2.inventoryContainer);
   }

   public Bridge3_21 method2(ContainerMarker var1) {
      Container var2 = (Container)var1;
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return (Bridge3_21)(new S30PacketWindowItems(var2.windowId, var2.getInventory()));
      } else {
         return ThreadModuleDump63.MC_VERSION == 1
            ? (Bridge3_21)(new S30PacketWindowItems(var2.windowId, var2.getInventory()))
            : (Bridge3_21)(new S30PacketWindowItems(var2.windowId, var2.getInventory()));
      }
   }
}
