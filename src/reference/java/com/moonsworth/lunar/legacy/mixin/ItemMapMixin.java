package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemMapBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemMap.class)
public abstract class ItemMapMixin implements ItemMapBridge {
   public ItemMapMixin() {
   }

   @Shadow
   public abstract MapData getMapData(ItemStack stack1, World world2);

   public MapDataBridge bridge$getMapData(ItemStackBridge bridgeextension_41, Itemcounter6 itemcounter62) {
      return (MapDataBridge)this.getMapData((ItemStack)bridgeextension_41, (World)itemcounter62);
   }

   public Integer bridge$getMapId(ItemStackBridge bridgeextension_41, Itemcounter6 itemcounter62) {
      MapData mapdata3 = (MapData)this.bridge$getMapData(bridgeextension_41, itemcounter62);
      if (mapdata3 == null) {
         return null;
      }

      String text4 = mapdata3.mapName.substring(4);

      try {
         int number5 = Integer.parseInt(text4);
         return number5 == 0 ? null : number5;
      } catch (Exception exception6) {
         return null;
      }
   }
}
