package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.ContainerMarker;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.IWorldNameable;

public class OpenWindowPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper17 {
   public OpenWindowPacketFactory(List<MixinHelper_19> var1) {
      super(S2DPacketOpenWindow.class, var1);
   }

   public Bridge3_21 method1(ContainerMarker var1) {
      Container var2 = (Container)var1;
      if (var2 instanceof ContainerChest var3) {
         IInventory var4 = var3.getLowerChestInventory();
         if (ThreadModuleDump63.MC_VERSION == 0) {
            return (Bridge3_21)(
               new S2DPacketOpenWindow(var2.windowId, 0, var4.getInventoryName$v1_7(), var4.getSizeInventory(), var4.isCustomInventoryName$v1_7())
            );
         } else {
            return var4 instanceof IInteractionObject var5
               ? (Bridge3_21)(new S2DPacketOpenWindow(var2.windowId, var5.getGuiID(), var5.getDisplayName(), var4.getSizeInventory()))
               : (Bridge3_21)(new S2DPacketOpenWindow(var2.windowId, "minecraft:container", ((IWorldNameable)var4).getDisplayName(), var4.getSizeInventory()));
         }
      } else {
         return null;
      }
   }
}
