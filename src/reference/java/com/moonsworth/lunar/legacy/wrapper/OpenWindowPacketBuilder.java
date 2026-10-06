package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.ContainerBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.IWorldNameable;

public class OpenWindowPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.OpenWindowPacketBridge {
   public OpenWindowPacketBuilder(List<PacketBuilder> list1) {
      super(S2DPacketOpenWindow.class, list1);
   }

   public PacketBridge method1(ContainerBridge bridge_121) {
      Container container2 = (Container)bridge_121;
      if (container2 instanceof ContainerChest containerchest3) {
         IInventory iinventory4 = containerchest3.getLowerChestInventory();
         if (Ref.MC_VERSION == 0) {
            return (PacketBridge)(
               new S2DPacketOpenWindow(container2.windowId, 0, iinventory4.getInventoryName$v1_7(), iinventory4.getSizeInventory(), iinventory4.isCustomInventoryName$v1_7())
            );
         } else {
            return iinventory4 instanceof IInteractionObject iinteractionobject5
               ? (PacketBridge)(new S2DPacketOpenWindow(container2.windowId, iinteractionobject5.getGuiID(), iinteractionobject5.getDisplayName(), iinventory4.getSizeInventory()))
               : (PacketBridge)(new S2DPacketOpenWindow(container2.windowId, "minecraft:container", ((IWorldNameable)iinventory4).getDisplayName(), iinventory4.getSizeInventory()));
         }
      } else {
         return null;
      }
   }
}
