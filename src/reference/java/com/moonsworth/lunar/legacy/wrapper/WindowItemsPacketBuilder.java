package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ContainerBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.server.S30PacketWindowItems;

public class WindowItemsPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.WindowItemsPacketBridge {
   public WindowItemsPacketBuilder(List<PacketBuilder> list) {
      super(S30PacketWindowItems.class, list);
   }

   public PacketBridge method1(Bridge5Extension_5 bridge5extension_51) {
      EntityPlayerSP player2 = (EntityPlayerSP)bridge5extension_51;
      return this.method2((ContainerBridge)player2.inventoryContainer);
   }

   public PacketBridge method2(ContainerBridge bridge_121) {
      Container container2 = (Container)bridge_121;
      if (Ref.MC_VERSION <= 0) {
         return (PacketBridge)(new S30PacketWindowItems(container2.windowId, container2.getInventory()));
      } else {
         return Ref.MC_VERSION == 1
            ? (PacketBridge)(new S30PacketWindowItems(container2.windowId, container2.getInventory()))
            : (PacketBridge)(new S30PacketWindowItems(container2.windowId, container2.getInventory()));
      }
   }
}
